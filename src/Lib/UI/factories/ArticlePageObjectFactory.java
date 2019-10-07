package Lib.UI.factories;

import Lib.Platform;
import Lib.UI.ArticlePageObject;
import Lib.UI.android.AndroidArticlePageObject;
import Lib.UI.ios.iOSArticlePageObject;
import io.appium.java_client.AppiumDriver;

public class ArticlePageObjectFactory {

    public static ArticlePageObject get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidArticlePageObject(driver);
        }else {
            return new iOSArticlePageObject(driver);
        }
    }
}
