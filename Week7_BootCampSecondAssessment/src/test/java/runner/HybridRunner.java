package runner;

import cucumber.api.CucumberOptions;

@CucumberOptions(features = "/Users/palanimohan/git/Week7_2ndAssessmentRep/Week7_BootCampSecondAssessment/resources/test/java/features/SecondAssessment.feature", glue = "stepDefinition", monochrome=true)
public class HybridRunner extends BaseClass {

}