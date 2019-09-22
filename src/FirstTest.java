import Lib.CoreTestCase;
import UI.*;
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

        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("QA");
        SearchPageObject.getAmountOfFoundArticles();
        SearchPageObject.clickCancelSearch();
        SearchPageObject.assertThereIsNotResultOfSearch();

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
            SearchPageObject SearchPageObject = new SearchPageObject(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(searchWord);
            SearchPageObject.clickByArticleWithSubstring(articleDescription);

            ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToMyList(nameOfList);
            ArticlePageObject.closeArticle();
        }

        String articleTitle2 = "JavaScript";
        String articleDescription2 = "Programming language";
        {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(searchWord);
            SearchPageObject.clickByArticleWithSubstring(articleDescription2);

            ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
            ArticlePageObject.waitForTitleElement();
            ArticlePageObject.addArticleToMyExistingList(nameOfList);
            ArticlePageObject.closeArticle();
        }

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = new MyListPageObject(driver);
        MyListPageObject.openFolderByName(nameOfList);
        MyListPageObject.swipeByArticleToDelete(articleTitle);
        MyListPageObject.waitForArticleToDisappearByTitle(articleTitle);
        MyListPageObject.waitForArticleToAppearByTitle(articleTitle2);
        assertEquals(MyListPageObject.getFirstArticleTitle(), articleTitle2);
    }


    //III Complex scenarios. EX6

    @Test
    public void testCheckArticleTitle() {
        String searchWord = "Java";
        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchWord);
        SearchPageObject.clickByArticleWithSubstring(articleDescription);

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);
        assertEquals(ArticlePageObject.getTitleElementText(), articleTitle);
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


