package samokat.yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class WhoIsCustomerPage {
    private final By name = By.xpath("//input[@placeholder='* Имя']");
    private final By surname = By.xpath("//input[@placeholder='* Фамилия']");
    private final By address = By.xpath("//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metro = By.xpath("//input[@placeholder='* Станция метро']");
    private final By phone = By.xpath("//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//div[@class='Order_NextButton__1_rCA']/button");
    private final WebDriver driver;

    public WhoIsCustomerPage(WebDriver driver) {
        this.driver = driver;
    }

    public void setName(String value) {
        driver.findElement(name).sendKeys(value);
    }

    public void setSurname(String value) {
        driver.findElement(surname).sendKeys(value);
    }

    public void setAddress(String value) {
        driver.findElement(address).sendKeys(value);
    }

    public void setMetro(String value) {
        WebElement element = driver.findElement(metro);
        element.sendKeys(value);
        element.sendKeys(Keys.DOWN, Keys.ENTER);
    }

    public void setPhone(String value) {
        driver.findElement(phone).sendKeys(value);
    }

    public void whoIsCustomerFormSet(String name, String surname, String address, String metro, String phone) {
        setName(name);
        setSurname(surname);
        setAddress(address);
        setMetro(metro);
        setPhone(phone);
        driver.findElement(nextButton).click();
    }


}
