package Lib.UI.android;

import Lib.UI.ArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidArticlePageObject extends ArticlePageObject {

    private static final String
            TITLE = "id:org.wikipedia:id/view_page_title_text",
            FOOTER = "xpath://*[@text='View page in browser']",
            OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "xpath://*[@text='Add to reading list']",
            ADD_TO_MY_LIST_OVERLAY = "id:org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "id:org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "id:android:id/button1",
            MY_LIST_ITEM = "xpath://*[@text='{LIST_NAME}']",
            CLOSE_ARTICLE_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']";


    public AndroidArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
