package Lib.UI.factories;

import Lib.Platform;
import Lib.UI.ArticlePageObject;
import Lib.UI.NavigationUI;
import Lib.UI.android.AndroidArticlePageObject;
import Lib.UI.android.AndroidNavigationUI;
import Lib.UI.ios.iOSArticlePageObject;
import Lib.UI.ios.iOSNavigationUI;
import io.appium.java_client.AppiumDriver;

public class NavigationUIFactory {

    public static NavigationUI get(AppiumDriver driver){
        if (Platform.getInstance().isAndroid()) {
            return new AndroidNavigationUI(driver);
        }else {
            return new iOSNavigationUI(driver);
        }
    }
}
