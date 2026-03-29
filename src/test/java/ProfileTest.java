import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BaseTest{
    @Test
    public void updateProfileName() throws InterruptedException {
        navigatePage();
        provideEmail("carina@testpro.io");
        providePassword("qatester");
        clickOnLoginButton();
        Thread.sleep(2000);
        clickOnAvatarIcon();
        Thread.sleep(2000);
        String newName = generateRandomName();
        System.out.println("generate name: " + newName);
        provideCurrentPassword("qatester");
        provideNewProfileName(newName);
        clickSaveButton();
        Thread.sleep(2000);
        //Assertions
        WebElement actualProfileName = driver.findElement(By.xpath("//span[@class='name']"));
        Assert.assertEquals(actualProfileName.getText(), newName);
    }

    public void provideCurrentPassword(String password) {
        WebElement currentPass = driver.findElement(By.xpath("//input[@id='inputProfileCurrentPassword']"));
        currentPass.clear();
        currentPass.sendKeys(password);
    }

    public void clickSaveButton() {
        WebElement saveBtn = driver.findElement(By.xpath("//button[@class='btn-submit']"));
        saveBtn.click();

    }

    public void provideNewProfileName(String newName) {
        WebElement newNameField = driver.findElement(By.xpath("//input[@id='inputProfileName']"));
        newNameField.clear();
        newNameField.sendKeys(newName);
    }

    public void clickOnAvatarIcon() {
        WebElement avatarIcon = driver.findElement(By.xpath("//img[@class='avatar']"));
        avatarIcon.click();
    }
}
