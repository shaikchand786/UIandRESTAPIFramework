package apiTests;

import io.restassured.response.Response;
import pages.ExtentBaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GetPostsTest extends ExtentBaseTest {
    @Test
    public void getPostsTest() {
        test = extent.createTest("GET /posts", "Test to validate GET method for /posts endpoint");
        Response response = ApiUtil.get("/posts");

        test.info("Status Code: " + response.getStatusCode());
        test.info("Response Body: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 200, "Expected status code not found!");
        Assert.assertTrue(response.jsonPath().getList("").size() > 0, "Expected posts not found!");

        test.pass("GET /posts Test Passed");
    }
}
