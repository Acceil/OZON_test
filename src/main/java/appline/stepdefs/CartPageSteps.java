package appline.stepdefs;

import appline.pages.CartPage;
import appline.utils.VirtualCart;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;

import java.util.Map;

import static appline.stepdefs.Hooks.basePage;

public class CartPageSteps {
    private final CartPage cartPage = new CartPage();

    @When("Сохраняем название и цену товара")
    public void saveNameAndPrice() {
        cartPage.save();
    }

    @When("Проверяем наличие товаров в корзине")
    public void checkItemsExistence() {
        cartPage.checkItems(true);
    }

    @When("Проверяем наличие надписи '{string}'")
    public void checkText(String name) throws Exception {
        basePage = new CartPage();
        basePage.checkField(name);
    }

    @When("{string}")
    public void clearCart(String name) throws Exception {
        basePage = new CartPage();
        basePage.click(name);
    }

    @When("Проверяем отсутствие товаров в корзине")
    public void checkItemsLack() {
        cartPage.checkItems(false);
    }

    @Then("Наименование товаров и их цены")
    public void itemNameAndPrice() {
        StringBuilder list = new StringBuilder();
        for (Map.Entry<String, Integer> items : VirtualCart.getPrices().entrySet()) {
            list.append(items.getKey() + (items.getKey() + "\t\t" + items.getValue() + "\n"));
        }
        Allure.addAttachment("Товары и цены", list.toString());
        Allure.addAttachment("Товар с наивысшей ценой", String.valueOf(VirtualCart.getMaxPrice()));
        VirtualCart.getPrices().clear();
    }
}
