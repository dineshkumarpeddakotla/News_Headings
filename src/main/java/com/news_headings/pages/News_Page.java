package com.news_headings.pages;

import com.news_headings.base.BaseClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.*;

public class News_Page extends BaseClass {

    ArrayList<String> headingsList = new ArrayList<>();
    ArrayList<Integer> headLinesPoints = new ArrayList<>();
    HashMap<String, Integer> headLinesAndPoints = new HashMap<>();
    ArrayList<String> wordsList = new ArrayList<>();
    HashMap<String,Integer> wordsCount = new HashMap<>();

    @FindBy(xpath = "//a[@class='storylink']")
    List<WebElement> headlinesOfNews;
    @FindBy(xpath = "//span[@class='score']")
    List<WebElement> points;
    @FindBy(xpath = "//a[contains(text(),'More')]")
    WebElement more;

    public News_Page(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void headLinesOfNewsList() {
        int count = 0;
        while (count<=120) {
            headlinesOfNews.forEach(element -> headingsList.add(element.getText()));
            points.parallelStream().forEach(point -> { String[] score = point.getText().split("[^\\d]+");
                int value = Integer.parseInt(score[0]);
                headLinesPoints.add(value);
            });
            
            for (int i =0; i <headLinesPoints.size();i++) {
                headLinesAndPoints.put(headingsList.get(i), headLinesPoints.get(i));
            }
            more.click();
            count +=30;
        }
    }

    public void repeatedWordCount() {
        headingsList.forEach(headings -> Collections.addAll(wordsList, headings.split(" ")));
        wordsList.forEach(word -> wordsCount.merge(word, 1, Integer::sum));
        System.out.println("maximum times repeated word is: "+wordsCount.entrySet().stream()
                .max(Comparator.comparingInt(Map.Entry::getValue)));
    }

    public void maximumPointsHeadlines() {
       Optional<Map.Entry<String, Integer>> popularHeading = headLinesAndPoints.entrySet().stream()
               .max(Comparator.comparingInt(Map.Entry::getValue));
        System.out.println("popular heading based on points is: "+popularHeading);
    }
}
