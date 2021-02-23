package runner;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = "/Users/palanimohan/New Workspace/Week7_BootCampSecondAssessment/resources/test/java/features/SecondAssessment.feature",
				glue = "stepDefinition")
public class HybridRunner extends BaseClass {

}
