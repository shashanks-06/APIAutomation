package Shashank.ApiAutomation.ex_21092024.testNgExample.CRUD.PATCH;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class TestCaseIntegration_PATCH {

    String token;
    String bookingId;

    RequestSpecification reqSpec;
    Response res;
    ValidatableResponse valRes;

    public String getToken(){

        String payloadToken = "{\n" +
                "    \"username\" : \"admin\",\n" +
                "    \"password\" : \"password123\"\n" +
                "}";

        reqSpec = RestAssured.given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/auth");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.body(payloadToken).log().all();

        res = reqSpec.when().post();

        valRes = res.then().log().all();
        valRes.statusCode(200);

        token = res.jsonPath().getString("token");
        System.out.println(token);
        return token;
    }

    public String getBookingId(){

        String payloadBookingId = "{\n" +
                "    \"firstname\" : \"Tony\",\n" +
                "    \"lastname\" : \"Stark\",\n" +
                "    \"totalprice\" : 111,\n" +
                "    \"depositpaid\" : true,\n" +
                "    \"bookingdates\" : {\n" +
                "        \"checkin\" : \"2018-01-01\",\n" +
                "        \"checkout\" : \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\" : \"Mark II\"\n" +
                "}";

        reqSpec = RestAssured.given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com");
        reqSpec.basePath("/booking");
        reqSpec.contentType(ContentType.JSON);
        reqSpec.body(payloadBookingId).log().all();

        res = reqSpec.when().post();

        valRes = res.then().log().all();
        valRes.statusCode(200);

        bookingId = res.jsonPath().getString("bookingid");
        System.out.println(bookingId);

        return bookingId;
    }

    @Test
    public void test_UpdateRequest_Patch(){
    token = getToken();
    bookingId = getBookingId();

    String payloadPatch = "{\n" +
            "    \"additionalneeds\" : \"Nano Tech\"\n" +
            "}";

    reqSpec = RestAssured.given();
    reqSpec.baseUri("https://restful-booker.herokuapp.com");
    reqSpec.basePath("/booking/" +bookingId);
    reqSpec.contentType(ContentType.JSON);
    reqSpec.cookie("token", token);
    reqSpec.body(payloadPatch).log().all();

    res = reqSpec.when().patch();

    valRes = res.then().log().all();
    valRes.statusCode(200);

    }

    @Test
    public void test_getUpdatedRequest_GET(){
        System.out.println("BookingID is "+bookingId);
        reqSpec = RestAssured.given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com/");
        reqSpec.basePath("/booking/" +bookingId);
        reqSpec.contentType(ContentType.JSON).log().all();

        res = reqSpec.when().get();

        valRes = res.then().log().all();
        valRes.statusCode(200);
    }

    @Test
    public void test_DELETE_Booking(){
        System.out.println("BookingID is "+bookingId);
        System.out.println("Token is "+token);

        reqSpec = RestAssured.given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com/");
        reqSpec.basePath("/booking/" +bookingId);
        reqSpec.contentType(ContentType.JSON);
        reqSpec.cookie("token", token).log().all();


        res = reqSpec.when().delete();

        valRes = res.then().log().all();
        valRes.statusCode(201);

        System.out.println(bookingId+ " is Successfully Deleted");
    }


    @Test
    public void test_after_delete_request_get() {
        System.out.println("BookingID is "+bookingId);

        reqSpec = RestAssured.given();
        reqSpec.baseUri("https://restful-booker.herokuapp.com/");
        reqSpec.basePath("/booking/" +bookingId);
        reqSpec.contentType(ContentType.JSON);

        res = reqSpec.when().get();

        valRes = res.then().log().all();
        valRes.statusCode(404);

        System.out.println(bookingId+ " is not available because it is already deleted");
    }

}

