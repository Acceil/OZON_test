
package appline.stepdefs;

import appline.pages.MainPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static appline.stepdefs.Hooks.basePage;

public class MainPageSteps {

    @When("Вбиваем в {string} товар {string}")
    public void fillField(String name, String value) throws Exception {
        basePage = new MainPage();
        basePage.inputValue(name, value);
    }

    @Then("Жмем {string}")
    public void findItem(String name) throws Exception {
        basePage = new MainPage();
        basePage.click(name);
    }
}
