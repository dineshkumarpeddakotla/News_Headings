package com.news_headings;

import com.news_headings.base.BaseClass;
import com.news_headings.pages.News_Page;
import org.testng.annotations.Test;

public class TestNews_Headings extends BaseClass {

    @Test
    public void test() {
        News_Page news_page = new News_Page(driver);
        news_page.headLinesOfNewsList();
        news_page.repeatedWordCount();
        news_page.maximumPointsHeadlines();
    }
}
