package Lib.UI.ios;

import Lib.UI.ArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class iOSArticlePageObject extends ArticlePageObject {

     static {
            TITLE = "id:Java (programming Language)";
            FOOTER = "xpath://View page in browser";
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://XCUIElementTypeButton[@name=\"Save for later\"]"; //""id:Save for later",
            CLOSE_ARTICLE_BUTTON = "id:Back";
            CLOSE_AUTHORIZATION_BUTTON = "xpath://XCUIElementTypeButton[@name=\"places auth close\"]";
     }



    public iOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
