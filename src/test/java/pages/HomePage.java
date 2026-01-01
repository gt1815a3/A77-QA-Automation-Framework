package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{
    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }
    // locators
    By playlistElement = By.xpath("//*[@id=\"playlists\"]/ul/li[3]");
    By playlistInputField = By.cssSelector("[name='name']");
    By userAvatarIcon = By.cssSelector("img.avatar");
    By notification = By.cssSelector("div.success.show");
    // page methods
    public WebElement getUserAvatar(){
        return findElement(userAvatarIcon);
    }
    public void doubleClickPlaylist(){
        WebElement plistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(playlistElement));
        actions.doubleClick(plistElement).perform();
    }
    public void enterNewPlaylistName(String somePlaylistname){

        WebElement myInputfield = wait.until(ExpectedConditions.visibilityOfElementLocated(playlistInputField));
        //Thread.sleep(5000);
        myInputfield.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        myInputfield.sendKeys(somePlaylistname);
        myInputfield.sendKeys(Keys.ENTER);
    }
    public String getRenamePlaylistSuccessMsg(){
        WebElement myNotification = wait.until(ExpectedConditions.visibilityOfElementLocated(notification));
        return myNotification.getText();
    }
}
