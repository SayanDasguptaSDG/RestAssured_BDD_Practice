package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resources.Resources;
import resources.TestdataBuild;
import resources.Utils;

import java.io.IOException;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class StepDefinition extends Utils {
    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    Response response;
    TestdataBuild testdataBuild = new TestdataBuild();
    Resources resources;
    static String place_id;

    /* To be used when Scenario is used, for Scenario Outline, need not be used
    @Given("Add place payload")
    public void add_place_payload() throws IOException {
        requestSpec = given().log().all().spec(requestSpecification())
                        .body(testdataBuild.addPlacePayload());
    }*/

    @Given("Add place payload with {string} {string} {string}")
    public void addPlacePayloadWith(String name, String language, String address) throws IOException {
        requestSpec = given().log().all().spec(requestSpecification())
                .body(testdataBuild.generateAddPlacePayload(name, language, address));
    }

    @When("user calls {string} with {string} HTTP request")
    public void user_calls_with_http_request(String resource, String httpMethod) {
        resources = Resources.valueOf(resource);
        responseSpec = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .expectContentType(ContentType.JSON)
                .build();

        if(httpMethod.equalsIgnoreCase("POST")) {
            response = requestSpec.when().post(resources.getResource());
        } else if(httpMethod.equalsIgnoreCase("GET")) {
            response = requestSpec.when().get(resources.getResource());
        }
    }
    @Then("the API call is successful with status code {int}")
    public void the_api_call_is_successful_with_status_code(Integer statusCode) {
        response = response.then().assertThat()
                .spec(responseSpec).extract().response();
        assertEquals((Integer)response.getStatusCode(), statusCode);
    }
    @Then("{string} in response body is {string}")
    public void in_response_body_is(String actualResult, String expectedResult) {
        assertEquals(getJsonPath(response, actualResult), expectedResult);;
    }

    @And("verify place_id created maps to {string} using {string}")
    public void verifyPlace_idCreatedMapsToUsing(String expectedName, String resource) throws IOException {
        place_id = getJsonPath(response, "place_id");
        requestSpec = given().log().all().spec(requestSpecification())
                .queryParam("place_id", place_id);
        user_calls_with_http_request(resource, "GET");
        assertEquals(getJsonPath(response, "name"), expectedName);
    }

    @Given("Delete Place payload")
    public void deletePlacePayload() throws IOException {
        requestSpec = given().log().all().spec(requestSpecification())
                .body(testdataBuild.generateDeletePlacePayload(place_id));
    }
}