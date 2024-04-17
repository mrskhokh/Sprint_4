import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import samokat.yandex.YandexSamokatPage;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class DropDownListOrdersSectionTest extends AbstractTest {
    private String expectedQuestion;
    private String expectedAnswer;
    private String questionId;
    private String answerId;
    private Browser browser;
    WebDriver driver;

    public DropDownListOrdersSectionTest(Browser browser, String questionId, String answerId, String expectedQuestion, String expectedAnswer) {
        this.questionId = questionId;
        this.answerId = answerId;
        this.expectedQuestion = expectedQuestion;
        this.expectedAnswer = expectedAnswer;
        this.browser=browser;
    }

    @Parameterized.Parameters
    public static Object[][] getCredentials() {
        return new Object[][]{
                {Browser.FF,"accordion__heading-0", "accordion__panel-0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {Browser.FF,"accordion__heading-1", "accordion__panel-1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {Browser.FF,"accordion__heading-2", "accordion__panel-2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {Browser.FF,"accordion__heading-3", "accordion__panel-3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {Browser.FF,"accordion__heading-4", "accordion__panel-4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {Browser.FF,"accordion__heading-5", "accordion__panel-5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {Browser.FF,"accordion__heading-6", "accordion__panel-6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {Browser.FF,"accordion__heading-7", "accordion__panel-7", "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."},
                {Browser.CHROME,"accordion__heading-0", "accordion__panel-0", "Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {Browser.CHROME,"accordion__heading-1", "accordion__panel-1", "Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {Browser.CHROME,"accordion__heading-2", "accordion__panel-2", "Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {Browser.CHROME,"accordion__heading-3", "accordion__panel-3", "Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {Browser.CHROME,"accordion__heading-4", "accordion__panel-4", "Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {Browser.CHROME,"accordion__heading-5", "accordion__panel-5", "Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {Browser.CHROME,"accordion__heading-6", "accordion__panel-6", "Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {Browser.CHROME,"accordion__heading-7", "accordion__panel-7", "Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
        };
    }

    @Test
    public void checkQuestionToAnswerForOrdersSection() {
        driver = getDriver(browser);
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
