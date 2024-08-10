package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;

public class loginTest {

    private WebDriver driver;

    @Given("The user navigates to the login page")
    public void navigateToLoginPage() {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Fatma Yıldırım/Desktop/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    @When("The user enters username {string} and password {string}")
    public void enterCredentials(String username, String password) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys(username);
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("The user should see an error message")
    public void verifyErrorMessage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        Assert.assertTrue(driver.findElement(By.xpath("//div[contains(@class, 'oxd-alert') and contains(@class, 'oxd-alert--error')]//p[contains(@class, 'oxd-text--p') and contains(@class, 'oxd-alert-content-text')]")).isDisplayed());
        driver.quit();
    }

    @When("The user enters valid credentials")
    public void enterValidCredentials() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @Then("The user should be redirected to the dashboard")
    public void verifyRedirectToDashboard() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        String expectedUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index";
        Assert.assertTrue(driver.getCurrentUrl().contains(expectedUrl));
        driver.quit();
    }

}
