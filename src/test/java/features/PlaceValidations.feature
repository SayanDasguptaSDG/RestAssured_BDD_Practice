Feature: Validating Place API's

  Scenario: Verify if Place is being successfully added using AddPlace API
    Given Add place payload
    When user calls "AddPlaceAPI" with POST HTTP request
    Then the API call is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"