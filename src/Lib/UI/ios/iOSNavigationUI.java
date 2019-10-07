package Lib.UI.ios;

import Lib.UI.NavigationUI;
import io.appium.java_client.AppiumDriver;

public class iOSNavigationUI extends NavigationUI {

     static {
         MY_LIST_LINK = "xpath://android.widget.FrameLayout[@content-desc='My lists']";

     }

    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }



}
