package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationForm {

    private WebDriver driver;
    public RegistrationForm(WebDriver driver){
        this.driver = driver;
    }

    //Поле ввода имени
    private By nameInputRegistration = By.xpath(".//label[text()='Имя']/following-sibling::input");

    //Поле ввода пароля
    private By emailInputRegistration = By.xpath(".//label[text()='Email']/following-sibling::input");
    //Поле ввода эмейла
    private By passwordInputRegistration = By.xpath(".//label[text()='Пароль']/following-sibling::input");
    //Кнопка зарегистрироваться
    private By registrationButton = By.xpath(".//button[text()='Зарегистрироваться']");

    private By errorText = By.xpath(".//p[text()='Некорректный пароль']");



    public void putNameInInput(String name){
        driver.findElement(nameInputRegistration).sendKeys(name);
    }

    public void putPasswordInInput(String password){
        driver.findElement(passwordInputRegistration).sendKeys(password);
    }

    public void putEmailInInput(String email){
        driver.findElement(emailInputRegistration).sendKeys(email);
    }

    public void clickRegButton(){
        driver.findElement(registrationButton).click();
    }

    public boolean waitErrorMessage(){
        return driver.findElement(errorText).isDisplayed();
    }
    public String getErrorMessage(){
        return driver.findElement(errorText).getText();
    }
    private By logInLink = By.xpath(".//a[text() = 'Войти']");

    public void waitPageOpen(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .elementToBeClickable(registrationButton));
    }

    @Step("Fill registration form and click registration button")
    public void fillRegForm(String name,  String email, String password){
        waitPageOpen();
        putNameInInput(name);
        putEmailInInput(email);
        putPasswordInInput(password);
        clickRegButton();

    }
    public void scroll(){
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(logInLink));
    }

    @Step("get error message")
    public String errorMessageCheck(){
        return getErrorMessage();
    }

    @Step("Get current page url")
    public String getUrl(){
        return driver.getCurrentUrl();
    }

    @Step("Click log in link")
    public void logInLink(){
        scroll();
        driver.findElement(logInLink).click();
    }

}
