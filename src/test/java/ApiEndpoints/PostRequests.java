package ApiEndpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.Map;

public class PostRequests {
    private final String jsonIdPath="id";
    private final String jsonJobPath="job";
    private final String jsonNamePath="name";
    private final String baseUri;
    private final String createUserPath="api/users";

    public PostRequests(String baseUri) {
        this.baseUri = baseUri;
    }

    public void createUser(Map<String,String> bodyMap){
        RestAssured.baseURI=baseUri;
        JSONObject jsonObject=new JSONObject();

        for(Map.Entry<String,String> entry:bodyMap.entrySet()){
            jsonObject.put(entry.getKey(),entry.getValue());
        }

        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .accept("*/*")
                .body(jsonObject.toJSONString())
                .when()
                .post(createUserPath);
        System.out.println("Status line: "+response.statusCode());
        System.out.println("Response: \n"+response.asPrettyString());
        JsonPath jsonResponseBody = response.getBody().jsonPath();
        Assert.assertEquals(response.getStatusCode(),201);
        Assert.assertEquals(jsonResponseBody.get(jsonNamePath),bodyMap.get(jsonNamePath));
        Assert.assertEquals(jsonResponseBody.get(jsonJobPath),bodyMap.get(jsonJobPath));
        Assert.assertNotNull(jsonResponseBody.get(jsonIdPath));
    }
}
