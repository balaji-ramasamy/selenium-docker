package org.learndocker.pages.flightreservation;

import org.learndocker.pages.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class FlightSearchPage extends BasePage {
    @FindBy(id="oneway")
    private WebElement oneWayRadioBtn;
    @FindBy(id="twoway")
    private WebElement roundTripRadioBtn;
    @FindBy(id="passengers")
    private WebElement passengersDropdown;
    @FindBy(id="depart-from")
    private WebElement departingFromDropdown;
    @FindBy(id="arrive-in")
    private WebElement arrivingInFromDropdown;
    @FindBy(id="search-flights")
    private WebElement searchFlightsButton;
        public FlightSearchPage(WebDriver driver) {
        super(driver);
    }
    @Override
    public boolean isAt() {
       this.wait.until(ExpectedConditions.visibilityOf(passengersDropdown));
       return this.passengersDropdown.isDisplayed();
    }

    public void selectPassengers(String noOfPassengers){
            Select passengers = new Select(passengersDropdown);
            passengers.selectByValue(noOfPassengers);
    }

    public void searchFlights(){
            this.searchFlightsButton.click();
    }
}
