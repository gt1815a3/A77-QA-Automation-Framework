import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.UUID;

public class BaseTest {
    public WebDriver driver;
    public String url;
    public String randomName;
    public String playListName = "Ricardo";
    public WebDriverWait wait;

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    @Parameters({"BaseURL"})
    public void launchBrowser(String BaseURL){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        //options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        url = BaseURL;
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    public void clickOnLoginButton() {
        //WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
    }

    public void providePassword(String password) {
        //WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']"))).click();
        //passwordField.click();
        //passwordField.clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']"))).clear();
        //passwordField.sendKeys(password);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='password']"))).sendKeys(password);
    }

    public void provideEmail(String email) {
        //WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']"))).click();
        //emailField.click();
        //emailField.clear();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']"))).clear();
        //emailField.sendKeys(email);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='email']"))).sendKeys(email);
    }

    public void navigatePage() {
        driver.get(url);
    }

    public String generateRandomName() {
        //generate random ID, convert it to string, then remove the "-", no spaces
        return UUID.randomUUID().toString().replace("-", "").substring(0,5);
    }



    public String getAddToPlayListSuccessMsg() {
        WebElement notifyMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='success show']")));
        return notifyMessage.getText();
    }

    public void enterRandomNamePlaylist() {
        //WebElement playListName = driver.findElement(By.xpath("//*[@id='songResultsWrapper']//input[@type='text']"));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='songResultsWrapper']//input[@type='text']"))).clear();
        //playListName.clear();
        randomName = generateRandomName();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='songResultsWrapper']//input[@type='text']"))).sendKeys(randomName);
        //playListName.sendKeys(randomName);
        //WebElement playListNameEnterBtn = driver.findElement(By.xpath("//*[@id='songResultsWrapper']//button[@type='submit']"));
        //playListNameEnterBtn.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='songResultsWrapper']//button[@type='submit']"))).click();
    }

    public void clickAddTo() {
        //WebElement firstSong = driver.findElement(By.xpath("//*[@id=\"songResultsWrapper\"]/header/div[3]/span/button[2]"));
        //firstSong.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"songResultsWrapper\"]/header/div[3]/span/button[2]"))).click();
    }

    public void clickFirstSong() {
        //WebElement firstSong = driver.findElement(By.xpath("//*[@id=\"songResultsWrapper\"]/div/div/div[1]/table/tr[1]/td[2]"));
        //firstSong.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"songResultsWrapper\"]/div/div/div[1]/table/tr[1]/td[2]"))).click();
    }

    public void clickViewAllSongsBtn() {
        //WebElement viewAll = driver.findElement(By.xpath("//*[@id=\"searchExcerptsWrapper\"]/div/div/section[1]/h1/button"));
        //viewAll.click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"searchExcerptsWrapper\"]/div/div/section[1]/h1/button"))).click();
    }

    public void EnterSongInField(String song) {
        //WebElement searchSongField = driver.findElement(By.xpath("//input[@type='search']"));
        WebElement searchSongField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
        searchSongField.clear();
        searchSongField.sendKeys(song);
    }
}
