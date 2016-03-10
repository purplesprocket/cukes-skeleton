Feature: check Google Geocoding API (JSON response)

  #output type json
  @Automated
  @P1
  Scenario: 1 - Response should have valid JSON scheme if output parameter is "json"
    Given request for json output was sent with parameters:
      |address   |1600 Amphitheatre Parkway, Mountain View, CA|
    Then response should have valid JSON scheme


  #required parameters
  @Automated
  @P1
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
  @P1
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
  @P1
  Scenario: 4 - Response should have correct result corresponding both to "address" and "components" parameter
    Given request for json output was sent with parameters:
      |components|country:US |
      |address   |1600 Amphitheatre Parkway, Mountain View, CA |
    Then response should have valid JSON scheme
    And json response should have location coordinates 37.4224504, -122.0840859


  @Automated
  @P2
  Scenario Outline: 5 - Response should have correct result if components parameter was passed in different styles
    Given request for json output was sent with parameters:
      |components   |<component>|
    Then response should have valid JSON scheme
    And json response should have location coordinates <lat>, <lng>
    Examples:
      |component                  |lat                 | lng               |
      |country:US                 | 37.09024           |  -95.712891       |
      |route:Amphitheatre Parkway |37.4232195          |-122.0853939       |
      |locality:Winnetka          |42.10808340000001   | -87.735895        |
      |administrative_area:Illinois| 40.6331249        | -89.3985283       |

  #optional parameters
  @Automated
  @P1
  Scenario: 7 - Response should have correct result corresponding to "bounds" parameter
    Given request for json output was sent with parameters:
      |address   |Winnetka                                    |
      |bounds    |34.172684,-118.604794\|34.236144,-118.500938&|
    Then response should have valid JSON scheme
    And json response should have location coordinates 42.10808340000001, -87.735895