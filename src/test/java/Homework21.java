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
    public void renamePlaylist(){
        String updatedPlayPlaylistMsg = "Updated playlist \"Sample Edited Playlist.\"";
        provideEmail("ricardo.lu@testpro.io");
        providePassword("DVNeY4ER");
        clickOnLoginButton();
        doubleClickPlaylist();
        enterNewPlaylistName();
        Assert.assertEquals(getRenamePlaylistSuccessMsg(), updatedPlayPlaylistMsg);
    }

}
