import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class Helper {
    public static void checkStatusIs200(Response res) {
        assertEquals("Status Check Failed!", 200, res.getStatusCode());
    }

    public static void checkString(String content, String expected) {
        assertEquals("String(" + content + ") Check Failed!", content, expected);
    }

    public static void checkBoolean(Boolean content, Boolean expected) {
        assertEquals(content, expected);
    }

    public static ArrayList getBySearch(JsonPath jp) {
        return (ArrayList) jp.getList("Search");
    }

    public static String getReleasedByTitle(JsonPath jp) {
        return jp.get("Released");
    }
}
