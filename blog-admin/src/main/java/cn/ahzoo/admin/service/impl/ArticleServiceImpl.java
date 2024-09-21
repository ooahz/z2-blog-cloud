package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.constant.Constant;
import cn.ahzoo.admin.enums.ResultCode;
import cn.ahzoo.admin.exception.BizException;
import cn.ahzoo.admin.feign.ESFeignClientInterface;
import cn.ahzoo.admin.mapper.ArticleMapper;
import cn.ahzoo.admin.model.dto.ArticleContentDTO;
import cn.ahzoo.admin.model.dto.ColumnDTO;
import cn.ahzoo.admin.model.entity.Article;
import cn.ahzoo.admin.model.mapstruct.ArticleMapping;
import cn.ahzoo.admin.model.record.ArticleESRecord;
import cn.ahzoo.admin.model.vo.ArticleItemVO;
import cn.ahzoo.admin.model.vo.ArticleVO;
import cn.ahzoo.admin.service.ArticleColumnService;
import cn.ahzoo.admin.service.ArticleService;
import cn.ahzoo.admin.utils.ArticleUtil;
import cn.ahzoo.common.constant.RedisConstant;
import cn.ahzoo.utils.model.Result;
import cn.ahzoo.utils.model.ResultList;
import cn.ahzoo.utils.model.ResultPage;
import cn.ahzoo.utils.utils.GenerateUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    @Autowired
    private ESFeignClientInterface esFeignClientInterface;

    @Autowired
    private ArticleColumnService articleColumnService;

    @Value("${config.seed}")
    private long seed;

    @Value("${config.elasticsearch}")
    private boolean enableElasticsearch;

    @Override
    public Result<ArticleVO> getArticleDetail(Long id) {
        ArticleVO articleVO = baseMapper.selectArticleById(id);
        buildArticleContent(articleVO);
        buildArticleColumn(articleVO);
        return Result.success(articleVO);
    }

    @Override
    public ResultList<List<ArticleItemVO>> listArticle(int pagination, String status, String type, String columnId) {
        int paginationIndex = pagination - 1;
        List<ArticleItemVO> articleItemVOS = baseMapper.listArticleItem(
                paginationIndex * Constant.PAGE_SIZE,
                Constant.PAGE_SIZE, status, type, columnId);
        long countArticle = baseMapper.countArticle();
        return ResultList.success(new ResultPage(countArticle, articleItemVOS.size(), Constant.PAGE_SIZE, pagination),
                articleItemVOS);
    }

    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(value = RedisConstant.SYSTEM_STATISTICS_KEY, allEntries = true)
    @Override
    public Result<?> saveArticle(ArticleVO articleVO) {
        Boolean isDraft = articleVO.getIsDraft();
        if (!isDraft) {
            generatePath(articleVO);
        }
        duplicateValidate(articleVO);
        setDefaultParams(articleVO);
        Article article = ArticleMapping.INSTANCE.vo2Article(articleVO);
        if (isDraft) {
            article.setStatus(Constant.ARTICLE_TYPE_DRAFT);
        }
        save(article);
        articleVO.setId(article.getId());
        saveArticleContent(articleVO);
        articleColumnService.saveArticleColumn(articleVO);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveArticleContent(ArticleVO articleVO) {
        String formatHtml = ArticleUtil.formatHtml(articleVO.getHtmlContent());
        articleVO.setHtmlContent(formatHtml);
        baseMapper.saveArticleContent(articleVO);
        if (enableElasticsearch) {
            ArticleESRecord articleESRecord = ArticleMapping.INSTANCE.articleVO2ArticleESRecord(articleVO);
            esFeignClientInterface.save(articleESRecord);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<?> updateArticle(ArticleVO articleVO) {
        updateParamsValidate(articleVO);
        duplicateValidate(articleVO);
        Article article = ArticleMapping.INSTANCE.vo2Article(articleVO);
        updateById(article);
        articleVO.setId(article.getId());
        updateArticleContent(articleVO);
        articleColumnService.updateArticleColumn(articleVO);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateArticleContent(ArticleVO articleVO) {
        String formatHtml = ArticleUtil.formatHtml(articleVO.getHtmlContent());
        articleVO.setHtmlContent(formatHtml);
        baseMapper.updateArticleContent(articleVO);
        if (enableElasticsearch) {
            ArticleESRecord articleESRecord = ArticleMapping.INSTANCE.articleVO2ArticleESRecord(articleVO);
            esFeignClientInterface.save(articleESRecord);
        }
    }

    @CacheEvict(value = RedisConstant.SYSTEM_STATISTICS_KEY, allEntries = true)
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<?> removeArticleById(Long id) {
        Article article = getById(id);
        article.deprecatedArticle();
        updateById(article);
        articleColumnService.removeArticleColumnByArticleId(id);
        return Result.success();
    }

    @Override
    public Result<?> updateArticlePart(ArticleVO articleVO) {
        Article beforeArticle = getById(articleVO.getId());
        beforeArticle.setStatus(articleVO.getStatus());
        updateById(beforeArticle);
        return Result.success();
    }

    private void updateParamsValidate(ArticleVO articleVO) {
        if (ObjectUtils.isEmpty(articleVO.getId())) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "文章ID为空");
        }
    }

    private void duplicateValidate(ArticleVO articleVO) {
        Long count = baseMapper.validateDuplicateCount(articleVO.getTitle(), articleVO.getPath(), articleVO.getId());
        if (count > 0) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "文章标题或者文章路径已存在");
        }
    }

    private void generatePath(ArticleVO articleVO) {
        if (StringUtils.isNotEmpty(articleVO.getPath())) {
            return;
        }
        String hexNameGenerate = GenerateUtil.hexNameGenerate(seed);
        articleVO.setPath(hexNameGenerate);
    }

    private void setDefaultParams(ArticleVO articleVO) {
        articleVO.setStatus(Constant.DEFAULT_STATUS);
        if (ObjectUtils.isEmpty(articleVO.getWeight())) {
            articleVO.setWeight(Constant.DEFAULT_INT_PARAM);
        }
        if (StringUtils.isEmpty(articleVO.getPath())) {
            articleVO.setPath(articleVO.getTitle());
        }
    }

    private void buildArticleContent(ArticleVO articleVO) {
        buildArticleContentByDB(articleVO);
    }

    private void buildArticleContentByDB(ArticleVO articleVO) {
        ArticleContentDTO articleContentById = baseMapper.selectArticleContentById(articleVO.getId());
        articleVO.setContent(articleContentById.getContent());
    }

    private void buildArticleColumn(ArticleVO articleVO) {
        List<ColumnDTO> columnDTOList = articleColumnService.listColumnByArticleId(articleVO.getId());
        List<Long> columnIds = columnDTOList.stream().map(ColumnDTO::getId).toList();
        articleVO.setColumnIds(columnIds);
    }
}




