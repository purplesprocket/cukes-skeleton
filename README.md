## Requirements

maven
java 8

## Building

```

export JAVA_HOME=`/usr/libexec/java_home -v 1.8`

mvn clean install
```


## To run tests:
```
mvn clean test
```

## Sample output:
```

  @Automated @P1
  Scenario: 7 - Response should have correct result corresponding to "bounds" parameter # Geocoding_JSONResponse.feature:64
    Given request for json output was sent with parameters:                             # RequestsSteps.sendRequestWithParameters(String,String>)
    Then response should have valid JSON scheme                                         # ResponseSteps.validateJSONScheme()
    And json response should have location coordinates 42.10808340000001, -87.735895    # ResponseSteps.validateCoordinates(String,String)

11 Scenarios (11 passed)
32 Steps (32 passed)
0m1.914s

Tests run: 43, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 2.07 sec - in Scenario: 7 - Response should have correct result corresponding to "bounds" parameter
```

