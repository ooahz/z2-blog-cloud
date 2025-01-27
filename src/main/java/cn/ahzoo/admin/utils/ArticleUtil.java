package cn.ahzoo.admin.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.concurrent.atomic.AtomicInteger;

public class ArticleUtil {

    private static final String ID_PREFIX = "header-";

    public static String formatHtml(String html) {
        Document document = Jsoup.parse(html, "UTF-8");
        Elements bodyElements = document.select("body");
        bodyElements.forEach(bodyElement -> {
            Elements bodyChildrenElements = bodyElement.children();
            AtomicInteger index = new AtomicInteger(0);
            bodyChildrenElements.forEach(element -> {
                String tagName = element.tagName();
                formatToc(tagName, element, index);
            });
        });
        return bodyElements.first().html()
                .replace("<pre><code>", "<pre><code class='language-text'>");
    }

    public static void formatToc(String tagName, Element element, AtomicInteger index) {
        if (tagName.startsWith("h") && !"hr".equals(tagName)) {
            element.attr("id", ID_PREFIX + index.getAndIncrement());
        }
    }
}
