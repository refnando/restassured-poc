package utils;

import io.restassured.response.Response;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ResponseValidator {
    public static void status(Response response, int expectCode){
        assertThat("Incorrect status code", response.statusCode(), equalTo(expectCode));
    }
}
