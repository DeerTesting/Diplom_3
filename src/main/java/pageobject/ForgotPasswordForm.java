package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgotPasswordForm {
    WebDriver driver;
    public ForgotPasswordForm(WebDriver driver){
        this.driver = driver;
    }
    private By logIn = By.xpath(".//a[text() = 'Войти']");
    public void waitForgotPasswordForm(){
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions
                .elementToBeClickable(logIn));
    }

    @Step("Click log in link")
    public void clickLogIn(){
        waitForgotPasswordForm();
        driver.findElement(logIn).click();
    }
}
