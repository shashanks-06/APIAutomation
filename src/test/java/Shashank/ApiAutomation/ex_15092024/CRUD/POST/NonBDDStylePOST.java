package Shashank.ApiAutomation.ex_15092024.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDStylePOST {

    @Test
    public void testng_Nonbdd () {

        String payload = " {\n" +
                "            \"username\" : \"admin\",\n" +
                "            \"password\" : \"password123\"\n" +
                "        }";

        // Given - Request Spec
//        Preparing the Request
        RequestSpecification reqSpec = RestAssured.given();

        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/auth");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.log().all();
        reqSpec.body(payload);


//        When - Response
//        Making Request
        Response res = reqSpec.when().post();


//       Then - ValidatableResponse
//        Validation
//        Verifying Request
        ValidatableResponse valRes = res.then();
        valRes.log().all();
        valRes.statusCode(200);
    }
}
