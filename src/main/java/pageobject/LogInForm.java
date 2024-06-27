package pageobject;

import io.qameta.allure.Step;
import configs.Url;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LogInForm {

    WebDriver driver;
    public LogInForm(WebDriver driver){
        this.driver = driver;
    }
    private By emailField = By.xpath(".//label[text() = 'Email']/following-sibling::input");
    private By passwordField = By.xpath(".//label[text() = 'Пароль']/following-sibling::input");
    private By logInButton = By.xpath(".//button[text()='Войти']");
    private By registrationLink = By.xpath(".//a[text() = 'Зарегистрироваться']");
    private By forgotPasswordLink = By.xpath(".//a[text() = 'Восстановить пароль']");

    public void waitLoginPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .elementToBeClickable(logInButton));
    }

    public void fillEmail(String email){
        driver.findElement(emailField).sendKeys(email);
    }
    public void fillPassword(String password){
        driver.findElement(passwordField).sendKeys(password);
    }
    public void clickLogInButton(){
        driver.findElement(logInButton).click();
    }
    public void scroll(){
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(forgotPasswordLink));
    }

    public void setUrl(){
        driver.get(Url.LOGIN_URL);
        waitLoginPageLoad();
    }

    @Step("Fill fields to log in and click log in button")
    public void fillLogInForm(String email, String password){
        waitLoginPageLoad();
        fillEmail(email);
        fillPassword(password);
        clickLogInButton();
    }

    @Step("Successful log out")
    public boolean logOut(){
        return driver.findElement(logInButton).isDisplayed();
    }

    @Step("Go to log in page and click registration link")
    public void clickRegistrationLink(){
        setUrl();
        driver.findElement(registrationLink).click();
    }

    @Step("Click forgot password  link")
    public void clickForgotPasswordLink(){
        setUrl();
        scroll();
        driver.findElement(forgotPasswordLink).click();
    }


}
