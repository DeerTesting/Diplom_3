import org.apache.commons.lang3.RandomStringUtils;
import configs.Browsers;
import configs.Url;
import pageobject.LogInForm;
import pageobject.OrderForm;
import pageobject.RegistrationForm;
import dto.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends AbstractClass{

    ApiMethods api = new ApiMethods();
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriver webDriver;

    RegistrationForm registrationForm;
    LogInForm logInForm;

    OrderForm orderForm;

    User user;

    @Before
    public void setUp(){
        webDriver = webDriverFactory.getWebDriver(Browsers.CHROME);
        webDriver.get(Url.REGISTRATION_URL);
        registrationForm = new RegistrationForm(webDriver);
        logInForm = new LogInForm(webDriver);
        orderForm = new OrderForm(webDriver);
    }

    @Test
    public void successfulRegistration(){
        user = new User(RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(10) + "@yandex.ru",  RandomStringUtils.randomAlphabetic(6));
        registrationForm.fillRegForm(user.getName(), user.getEmail(), user.getPassword());
        logInForm.fillLogInForm(user.getEmail(), user.getPassword());
        assertTrue(orderForm.isVisible());
        assertEquals(Url.HOST, orderForm.getUrl());
    }

    @Test
    public void wrongPassword(){
        user = new User(RandomStringUtils.randomAlphabetic(7), RandomStringUtils.randomAlphabetic(10) + "@yandex.ru",  RandomStringUtils.randomAlphabetic(5));
        registrationForm.fillRegForm(user.getName(), user.getEmail(), user.getPassword());
        registrationForm.waitErrorMessage();
        assertEquals(Url.REGISTRATION_URL, registrationForm.getUrl());
        assertEquals("Некорректный пароль", registrationForm.errorMessageCheck());

    }

    @After
    public void tearDown(){
        webDriver.quit();

        String token = api.getToken(new User (user.getEmail(), user.getPassword()));
        if(token != null){
            api.deleteUser(token);
        }
    }
}
