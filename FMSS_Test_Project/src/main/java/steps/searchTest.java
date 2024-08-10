package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class searchTest {

    private WebDriver driver;
    private String enteredUsername;
    private String selectedRole;

    @Given("the user is logged into the OrangeHRM system")
    public void theUserIsLoggedIntoTheOrangeHRMSystem() {
        //Your should add your path
        System.setProperty("webdriver.chrome.driver", "C:/Users/Fatma Yıldırım/Desktop/chromedriver-win64/chromedriver-win64/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("admin123");
        driver.findElement(By.xpath("//button[@type='submit']")).click();
    }

    @And("the user is on the OrangeHRM Dashboard page")
    public void theUserIsOnTheOrangeHRMDashboardPage() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @When("the user clicks on admin button in the left menu")
    public void theUserClicksOnInTheLeftMenu() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item' and @href='/web/index.php/admin/viewAdminModule']")).click();
    }

    @And("the user is on the User Management page")
    public void theUserIsOnTheUserManagementPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/admin/viewSystemUsers");
    }

    @Then("the search button should be visible")
    public void theSearchButtonShouldBeVisible() {
        WebElement searchButton = driver.findElement(By.cssSelector("div.oxd-form-actions button[type='submit']"));
        assertTrue(searchButton.isDisplayed());
        driver.quit();
    }

    @When("the user selects a random role from the dropdown")
    public void theUserSelectsFromTheDropdown() {
        // Click on the Dropdown menu
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//div[@class='oxd-select-text oxd-select-text--active']")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Get dropdown options
        List<WebElement> options = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-v-957b4417='']//div[contains(@class, 'oxd-select-dropdown')]/div")));
        // Choosing a random option
        Random random = new Random();
        int randomIndex = random.nextInt(options.size());
        WebElement randomOption = options.get(randomIndex);
        selectedRole = randomOption.getText().trim(); // Seçilen rolü kaydet
        randomOption.click();
    }
    @Then("the roles in the result table should match the selected role")
    public void theRolesInTheResultTableShouldMatchTheSelectedRole() {

        List<WebElement> roleElements = driver.findElements(By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-cell' and @role='cell'][4]/div"));
        // Loop through the list to check the value of each role
        for (WebElement roleElement : roleElements) {
            String tableRole = roleElement.getText().trim();
            Assert.assertEquals("Role does not match!", selectedRole, tableRole);
        }
        driver.quit();
    }
    
    @Then("the user clicks on the search button")
    public void theUserClicksOnTheButton() {
        WebElement searchButton = driver.findElement(By.cssSelector("div.oxd-form-actions button[type='submit']"));
        searchButton.click();
    }

    @When("the user types {string} in the search input of the navigation menu")
    public void theUserTypesInTheSearchInputOfTheNavigationMenu(String searchTerm) {
        WebElement searchInput = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        searchInput.clear(); // Clearing existing text
        searchInput.sendKeys(searchTerm); // Writing new text
    }


    @Then("the user clicks on {string}")
    public void theUserClicksOn(String itemText) {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        WebElement item = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name' and text()='" + itemText + "']"));
        item.click();
    }

    @And("the user should be redirected to the {string} page")
    public void theUserShouldBeRedirectedToThePage(String expectedPageTitle) {
        WebElement headerElement = driver.findElement(By.cssSelector(".oxd-text--h6"));
        String actualHeaderText = headerElement.getText();
        Assert.assertEquals(expectedPageTitle, actualHeaderText);
        driver.quit();
    }

    @Then("the user enters username to the input field")
    public void theUserEntersInTheUsernameInputField() {
        enteredUsername = "username";
        WebElement usernameInput = driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@class='oxd-input oxd-input--active']"));
        usernameInput.sendKeys(enteredUsername);
    }
    @Then("the usernames in the result table should match the searched username")
    public void theUsernamesInTheResultTableShouldMatchTheSearchedUsername() {

        List<WebElement> usernameElements = driver.findElements(By.xpath("//div[@class='oxd-table-body']//div[@class='oxd-table-cell' and @role='cell'][2]/div"));
        for (WebElement usernameElement : usernameElements) {
            String tableUsername = usernameElement.getText().trim();
            Assert.assertEquals("Username does not match!", enteredUsername, tableUsername);
        }
        driver.quit();
    }

    @Then("the user enters employee name to the input field")
    public void theUserEntersEmployeeNameToTheInputField() {
        WebElement employeeNameInput = driver.findElement(By.xpath("//div[@class='oxd-grid-item oxd-grid-item--gutters']//input[@placeholder='Type for hints...']"));
        employeeNameInput.sendKeys("employeeName");
    }

    @Then("the user should see an invalid entry warning if no results are found")
    public void theUserShouldSeeAnInvalidEntryWarningIfNoResultsAreFound() {
        WebElement invalidMessage = driver.findElement(By.xpath("//span[@class='oxd-text oxd-text--span oxd-input-field-error-message oxd-input-group__message']"));
        String messageText = invalidMessage.getText();
        Assert.assertEquals("Invalid", messageText);
        driver.quit();
    }

    @When("the user clicks on the PIM button in the left menu")
    public void theUserClicksOnThePIMButtonInTheLeftMenu() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item' and @href='/web/index.php/pim/viewPimModule']")).click();
    }

    @And("the user is on the PIM page")
    public void theUserIsOnThePIMPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/pim/viewEmployeeList");
    }

    @Then("the user enters employeeId in the Employee ID input field")
    public void theUserEntersEmployeeIdInTheEmployeeIDInputField() {
        WebElement employeeIdInput = driver.findElement(By.xpath("//div[@data-v-957b4417='']//input[@class='oxd-input oxd-input--active']"));
        employeeIdInput.sendKeys("1");
    }

    @Then("the user should see a no results found message")
    public void theUserShouldSeeANoResultsFoundMessage() {
        WebElement noResultsMessage = driver.findElement(By.xpath("//span[text()='No Records Found']"));
        Assert.assertTrue(noResultsMessage.isDisplayed());
        driver.quit();
    }

    @When("the user clicks on the Time button in the left menu")
    public void theUserClicksOnTheTimeButtonInTheLeftMenu() {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//a[@class='oxd-main-menu-item' and @href='/web/index.php/time/viewTimeModule']")).click();
    }

    @And("the user is on the Employee Timesheet page")
    public void theUserIsOnTheEmployeeTimesheetPage() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/time/viewEmployeeTimesheet");
    }

    @Then("the user leaves the employee name input field empty")
    public void theUserLeavesTheEmployeeNameInputFieldEmpty() {
        WebElement employeeNameInput = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
        employeeNameInput.clear();
    }

    @And("the user clicks on the View button")
    public void theUserClicksOnTheViewButton() {
        driver.findElement(By.xpath("//button[@type='submit' and contains(@class, 'oxd-button--secondary')]")).click();
    }

    @Then("the user should see a required entry warning")
    public void theUserShouldSeeARequiredEntryWarning() {
        WebElement requiredWarning = driver.findElement(By.xpath("//span[text()='Required']"));
        Assert.assertTrue(requiredWarning.isDisplayed());
        driver.quit();
    }
}
