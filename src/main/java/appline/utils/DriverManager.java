package appline.utils;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class DriverManager {
    private static WebDriver driver;
    public static WebDriverWait webDriverWait;
    private static final ChromeOptions options = new ChromeOptions();
    public static Actions actions;

    public static void init() {
        Properties properties = PropsSettings.getInstance().getProperties();

        String browser = properties.getProperty("browser", "chrome");

        switch (browser) {
            case "chrome":
                System.setProperty(properties.getProperty("chromeDriver"), properties.getProperty("driverPathChrome"));
                options.setPageLoadStrategy(PageLoadStrategy.EAGER);
                driver = new ChromeDriver();
                break;
            case "opera":
                System.setProperty(properties.getProperty("operaDriver"), properties.getProperty("driverPathOpera"));
                driver = new OperaDriver();
                break;
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        webDriverWait = new WebDriverWait(driver, 20);
        actions = new Actions(driver);
    }

    public static void getSite(String url) {
        driver.get(url);
    }

    public static void quit() {
        driver.quit();
    }

    public static WebDriver getDriver() {
        return driver;
    }

}
