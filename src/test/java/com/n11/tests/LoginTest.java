package com.n11.tests;
import com.n11.pages.LoginPage;
import org.junit.Test;

// Authored by Ege Bartu Teker
public class LoginTest extends LoginPage {

    //Enter your email ve password from LoginPage
    @Test
    public void loginTest() {
        GoToLoginPage();
        MoveToLoginFormUI();
        IsDisplayedEmailTextBox();
        IsDisplayedPasswordTextBox();
        IsDisplayedRememberMeButton();
        IsDisplayedForgotPasswordButton();
        IsDisplayedSignUpButton();
        IsDisplayedLoginViaFacebook();
        IsDisplayedLoginViaApple();
        IsDisplayedQuickLogin();
        IsDisplayedLoginButton();

        CheckPasswordInURL();
        CheckHTTPSProtocol();
        CheckPasswordMasked();

        PasswordInputLessThan6();
        PasswordInputMoreThan15();
        PasswordInputBetween6And15();
        NullEmailAndPasswordInput();
        NullEmailInput();
        NullPasswordInput();
        CheckShowButtonToShowPassword();
        CheckHideButtonToMaskPassword();
        EmailDomainLessThan2();
        EmailDomainMoreThan15();
        EmailDomainBetween2And15();
        UnSuccessfulLogIn();
        SuccessfulLogIn();
        CheckAfterLoginPage();
        SuccessfulLogOut();
    }
}
