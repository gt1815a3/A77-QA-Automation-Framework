package pagefactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage{

    @FindBy(css = "input[type='email']")
    WebElement emailField;
    @FindBy(css = "input[type='password']")
    WebElement passwordField;
    @FindBy(css = "button[type='submit']")
    WebElement submitBtn;
    public LoginPage(WebDriver givenDriver){
        super(givenDriver);
    }
    public LoginPage provideEmail(String email){
        emailField.sendKeys(email);
        return this;
    }
    public LoginPage providePassword(String password){
        passwordField.sendKeys(password);
        return this;
    }
    public LoginPage clickSubmitBtn(){
        submitBtn.click();
        return this;
    }
    public LoginPage login(String email, String password) {
        provideEmail(email);
        providePassword(password);
        clickSubmitBtn();
        return this;
    }
}

