package Shashank.ApiAutomation.ex_22092024.pojos;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class POJO001 {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;
    String token;
    String bookingId;

    @Test
    public void test_with_Post_hashmap(){
        // {
        //    "firstname" : "sally",
        //    "lastname" : "brown",
        //    "totalprice" : 111,
        //    "depositpaid" : true,
        //    "bookingdates" : {
        //        "checkin" : "2018-01-01",
        //        "checkout" : "2019-01-01"
        //    },
        //    "additionalneeds" : "Breakfast"
        //}

        // Hashmap ->
        // {} - map
//        String -> Keys are only String values
//        Object -> Values are heterogeneous i.e. String, Integer, Boolean, etc
        Map<String, Object> jsonDataUsingMap = new LinkedHashMap<>();
        jsonDataUsingMap.put("firstname", "Sally");
        jsonDataUsingMap.put("lastname", "Brown");
        jsonDataUsingMap.put("totalprice", 111);
        jsonDataUsingMap.put("depositpaid", true);

        Map<String, Object> bookingDatesMap = new LinkedHashMap<>();
        bookingDatesMap.put("checkin", "2018-01-01");
        bookingDatesMap.put("checkout", "2019-01-01");

        jsonDataUsingMap.put("bookingdates", bookingDatesMap);
        jsonDataUsingMap.put("additionalneeds", "Breakfast");

        System.out.println(jsonDataUsingMap);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri("https://restful-booker.herokuapp.com/");
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(jsonDataUsingMap).log().all();

        response = requestSpecification.when().post();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        bookingId = response.jsonPath().getString("bookingid");
        System.out.println(bookingId);
    }
}
