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
    @FindBy(css = ".playlist:nth-child(3)")
    WebElement playlistElement;
    @FindBy(css = "[name='name']")
    WebElement playlistInputField;
    @FindBy(css = "img.avatar")
    WebElement userAvatarIcon;
    @FindBy(css = "div.success.show")
    WebElement notification;

    //need to implement the methods
    // page methods
    /*
    public WebElement getUserAvatar(){
        return findElement(userAvatarIcon);
    }*/
    public HomePage doubleClickPlaylist(){
        doubleClick(playlistElement);
        return this;
    }
    public HomePage enterNewPlaylistName(String someName){
        playlistInputField.sendKeys(Keys.chord(Keys.CONTROL, "A", Keys.BACK_SPACE));
        playlistInputField.sendKeys(someName);
        playlistInputField.sendKeys(Keys.ENTER);
        findElement(notification);
        return this;
    }
    public boolean isAvatarDisplayed(){
        return findElement(userAvatarIcon).isDisplayed();
    }
    public String getPlaylistName(){
        return findElement(playlistElement).getText();
    }
    /*
    public String getRenamePlaylistSuccessMsg(){
        WebElement myNotification = wait.until(ExpectedConditions.visibilityOfElementLocated(notification));
        return myNotification.getText();
    }*/

}
