import org.testng.Assert;
import org.testng.annotations.Test;

public class Homework17 extends BaseTest{
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
        getAddToPlayListSuccessMsg();
        Thread.sleep(2000);
        Assert.assertTrue(getAddToPlayListSuccessMsg().contains(randomName));
    }
}
