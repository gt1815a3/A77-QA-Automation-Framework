import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testing.CucumberOptions;
import io.cucumber.testing.TestNGCucumberRunner;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features/Login.features"}
)
public class CucumberRunner extends AbstractTestNgCucumberTests{
    private TestNGCucumberRunner testNGCucumberRunner;

    @BeforeClass(alwaysRun = true)
    public void setupCucumber(){
        testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
    }
    @DataProvider
    public Object[][] features(){
        return testNGCucumberRunner.providerScenarios();
    }
    @AfterClass(alwaysRun = true)
    public void tearDownClass(){
        testNGCucumberRunner.finish();
    }
}
