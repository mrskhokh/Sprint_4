import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

abstract class AbstractTest {

    public enum Browser {
        FF,
        CHROME
    }

    protected WebDriver getDriver(Browser browser) {
        final WebDriver result;
        switch (browser) {
            case FF: {
                FirefoxOptions options = new FirefoxOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                result = new FirefoxDriver(options);
                break;
            }
            case CHROME: {
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
                result = new ChromeDriver(options);
                break;
            }
            default:
                throw new UnsupportedOperationException("Unsupported browser type");
        }
        return result;
    }
}