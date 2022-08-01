# Origin Backend Assignment

This application was developed using Java 11 (Oracle OpenJDK 11.0.2), Spring boot, Gradle, IntelliJ and guided by tests.

## How to run the application:

To be able to run this application you will need to have some Java 11 SDK installed.

In order to make it easy to run the Gradle tasks, the project contains a Makefile, so once you have your JDK configured,
you can enter `make run` to run the application on port `8080`, `make test` to run the unit and integration tests or
`make build` to build the application.

---

## Available endpoints:

### Base Rate:

- Method: `POST`
- URL: `/risk`
- Request body:
    - Age: an integer equal or greater than 0.
    - The number of dependents: an integer equal or greater than 0.
    - Income: an integer equal or greater than 0.
    - Marital status: "single" or "married".
    - Risk questions: an array with 3 booleans represented by 0 for "false" and 1 for "true".
    - House: an object containing just one attribute: `ownership_status`, which can be "owned" or "mortgaged". Expected
      to be null when the user doesn't have a house.
    - Vehicle: an object containing just one attribute: a positive integer corresponding to the `year` it was
      manufactured. Expected to be null when the user doesn't have a vehicle.

### Usage example:

Once the application is running, you can hit the `/risk` endpoint

    http://localhost:8080/risk

With a request body:

    {
      "age": 35,
      "dependents": 2,
      "house": {"ownership_status": "owned"},
      "income": 0,
      "marital_status": "married",
      "risk_questions": [0, 1, 0],
      "vehicle": {"year": 2018}
    }

Using the following curl command:

    curl --location --request POST 'localhost:8080/risk' \
    --header 'Content-Type: application/json' \
    --data-raw '{
    "age": 35,
    "dependents": 2,
    "house": {"ownership_status": "owned"},
    "income": 0,
    "marital_status": "married",
    "risk_questions": [0, 1, 0],
    "vehicle": {"year": 2018}
    }'

is expected to return the following response body, with HTTP code **200**:

    {
      "auto": "regular",
      "disability": "ineligible",
      "home": "economic",
      "life": "regular"
    }

---
If any of the fields is missing or invalid, you will see a **400** response code with a response body similar to the
following one:

    {
      "messages": [
        "risk_questions must have exactly 3 elements",
        "age must not be null",
        "dependents must be an integer equal or greater than 0"
      ]
    }

---
      