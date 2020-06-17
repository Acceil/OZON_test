package appline.pages;

import appline.annotations.FieldName;
import appline.utils.DriverManager;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static appline.utils.DriverManager.getDriver;
import static org.hamcrest.MatcherAssert.assertThat;

public abstract class BasePage {
    WebDriverWait wait = DriverManager.webDriverWait;

    public BasePage() {
        PageFactory.initElements(getDriver(), this);
    }

    public WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void moveToElement(WebElement webElement) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(webElement).perform();
    }

    public void doubleClick(WebElement webElement) {
        Actions actions = new Actions(getDriver());
        actions.doubleClick(webElement).perform();
    }

    public void click(String name) throws Exception {
        WebElement element = getField(name);
        moveToElement(element);
        waitUntilClickable(element).click();
    }

    public void customWait(WebElement element, String value) {
        waitUntilVisible(element);
        wait.until(webDriver -> {
            String currentValue = element.getText();
            return !currentValue.equals(value);
        });
    }

    public void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void switchToFrame(WebElement element) {
        getDriver().switchTo().frame(element);
    }

    public void checkField(String name) throws Exception {
        WebElement element = getField(name);
        assertThat(String.format("Надпись '%s' отсутствует", name), element.isDisplayed());
    }

    public void inputValue(WebElement field, String value) {
        doubleClick(field);
        field.sendKeys(value);
    }

    public void inputValue(String name, String value) throws Exception {
        WebElement element = getField(name);
        inputValue(element, value);
    }

    public void setValueRange(String name, String value) throws Exception {
        WebElement element = getField(name);
        inputValue(element, value);
        element.sendKeys(Keys.ENTER);
        waitElementRefreshing(element);
    }

    public WebElement getField(String name, String className) throws Exception {
        Class example = Class.forName(className);
        List<Field> fields = Arrays.asList(example.getFields());
        for (Field field : fields) {
            if (field.getAnnotation(FieldName.class).name().equals(name)) {
                return DriverManager.getDriver().findElement(By.xpath(field.getAnnotation(FindBy.class).xpath()));
            }
        }
        Assert.fail("Не объявлен элемент с наименованием " + name);
        return null;
    }

    void waitElementRefreshing(WebElement webElement) {
        DriverManager.webDriverWait.until(ExpectedConditions.visibilityOf(webElement));
        WebDriverWait webDriverWait = new WebDriverWait(DriverManager.getDriver(), 10);
        webDriverWait.until(webDriver -> {
            String oldValue = webElement.getText();//берем старое значение целевого поля
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String newValue = webElement.getText();//получаем новое значение целевого поля
            return newValue.equals(oldValue);//если значение не равны ждем
        });
    }


    public abstract WebElement getField(String name) throws Exception;

}
