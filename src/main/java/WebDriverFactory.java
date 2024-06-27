import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public WebDriver getWebDriver(String browserType) {
        if (browserType.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            return new FirefoxDriver();

        } else if (browserType.equalsIgnoreCase("yandex")) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/yandex/yandexdriver.exe");
            return new ChromeDriver();
        } else {
            WebDriverManager.chromedriver().setup();
            return new ChromeDriver();
        }
    }

}
