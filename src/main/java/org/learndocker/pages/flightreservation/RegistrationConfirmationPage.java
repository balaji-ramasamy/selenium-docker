package org.learndocker.pages.flightreservation;

import org.learndocker.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class RegistrationConfirmationPage extends BasePage {
    @FindBy(id="go-to-flights-search")
    private WebElement goToFlightSearchButton;

    @FindBy(css ="#registration-confirmation-section p b")
    private WebElement firstNameElement;
    public RegistrationConfirmationPage(WebDriver driver){
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(goToFlightSearchButton));
        return this.goToFlightSearchButton.isDisplayed();
    }

    public void goToFlightSearch(){
        this.goToFlightSearchButton.click();
    }
    public String getFirstName(){
        return this.firstNameElement.getText();
    }
}
