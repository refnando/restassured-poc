package base;

import config.Environment;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = Environment.BASE_URL;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
