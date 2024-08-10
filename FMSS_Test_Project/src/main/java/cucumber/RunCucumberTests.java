package cucumber;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features",
        glue = "steps",
        tags = "@true_login or @false_login or @search_1 or @search_2 or @search_3 or @search_4 or @search_5 or @search_6 or @search_7 "
        //tags="@search_"
)
public class RunCucumberTests {
}
