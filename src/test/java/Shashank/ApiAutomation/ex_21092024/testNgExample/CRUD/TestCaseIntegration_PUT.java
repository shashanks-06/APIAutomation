package Shashank.ApiAutomation.ex_21092024.testNgExample.CRUD;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

// To run test cases sequentially we can do
// - add PRIORITY
// - add names of TestCases in alphabetical order
// - use dependsUponMethods function as @Test(dependsUponMethods = "-------")
// - add <methods> in testng files in sequence o run the TC
public class TestCaseIntegration_PUT {
    //  Create a Token
    // Create a Booking
    //  Perform  a PUT request
    //  Verify that PUT is success by GET Request
    // Delete the ID
    // Verify it doesn't exist GET Request

    String token;
    String bookingId;




    public String getToken(){

//        payload to send
        String payloadToken = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        // Given - Request Spec
        RequestSpecification reqSpec = RestAssured.given();

        reqSpec.baseUri("https://restful-booker.herokuapp.com/");
        reqSpec.basePath("/auth");
        reqSpec.contentType(ContentType.JSON).log().all();
        reqSpec.body(payloadToken);

        // When -     Response
        Response res = reqSpec.when().post();

        // Then - ValidatableResponse
        // Validation
        ValidatableResponse valRes = res.then().log().all();
        valRes.statusCode(200);

//        Extract the token
        token = res.jsonPath().getString("token");
        System.out.println(token);
        return token;
    }

    public String getBookingId(){

        String payloadPOST = "{\n" +
                "    \"firstname\" : \"James\",\n" +
                "    \"lastname\" : \"Bond\",\n" +
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

        return bookingId;
    }

    @Test
    public void test_UpdateRequest_PUT(){
        token = getToken();
        bookingId = getBookingId();

        String payloadPUT = "{\n" +
                "    \"firstname\" : \"Shashank\",\n" +
                "    \"lastname\" : \"Surjekar\",\n" +
                "    \"totalprice\" : 611,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2024-01-01\",\n" +
                "        \"checkout\" : \"2024-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Dinner\"\n" +
                "}";

        RequestSpecification reqSpec = RestAssured.given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com/");
        reqSpec.basePath("/booking/" +bookingId);
        reqSpec.contentType(ContentType.JSON);
        reqSpec.cookie("token", token);
        reqSpec.body(payloadPUT).log().all();

        Response res = reqSpec.when().put();

        ValidatableResponse valRes = res.then().log().all();
        valRes.statusCode(200);
    }

//    @Test(dependsOnMethods = "test_UpdateRequest_PUT")
// No need of dependsUpon method as we added <include> tag in <methods> in testng.xml
    @Test       // No need of dependsUpon method as we added include tag in testng.xml
    public void test_UpdateRequest_GET(){
        System.out.println("BookingID is "+bookingId);
    }

//    @Test(dependsOnMethods = "test_UpdateRequest_GET")
    @Test
    public void test_DELETE_Booking(){
        System.out.println("BookingID is "+bookingId);
        System.out.println("Token is "+token);
    }

//    @Test(dependsOnMethods = "test_DELETE_Booking")
    @Test
    public void test_after_delete_request_get() {
        System.out.println("BookingID is "+bookingId);
    }
}
