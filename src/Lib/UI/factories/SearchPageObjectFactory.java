package Lib.UI.factories;

import Lib.Platform;
import Lib.UI.SearchPageObject;
import Lib.UI.android.AndroidSearchPageObject;
import Lib.UI.ios.iOSSearchPageObject;
import io.appium.java_client.AppiumDriver;

public class SearchPageObjectFactory {

    public static SearchPageObject get(AppiumDriver driver){

        if (Platform.getInstance().isAndroid()) {
            return new AndroidSearchPageObject(driver);
        }else {
            return new iOSSearchPageObject(driver);
        }
    }
}
