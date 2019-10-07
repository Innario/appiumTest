package Lib.UI;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract public class NavigationUI extends MainPageObject{

    protected static String
            MY_LIST_LINK;

    public NavigationUI (AppiumDriver driver) {
        super(driver);
    }

    public void clickMyLists(){
        this.waitForElementAndClick(MY_LIST_LINK, "Can not find  navigation button to 'My lists'", 5);
    }
}
