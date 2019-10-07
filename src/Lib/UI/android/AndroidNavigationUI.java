package Lib.UI.android;

import Lib.UI.NavigationUI;
import io.appium.java_client.AppiumDriver;

public class AndroidNavigationUI extends NavigationUI {

    static {
        MY_LIST_LINK = "id:Saved";

    }

    public AndroidNavigationUI(AppiumDriver driver) {
        super(driver);
    }
}
