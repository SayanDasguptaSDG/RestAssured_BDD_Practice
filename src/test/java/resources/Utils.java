package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    String[] credentials = {"key", "qaclick123"};
    PrintStream log;

    public RequestSpecification requestSpecification() throws IOException {
        log = new PrintStream(new FileOutputStream("logging.log"));
        return new RequestSpecBuilder()
                .setBaseUri(getGlobalValue("baseUri"))
                .addQueryParam(credentials[0], credentials[1])
                .addFilter(RequestLoggingFilter.logRequestTo(log))
                .addFilter(ResponseLoggingFilter.logResponseTo(log))
                .setContentType(ContentType.JSON)
                .build();
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = null;
        fis = new FileInputStream("src/test/java/resources/global.properties");

        properties.load(fis);
        return properties.getProperty(key);
    }
}
