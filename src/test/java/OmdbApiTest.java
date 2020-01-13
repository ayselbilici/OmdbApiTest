import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.response.Response;
import org.junit.*;
import org.junit.runners.MethodSorters;

import java.util.ArrayList;
import java.util.HashMap;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class OmdbApiTest {
    private String search = "Harry Potter";
    private String myMovie = "Harry Potter and the Sorcerer's Stone";
    private String myYear = "2001";
    private Response response;
    private JsonPath jp;

    @Test
    public void statusCodeTest() {
        Util.prepareOmdbApi();
        response = Util.getResponse(Util.bySearch(search));
        Helper.checkStatusIs200(response);
    }

    @Test
    public void movieListTest() {
        Util.prepareOmdbApi();
        response = Util.getResponse(Util.bySearch(search));
        jp = Util.getJsonPath(response);
        ArrayList<HashMap> movieTitleList = Helper.getBySearch(jp);
        Boolean testResult = false;
        for (HashMap<String, String> hm : movieTitleList) {
            String title = hm.get("Title");
            if (title.equals(myMovie)) testResult = true;
        }
        Helper.checkBoolean(testResult, true);
    }

    @Test
    public void movieYearTest() {
        Util.prepareOmdbApi();
        response = Util.getResponse(Util.bySearch(search));
        jp = Util.getJsonPath(response);
        ArrayList<HashMap> movieYearList = Helper.getBySearch(jp);
        Boolean testResult = false;
        for (HashMap<String, String> hm : movieYearList) {
            String year = hm.get("Year");
            if (year.equals(myYear)) testResult = true;
        }
        Helper.checkBoolean(testResult, true);
    }

    @Test
    public void movieReleasedTest() {
        Util.prepareOmdbTitle();
        response = Util.getResponse(Util.byTitle(myMovie));
        jp = Util.getJsonPath(response);
        String released = Helper.getReleasedByTitle(jp);
        Helper.checkString(released, "16 Nov 2001");
    }
}
