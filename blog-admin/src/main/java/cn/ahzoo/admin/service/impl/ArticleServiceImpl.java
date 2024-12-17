package cn.ahzoo.admin.service.impl;

import cn.ahzoo.admin.constant.Constant;
import cn.ahzoo.admin.enums.ResultCode;
import cn.ahzoo.admin.exception.BizException;
import cn.ahzoo.admin.feign.ESFeignClientInterface;
import cn.ahzoo.admin.mapper.ArticleMapper;
import cn.ahzoo.admin.model.dto.ArticleContentDTO;
import cn.ahzoo.admin.model.dto.ArticleDTO;
import cn.ahzoo.admin.model.dto.BriefColumnDTO;
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
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article>
        implements ArticleService {

    private final ESFeignClientInterface esFeignClientInterface;
    private final ArticleColumnService articleColumnService;

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
    public Result<?> saveArticle(ArticleDTO articleDTO) {
        generatePath(articleDTO);
        duplicateValidate(articleDTO);
        setDefaultParams(articleDTO);
        Article article = ArticleMapping.INSTANCE.dto2Article(articleDTO);
        save(article);
        articleDTO.setId(article.getId());
        String formatHtml = ArticleUtil.formatHtml(articleDTO.getHtmlContent());
        articleDTO.setHtmlContent(formatHtml);
        baseMapper.saveArticleContent(articleDTO);
        if (enableElasticsearch) {
            ArticleESRecord articleESRecord = ArticleMapping.INSTANCE.dto2ArticleESRecord(articleDTO);
            esFeignClientInterface.save(articleESRecord);
        }
        articleColumnService.saveArticleColumn(articleDTO);
        return Result.success();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Result<?> updateArticle(ArticleDTO articleDTO) {
        updateParamsValidate(articleDTO);
        duplicateValidate(articleDTO);
        Article article = ArticleMapping.INSTANCE.dto2Article(articleDTO);
        updateById(article);
        articleDTO.setId(article.getId());
        String formatHtml = ArticleUtil.formatHtml(articleDTO.getHtmlContent());
        articleDTO.setHtmlContent(formatHtml);
        baseMapper.updateArticleContent(articleDTO);
        if (enableElasticsearch) {
            ArticleESRecord articleESRecord = ArticleMapping.INSTANCE.dto2ArticleESRecord(articleDTO);
            esFeignClientInterface.save(articleESRecord);
        }
        articleColumnService.updateArticleColumn(articleDTO);
        return Result.success();
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
    public Result<?> updateArticlePart(ArticleDTO articleDTO) {
        Article beforeArticle = getById(articleDTO.getId());
        beforeArticle.setStatus(articleDTO.getStatus());
        updateById(beforeArticle);
        return Result.success();
    }

    private void updateParamsValidate(ArticleDTO articleDTO) {
        if (ObjectUtils.isEmpty(articleDTO.getId())) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "文章ID为空");
        }
    }

    private void duplicateValidate(ArticleDTO articleDTO) {
        Long count = baseMapper.validateDuplicateCount(articleDTO.getTitle(), articleDTO.getPath(), articleDTO.getId());
        if (count > 0) {
            throw new BizException(ResultCode.INVALID_PARAM.getCode(), "文章标题或者文章路径已存在");
        }
    }

    private void generatePath(ArticleDTO articleDTO) {
        if (StringUtils.isNotEmpty(articleDTO.getPath())) {
            return;
        }
        String hexNameGenerate = GenerateUtil.hexNameGenerate(seed);
        articleDTO.setPath(hexNameGenerate);
    }

    private void setDefaultParams(ArticleDTO articleDTO) {
        articleDTO.setStatus(Constant.DEFAULT_STATUS);
        if (ObjectUtils.isEmpty(articleDTO.getWeight())) {
            articleDTO.setWeight(Constant.DEFAULT_INT_PARAM);
        }
        if (StringUtils.isEmpty(articleDTO.getPath())) {
            articleDTO.setPath(articleDTO.getTitle());
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
        List<BriefColumnDTO> columnDTOList = articleColumnService.listColumnByArticleId(articleVO.getId());
        List<Long> columnIds = columnDTOList.stream().map(BriefColumnDTO::getId).toList();
        articleVO.setColumnIds(columnIds);
    }
}
