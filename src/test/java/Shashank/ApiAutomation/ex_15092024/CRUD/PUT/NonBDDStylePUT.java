package Shashank.ApiAutomation.ex_15092024.CRUD.PUT;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class NonBDDStylePUT {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    // 6118
    // Token -  Token?
//     Payload - {
//        "firstname" : "James",
//        "lastname" : "Brown",
//        "totalprice" : 111,
//        "depositpaid" : true,
//        "bookingdates" : {
//            "checkin" : "2018-01-01",
//            "checkout" : "2019-01-01"
//        },
//        "additionalneeds" : "Breakfast"
//    }

    // POST - Auth - token
    // POST - Booking ID
    // PUT - token and BookingID

//    public void get_token(){
//
//    }
//
//    public void get_booking_id(){
//        return bookingid;
//    }

    @Test
    public void testPUT_positive_TC(){
        String token  = "d5b31aa9ce612d5";
        String bookingId = "5904";

        String payloadPUT = "{\n" +
                "        \"firstname\" : \"Shashank\",\n" +
                "        \"lastname\" : \"Surjekar\",\n" +
                "        \"totalprice\" : 207,\n" +
                "        \"depositpaid\" : true,\n" +
                "        \"bookingdates\" : {\n" +
                "            \"checkin\" : \"2024-01-01\",\n" +
                "            \"checkout\" : \"2024-01-01\"\n" +
                "        },\n" +
                "        \"additionalneeds\" : \"Lunch\"\n" +
                "    }";

       RequestSpecification requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking/"+bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token",token);
        requestSpecification.body(payloadPUT).log().all();

        Response response = requestSpecification.when().put();

        // Get Validatable response to perform validation
        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);



    }
}
