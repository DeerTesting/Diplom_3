package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LmsForm {
    WebDriver driver;
    public LmsForm(WebDriver driver){
        this.driver = driver;
    }

    private By profileText = By.xpath(".//a[text() ='Профиль']");
    private By burgerIcon = By.className("AppHeader_header__logo__2D0X2");
    private By exitButton = By.xpath(".//button[text() = 'Выход']");


    public void waitLoginPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .visibilityOfElementLocated(profileText));
    }

    public boolean profileTextVisible(){
        return driver.findElement(profileText).isDisplayed();
    }

    @Step("Get LMS url")
    public String getUrl(){
       return driver.getCurrentUrl();
    }

    @Step("Click on burger icon")
    public void clickBurgerIcon(){
        driver.findElement(burgerIcon).click();
    }

    @Step("Click exit button")
    public void clickExitButton(){
        driver.findElement(exitButton).click();
    }

}
