package org.learndocker.pages.flightreservation;

import org.learndocker.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class FlightsSelectionPage extends BasePage {
    @FindBy(name="departure-flight")
    private List<WebElement> departureFlightBtn;
    @FindBy(name="arrival-flight")
    private List<WebElement> arrivalFlightBtn;
    @FindBy(id="confirm-flights")
    private WebElement confirmFlightsBtn;

    public FlightsSelectionPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public boolean isAt() {
        this.wait.until(ExpectedConditions.visibilityOf(this.confirmFlightsBtn));
        return this.confirmFlightsBtn.isDisplayed();
    }

    public void selectFlights(){
        int random = ThreadLocalRandom.current().nextInt(0, departureFlightBtn.size());
        this.departureFlightBtn.get(random).click();
        this.arrivalFlightBtn.get(random).click();
    }
    public void confirmFlights(){
        this.confirmFlightsBtn.click();
    }

}
