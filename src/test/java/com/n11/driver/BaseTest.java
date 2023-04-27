package com.n11.driver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;

// Authored by Ege Bartu Teker
public class BaseTest {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Actions actions;
    //protected static final Logger logger = LogManager.getLogger(BaseTest.class);
    // Logger is not used due to problems.

    @Before
    public void setUp(){

        System.setProperty("webdriver.chrome.driver","src/test/resources/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--disable-translate");

        driver = new ChromeDriver(chromeOptions);
        wait = new WebDriverWait(driver,5);
        actions = new Actions(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.n11.com/");
    }
    @After
    public void tearDown(){
    if(driver != null) {
    driver.close();
    driver.quit();
        }
    }
}