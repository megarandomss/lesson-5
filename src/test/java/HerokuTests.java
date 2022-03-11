import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HerokuTests {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get("https://the-internet.herokuapp.com/");
        driver.manage().window().maximize();

    }

    @Test
    public void checkCurrentUrl() throws InterruptedException {
        Assertions.assertEquals("https://the-internet.herokuapp.com/", driver.getCurrentUrl());
        Thread.sleep(2000);

    }

    @Test
    public void checkTitle() {
        Assertions.assertEquals("The Internet", driver.getTitle());
    }

    @Test
    public void authenticationTest() throws InterruptedException {
        String username = "tomsmith";
        String password = "SuperSecretPassword!";

        driver.findElement(By.xpath("//a[text()='Form Authentication']")).click();
        driver.findElement(By.id("username")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
        driver.findElement(By.className("radius")).click();

        WebElement successfulLoginMessageLabel = driver.findElement(By.id("flash"));
        String successfulLoginMessage = successfulLoginMessageLabel.getText();

        Thread.sleep(3000);

        Assertions.assertTrue(successfulLoginMessageLabel.isDisplayed());
        Assertions.assertTrue(successfulLoginMessage.contains("You logged into a secure area!"));


    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}
