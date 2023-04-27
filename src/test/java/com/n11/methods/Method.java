package com.n11.methods;
import com.n11.driver.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

// Authored by Ege Bartu Teker
public class Method extends BaseTest {

    public WebElement findElement(By by) {
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
        return driver.findElement(by);
    }

    public void clickToElement(By by) {
        wait.until(ExpectedConditions.elementToBeClickable(by));
        findElement(by).click();
    }

    public void sendKeys(By by, String key) {
        findElement(by).clear();
        findElement(by).sendKeys(key);
    }

    public String getAttribute(By by, String name){
        return findElement(by).getAttribute(name);
    }
    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }
    public String getText(By by){
        return findElement(by).getText();
    }

    public void moveToElement(By by) {
        WebElement webElement = findElement(by);
        actions.moveToElement(webElement).build().perform();
    }
    public boolean pageControl(By by) {
        return findElement(by).isDisplayed();
    }

    public void login(String email, String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginContainer")));
        sendKeys(By.id("email"),email);
        sendKeys(By.id("password"),password);
        clickToElement(By.id("loginButton"));
    }
    public void loginDataControl(String email, String password){
        sendKeys(By.id("email"),email);
        sendKeys(By.id("password"),password);
        findElement(By.id("password")).sendKeys(Keys.TAB);
    }
}
