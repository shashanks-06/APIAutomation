package Shashank.ApiAutomation.exe_15092024.CRUD.POST;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class NonBDDStylePOST {
    public static void main(String[] args) {

        String payload = " {\n" +
                "            \"username\" : \"admin\",\n" +
                "            \"password\" : \"password123\"\n" +
                "        }";

        // Given - Request Spec
//        Preparing request
        RequestSpecification reqSpec = RestAssured.given();

        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/auth");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.log().all();
        reqSpec.body(payload);


//        When - Response
//        Making request
        Response res = reqSpec.when().post();


//       Then - ValidatableResponse
//        Validation
//        Verifying request
        ValidatableResponse valRes = res.then();
        valRes.log().all();
        valRes.statusCode(200);
    }
}
