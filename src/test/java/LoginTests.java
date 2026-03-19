import org.testng.Assert;
import org.testng.annotations.Test;
import pagefactory.HomePage;
import pagefactory.LoginPage;

public class LoginTests extends BaseTest {
    @Test
    public void loginEmptyEmailPassword() throws InterruptedException {
        //create fork
        LoginPage loginPage = new LoginPage(getDriver());
        HomePage homePage = new HomePage(getDriver());
        loginPage.login("ricardo.lu@testpro.io", "");
        //navigatePage();
        //provideEmail("carina@testpro.io");
        //providePassword("qatester");
        //providePassword("");
        //Thread.sleep(2000);
        //clickOnLoginButton();


        // TODO (for students): Review the configuration as part of HW15


        Assert.assertFalse(homePage.isAvatarDisplayed());
        //Assert.assertEquals(driver.getCurrentUrl(), url);
    }

}
