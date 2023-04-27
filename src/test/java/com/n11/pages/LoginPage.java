package com.n11.pages;
import com.n11.driver.BaseTest;
import com.n11.methods.Method;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

// Authored by Ege Bartu Teker
public class LoginPage extends BaseTest {
    Method method;

    public LoginPage(){
        method = new Method();
    }
    // These variables are there for easy to login. Enter your Email and Password:
    String email = "enter your mail";
    String password = "enter your password";

    // invalidEmail variable is there to change in case CAPTCHA pops up
    String invalidEmail = "example403@mail.com";


    @Test
    public void GoToLoginPage(){method.clickToElement(By.cssSelector(".btnSignIn"));}
    @Test
    public void MoveToLoginFormUI(){method.moveToElement(By.id("loginButton"));}

    @Test
    public void IsDisplayedRememberMeButton(){Assert.assertTrue("'Remember Me' button is not visible!",method.pageControl(By.className("rememberMe")));}
    @Test
    public void IsDisplayedForgotPasswordButton(){Assert.assertTrue("'I forgot my password' button is not visible!",method.pageControl(By.id("forgotPassword")));}
    @Test
    public void IsDisplayedSignUpButton(){Assert.assertTrue("'Sign Up' button is not visible!",method.pageControl(By.cssSelector(".signUpButton>a")));}
    @Test
    public void IsDisplayedLoginViaFacebook(){Assert.assertTrue("'Login with Facebook' button is not visible!",method.pageControl(By.cssSelector(".facebook_large.medium.facebookBtn.btnLogin")));}
    @Test
    public void IsDisplayedQuickLogin(){Assert.assertTrue("'Hızlı Giriş' button is not visible!",method.pageControl(By.cssSelector(".quickLogin ")));}
    @Test
    public void IsDisplayedLoginViaApple(){Assert.assertTrue("'Sign in with Apple' button is not visible!",method.pageControl(By.cssSelector(".apple-login-btn")));}
    @Test
    public void IsDisplayedEmailTextBox(){Assert.assertTrue("Email textbox is not visible!",method.pageControl(By.id("email")));}
    @Test
    public void IsDisplayedPasswordTextBox(){Assert.assertTrue("Password textbox is not visible!",method.pageControl(By.id("password")));}
    @Test
    public void IsDisplayedLoginButton(){Assert.assertTrue("Login button is not visible!",method.pageControl(By.id("loginButton")));}

    @Test
    public void CheckPasswordInURL(){
        method.sendKeys(By.id("password"),"123456");
        Assert.assertFalse("Password must not appear in URL!",method.getCurrentUrl().contains("password"));
    }
    @Test
    public void CheckHTTPSProtocol(){
        Assert.assertTrue("Page does not have HTTPS protocol!",method.getCurrentUrl().contains("https"));
    }
    @Test
    public void CheckPasswordMasked(){
        method.loginDataControl("","3456789101");
        Assert.assertTrue("Password is not masked!",method.pageControl(By.cssSelector("input[type='password']")));
    }

    @Test
    public void SuccessfulLogIn() {
        method.login(email,password);
        Assert.assertEquals("Must login successfully!","Hesabım",method.getAttribute(By.cssSelector(".myAccount.myAccountElliptical>a"),"title"));

    }
    @Test
    public void SuccessfulLogOut(){
        method.moveToElement(By.cssSelector("a[title='Hesabım']"));
        method.clickToElement(By.cssSelector(".logoutBtn"));
        Assert.assertEquals("Must logout successfully!","Giriş Yap",method.getAttribute(By.cssSelector(".btnSignIn"),"title"));
    }
    @Test
    public void CheckAfterLoginPage() {
        Assert.assertEquals("Incorrect page URL redirect after login!","https://www.n11.com/", method.getCurrentUrl());
    }
    @Test
    public void UnSuccessfulLogIn() {
        method.login(invalidEmail,"123wrong");
        Assert.assertEquals("Email and password must be invalid!","https://www.n11.com/giris-yap", method.getCurrentUrl());
    }

    @Test
    public void PasswordInputLessThan6(){
        method.loginDataControl("","test1");
        Assert.assertTrue("Error message must be displayed!",method.pageControl(By.className("val-error")));
    }
    @Test
    public void PasswordInputMoreThan15(){
        method.loginDataControl("","test123456789101");
        Assert.assertTrue("Error message must be displayed!",method.pageControl(By.className("val-error")));
    }
    @Test
    public void PasswordInputBetween6And15(){
        method.loginDataControl("username", "test1234");
        Assert.assertTrue("Error message should not be displayed.", method.getText(By.className("val-error")).isEmpty());
    }
    @Test
    public void NullEmailAndPasswordInput(){
        method.login("", "");
        Assert.assertTrue("Email section is not null!",method.pageControl(By.xpath("//div[text()='Lütfen e-posta adresinizi girin.']")));
        Assert.assertTrue("Password section is not null!",method.pageControl(By.xpath("//div[text()='Bu alanın doldurulması zorunludur.']")));
    }
    @Test
    public void NullEmailInput(){
        method.login("", "test1234");
        Assert.assertTrue("Email section is not null!", method.pageControl(By.xpath("//div[text()='Lütfen e-posta adresinizi girin.']")));
    }
    @Test
    public void NullPasswordInput(){
        method.login("test@hotmail.co", "");
        Assert.assertTrue("Password section is not null!", method.pageControl(By.xpath("//div[text()='Bu alanın doldurulması zorunludur.']")));
    }
    @Test
    public void EmailDomainLessThan2(){
        method.login("test@hotmail.c", "");
        Assert.assertTrue("A valid email has been entered!",method.pageControl(By.xpath("//div[text()='Lütfen geçerli bir e-posta adresi girin.']")));
    }
    @Test
    public void EmailDomainMoreThan15(){
        method.login("test@hotmail.commmmmmmmmmmmmm", "");
        Assert.assertTrue("A valid email has been entered!",method.pageControl(By.xpath("//div[text()='Lütfen geçerli bir e-posta adresi girin.']")));
    }
    @Test
    public void EmailDomainBetween2And15(){
        method.loginDataControl("test@hotmail.com", "");
        Assert.assertTrue("An invalid email has been entered!", method.getText(By.className("val-error")).isEmpty());
    }

    @Test
    public void CheckShowButtonToShowPassword(){
        method.loginDataControl("", "test12mask");
        Assert.assertEquals("Password must be masked.", "password", method.getAttribute(By.id("password"), "type"));
        Assert.assertEquals("Button must be named 'Göster'", "Göster", method.getText(By.className("showPass")));

    }
    @Test
    public void CheckHideButtonToMaskPassword(){
        method.loginDataControl("", "test12mask");
        method.clickToElement(By.cssSelector(".showPass"));
        Assert.assertEquals("Password must be visible.", "text", method.getAttribute(By.id("password"), "type"));
        Assert.assertEquals("Button must be named 'Gizle'", "Gizle", method.getText(By.className("showPass")));

    }
}
