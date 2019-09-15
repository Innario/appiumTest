import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {

        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "5.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\inna\\Desktop\\automator\\JavaAppiumAutomation1\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
    }


    @Test
    public void firstTest() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                15
        );
    }

    @Test
    public void testCancelSearch() {

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                5

        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5

        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5

        );

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X is still present on the page",
                5
        );
    }

    @Test
    public void testCompareArticleTitle() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5
        );

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                "Java (programming language)",
                article_title
        );


    }


    @Test /*II.Ex2*/
    public void testSearchPlaceHolder() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                5

        );
        WebElement element = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search box",
                5
        );
        Assert.assertEquals(
                "We see unexpected title",
                "Search…",
                element.getText()
        );
    }


    @Test /*II.Ex3*/
    public void testFindSeveralArticles() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                5

        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "QA",
                "Cannot find search input",
                5
        );

        waitForElementsPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot find search box",
                5,
                3
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5

        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "Cannot find search box",
                5
        );
    }


    @Test /*II.Ex4*/
    public void testFindSeveralArticlesTitles() {
        String wordToSearch = "QA";

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                wordToSearch,
                "Cannot find search input",
                5
        );

        List<WebElement> results = waitForElementsPresent(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find search box",
                5,
                3
        );

        String expected = wordToSearch.toLowerCase();
        for (WebElement result : results) {
            String actual = result.getText().toLowerCase();
            Assert.assertTrue(actual.contains(expected));
        }
    }



    //III Complex scenarios


    @Test
    public void testSwipeArticle() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' article in search",
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20


        );

    }

    @Test
    public void saveFirstArticleToMyList(){
        String searchWord = "Java";
        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";
        String nameOfList = "My article list";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchWord,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleDescription + "']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchWord,
                5
        );

        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                "Can not find element",
                5

        );

        waitForElementAndClick(
                By.xpath("//*[@text='Add to reading list']"),
                "Can not find element 'Add to reading list'",
                5

        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "Can not find element 'GOT IT'",
                5

        );

        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Can not find input to set name of articles folder",
                5
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                nameOfList,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.id("android:id/button1"),
                "Can not find element 'OK'",
                5

        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Can not find element 'X'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can not find  navigation button to 'My lists'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='My article list']"),
                "Can not find created folder",
                5
        );

        swipeUpToFindElementToLeft(
                By.xpath("//*[@text='" + articleTitle + "']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//*[@text='"  + articleTitle + "']"),
                "Cannot find saved article",
                5
        );
    }


    @Test
    public void testAmountOfNotEmptySearch(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        String search_line = "Linkin Park discography";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']/*[@resource-id='org.wikipedia:id/page_list_item_container']";
        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by the request " + search_line,
                25
        );

        int amount_of_search_result = getAmountOfElements(By.xpath(search_result_locator));
        Assert.assertTrue(
                "We found to few results!",
                amount_of_search_result > 0
        );
    }

    @Test
    public void testChangeOrientationOnSearchResults(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5
        );

        String title_before_rotation = waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_page_title_text"),
            "text",
            "Cannot find title of article",
            15
        );

        driver.rotate(ScreenOrientation.LANDSCAPE);

        String title_after_rotation = waitForElementAndGetAttribute(
            By.id("org.wikipedia:id/view_page_title_text"),
                    "text",
                    "Cannot find title of article",
                    15

        );

        Assert.assertEquals(
                title_before_rotation,
                title_after_rotation
        );


    }

    @Test
    public void testCheckSearchArticleInBackground(){
        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        String search_line = "Java";
        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by 'Java'",
                5
        );

        driver.runAppInBackground(2);

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Cannot find article after returning from background",
                5
        );

    }



    //III Complex scenarios. EX5

    @Test
    public void saveTwoArticleToMyList(){
        String searchWord = "Java";
        String nameOfList = "My article list";

        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find search 'Search Wikipedia' input",
                    5
            );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    searchWord,
                    "Cannot find search input",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleDescription + "']"),
                    "Cannot find 'Object-oriented programming language' topic searching by " + searchWord,
                    25
            );

            try {
                Thread.sleep(2000);
                waitForElementAndClick(
                        By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                        "Can not find element",
                        5

                );
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            waitForElementAndClick(
                    By.xpath("//android.widget.TextView[@text='Add to reading list']"),
                    "Can not find element 'Add to reading list'",
                    5

            );

            waitForElementAndClick(
                    By.id("org.wikipedia:id/onboarding_button"),
                    "Can not find element 'GOT IT'",
                    5

            );

            waitForElementAndClear(
                    By.id("org.wikipedia:id/text_input"),
                    "Can not find input to set name of articles folder",
                    5
            );

            waitForElementAndSendKeys(
                    By.id("org.wikipedia:id/text_input"),
                    nameOfList,
                    "Cannot find search input",
                    5
            );

            waitForElementAndClick(
                    By.id("android:id/button1"),
                    "Can not find element 'OK'",
                    5

            );

            waitForElementAndClick(
                    By.xpath("//*[@content-desc='Navigate up']"),
                    "Can not find element 'X'",
                    5
            );
        }

        String articleTitle2 = "JavaScript";
        String articleDescription2 = "Programming language";
        {
            waitForElementAndClick(
                    By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                    "Cannot find search 'Search Wikipedia' input",
                    5
            );

            waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    searchWord,
                    "Cannot find search input",
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleDescription2 + "']"),
                    "Cannot find 'Object-oriented programming language' topic searching by " + searchWord,
                    5
            );

            waitForElementPresent(
                    By.id("org.wikipedia:id/view_page_title_text"),
                    "Cannot find article title",
                    15
            );

            waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='More options']"),
                    "Can not find element",
                    5

            );

            waitForElementAndClick(
                    By.xpath("//*[@text='Add to reading list']"),
                    "Can not find element 'Add to reading list'",
                    5

            );

            waitForElementAndClick(
                    By.xpath("//*[@text='" + nameOfList + "']"),
                    "Can not find element " + nameOfList,
                    5
            );

            waitForElementAndClick(
                    By.xpath("//*[@content-desc='Navigate up']"),
                    "Can not find element 'X'",
                    5
            );
        }


        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Can not find  navigation button to 'My lists'",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='My article list']"),
                "Can not find created folder",
                5
        );

        // Delete first article
        swipeUpToFindElementToLeft(
                By.xpath("//*[@text='" + articleTitle + "']"),
                "Cannot find element " + articleTitle
        );
        // Check first article was deleted
        waitForElementNotPresent(
                By.xpath("//*[@text='"  + articleTitle + "']"),
                "Can find deleted article",
                5
        );
        // Check existing article
        waitForElementAndClick(
                By.id("org.wikipedia:id/page_list_item_title"),
                "Cannot find saved article",
                25
        );
        String title = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15

        );

        Assert.assertEquals(
                title,
                articleTitle2
        );
    }


    //III Complex scenarios. EX6

    @Test
    public void checkArticleTitle() {
        String searchWord = "Java";
        String articleTitle = "Java (programming language)";
        String articleDescription = "Object-oriented programming language";

        waitForElementAndClick(
                By.xpath("//*[contains(@text,'Search Wikipedia')]"),
                "Cannot find search 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchWord,
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='" + articleDescription + "']"),
                "Cannot find 'Object-oriented programming language' topic searching by " + searchWord,
                25
        );

        String title = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/view_page_title_text"),
                "text",
                "Cannot find title of article",
                15

        );


        WebElement titleElement = assertElementPresent(By.id("org.wikipedia:id/view_page_title_text"));
        checkElementAttribute(titleElement, "text", articleTitle);
    }


    //III Complex scenarios. EX7* if testResetOrientation1 fails then testResetOrientation2 should be ok

    @Test
    public void testResetOrientation1(){
        Assert.assertEquals(ScreenOrientation.PORTRAIT, driver.getOrientation());
        driver.rotate(ScreenOrientation.LANDSCAPE);
        Assert.assertTrue(false);
    }

    @Test
    public void testResetOrientation2(){
        Assert.assertEquals(ScreenOrientation.PORTRAIT, driver.getOrientation());
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }


    private void checkElementAttribute(WebElement element, String attribute, String expectedValue) {
        String realValue = element.getAttribute(attribute);
        Assert.assertEquals(realValue, expectedValue);
    }

    private WebElement assertElementPresent(By by) {
        // Throws NoSuchElementException if there is no such an element
        WebElement element = driver.findElement(by);
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private List<WebElement> waitForElementsPresent(By by, String error_message, long timeoutInSeconds, int number) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(by, number));
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;

    }


    //III Complex scenarios

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action.press(x, start_y).waitAction(timeOfSwipe).moveTo(x, end_y).release().perform();
    }

    protected void swipeUpQuick(){

        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes){
        int already_swiped = 0;
        driver.findElements(by);
        driver.findElements(by).size();

        while (driver.findElements(by).size() == 0){
            swipeUpQuick();
            ++already_swiped;

            if (already_swiped>max_swipes){
                waitForElementPresent(by, "Cannot find element by swiping up.\n" + error_message, 0);
            }
        }
    }

    protected void swipeUpToFindElementToLeft(By by, String error_message){
        WebElement element = waitForElementPresent(by,error_message,10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action.press(right_x,middle_y).waitAction(300).moveTo(left_x,middle_y).release().perform();
    }

    private int getAmountOfElements(By by){
        List elements = driver.findElements(by);
        return elements.size();
    }

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message,timeoutInSeconds);
        return element.getAttribute(attribute);
    }


}


