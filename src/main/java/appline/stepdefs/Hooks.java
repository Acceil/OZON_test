package appline.stepdefs;

import appline.pages.BasePage;
import appline.utils.PropsSettings;
import appline.utils.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import java.util.Properties;

public class Hooks {

    public static BasePage basePage;

    @Before
    public void start() {
        Properties properties = PropsSettings.getInstance().getProperties();
        DriverManager.init();
        DriverManager.getSite(properties.getProperty("url"));
    }

    @After
    public void quit() {
        DriverManager.quit();
    }
}
