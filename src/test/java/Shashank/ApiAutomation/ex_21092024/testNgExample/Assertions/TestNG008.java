package Shashank.ApiAutomation.ex_21092024.testNgExample.Assertions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class TestNG008 {
    String token;
    String bookingId;

    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse valRes;

    @Test
    public void test_booking_POST(){

        String payloadPOST = "{\n" +
                "    \"firstname\" : \"Shashank\",\n" +
                "    \"lastname\" : \"Surjekar\",\n" +
                "    \"totalprice\" : 420,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"lunch\"\n" +
                "}";

        RequestSpecification reqSpec = RestAssured.given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com/");
        reqSpec.basePath("/booking");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.body(payloadPOST).log().all();

        Response res = reqSpec.when().post();

        // Get Validatable response to perform validation
        ValidatableResponse valRes = res.then().log().all();
        valRes.statusCode(200);

        bookingId = res.jsonPath().getString("bookingid");
        System.out.println(bookingId);

        // Rest Assured Default - Hamcrest
        // import org.hamcrest.Matchers;
//        valRes.body("booking.firstname", Matchers.equalTo("Shashank"));
//        valRes.body("booking.lastname", Matchers.equalTo("Surjekar"));
//        valRes.body("booking.depositpaid", Matchers.equalTo(true));
//        valRes.body("bookingid", Matchers.notNullValue());


        // TestNG Assertion
        // SoftAssert vs
        // HardAssert - This means that if any assertion fails, the remaining statements in that test method will not be executed.



    }

}
