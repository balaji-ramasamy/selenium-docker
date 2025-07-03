package com.learndocker.tests;

import com.learndocker.listener.TestListener;
import com.learndocker.utils.Config;
import com.learndocker.utils.Constants;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

@Listeners({TestListener.class})
public abstract class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    protected WebDriver driver;
    @BeforeSuite
    public void initialize(){
        Config.initialize();
    }
    @BeforeTest
    public void setDriver(ITestContext context) throws MalformedURLException {
        this.driver = Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))? getRemoteDriver() : getLocalDriver();
        context.setAttribute(Constants.DRIVER, this.driver);
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.credentials_enable_service",false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection",false);
        Capabilities capabilities = new ChromeOptions().setExperimentalOption("prefs", prefs);
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER)))
        {
            capabilities = new FirefoxOptions();
        }
        String urlFormat = Config.get(Constants.GRID_URL_FORMAT);
        String hubHost = Config.get(Constants.GRID_HUB_HOST);
        String url = String.format(urlFormat, hubHost);
        log.info("grid url: {}",url);
        return new RemoteWebDriver(new URL(url), capabilities );
    }
    private WebDriver getLocalDriver(){
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> prefs = new HashMap<>();
        prefs.put("profile.credentials_enable_service",false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection",false);
        options.setExperimentalOption("prefs", prefs);
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver(options);
    }
    @AfterTest
    public void quit(){
        driver.quit();
    }
}
