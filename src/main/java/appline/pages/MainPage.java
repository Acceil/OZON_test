package appline.pages;


import appline.annotations.FieldName;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {
    @FindBy(xpath = "//input[@placeholder='Искать на Ozon']")
    @FieldName(name = "поисковую строку")
    public WebElement inputField;

    @FindBy(xpath = "//button[@type='submit']")
    @FieldName(name = "кнопку поиска")
    public WebElement findButton;

    @Override
    public WebElement getField(String name) throws Exception {
        return getField(name, "appline.pages.MainPage");
    }
}