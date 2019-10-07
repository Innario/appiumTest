package Lib.UI.ios;

import Lib.UI.MyListPageObject;
import io.appium.java_client.AppiumDriver;

public class iOSMyListPageObject extends MyListPageObject {

    static{
            ARTICLE_BY_TITLE_TRL = "xpath://XCUIElementTypeLink[contains(@name,'{TITLE}')]";
    }

    public iOSMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
