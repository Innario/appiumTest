package Lib;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {

    private static final String
            PLATFORM_IOS = "ios",
            PLATFORM_ANDROID = "android",
            APPIUM_URL = "http://127.0.0.1:4723/wd/hub";

    private static Platform instance;


    public static Platform getInstance(){
        if (instance == null){
            instance = new Platform();
        }
        return instance;
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }
    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    public AppiumDriver getDriver() throws Exception{

        URL URL = new URL(APPIUM_URL);
        if(this.isAndroid()){
            return  new AndroidDriver(URL, this.getAndroidDesiredCapabilities());
        }else if(this.isIOS()){
            return new IOSDriver(URL, this.getIOSDesiredCapabilities());
        }else{
            throw new Exception("Cannot detect type of the Driver. Platform value: " + this.getPlatformVar());
        }
    }

    private DesiredCapabilities getAndroidDesiredCapabilities(){

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/innaVoevodina/AndroidStudioProjects/appiumTest/apks/org.wikipedia.apk");

        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities(){

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone SE");
        capabilities.setCapability("platformVersion", "12.0");
        capabilities.setCapability("app", "/Users/innaVoevodina/AndroidStudioProjects/appiumTest/apks/Wikipedia.app");

        return capabilities;
    }

    private boolean isPlatform(String my_platform){
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar(){
        return System.getenv("PLATFORM");

    }

}
