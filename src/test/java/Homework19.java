import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Homework19 extends BaseTest{
    @Test
    public void deletePlayList() throws InterruptedException {
        navigatePage();
        provideEmail("ricardo.lu@testpro.io");
        providePassword("DVNeY4ER");
        clickOnLoginButton();
        //Thread.sleep(2000);
        checkPlayListExits();
}
    public boolean isElementPresent(By locator) {
        List<WebElement> elements = driver.findElements(locator);
        return !elements.isEmpty();
    }
    public void checkPlayListExits() throws InterruptedException {
    //check to see if a playlist named Ricardo exits
        By elementLocator = By.xpath("//*[@id=\"playlists\"]/ul/li[4]/a[contains(text(), 'Ricardo')]");
        if (isElementPresent(elementLocator )){
           //it exists so click on it
            System.out.println("List already exits");
            //WebElement playList = driver.findElement(By.xpath("//*[@id=\"playlists\"]/ul/li[4]/a[contains(text(), 'Ricardo')]"));
            //playList.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"playlists\"]/ul/li[4]/a[contains(text(), 'Ricardo')]"))).click();
            //WebElement deletePlayListBtn = driver.findElement(By.xpath("//*[@id=\"playlistWrapper\"]//button[@title='Delete this playlist']"));
            //deletePlayListBtn.click();
            //click on delete playlist button
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"playlistWrapper\"]//button[@title='Delete this playlist']"))).click();
            //Thread.sleep(2000);
            String actualText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"success show\"]"))).getText();
            // WebElement successNotification = driver.findElement(By.xpath("//*[@class=\"success show\"]"));
            // String actualText = successNotification.getText();
            System.out.println("Actual text = "+ actualText);
            System.out.println("playListName = " + playListName);
            Assert.assertTrue(actualText.contains(playListName));
        } else {
            //play list does not exist so add it and click on it
            System.out.println("Playlist does not exist");
            //WebElement addPlayListBtn = driver.findElement(By.xpath("//*[@id=\"playlists\"]/h1/i[@title='Create a new playlist']"));
            //addPlayListBtn.click();
            //click on Add Playlist button
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"playlists\"]/h1/i[@title='Create a new playlist']"))).click();
            //Thread.sleep(2000);
            //WebElement newPlayListBtn = driver.findElement(By.xpath("//*[@id=\"playlists\"]/nav/ul/li[@data-testid='playlist-context-menu-create-simple']"));
            //newPlayListBtn.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"playlists\"]/nav/ul/li[@data-testid='playlist-context-menu-create-simple']"))).click();
            //Thread.sleep(2000);
            WebElement newPlayListNameEntryBox = driver.findElement(By.xpath("//*[@id=\"playlists\"]//input"));
            newPlayListNameEntryBox.sendKeys(playListName);
            //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"playlists\"]//input"))).sendKeys(playListName);
            //Thread.sleep(1000);
            newPlayListNameEntryBox.sendKeys(Keys.ENTER);
            //wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"playlists\"]//input"))).sendKeys(Keys.END);
            //Thread.sleep(5000);
            //WebElement deletePlayListBtn = driver.findElement(By.xpath("//*[@id=\"playlistWrapper\"]//button[@title='Delete this playlist']"));
            //deletePlayListBtn.click();
            //wait until the list added successfully message disappears
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class=\"success show\"]")));
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"playlistWrapper\"]//button[@title='Delete this playlist']"))).click();
            //Thread.sleep(2000);
            //WebElement successNotification = driver.findElement(By.xpath("//*[@class=\"success show\"]"));
            WebElement successNotification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class=\"success show\"]")));
            String actualText = successNotification.getText();
            Assert.assertTrue(actualText.contains(playListName));
        }//end else

        } //end deletePlaylist
    } //end class
