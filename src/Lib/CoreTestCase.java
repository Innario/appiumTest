package Lib;

import Lib.UI.WelcomePageObject;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class CoreTestCase extends TestCase {

    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android";

    protected AppiumDriver driver;
    protected Platform Platform;


    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        /*driver = getDriverByPlatformEnv(capabilities);
        driver.rotate(ScreenOrientation.PORTRAIT);*/
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();
    }

    @Override
    protected void tearDown() throws Exception {
        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrait(){
        driver.rotate(ScreenOrientation.PORTRAIT);

    }

    protected void rotateScreenLandscape(){
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }


    protected void backgroundApp(int seconds){
        driver.runAppInBackground(seconds);
    }

/*    private AppiumDriver getDriverByPlatformEnv(DesiredCapabilities capabilities) throws Exception{

        String platform = System.getenv("PLATFORM");

        if(platform.equals(PLATFORM_ANDROID)){
            return new AndroidDriver(new URL(AppiumURL), capabilities);

        } else if (platform.equals(PLATFORM_IOS)){
            return new IOSDriver(new URL(AppiumURL), capabilities);

        }else{
            throw new Exception("Cannot get run platform from env variable. Platform value " + platform);
        }

    }*/

    private void skipWelcomePageForIOSApp(){
        if (Platform.getInstance().isIOS()) {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

}
