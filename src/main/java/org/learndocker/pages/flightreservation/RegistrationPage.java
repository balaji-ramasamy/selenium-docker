package org.learndocker.pages.flightreservation;

import org.learndocker.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class RegistrationPage extends BasePage {
    @FindBy(id = "firstName")
    private WebElement firstNameInput;
    @FindBy(id = "lastName")
    private WebElement lastNameInput;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "password")
    private WebElement passwordInput;
    @FindBy(name = "street")
    private WebElement streetInput;
    @FindBy(name = "city")
    private WebElement cityInput;
    @FindBy(id = "inputState")
    private WebElement stateDropdown;
    @FindBy(name = "zip")
    private WebElement zipInput;
    @FindBy(id = "register-btn")
    private WebElement registerButton;
    public RegistrationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(firstNameInput));
        return this.firstNameInput.isDisplayed();
    }

    public void goTo(String url){
        this.driver.get(url);
    }

    public void enterUserDetails(String firstName, String lastName){
        this.firstNameInput.sendKeys(firstName);
        this.lastNameInput.sendKeys(lastName);
    }

    public void enterUserCredentials(String email, String password){
        this.emailInput.sendKeys(email);
        this.passwordInput.sendKeys(password);
    }
    public void enterUserAddress(String street, String city, String state, String zip ){
        this.streetInput.sendKeys(street);
        this.cityInput.sendKeys(city);
        Select s = new Select(this.stateDropdown);
        s.selectByVisibleText(state);
        this.zipInput.sendKeys(zip);
    }

    public void register(){
        this.registerButton.click();
    }

}
