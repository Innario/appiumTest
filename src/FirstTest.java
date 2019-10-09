import Lib.CoreTestCase;
import Lib.UI.*;
import Lib.UI.factories.ArticlePageObjectFactory;
import Lib.UI.factories.MyListPageObjectFactory;
import Lib.UI.factories.NavigationUIFactory;
import Lib.UI.factories.SearchPageObjectFactory;
import org.junit.Assert;
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
                "id:org.wikipedia:id/search_container",
                "Cannot find search 'Search Wikipedia' input",
                5

        );
        WebElement element = MainPageObject.waitForElementPresent(
                "id:org.wikipedia:id/search_src_text",
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

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

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
                "id:org.wikipedia:id/search_container",
                "Cannot find search 'Search Wikipedia' input",
                5
        );
        MainPageObject.waitForElementAndSendKeys(
                "id:org.wikipedia:id/search_src_text",
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
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.typeSearchLine(searchWord);
            SearchPageObject.clickByArticleWithSubstring(articleDescription);
            ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);


            if (Platform.getInstance().isAndroid()) {
                ArticlePageObject.addArticleToMyList(nameOfList);
            } else if (Platform.getInstance().isIOS()) {
                ArticlePageObject.addArticlesToMySaved();
                ArticlePageObject.closePopUpAuthorization();
                ArticlePageObject.closeArticle();
            }
            ArticlePageObject.waitForTitleElement();
        }

        String articleTitle2 = "JavaScript";
        String articleDescription2 = "Programming language";
        {
            SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
            SearchPageObject.initSearchInput();
            if (Platform.getInstance().isAndroid()) {
                SearchPageObject.typeSearchLine(searchWord);
            }
            SearchPageObject.clickByArticleWithSubstring(articleDescription2);
            ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);


            if (Platform.getInstance().isAndroid()) {
                ArticlePageObject.addArticleToMyList(nameOfList);
            } else if (Platform.getInstance().isIOS()) {
                ArticlePageObject.addArticlesToMySaved();
                ArticlePageObject.closeArticle();
            }
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);
        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(nameOfList);
        }
        MyListPageObject.swipeByArticleToDelete(articleTitle);
        MyListPageObject.waitForArticleToDisappearByTitle(articleTitle);
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.clickByArticleWithSubstring(articleDescription2);
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        Assert.assertEquals(ArticlePageObject.getArticleTitle(),articleTitle2);
    }


    //III Complex scenarios. EX6

    @Test
    public void testCheckArticleTitle() {
        String searchWord = "Java";
        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(searchWord);
        SearchPageObject.clickByArticleWithSubstring(articleDescription);

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
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


