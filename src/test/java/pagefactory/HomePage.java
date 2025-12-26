package pagefactory;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage{
    public HomePage(WebDriver givenDriver){
        super(givenDriver);
    }

    // locators
    @FindBy(xpath="/*[@id=\"playlists\"]/ul/li[3]")
    WebElement playlistElement;
    @FindBy(css = "[name='name']")
    WebElement playlistInputField;
    @FindBy(css = "img.avatar")
    WebElement userAvatarIcon;
    @FindBy(css = "div.success.show")
    WebElement notification;

    //need to implement the methods
    // page methods
    public WebElement getUserAvatar(){
        return findElement(userAvatarIcon);
    }
    public HomePage doubleClickPlaylist(){
        WebElement plistElement = wait.until(ExpectedConditions.visibilityOfElementLocated(playlistElement));
        actions.doubleClick(plistElement).perform();
        return this;
    }
    public HomePage enterNewPlaylistName(String somePlaylistname){
        myInputfield.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        myInputfield.sendKeys(somePlaylistname);
        myInputfield.sendKeys(Keys.ENTER);
        return this;
    }
    public String getRenamePlaylistSuccessMsg(){
        WebElement myNotification = wait.until(ExpectedConditions.visibilityOfElementLocated(notification));
        return myNotification.getText();
    }

}
