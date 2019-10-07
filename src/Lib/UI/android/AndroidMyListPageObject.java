package Lib.UI.android;

import Lib.UI.MyListPageObject;
import io.appium.java_client.AppiumDriver;

public class AndroidMyListPageObject extends MyListPageObject {

    static{
            FOLDER_BY_NAME_TRL = "xpath://*[@text='{FOLDER_NAME}']";
            ARTICLE_BY_TITLE_TRL = "xpath://*[@text='{TITLE}']";
    }
    public AndroidMyListPageObject(AppiumDriver driver) {
        super(driver);
    }
}
