import io.github.bonigarcia.wdm.WebDriverManager;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class Homework {

    private WebDriver driver;
    private File file = new File("src/img.jpg");;

    @BeforeEach
    public void setUp() {

        String browser = System.getProperty("browser");

        if (browser.equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equals("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equals("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get("https://demoqa.com/automation-practice-form");
        driver.manage().window().maximize();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void checkCurrentUrl() throws InterruptedException {

        driver.getCurrentUrl();
        Assertions.assertEquals("https://demoqa.com/automation-practice-form", driver.getCurrentUrl());
        Thread.sleep(2000);
    }

    @Test
    public void checkTitle() {
        driver.getTitle();
        Assertions.assertEquals("ToolsQA", driver.getTitle());
    }

    @Test
    public void mainTest() throws InterruptedException {
        String firstName = "Andrey";
        String lastName = "Makovey";
        String email = "test@mail.ru";
        String gender = "Male";
        String mobile = "1234567890";
        String birthday = "01 January,1990";
        String address = "NSO 123, Berdsk 456";
        String subjectFirst = "Maths";
        String subjectSecond = "Physics";
        String hobbyFirst = "Reading";
        String hobbySecond = "Music";
        String picture = "img.jpg";
        String studentsState = "NCR";
        String studentsCity = "Noida";


        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);
        driver.findElement(By.id("userEmail")).sendKeys(email);

        driver.findElement(By.xpath("//*[@id='genterWrapper']/div[2]/div[1]")).click();

        driver.findElement(By.id("userNumber")).sendKeys(mobile);

        driver.findElement(By.id("dateOfBirthInput")).click();
        WebElement year = driver.findElement(By.xpath("//*[@id='dateOfBirth']//select[@class = 'react-datepicker__year-select']"));
        Select selectYear = new Select(year);
        WebElement month = driver.findElement(By.xpath("//*[@id='dateOfBirth']//select[@class = 'react-datepicker__month-select']"));
        Select selectMonth = new Select(month);
        selectYear.selectByValue("1990");
        selectMonth.selectByValue("0");
        driver.findElement(By.xpath("//*[@id='dateOfBirth']//div[@aria-label='Choose Monday, January 1st, 1990']")).click();

        WebElement subjects = driver.findElement(By.id("subjectsInput"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", subjects);
        subjects.sendKeys(subjectFirst);
        subjects.sendKeys(Keys.ENTER);
        subjects.sendKeys(subjectSecond);
        subjects.sendKeys(Keys.ENTER);

        driver.findElement(By.cssSelector("[for='hobbies-checkbox-2']")).click();
        driver.findElement(By.cssSelector("[for='hobbies-checkbox-3']")).click();

        WebElement selectPictureButton = driver.findElement(By.id("uploadPicture"));
        selectPictureButton.sendKeys(file.getAbsolutePath());

        driver.findElement(By.id("currentAddress")).sendKeys(address);

        WebElement state = driver.findElement(By.id("react-select-3-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", state);
        state.sendKeys(studentsState);
        state.sendKeys(Keys.ENTER);

        WebElement city = driver.findElement(By.id("react-select-4-input"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", city);
        city.sendKeys(studentsCity);
        city.sendKeys(Keys.ENTER);

        WebElement submitButton = driver.findElement(By.id("submit"));

        submitButton.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        SoftAssertions softAssert = new SoftAssertions();

        softAssert.assertThat(driver.findElement(By.id("example-modal-sizes-title-lg")).getText()).isEqualTo("Thanks for submitting the form");
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[1]/td[2]")).getText()).isEqualTo(firstName + " " + lastName);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[2]/td[2]")).getText()).isEqualTo(email);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[3]/td[2]")).getText()).isEqualTo(gender);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[4]/td[2]")).getText()).isEqualTo(mobile);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[5]/td[2]")).getText()).isEqualTo(birthday);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[6]/td[2]")).getText()).isEqualTo(subjectFirst + ", " + subjectSecond);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[7]/td[2]")).getText()).isEqualTo(hobbyFirst + ", " + hobbySecond);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[8]/td[2]")).getText()).isEqualTo(picture);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[9]/td[2]")).getText()).isEqualTo(address);
        softAssert.assertThat(driver.findElement(By.xpath("//tbody/tr[10]/td[2]")).getText()).isEqualTo(studentsState + " " + studentsCity);

        softAssert.assertAll();


    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}

