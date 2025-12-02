import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.UUID;

public class BaseTest {
    public WebDriver driver;
    public String url;
    public String randomName;
    public String playListName = "Ricardo";

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
        url = BaseURL;
    }
    @AfterMethod
    public void closeBrowser(){
        driver.quit();
    }

    public void clickOnLoginButton() {
        WebElement loginButton = driver.findElement(By.xpath("//button[@type='submit']"));
        loginButton.click();
    }

    public void providePassword(String password) {
        WebElement passwordField = driver.findElement(By.xpath("//input[@type='password']"));
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys(password);
    }

    public void provideEmail(String email) {
        WebElement emailField = driver.findElement(By.xpath("//input[@type='email']"));
        emailField.click();
        emailField.clear();
        emailField.sendKeys(email);
    }

    public void navigatePage() {
        driver.get(url);
    }

    public String generateRandomName() {
        //generate random ID, convert it to string, then remove the "-", no spaces
        return UUID.randomUUID().toString().replace("-", "").substring(0,5);
    }



    public String getAddToPlayListSuccessMsg() {
        WebElement notifyMessage = driver.findElement(By.xpath("//div[@class='success show']"));
        return notifyMessage.getText();
    }

    public void enterRandomNamePlaylist() {
        WebElement playListName = driver.findElement(By.xpath("//*[@id='songResultsWrapper']//input[@type='text']"));
        playListName.clear();
        randomName = generateRandomName();
        playListName.sendKeys(randomName);
        WebElement playListNameEnterBtn = driver.findElement(By.xpath("//*[@id='songResultsWrapper']//button[@type='submit']"));
        playListNameEnterBtn.click();
    }

    public void clickAddTo() {
        WebElement firstSong = driver.findElement(By.xpath("//*[@id=\"songResultsWrapper\"]/header/div[3]/span/button[2]"));
        firstSong.click();
    }

    public void clickFirstSong() {
        WebElement firstSong = driver.findElement(By.xpath("//*[@id=\"songResultsWrapper\"]/div/div/div[1]/table/tr[1]/td[2]"));
        firstSong.click();
    }

    public void clickViewAllSongsBtn() {
        WebElement viewAll = driver.findElement(By.xpath("//*[@id=\"searchExcerptsWrapper\"]/div/div/section[1]/h1/button"));
        viewAll.click();
    }

    public void EnterSongInField(String song) {
        WebElement searchSongField = driver.findElement(By.xpath("//input[@type='search']"));
        searchSongField.clear();
        searchSongField.sendKeys(song);
    }
}
