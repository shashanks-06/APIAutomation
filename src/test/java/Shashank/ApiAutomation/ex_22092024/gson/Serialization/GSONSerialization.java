package Shashank.ApiAutomation.ex_22092024.gson.Serialization;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

// For easy conversion use  -> https://www.jsonschema2pojo.org/

public class GSONSerialization {
    // Class - Pojo -
    // Create class for the Payload - pojo

    // PUT Request
    // token, booking id

//    {
//                "firstname" : "Jim",
//                "lastname" : "Brown",
//                "totalprice" : 111,
//                "depositpaid" : true,
//                "bookingdates" : {
//                "checkin" : "2018-01-01",
//                "checkout" : "2019-01-01"
//                 },
//                "additionalneeds" : "Breakfast"
//            }

       RequestSpecification reqSpec;
       Response res;
       ValidatableResponse valRes;

       @Description("TC#1 - Verify that create booking is working with valid payload")
       @Test
       public void test_NonBddStylePost_Positive(){

           Booking booking = new Booking();

           booking.setFirstname("Shashank");
           booking.setLastname("Surjekar");
           booking.setTotalprice(111);
           booking.setDepositpaid(true);

           Bookingdates bookingdates = new Bookingdates();
           bookingdates.setCheckin("2024-01-01");
           bookingdates.setCheckout("2024-12-31");

           booking.setBookingdates(bookingdates);

           booking.setAdditionalneeds("Breakfast");

//           System.out.println(booking);   // -> Java Object
           // Java Object -> JSON String (byteStream) - Serialization
           Gson gson = new Gson();
           String jsonPayload = gson.toJson(booking);
//           System.out.println(jsonPayload);   // -> JSON String

           String BASE_URL = "https://restful-booker.herokuapp.com";
           String BASE_PATH = "/booking";

           reqSpec = RestAssured.given();
           reqSpec.baseUri(BASE_URL);
           reqSpec.basePath(BASE_PATH);
           reqSpec.contentType(ContentType.JSON).log().all();
           reqSpec.body(jsonPayload);

           res = reqSpec.when().log().all().post();
           String responseString = res.asString();
           System.out.println(responseString);

           valRes = res.then().log().all();
           valRes.statusCode(200);

           // Parse - DeSerilization

       }
}
