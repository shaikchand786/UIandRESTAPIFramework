package apiTests;

import io.restassured.response.Response;
import pages.ExtentBaseTest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class PostCreatePostTest extends ExtentBaseTest {
    @Test
    public void createPostTest() {
        test = extent.createTest("POST /posts", "Test to validate POST method for /posts endpoint");
        String requestBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";
        Response response = ApiUtil.post("/posts", requestBody);

        test.info("Status Code: " + response.getStatusCode());
        test.info("Response Body: " + response.asString());

        Assert.assertEquals(response.getStatusCode(), 201, "Expected status code not found!");
        Assert.assertEquals(response.jsonPath().getString("title"), "foo", "Expected title not found!");
        Assert.assertEquals(response.jsonPath().getString("body"), "bar", "Expected body not found!");
        Assert.assertEquals(response.jsonPath().getInt("userId"), 1, "Expected userId not found!");

        test.pass("POST /posts Test Passed");
    }
}
