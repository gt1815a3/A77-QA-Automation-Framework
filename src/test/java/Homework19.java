import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class Homework19 extends BaseTest{
    @Test
    public void deletePlayList() throws InterruptedException {
        navigatePage();
        provideEmail("ricardo.lu@testpro.io");
        providePassword("DVNeY4ER");
        clickOnLoginButton();
        Thread.sleep(2000);
        checkPlayListExits();
}
    public boolean isElementPresent(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty();
    }
    public void checkPlayListExits() throws InterruptedException {
    //check to see if a playlist named Ricardo exits
        By elementLocator = By.xpath("/*[@id=\"playlists\"]/ul//*[contains(text(), 'Ricardo')]");
        if (isElementPresent(elementLocator )){
           //it exits so click on it
            System.out.println("List already exits");
            WebElement playList = driver.findElement(By.xpath("/*[@id=\"playlists\"]/ul//*[contains(text(), 'Ricardo')]"));
            playList.click();
        }else {
            //play list does not exist so add it and click on it
            System.out.println("Playlist does not exist");
            WebElement addPlayListBtn = driver.findElement(By.xpath("//*[@id=\"playlists\"]/h1/i[@title='Create a new playlist']"));
            addPlayListBtn.click();
            Thread.sleep(2000);
            WebElement newPlayListBtn = driver.findElement(By.xpath("//*[@id=\"playlists\"]/nav/ul/li[@data-testid='playlist-context-menu-create-simple']"));
            newPlayListBtn.click();
            Thread.sleep(2000);
            WebElement newPlayListNameEntryBox = driver.findElement(By.xpath("//*[@id=\"playlists\"]//input"));
            newPlayListNameEntryBox.sendKeys("Ricardo");
            Thread.sleep(2000);
            newPlayListNameEntryBox.sendKeys(Keys.ENTER);
            Thread.sleep(2000);
            WebElement deletePlayListBtn = driver.findElement(By.xpath("//*[@id=\"playlistWrapper\"]//button[@title='Delete this playlist']"));
            deletePlayListBtn.click();
        }//end else

        } //end deletePlaylist
    } //end class
