package UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    public ArticlePageObject(AppiumDriver driver) {
        super(driver);

    }

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "//*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "android:id/button1",
            MY_LIST_ITEM = "//*[@text='{LIST_NAME}']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']";

    private static String getListXpathByName(String name_of_folder) {
        return MY_LIST_ITEM.replace("{LIST_NAME}", name_of_folder);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public String getTitleElementText() {
        WebElement element = driver.findElement(By.id(TITLE));
        return element.getAttribute("text");
    }

    public String getArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter() {
        this.swipeUpToFindElement(By.xpath(FOOTER), "Cannot find the end of article", 20);
    }


    public void addArticleToMyList(String name_of_folder) {

        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can not find element",
                5

        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find element 'Add to reading list'",
                5

        );

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Can not find element 'GOT IT'",
                5

        );

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Can not find input to set name of articles folder",
                5
        );

        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot find search input",
                5
        );

        this.waitForElementAndClick(
                By.id(MY_LIST_OK_BUTTON),
                "Can not find element 'OK'",
                5

        );

    }

    public void addArticleToMyExistingList(String name_of_folder) {

        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Can not find element",
                5

        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Can not find element 'Add to reading list'",
                5

        );
        String listXpath = getListXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(listXpath),
                "Can not find element " + name_of_folder,
                5
        );
    }


    public void closeArticle() {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Can not find element 'X'",
                5
        );
    }

}
