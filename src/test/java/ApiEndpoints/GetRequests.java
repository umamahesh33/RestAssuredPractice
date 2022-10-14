package ApiEndpoints;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import java.util.Map;

public class GetRequests {
    private final int maxUsersPerPage=6;
    private final int totalUsers=12;

    private final int maxPages=Math.floorDiv(totalUsers,maxUsersPerPage);
    private final String jsonIdPath="data.id";
    private final String jsonEmailPath="data.email";

    private final String baseUri;
    private final String getAllUsersPath="api/users";
    private final String getSingleUserPath="api/users/";

    public GetRequests(String url) {
        this.baseUri=url;
    }

    public void getUsersList(Map<String,String> queryParamsMap){
        RestAssured.baseURI=baseUri;
        RequestSpecification requestSpecification=RestAssured.given();
        requestSpecification.queryParams(queryParamsMap);
        Response response = requestSpecification.get(getAllUsersPath);
        ResponseBody responseBody=response.getBody();
        System.out.println("Status line: "+response.getStatusLine());
        System.out.println("Response body: \n"+responseBody.asPrettyString());
        Assert.assertEquals(response.getStatusCode(),200);
    }

    public void getSingleUser(int userId){
        RestAssured.baseURI=baseUri;
        RequestSpecification makeReq=RestAssured.given();
        Response response=makeReq.get(getSingleUserPath+userId);
        ResponseBody responseBody=response.getBody();
        JsonPath jsonResponseBody=response.jsonPath();

        System.out.println("Status line: "+response.getStatusLine());
        System.out.println("Response body: \n"+responseBody.asPrettyString());

        if(userId>=0 && userId<=totalUsers){
            Assert.assertEquals(response.getStatusCode(),200);
            int id=jsonResponseBody.getInt(jsonIdPath);
            String email=jsonResponseBody.getString(jsonEmailPath);
            Assert.assertNotNull(id);
            Assert.assertNotNull(email);
        }
        else Assert.assertEquals(response.getStatusCode(),404);
    }
}
