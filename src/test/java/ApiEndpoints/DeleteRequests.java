package ApiEndpoints;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class DeleteRequests {
    private final String baseUri;
    private final String deleteUserPath="api/users/";

    public DeleteRequests(String baseUri) {
        this.baseUri = baseUri;
    }

    public void deleteUser(int userId){
        RestAssured.baseURI=this.baseUri;
        Response response=RestAssured.given().when().delete(deleteUserPath+userId);
        System.out.println("Status line: "+response.getStatusLine());
        System.out.println("Response: "+response.asPrettyString());
    }
}
