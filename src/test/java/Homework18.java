import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework18 extends BaseTest{
    @Test
    public void playSong() throws InterruptedException {
        navigatePage();
        provideEmail("ricardo.lu@testpro.io");
        providePassword("DVNeY4ER");
        clickOnLoginButton();
        Thread.sleep(2000);
        clickOnPlayNextSong();
        Thread.sleep(2000);
        clickPlayBtn();
        Thread.sleep(2000);
        verifySongIsPlaying();
    }

    public void verifySongIsPlaying() {
        WebElement pauseBtn = driver.findElement(By.xpath("//*[@id=\"mainFooter\"]/div[1]/span/span[@title=\"Pause\"]"));
        Assert.assertTrue(pauseBtn.isDisplayed());
    }

    public void clickPlayBtn() {
        WebElement playBtn = driver.findElement(By.xpath("//*[@id=\"mainFooter\"]/div[1]/span/span[2]"));
        playBtn.click();
    }

    public void clickOnPlayNextSong() {
        WebElement playNextSong = driver.findElement(By.xpath("//*[@id=\"mainFooter\"]/div[1]/i[2]"));
        playNextSong.click();
    }

}
