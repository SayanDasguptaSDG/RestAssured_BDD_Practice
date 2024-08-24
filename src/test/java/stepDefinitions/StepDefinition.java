package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.TestdataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    Response response;
    JsonPath js;
    TestdataBuild testdataBuild = new TestdataBuild();

    @Given("Add place payload")
    public void add_place_payload() throws IOException {
        requestSpec = given().log().all().spec(requestSpecification())
                        .body(testdataBuild.addPlacePayload());
    }

    @When("user calls {string} with POST HTTP request")
    public void user_calls_with_post_http_request(String path) {
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();
        response = requestSpec.when().post("/maps/api/place/add/json")
                .then().assertThat().spec(responseSpec).extract().response();
    }
    @Then("the API call is successful with status code {int}")
    public void the_api_call_is_successful_with_status_code(Integer expectedResult) {
        assertEquals((Integer)response.getStatusCode(), (Integer)200);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String actualResult, String expectedResult) {
        String responseStr = response.asString();
        js = new JsonPath(responseStr);
        assertEquals(js.get(actualResult).toString(), expectedResult);;
    }

}
