import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class Homework21 extends BaseTest{
    //At least one user-created playlist
    String newPlaylistName = "Sample Edited Playlist";

    @Test
    public void renamePlaylist() throws InterruptedException {
        String updatedPlayPlaylistMsg = "Updated playlist \"Sample Edited Playlist.\"";
        provideEmail("ricardo.lu@testpro.io");
        providePassword("DVNeY4ER");
        clickOnLoginButton();
        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), updatedPlayPlaylistMsg);
    }
    public void doubleClickPlaylist()  {
        //WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".playlist:nth-child(3)")));
        //actions.doubleClick(playlistElement).perform();
        WebElement playlistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"playlists\"]/ul/li[3]")));
        actions.doubleClick(playlistElement).perform();

        //*[@id="playlists"]/ul/li[3]/a/text()
    }
    public void enterNewPlaylistName() throws InterruptedException {
        WebElement playlistInputField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name='name']")));
        Thread.sleep(5000);
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(newPlaylistName);
        playlistInputField.sendKeys(Keys.ENTER);
    }
    public String getRenamePlaylistSuccessMsg(){
        WebElement notification = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.success.show")));
        return notification.getText();
    }

}
