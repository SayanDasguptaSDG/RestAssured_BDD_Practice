package stepDefinitions;

import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")
    public void beforeScenario() throws IOException {
        StepDefinition stepDefinition = new StepDefinition();

        if(StepDefinition.place_id == null) {
            stepDefinition.addPlacePayloadWith("Conan", "Spanish", "Asia");
            stepDefinition.user_calls_with_http_request("AddPlaceAPI", "POST");
            stepDefinition.verifyPlace_idCreatedMapsToUsing("Conan", "GetPlaceAPI");
        }
    }
}
