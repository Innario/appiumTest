package Lib.UI.ios;

import Lib.UI.ArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class iOSArticlePageObject extends ArticlePageObject {

    private static final String
            TITLE = "id:Java (programming Language)",
            FOOTER = "xpath://View page in browser",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later",
            CLOSE_ARTICLE_BUTTON = "id:Back";



    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
