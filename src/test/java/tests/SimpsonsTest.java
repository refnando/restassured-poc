package tests;

import base.BaseTest;
import constants.ApiConstants;
import constants.HttpStatus;
import constants.Numbers;
import http.RequestSpecFactory;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ResponseValidator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class SimpsonsTest extends BaseTest {

    @Test
    @DisplayName("GET /characters returns a list of characters")
    void getCharacters_ok() {
        Response response = given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .when()
                .get(ApiConstants.CHARACTERS)
                .then()
                .extract().response();

        ResponseValidator.status(response, HttpStatus.OK);
        response.then()
                .body("size()", greaterThan(Numbers.ZERO));
    }

    @Test
    @DisplayName("GET /characters/{id} returns Homer Simpson data")
    void getCharacterById_ok() {
        Response response = given()
                .spec(RequestSpecFactory.getDefaultSpec())
                .pathParam("id", 1)
                .when()
                .get(ApiConstants.CHARACTER_BY_ID)
                .then()
                .extract().response();

        ResponseValidator.status(response, HttpStatus.OK);
        response.then()
                .body("name", equalTo("Homer Simpson"))
                .body("occupation", equalTo("Safety Inspector"))
                .body("status", equalTo("Alive"))
                .body("phrases", hasItem("Doh!"));
    }
}