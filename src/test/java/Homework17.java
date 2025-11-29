import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class Homework17 extends BaseTest{
    public String randomName;
    @Test
    public void addSongToPlaylist() throws InterruptedException {
        navigatePage();
        provideEmail("ricardo.lu@testpro.io");
        providePassword("DVNeY4ER");
        clickOnLoginButton();
        Thread.sleep(2000);
        EnterSongInField("Dark Days");
        Thread.sleep(2000);
        clickViewAllSongsBtn();
        Thread.sleep((2000));
        clickFirstSong();
        Thread.sleep((2000));
        clickAddTo();
        Thread.sleep((2000));
        enterRandomNamePlaylist();
        Thread.sleep((2000));

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
