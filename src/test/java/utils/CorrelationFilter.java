package utils;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

/**
 * LocalCorrelationFilter
 * Generates a unique correlation ID per request for logging purposes.
 * The ID is not sent to the server but is logged for internal traceability.
 */
public class CorrelationFilter implements Filter {

    private static final Logger logger = LoggerFactory.getLogger(CorrelationFilter.class);

    @Override
    public Response filter(FilterableRequestSpecification requestSpec,
                           FilterableResponseSpecification responseSpec,
                           FilterContext ctx) {

        // Generate a local unique correlation ID
        String correlationId = UUID.randomUUID().toString();

        // Log request info
        logger.info("[Request] [{}] {} {}", correlationId, requestSpec.getMethod(), requestSpec.getURI());

        // Execute request and capture response
        Response response = ctx.next(requestSpec, responseSpec);

        // Log response info
        logger.info("[Response] [{}] Status: {} | Time: {} ms", correlationId,
                response.getStatusCode(), response.getTime());

        return response;
    }
}