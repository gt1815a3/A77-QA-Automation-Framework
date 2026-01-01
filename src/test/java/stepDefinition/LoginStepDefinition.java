package stepDefinition;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;

import java.time.Duration;

public class LoginStepDefinition {
    WebDriver driver;
    WebDriverWait wait;

    @Before
    public void iOpenBrowser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @After
    public void closeBrowser(){
        driver.quit();
    }

    @Given("I open Koel Login Page")
    public void iOpenKoelLoginPage(){
        driver.get("https://qa.koel.app/");
    }
    @When("I enter email {string}")
    public void iEnterEmail(String){
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email]"))).clear();
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='email"))).sendKeys(email);
    }
    @And("I enter password {string}")
    public void iEnterPassword(String password){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']"))).clear();
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='password']"))).sendKeys(password);
    }
    @And("I click submit")
    public void iClickSubmit(){
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[type='submit']"))).click();
    }
    @Then("I am logged in")
    public void iAmLoggedIn(){
        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img.avatar"))).isDisplayed());
    }

}
