package samokat.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class YandexSamokatPage {
    //Кнопка "Заказать" вверху страницы
    private final By orderButton1 = By.className("Button_Button__ra12g");
    //Кнопка "Заказать" внизу страницы
    private final By orderButton2 = By.cssSelector(".Button_Button__ra12g.Button_Middle__1CSJM");
    WebDriver driver;

    public YandexSamokatPage(WebDriver driver) {
        this.driver = driver;
    }

    // метод скролаа до списка вопросов
    public void scrollPageToQuestionList(String url) {
        var accordion = driver.findElement(By.className("accordion"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", accordion);
    }

    public void clickOrderButton1() {
        driver.findElement(orderButton1).click();
    }

    public void clickOrderButton2() {
        driver.findElement(orderButton2).click();
    }
}
