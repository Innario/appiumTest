package tests;

import Lib.CoreTestCase;
import Lib.UI.ArticlePageObject;
import Lib.UI.MyListPageObject;
import Lib.UI.NavigationUI;
import Lib.UI.SearchPageObject;
import Lib.UI.factories.ArticlePageObjectFactory;
import Lib.UI.factories.MyListPageObjectFactory;
import Lib.UI.factories.NavigationUIFactory;
import Lib.UI.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTests extends CoreTestCase {

    private static final String name_of_folder = "Learning programming";


    @Test
    public void testSaveFirstArticleToMyList(){
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        }else {
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.clickMyLists();

        MyListPageObject MyListPageObject = MyListPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListPageObject.openFolderByName(name_of_folder);
        }
        MyListPageObject.swipeByArticleToDelete(article_title);
    }

}
