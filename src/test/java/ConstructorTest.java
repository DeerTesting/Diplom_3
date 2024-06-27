import configs.Browsers;
import configs.Url;
import pageobject.OrderForm;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.assertEquals;

public class ConstructorTest extends AbstractClass{
    private WebDriverFactory webDriverFactory = new WebDriverFactory();
    private WebDriver webDriver;

    OrderForm orderForm;

    @Before
    public void setUp(){
        webDriver = webDriverFactory.getWebDriver(Browsers.CHROME);
        webDriver.get(Url.HOST);
        orderForm = new OrderForm(webDriver);
    }

    @Test
    public void souces(){
        orderForm.clickSouce();
        assertEquals( "Соусы", orderForm.isSelected());
    }
    @Test
    public void buns(){
        orderForm.clickSouce();
        orderForm.clickBuns();
        assertEquals( "Булки", orderForm.isSelected());
    }
    @Test
    public void fillings(){
        orderForm.clickFilling();
        assertEquals( "Начинки", orderForm.isSelected());
    }

    @After
    public void tearDown(){
        webDriver.quit();
    }


}
