package com.learndocker.tests.vendorportal;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.learndocker.tests.BaseTest;
import com.learndocker.tests.vendorportal.model.VendorPortalTestData;
import com.learndocker.utils.Config;
import com.learndocker.utils.Constants;
import com.learndocker.utils.JsonUtil;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.learndocker.pages.vendorportal.DashboardPage;
import org.learndocker.pages.vendorportal.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class VendorPortalTest extends BaseTest {
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private VendorPortalTestData testData;
    @BeforeTest
    @Parameters("testDataPath")
    public void start(String testDataPath){
        this.testData = JsonUtil.getTestData(testDataPath, VendorPortalTestData.class);
        this.loginPage = new LoginPage(driver);
        this.dashboardPage = new DashboardPage(driver);
    }
    @Test
    public void loginTest(){
        loginPage.goTo(Config.get(Constants.VENDOR_PORTAL_URL));
        Assert.assertTrue(loginPage.isAt());
        loginPage.login(testData.username(),testData.password());
    }

    @Test(dependsOnMethods = "loginTest")
    public void dashboardTest(){
        Assert.assertTrue(dashboardPage.isAt());
        //finance metrics
        Assert.assertEquals(dashboardPage.getMonthlyEarning(), testData.monthlyEarning());
        Assert.assertEquals(dashboardPage.getAnnualEarning(),testData.annualEarning());
        Assert.assertEquals(dashboardPage.getProfitMargin(),testData.profitMargin());
        Assert.assertEquals(dashboardPage.getAvailableInventory(),testData.availableInventory());
        dashboardPage.searchOrderHistoryBy(testData.searchKeyword());
        Assert.assertEquals(dashboardPage.getSearchResultsCount(),testData.searchResultsCount());
    }

    @Test(dependsOnMethods = "dashboardTest")
    public void logOutTest(){
        Assert.assertTrue(dashboardPage.isAt());
        dashboardPage.logOut();
        Assert.assertTrue(this.loginPage.isAt());
    }



}
