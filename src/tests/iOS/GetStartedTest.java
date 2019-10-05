package tests.iOS;

import Lib.UI.WelcomePageObject;
import org.junit.Test;

import Lib.iOSTestCase;

public class GetStartedTest extends iOSTestCase {



    @Test
    public void testPassThroughWelcome(){

        WelcomePageObject WelcomePage = new WelcomePageObject(driver);

        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPreferredLangText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollectedText();
        WelcomePage.clickGetStartedButton();


    }

}
