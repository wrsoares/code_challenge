package api;

import io.restassured.response.Response;
import org.json.JSONObject;
import utils.Utils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Request implements Methods{

    protected Response response;
    protected String uri;
    public Response GET() {
        response = given()
                .relaxedHTTPSValidation()
                .request().log().all()
                .get(uri);
        return response;
    }
}