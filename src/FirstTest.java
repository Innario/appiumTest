import Lib.CoreTestCase;
import UI.MainPageObject;
import UI.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;

import java.util.List;

public class FirstTest extends CoreTestCase {

    private MainPageObject MainPageObject;

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test /*II.Ex2*/
    public void testSearchPlaceHolder() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                5

        );
        WebElement element = MainPageObject.waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search box",
                5
        );
        assertEquals(
                "We see unexpected title",
                "Search…",
                element.getText()
        );
    }


    @Test /*II.Ex3*/
    public void testFindSeveralArticles() {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                5

        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "QA",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementsPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot find search box",
                5,
                3
        );
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5

        );
        MainPageObject.waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot find search box",
                5
        );
    }


    @Test /*II.Ex4*/
    public void testFindSeveralArticlesTitles() {
        String wordToSearch = "QA";

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                wordToSearch,
                "Cannot find search input",
                5
        );

        List<WebElement> results = MainPageObject.waitForElementsPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find search box",
                5,
                3
        );

        String expected = wordToSearch.toLowerCase();
        for (WebElement result : results) {
            String actual = result.getText().toLowerCase();
            assertTrue(actual.contains(expected));
        }
    }


    //III Complex scenarios. EX5

    @Test
    public void testSaveTwoArticleToMyList(){
        String searchWord = "Java";
        String nameOfList = "My article list";

        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";
        {
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find search 'Search Wikipedia' input",
                    5
            );

            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    searchWord,
                    "Cannot find search input",
                    5
            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleDescription + "']"),
                    "Cannot find 'Object-oriented programming language' topic searching by " + searchWord,
                    25
            );

            try {
                Thread.sleep(2000);
                MainPageObject.waitForElementAndClick(
                        By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                        "Can not find element",
                        5

                );
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                    "Can not find element 'Add to reading list'",
                    5

            );

            MainPageObject.waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Can not find element 'GOT IT'",
                    5

            );

            MainPageObject.waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Can not find input to set name of articles folder",
                    5
            );

            MainPageObject.waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    nameOfList,
                    "Cannot find search input",
                    5
            );

            MainPageObject.waitForElementAndClick(
                    By.id("android:id/button1"),
                    "Can not find element 'OK'",
                    5

            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@content-desc='Navigate up']"),
                    "Can not find element 'X'",
                    5
            );
        }

        String articleTitle2 = "JavaScript";
        String articleDescription2 = "Programming language";
        {
            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find search 'Search Wikipedia' input",
                    5
            );

            MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    searchWord,
                    "Cannot find search input",
                    5
            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleDescription2 + "']"),
                    "Cannot find 'Object-oriented programming language' topic searching by " + searchWord,
                    5
            );

            MainPageObject.waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot find article title",
                    15
            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Can not find element",
                    5

            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"),
                    "Can not find element 'Add to reading list'",
                    5

            );

            MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@text='" + nameOfList + "']"),
                    "Can not find element " + nameOfList,
                    5
            );

            MainPageObject. waitForElementAndClick(
                    By.xpath("//*[@content-desc='Navigate up']"),
                    "Can not find element 'X'",
                    5
            );
        }


        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can not find  navigation button to 'My lists'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='My article list']"),
                "Can not find created folder",
                5
        );

        // Delete first article
        MainPageObject.swipeUpToFindElementToLeft(
                By.xpath("//*[@text='" + articleTitle + "']"),
                "Cannot find element " + articleTitle
        );
        // Check first article was deleted
        MainPageObject.waitForElementNotPresent(
                By.xpath("//*[@text='"  + articleTitle + "']"),
                "Can find deleted article",
                5
        );
        // Check existing article
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find saved article",
                25
        );
        String title = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15

        );

        assertEquals(
                title,
                articleTitle2
        );
    }


    //III Complex scenarios. EX6

    @Test
    public void testCheckArticleTitle() {
        String searchWord = "Java";
        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchWord,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleDescription + "']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchWord,
                25
        );

        String title = MainPageObject.waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15

        );


        WebElement titleElement = assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
        checkElementAttribute(titleElement, "text", articleTitle);
    }


    //III Complex scenarios. EX7* if testResetOrientation1 fails then testResetOrientation2 should be ok

    @Test
    public void testResetOrientation1(){
        assertEquals(ScreenOrientation.PORTRAIT, driver.getOrientation());
        driver.rotate(ScreenOrientation.LANDSCAPE);
        assertTrue(false);
    }

    @Test
    public void testResetOrientation2(){
        assertEquals(ScreenOrientation.PORTRAIT, driver.getOrientation());
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }


    private void checkElementAttribute(WebElement element, String attribute, String expectedValue) {
        String realValue = element.getAttribute(attribute);
        assertEquals(realValue, expectedValue);
    }

    private WebElement assertElementPresent(By by) {
        // Throws NoSuchElementException if there is no such an element
        WebElement element = driver.findElement(by);
        return element;
    }

}


