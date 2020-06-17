package appline.pages;

import appline.annotations.FieldName;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import static appline.utils.DriverManager.getDriver;

public class ItemPage extends BasePage {
    private final String switches = "//div[@value='%s']//div[@class='ui-at4']";
    private final String checkboxes = "//div[div[contains(text(), '%s')]]//span[contains(text(), '%s')]";
    private final String findCheckboxes = "(//div[div[contains(text(), '%s')]]//input)[1]";

    @FindBy(xpath = "(//input[@qa-id='range-to'])[1]")
    @FieldName(name = "максимальную цену")
    public WebElement maxValue;

    @FindBy(xpath = "(//span[contains(text(), 'Посмотреть все')])[1]")
    @FieldName(name = "Просмотреть все")
    public WebElement showUp;

    @FindBy(xpath = "//span[contains(text(),'Корзина')]")
    @FieldName(name = "корзину")
    public WebElement toCart;

    @FindBy(xpath = "//div[contains(@style,'grid-template-columns')]/div")
    public List<WebElement> items;

    public void setSwitch(String switchName) {
        By locator = By.xpath(String.format(switches, switchName));
        WebElement switchEl = getDriver().findElement(locator);
        moveToElement(switchEl);
        waitUntilClickable(switchEl).click();
        sleep(1000);
    }

    public void setCheckbox(String blockName, String checkboxName) {
        By locator = By.xpath(String.format(checkboxes, blockName, checkboxName));
        WebElement checkboxEl = getDriver().findElement(locator);
        moveToElement(checkboxEl);
        waitUntilClickable(checkboxEl).click();
        sleep(1000);
    }

    public void findCheckbox(String blockName, String checkboxName) {
        By locator = By.xpath(String.format(findCheckboxes, blockName));
        WebElement checkboxInput = getDriver().findElement(locator);
        moveToElement(checkboxInput);
        doubleClick(checkboxInput);
        waitUntilClickable(checkboxInput).sendKeys(checkboxName);
        try {
            setCheckbox(blockName, checkboxName);
        } catch (org.openqa.selenium.NoSuchElementException e) {
            e.printStackTrace();
        }
    }

    public void getOddEvenItems(int amount, int oddEven) {
        int index = 1;
        for (WebElement element : items) {
            if (index % 2 == oddEven) {
                getDriver().manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
                try {
                    WebElement addToCart = element.findElement(By.xpath(".//div[contains(text(),'В корзину')]"));
                    waitUntilClickable(addToCart).click();
                } catch (NoSuchElementException e) {
                    index++;
                    continue;
                }
                amount--;
            }

            if (amount == 0) {
                break;
            }
            index++;
        }
    }

    @Override
    public WebElement getField(String name) throws Exception {
        return getField(name, "appline.pages.ItemPage");
    }
}
