import com.fasterxml.jackson.databind.ser.Serializers;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.beans.DesignMode;
import java.net.MalformedURLException;
import java.net.URI;
import java.time.Duration;
import java.util.HashMap;
import java.util.UUID;


public class BaseTest {
    public static WebDriver driver = null;
    public static String url = null;
    public String randomName;
    public String playListName = "Ricardo";
    public static WebDriverWait wait = null;
    public static Wait<WebDriver> fluentWait;
    public static Actions actions = null;
    public static final ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();

    public static WebDriver getDriver(){
        return threadDriver.get();
    }

    @BeforeSuite
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }
    @BeforeMethod
    @Parameters({"BaseURL"})
    public void setupBrowser(String BaseURL) throws MalformedURLException {
        threadDriver.set(pickBrowser(System.getProperty("browser")));
        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        getDriver().manage().window().maximize();
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
        fluentWait = new FluentWait<WebDriver>(getDriver()).withTimeout(Duration.ofSeconds(5)).pollingEvery(Duration.ofMillis(200));
        actions = new Actions(getDriver());
        url = BaseURL;
        navigatePage();
    }
    //single thread
    public void launchBrowser(String BaseURL) throws MalformedURLException {
        /*ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get(BaseURL);
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        url = BaseURL;
         */
        driver = pickBrowser(System.getProperty("browser"));

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get(BaseURL);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        actions = new Actions(driver);
        url = BaseURL;

    }
    @AfterMethod
    public void TearDown(){
        threadDriver.get().close();
        threadDriver.remove();
    }
    public void closeBrowser(){
        driver.quit();
    }
    public void navigateToPage(){
        getDriver().get(url);
    }
    public static WebDriver pickBrowser(String browser) throws MalformedURLException {
        DesiredCapabilities caps = new DesiredCapabilities();
        String gridUrl = "http://192.168.1.197:4444/";

        switch(browser){
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return driver = new FirefoxDriver();
            case "safari":
                WebDriverManager.safaridriver().setup();
                return driver = new SafariDriver();
            case "grid-chrome":
                caps.setCapability("browserName", "chrome");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
            case "grid-firefox":
                caps.setCapability("browserName", "firefox");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
            case "grid-safari":
                caps.setCapability("browserName", "safari");
                return driver = new RemoteWebDriver(URI.create(gridUrl).toURL(), caps);
            case "cloud-chrome":
                return lambdaTest();
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-notifications");
                return driver = new ChromeDriver(chromeOptions);
        }
    }
    public static WebDriver lambdaTest() throws MalformedURLException{
        String hubURL =  "https://hub.lambdatest.com/wd/hub";
        ChromeOptions browserOptions = new ChromeOptions();
        browserOptions.setPlatformName("Windows 10");
        browserOptions.setBrowserVersion("dev");
        HashMap<String, Object> ltOptions = new HashMap<String, Object>();
        ltOptions.put("username", "ricardolu");
        ltOptions.put("accessKey", "LT_riyIh9qUQ20zYTOrT4FChUlymjL1BQhdiwQGbSR1rogfRCJ");
        ltOptions.put("build", "Selenium 4");
        ltOptions.put("project", "Untitled");
        ltOptions.put("selenium_version", "4.0.0");
        ltOptions.put("w3c", true);
        ltOptions.put("name", this.getClass().getName());
        ltOptions.put("plugin", "java-testNG");
        browserOptions.setCapability("LT:Options", ltOptions);
        return new RemoteWebDriver(URI.create(hubURL).toURL(), browserOptions);
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
