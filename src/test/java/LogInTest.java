import org.apache.commons.lang3.RandomStringUtils;
import configs.Browsers;
import configs.Url;
import dto.User;
import pageobject.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class LogInTest extends AbstractClass{
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriver webDriver;

    ApiMethods api = new ApiMethods();

    LmsForm lms;
    LogInForm logInForm;

    OrderForm orderForm;
    RegistrationForm registrationForm;

    ForgotPasswordForm forgotPasswordForm;

    User user;

    @Before
    public void setUp(){
        webDriver = webDriverFactory.getWebDriver(Browsers.CHROME);
        webDriver.get(Url.HOST);

        lms = new LmsForm(webDriver);
        logInForm = new LogInForm(webDriver);
        orderForm = new OrderForm(webDriver);
        forgotPasswordForm = new ForgotPasswordForm(webDriver);
        registrationForm = new RegistrationForm(webDriver);

        user = new User(RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(10) + "@yandex.ru",  RandomStringUtils.randomAlphabetic(7));
        api.postUserCreation(user);
    }

    @Test
    public void logInByButton(){
        orderForm.clickLogInButton();
        logInForm.fillLogInForm(user.getEmail(), user.getPassword());
        orderForm.waitOrderPageLoggedIn();
        Assert.assertTrue(orderForm.isVisible());
        assertEquals(Url.HOST, orderForm.getUrl());
    }

    @Test
    public void logInByLms(){
        orderForm.waitOrderPageLoad();
        orderForm.waitOrderPageLoad();
        orderForm.clickLms();
        logInForm.fillLogInForm(user.getEmail(), user.getPassword());
        Assert.assertTrue(orderForm.isVisible());
        assertEquals(Url.HOST, orderForm.getUrl());
    }

    @Test
    public void logInFromRegistration(){
        logInForm.clickRegistrationLink();
        registrationForm.logInLink();
        logInForm.fillLogInForm(user.getEmail(), user.getPassword());
        Assert.assertTrue(orderForm.isVisible());
        assertEquals(Url.HOST, orderForm.getUrl());

    }

    @Test
    public void logInFromForgotPassword(){
        logInForm.clickForgotPasswordLink();
        forgotPasswordForm.clickLogIn();
        logInForm.fillLogInForm(user.getEmail(), user.getPassword());
        Assert.assertTrue(orderForm.isVisible());
        assertEquals(Url.HOST, orderForm.getUrl());
    }


    @After
    public void tearDown() {
        webDriver.quit();

        String token = api.getToken(new User(user.getEmail(), user.getPassword()));
        if (token != null) {
            api.deleteUser(token);
        }
    }
}
