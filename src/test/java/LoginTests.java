import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    @Test
    public void loginEmptyEmailPassword() throws InterruptedException {
        //create fork
        navigatePage();
        provideEmail("carina@testpro.io");
        //providePassword("qatester");
        providePassword("");
        Thread.sleep(2000);
        clickOnLoginButton();


        // TODO (for students): Review the configuration as part of HW15



        Assert.assertEquals(driver.getCurrentUrl(), url);
    }

}
