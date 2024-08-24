Feature: Validating Place API's

  Scenario Outline: Verify if Place is being successfully added using AddPlace API
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with POST HTTP request
    Then the API call is successful with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"

    Examples:
      | name | language | address |
      | AAhouse | English  | World Crosss Center |
      | BBHouse | Deutsch | Julicherstr. |