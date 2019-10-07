package Lib.UI.factories;

import Lib.Platform;
import Lib.UI.ArticlePageObject;
import Lib.UI.MyListPageObject;
import Lib.UI.android.AndroidMyListPageObject;
import Lib.UI.ios.iOSMyListPageObject;
import io.appium.java_client.AppiumDriver;

public class MyListPageObjectFactory {

    public static MyListPageObject get(AppiumDriver driver){

        if (Platform.getInstance().isAndroid()) {
            return new AndroidMyListPageObject(driver);
        }else {
            return new iOSMyListPageObject(driver);
        }
    }

}
