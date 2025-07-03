package org.learndocker.pages.flightreservation;

import org.learndocker.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FlightConfirmationPage extends BasePage {
    private static final Logger log = LoggerFactory.getLogger(FlightConfirmationPage.class);
    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(1) .col:nth-child(2)")
    private WebElement flightConfirmationWlement;

    @FindBy(css="#flights-confirmation-section .card-body .row:nth-child(3) .col:nth-child(2)")
    private WebElement priceWlement;

    public FlightConfirmationPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.flightConfirmationWlement));
        return this.flightConfirmationWlement.isDisplayed();
    }

    public String getPrice(){
        String confirmation = this.flightConfirmationWlement.getText();
        String price = this.priceWlement.getText();
        log.info("flight comnfirmation# : {}", confirmation);
        log.info("Total price: {}", price);
        return this.priceWlement.getText();
    }
}
