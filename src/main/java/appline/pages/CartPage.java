package appline.pages;

import appline.annotations.FieldName;
import appline.utils.VirtualCart;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

public class CartPage extends BasePage {
    @FindBy(xpath = "//span[@style='color: rgb(0, 26, 52);']")
    List<WebElement> items;

    @FindBy(xpath = "//span[contains(text(), 'Ваша корзина')]")
    @FieldName(name = "Ваша корзина")
    public WebElement textToCheck;

    @FindBy(xpath = " //span[contains(text(), 'товаров')]")
    @FieldName(name = "8 товаров")
    public WebElement textToCheck1;

    @FindBy(xpath = "//span[contains(text(), 'Удалить выбранные')]")
    @FieldName(name = "Очищаем корзину")
    public WebElement clearCart;

    @FindBy(xpath = "//span[contains(text(),'Корзина')]")
    WebElement toCart;

    @FindBy(xpath = "//div[contains(text(), 'Удалить')]")
    @FieldName(name = "Точно-преточно очищаем корзину")
    public WebElement certainlyClearCart;

    @FindBy(xpath = "(//div[@data-widget='container'])[1]//div[@data-widget='split']//label/../..")
    List<WebElement> products;

    public void checkItems(boolean assertion) {
        sleep(1000);
        items.forEach(element -> {
            if (assertion) {
                assertThat("Товаров нет", element.isDisplayed());
            }
            if (!assertion) {
                assertThat("Товары не были удалены", !element.isDisplayed());
            }
        });
    }

    public void save() {
        sleep(1000);
        products.forEach(item -> {
            String nameProduct = item.findElement(By.xpath(".//span[@style='color: rgb(0, 26, 52);']")).getText();
            Integer priceProduct = Integer.parseInt(item.findElement(By.xpath(
                    ".//div[@style='font-size: 15px; color: rgb(0, 26, 52); font-weight: bold;']//span[contains(text(), '₽')]"))
                    .getText().replaceAll(" ", "").replaceAll("₽", ""));
            VirtualCart.addToVirtualCart(nameProduct, priceProduct);
        });
    }


    @Override
    public WebElement getField(String name) throws Exception {
        return getField(name, "appline.pages.CartPage");
    }
}
