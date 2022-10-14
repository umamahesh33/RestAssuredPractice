package ApiEndpoints;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;

import java.util.Map;

public class PutRequests {
    private final String baseUri;
    private final String updateUserPath="api/users/";

    public PutRequests(String baseUri) {
        this.baseUri = baseUri;
    }

    public void updateUser(Map<String,String> bodyMap, int id){
        RestAssured.baseURI=this.baseUri;

        JSONObject jsonObject=new JSONObject();
        for(Map.Entry<String,String> entry:bodyMap.entrySet()){
            jsonObject.put(entry.getKey(),entry.getValue());
        }

        Response response=RestAssured.given()
                .contentType(ContentType.JSON)
                .accept("*/*")
                .body(jsonObject.toJSONString())
                .when()
                .put(updateUserPath+id);

        JsonPath jsonResponse=response.jsonPath();

        System.out.println("Status line: "+response.getStatusLine());
        System.out.println("Response: \n"+response.asPrettyString());

        Assert.assertEquals(response.getStatusCode(),200);
        for(Map.Entry<String,String> entry:bodyMap.entrySet()){
            Assert.assertEquals(jsonResponse.get(entry.getKey()),entry.getValue());
        }
    }
}
