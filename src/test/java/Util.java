import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import static com.jayway.restassured.RestAssured.*;

public class Util {
    private static String PATH = "?apikey=65fb3427";

    public static void prepareOmdbApi() {
        RestAssured.baseURI = "http://www.omdbapi.com/";
        RestAssured.basePath = "Search";
        given().contentType(ContentType.JSON);
    }

    public static void prepareOmdbTitle() {
        baseURI = "http://www.omdbapi.com/";
        basePath = "Title ";
        given().contentType(ContentType.JSON);
    }

    public static String bySearch(String search) {
        return RestAssured.baseURI + PATH + "&s=" + search;
    }

    public static String byTitle(String title) {
        return RestAssured.baseURI + PATH + "&t=" + title;
    }

    public static Response getResponse(String uri) {
        return get(uri);
    }

    public static JsonPath getJsonPath(Response res) {
        String json = res.asString();
        return new JsonPath(json);
    }
}
