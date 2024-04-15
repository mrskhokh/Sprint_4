import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DropDownListOrdersSectionTest {
    private final String expectedQuestion;
    private final String expectedAnswer;
    private final String questionId;
    private final String answerId;
    WebDriver driver;

    public DropDownListOrdersSectionTest(String questionId, String answerId, String expectedQuestion, String expectedAnswer) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {"accordion__heading-0", "accordion__panel-0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"accordion__heading-1", "accordion__panel-1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"accordion__heading-2", "accordion__panel-2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"accordion__heading-3", "accordion__panel-3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"accordion__heading-4", "accordion__panel-4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"accordion__heading-5", "accordion__panel-5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"accordion__heading-6", "accordion__panel-6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"accordion__heading-7", "accordion__panel-7", "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void checkQuestionToAnswerForOrdersSectionChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        // переход на страницу тестового приложения
        String url = "https://qa-scooter.praktikum-services.ru/";
        driver.get(url);

        //Создаем объект страницы Яндек Самокат
        YandexSamokatPage objYandexSamokatPage = new YandexSamokatPage(driver);

        //Скроллируем страницу на секцию с вопросами
        objYandexSamokatPage.scrollPageToQuestionList(url);

        WebElement questionElement = driver.findElement(By.id(questionId));
        var questionText = questionElement.getText();
        // Сравниваем, что найденый элемент соответствует искомому по тексту вопроса
        assertEquals("Checking question text", expectedQuestion, questionText);

        questionElement.click();

        WebElement answerElement = driver.findElement(By.id(answerId));
        var answerText = answerElement.getText();
        assertEquals("Checking answer text", expectedAnswer, answerText);
    }

    @Test
    public void checkQuestionToAnswerForOrdersSectionFF() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");

        driver = new FirefoxDriver(options);
        // переход на страницу тестового приложения
        String url = "https://qa-scooter.praktikum-services.ru/";
        driver.get(url);

        //Создаем объект страницы Яндек Самокат
        YandexSamokatPage objYandexSamokatPage = new YandexSamokatPage(driver);

        //Скроллируем страницу на секцию с вопросами
        objYandexSamokatPage.scrollPageToQuestionList(url);

        WebElement questionElement = driver.findElement(By.id(questionId));
        var questionText = questionElement.getText();
        // Сравниваем, что найденый элемент соответствует искомому по тексту вопроса
        assertEquals("Checking question text", expectedQuestion, questionText);

        questionElement.click();

        WebElement answerElement = driver.findElement(By.id(answerId));
        var answerText = answerElement.getText();
        assertEquals("Checking answer text", expectedAnswer, answerText);
    }

    @After
    public void tearDown() {
        // Закрой браузер
        driver.quit();
    }
}
