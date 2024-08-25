package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    String[] credentials = {"key", "qaclick123"};
    PrintStream log;
    public static RequestSpecification request;

    public RequestSpecification requestSpecification() throws IOException {
        if(request == null) {
            log = new PrintStream(new FileOutputStream("logging.log"));
            request = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseUri"))
                    .addQueryParam(credentials[0], credentials[1])
                    .addFilter(RequestLoggingFilter.logRequestTo(log))
                    .addFilter(ResponseLoggingFilter.logResponseTo(log))
                    .setContentType(ContentType.JSON)
                    .build();
        }
        return request;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fis = null;
        fis = new FileInputStream("src/test/java/resources/global.properties");

        properties.load(fis);
        return properties.getProperty(key);
    }

    public String getJsonPath(Response response, String key) {
        JsonPath js;
        String responseStr = response.asString();
        js = new JsonPath(responseStr);
        return js.get(key).toString();
    }
}
