package http;

import config.Environment;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.specification.RequestSpecification;
import utils.CorrelationFilter;

import static  io.restassured.http.ContentType.JSON;

public class RequestSpecFactory {
    public static RequestSpecification getDefaultSpec() {
        RestAssuredConfig config = RestAssuredConfig.newConfig()
                .httpClient(HttpClientConfig.httpClientConfig()
                        .setParam("http.connection.timeout", Environment.TIMEOUT_MS)
                        .setParam("http.socket.timeout", Environment.TIMEOUT_MS));

        return new RequestSpecBuilder()
                .setBaseUri(Environment.BASE_URL)
                .setContentType(JSON)
                .addFilter(new CorrelationFilter())
                .addFilter(new ErrorLoggingFilter())
                .setConfig(config)
                .build();
    }
}
