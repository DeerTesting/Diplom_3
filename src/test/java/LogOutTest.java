import org.apache.commons.lang3.RandomStringUtils;
import configs.Browsers;
import configs.Url;
import dto.User;
import pageobject.LmsForm;
import pageobject.LogInForm;
import pageobject.OrderForm;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class LogOutTest extends AbstractClass{

    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriver webDriver;

    ApiMethods api = new ApiMethods();

    LmsForm lms;
    LogInForm logInForm;

    OrderForm orderForm;

    User user;

    @Before
    public void setUp(){
        webDriver = webDriverFactory.getWebDriver(Browsers.YANDEX);
        webDriver.get(Url.LOGIN_URL);

        lms = new LmsForm(webDriver);
        logInForm = new LogInForm(webDriver);
        orderForm = new OrderForm(webDriver);

        user = new User(RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(10) + "@yandex.ru",  RandomStringUtils.randomAlphabetic(7));
        api.postUserCreation(user);
    }

    @Test
    public void lmsIconToLms(){
        logInForm.fillLogInForm(user.getEmail(), user.getPassword());
        orderForm.waitOrderPageLoggedIn();
        orderForm.clickLms();
        lms.waitLoginPageLoad();
        lms.clickExitButton();
        logInForm.waitLoginPageLoad();
        assertEquals(Url.LOGIN_URL, orderForm.getUrl());
        Assert.assertTrue(logInForm.logOut());
    }

    @After
    public void tearDown(){
        webDriver.quit();

        String token = api.getToken(new User (user.getEmail(), user.getPassword()));
        if (token != null){
            api.deleteUser(token);
        }
    }
}
