package appline.stepdefs;

import appline.pages.ItemPage;
import io.cucumber.java.en.When;

import static appline.stepdefs.Hooks.basePage;

public class ItemPageSteps {
    private final ItemPage itemPage = new ItemPage();

    @When("Устанавливаем {string} в {string}")
    public void setMaxValue(String name, String value) throws Exception {
        basePage = new ItemPage();
        basePage.setValueRange(name, value);
    }

    @When("Жмем на '{string}'")
    public void showMenu(String name) throws Exception {
        basePage = new ItemPage();
        basePage.click(name);
    }

    @When("В блоке '{string}' выбираем '{string}'")
    public void setCheckbox(String blockName, String checkboxName) {
        itemPage.setCheckbox(blockName, checkboxName);
    }

    @When("В блоке '{string}' находим и выбираем '{string}'")
    public void findCheckbox(String blockName, String checkboxName) {
        itemPage.findCheckbox(blockName, checkboxName);
    }

    @When("Выбираем '{string}'")
    public void setSwitch(String switchName) {
        itemPage.setSwitch(switchName);
    }

    @When("Выбираем {int} товаров. % 2 == {int}")
    public void getItems(int amount, int oddEven){
        itemPage.getOddEvenItems(amount, oddEven);
    }

    @When("Переходим в {string}")
    public void goToCart(String name) throws Exception {
        basePage = new ItemPage();
        basePage.click(name);
    }
}
