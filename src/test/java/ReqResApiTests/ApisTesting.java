package ReqResApiTests;

import ApiEndpoints.DeleteRequests;
import ApiEndpoints.GetRequests;
import ApiEndpoints.PostRequests;
import ApiEndpoints.PutRequests;
import org.testng.annotations.Test;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class ApisTesting {

    private final String baseUri="https://reqres.in/";
    private final int maxUsersPerPage=6;
    private final int totalUsers=12;
    private final int maxPages=Math.floorDiv(totalUsers,maxUsersPerPage);
    private BufferedReader br;
    private GetRequests getRequests;
    private PostRequests postRequests;
    private PutRequests putRequests;
    private DeleteRequests deleteRequests;

    public ApisTesting() {
        this.br=new BufferedReader(new InputStreamReader(System.in));
        this.getRequests = new GetRequests(this.baseUri);
        this.postRequests=new PostRequests(this.baseUri);
        this.putRequests=new PutRequests(this.baseUri);
        this.deleteRequests=new DeleteRequests(this.baseUri);
    }

    @Test
    public void getUsersList(){
        System.out.println("Max Per Page: "+maxUsersPerPage+"\nNo Of Pages: "+maxPages);
        //TODO:get with data providers
        int pageNum=1;
        Map<String,String> queryParamsMap=new HashMap<>();
        queryParamsMap.put("page",String.valueOf(pageNum));
        getRequests.getUsersList(queryParamsMap);
    }

    @Test
    public void getSingleUser(){
        System.out.println("Total Users: "+totalUsers);
        //TODO:get with data providers
        int userId=6;
        getRequests.getSingleUser(userId);
    }

    @Test
    public void createUser(){
        //TODO:get with data providers
        String name="Uma mahesh";
        String job="emp";
        Map<String,String> bodyMap=new HashMap<>();
        bodyMap.put("name",name);
        bodyMap.put("job",job);
        postRequests.createUser(bodyMap);
    }

    @Test
    public void updateUser(){
        //TODO:get with data providers
        String name="Uma maheshwar rao";
        String job="abcd";
        int userId=6;
        Map<String,String> bodyMap=new HashMap<>();
        bodyMap.put("name",name);
        bodyMap.put("job",job);
        putRequests.updateUser(bodyMap,userId);
    }

    @Test
    public void deleteUser(){
        //TODO:get with data providers
        int userId=6;
        deleteRequests.deleteUser(userId);
    }
}
