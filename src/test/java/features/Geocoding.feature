Feature: check Google Geocoding API (JSON response)

  @Automated
  Scenario: 1 - Response should have valid JSON scheme if output parameter is "json"
    Given request for json output was sent with parameters:
      |address   |1600 Amphitheatre Parkway, Mountain View, CA|
    Then response should have valid JSON scheme


  @Automated
  Scenario Outline: 2 - Response should have correct result corresponding to "address" parameter
    Given request for json output was sent with parameters:
      |address   |<address>|
    Then response should have valid JSON scheme
    And json response should have location coordinates <lat>, <lng>
    Examples:
    |address                                               |lat         | lng        |
    |1600 Amphitheatre Parkway, Mountain View, CA          |37.4224504  |-122.0840859|
    |1 Great Winchester St, London EC2N 2DB, United Kingdom|51.5163823  |-0.08452559999999999|

  @Automated
  Scenario Outline: 3 - Response should have correct result corresponding to "components" parameter
    Given request for json output was sent with parameters:
      |components|<component>|
    Then response should have valid JSON scheme
    And json response should have location coordinates <lat>, <lng>
    Examples:
    |component    |lat               | lng        |
    |country:ES  |40.46366700000001  |-3.74922    |
    |country:US  |37.09024           |-95.712891  |

  @Automated
  Scenario: 4 - Response should have correct result corresponding both to "address" and "components" parameter
    Given request for json output was sent with parameters:
      |components|country:US |
      |address   |1600 Amphitheatre Parkway, Mountain View, CA |
    Then response should have valid JSON scheme
    And json response should have location coordinates 37.4224504, -122.0840859