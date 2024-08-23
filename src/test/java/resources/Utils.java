package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Utils {
    String baseURI = "https://rahulshettyacademy.com";
    String[] credentials = {"key", "qaclick123"};
    PrintStream log;

    public RequestSpecification requestSpecification() throws FileNotFoundException {
        log = new PrintStream(new FileOutputStream("logging.log"));
        return new RequestSpecBuilder()
                .setBaseUri(baseURI)
                .addQueryParam(credentials[0], credentials[1])
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON)
                .build();
    }
}
