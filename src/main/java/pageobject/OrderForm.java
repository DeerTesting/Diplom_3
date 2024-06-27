package pageobject;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderForm {
    WebDriver driver;
    public OrderForm(WebDriver driver){
        this.driver = driver;
    }

    private By orderButton = By.xpath(".//button[text()='Оформить заказ']");
    private By logInButton = By.xpath(".//button[text() = 'Войти в аккаунт']");
    private By lms = By.xpath(".//p[text()='Личный Кабинет']");
    private By bunsText = By.xpath(".//span[text() = 'Булки']");
    private By souceText = By.xpath(".//span[text() = 'Соусы']");
    private By fillingText = By.xpath(".//span[text() = 'Начинки']");
    public By  selectedElement = By.xpath(".//div[contains(@class, 'tab_type_current')]");

    @Step("Wait gor page to load with logged in user")
    public void waitOrderPageLoggedIn(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .elementToBeClickable(orderButton));
    }

    @Step("Wait for page to load")
    public void waitOrderPageLoad(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .elementToBeClickable(logInButton));
    }
    public boolean isVisible(){
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions
                .elementToBeClickable(orderButton));
        return driver.findElement(orderButton).isDisplayed();
    }

    @Step("Click on fillings")
    public void clickFilling(){
        waitOrderPageLoad();
        driver.findElement(fillingText).click();
    }
    @Step("Click on souses")
    public void clickSouce(){
        waitOrderPageLoad();
        driver.findElement(souceText).click();

    }
    @Step("Click on buns")
    public void clickBuns(){
        waitOrderPageLoad();
        driver.findElement(bunsText).click();
    }


    @Step("Check right ingredient is shown")
    public String isSelected(){
        return driver.findElement(selectedElement).getText();
    }

    @Step("Get current url")
    public String getUrl(){
        return driver.getCurrentUrl();
    }

    @Step("Click on lms icon")
    public void clickLms(){
        driver.findElement(lms).click();
    }

    @Step("Click log in button")
    public void clickLogInButton(){
        waitOrderPageLoad();
        driver.findElement(logInButton).click();
    }

}
