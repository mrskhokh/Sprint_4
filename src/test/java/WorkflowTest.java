import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import samokat.yandex.AboutOrderPage;
import samokat.yandex.WhoIsCustomerPage;
import samokat.yandex.YandexSamokatPage;

@RunWith(Parameterized.class)
public class WorkflowTest extends AbstractTest {
    private final Browser browser;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phone;
    private final String date;
    private final String days;
    private final String color;
    private final String comment;
    WebDriver driver;

    public WorkflowTest(Browser browser, String name, String surname, String address, String metro, String phone, String date, String days, String color, String comment) {
        this.browser = browser;
        this.address = address;
        this.color = color;
        this.comment = comment;
        this.date = date;
        this.metro = metro;
        this.days = days;
        this.surname = surname;
        this.name = name;
        this.phone = phone;

    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {Browser.FF, "Иванов", "Иван", "Ивановская область, ул Иванова 7 И", "Южная", "89277654501", "31.12.2024", "сутки", "black", "какой-то коммент"},
                {Browser.FF, "Петров", "Петр", "Ивановская область, ул Петрова 7 И", "Рос", "89277123123", "31.12.2024", "сутки", "grey", "какой-то коммент"},
                {Browser.FF, "Букин", "Иван", "Ивановская область, ул Иванова 7 И", "Б", "89277654501", "31.12.2024", "сутки", "black", "какой-то коммент"},
                {Browser.CHROME, "Иванов", "Иван", "Ивановская область, ул Иванова 7 И", "Южная", "89277654501", "31.12.2024", "сутки", "black", "какой-то коммент"},
                {Browser.CHROME, "Петров", "Петр", "Ивановская область, ул Петрова 7 И", "Рос", "89277123123", "31.12.2024", "сутки", "grey", "какой-то коммент"},
                {Browser.CHROME, "Букин", "Иван", "Ивановская область, ул Иванова 7 И", "Б", "89277654501", "31.12.2024", "сутки", "black", "какой-то коммент"}
        };

    }

    @Test
    public void checkWorkflow() {
        driver = getDriver(browser);
        // переход на страницу тестового приложения
        String url = "https://qa-scooter.praktikum-services.ru/";
        driver.get(url);
        //Создаем объект страницы Яндек Самокат
        YandexSamokatPage objYandexSamokatPage = new YandexSamokatPage(driver);
        // Нажимаем на кнопку "Заказать" вверху страницы
        objYandexSamokatPage.clickOrderButton1();
        //Создаем объект страницы данных о заказчике
        WhoIsCustomerPage objWhoIsCustomerPage = new WhoIsCustomerPage(driver);
        // Заполняем форму о заказчике данными из параметров
        objWhoIsCustomerPage.whoIsCustomerFormSet(name, surname, address, metro, phone);
        //Создаем объект страницы данных о заказе
        AboutOrderPage objAboutOrderPage = new AboutOrderPage(driver);
        //Заполняем форму страницы данными из параметрах
        objAboutOrderPage.setAllFieldsAndOrder(date, days, color, comment);
        try {
            Thread.sleep(1000); // Пауза на 5 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//div[@class='Order_ModalHeader__3FDaJ']"));
        System.out.println("Заказ успешно создан");
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}

