import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//Класс страницы "Про Аренду"
public class AboutOrderPage {
    private final WebDriver driver;
    // Срок аренды поле
    private final By whenOrderStarts = By.xpath("//input[@placeholder='* Когда привезти самокат']");
    LocalDate currentDate = LocalDate.now();
    String formattedDate = currentDate.format(DateTimeFormatter.ofPattern("dd"));

    //Текущий день календаря
    private final By calendarDay = By.xpath("//div[contains(@class, 'react-datepicker__day--0" + formattedDate + "')]");

    // Количество дней аренды
    private final By days = By.xpath("//div[@class='Dropdown-placeholder' and text()='* Срок аренды']");
    // поле "Комментарий"
    private final By comment = By.xpath("//input[@placeholder='Комментарий для курьера']");

    private final By orderButton=By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM']");

    private final By confirmButton=By.xpath("//button[text()='Да']");
    AboutOrderPage(WebDriver driver){
        this.driver=driver;
    }

    // метод установки даты
    public void setWhenOrderStarts (String date){
        driver.findElement(whenOrderStarts).click();
        driver.findElement(calendarDay).click();
    }
    // метод установки цвета
    public void setColor (String color){
        driver.findElement(By.xpath("//input[@id='"+color+"']")).click();
    }
    // метод установки количества дней
    public void setDays(String value){
        driver.findElement(days).click();

        //driver.findElement(By.xpath("/html/body/div/div/div[2]/div[2]/div[2]/div[2]/div[2]")).click();
        driver.findElement(By.xpath("//div[@class='Dropdown-menu']/div[@class='Dropdown-option' and text()='"+value+"']")).click();
    }
    // метод установки комментария
    public void setComment(String value){
        driver.findElement(comment).sendKeys(value);
    }
    public void setAllFieldsAndOrder(String date, String days, String color, String comment){
        setWhenOrderStarts(date);
        setDays(days);
        setColor(color);
        setComment(comment);
        driver.findElement(orderButton).click();

        driver.findElement(confirmButton).click();
    }

}
