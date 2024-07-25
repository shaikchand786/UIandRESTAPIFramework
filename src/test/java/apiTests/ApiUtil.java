package apiTests;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiUtil {
    public static Response get(String endpoint) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        return RestAssured.given()
                .when()
                .get(endpoint)
                .then()
                .extract().response();
    }

    public static Response post(String endpoint, String body) {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        return RestAssured.given()
                .header("Content-type", "application/json")
                .and()
                .body(body)
                .when()
                .post(endpoint)
                .then()
                .extract().response();
    }
}
