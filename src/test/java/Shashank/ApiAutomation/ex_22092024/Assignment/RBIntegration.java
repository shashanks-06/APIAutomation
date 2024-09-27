package Shashank.ApiAutomation.ex_22092024.Assignment;

import com.google.gson.Gson;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;


public class RBIntegration {
    RequestSpecification requestSpecification;
    Response response;
    ValidatableResponse validatableResponse;

    String token;
    String bookingId;
    String BASE_URL = "https://restful-booker.herokuapp.com";

    @Description("TC#1 - Create auth Token -> POST")
    @Test
    public void test_createAuthToken(){
        RequestToken requestToken = new RequestToken();
        requestToken.setUsername("admin");
        requestToken.setPassword("password123");

        Gson gson = new Gson();
        String payloadToken = gson.toJson(requestToken);
        System.out.println(payloadToken);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.basePath("/auth");
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.body(payloadToken).log().all();

        response = requestSpecification.when().post();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        ResponseToken responseToken = gson.fromJson(responseString, ResponseToken.class);
        token = responseToken.getToken();
        System.out.println(token);

        assertThat(token).isNotEmpty().isNotNull();
    }

    @Description("TC#2 - Create Booking ID -> POST")
    @Test
    public void test_createBooking(){
        Booking booking = new Booking();
        booking.setFirstName("James");
        booking.setLastName("Doe");
        booking.setTotalPrice(1300);
        booking.setDepositPaid(true);

        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckIn("2018-01-01");
        bookingDates.setCheckOut("2020-01-01");

        booking.setBookingDates(bookingDates);

        booking.setAdditionalNeeds("Dinner");

        Gson gson = new Gson();
        String payLoadPOST = gson.toJson(booking);
        System.out.println(payLoadPOST);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.body(payLoadPOST);

        response = requestSpecification.when().post();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        ResponseBooking responseBooking = gson.fromJson(responseString, ResponseBooking.class);
        bookingId = responseBooking.getBookingId();
        System.out.println(bookingId);

        assertThat(responseBooking.getBookingId()).isNotNull();
        assertThat(responseBooking.getBooking().getFirstName()).isNotEmpty().isEqualTo("James");
        assertThat(responseBooking.getBooking().getLastName()).isNotEmpty().isEqualTo("Doe");
    }

    @Description("TC#3 - Update the Booking Name -> PATCH")
    @Test
    public void test_update_bookingName(){

        Booking booking = new Booking();
        booking.setFirstName("John");

        Gson gson = new Gson();
        String payloadPatch = gson.toJson(booking);
        System.out.println(payloadPatch);

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.basePath("/booking/" +bookingId);
        requestSpecification.contentType(ContentType.JSON);
        requestSpecification.cookie("token", token);
        requestSpecification.body(payloadPatch).log().all();

        response = requestSpecification.when().patch();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Booking bookingPatch = gson.fromJson(responseString, Booking.class);
        String updatedFirstname = bookingPatch.getFirstName();
        System.out.println(updatedFirstname);

        assertThat(bookingPatch.getFirstName()).isNotEmpty().isEqualTo("John");

    }


    @Description("TC#4 - Get the Booking by Id and verify -> GET")
    @Test
    public void test_getBooking(){

        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.basePath("/booking/" +bookingId);
        requestSpecification.contentType(ContentType.JSON);

        response = requestSpecification.when().get();
        String ResponseString = response.asString();

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Gson gson = new Gson();
        Booking booking = gson.fromJson(ResponseString, Booking.class);
        String firstname = booking.getFirstName();
        System.out.println(firstname);

        assertThat(firstname).isNotEmpty().isNotBlank().isEqualTo("John");

    }

    @Description("TC#5 - Delete the created Booking by id -> DELETE")
    @Test
    public void test_DeleteBookingById() {
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.basePath("/booking/" + bookingId);
        requestSpecification.contentType(ContentType.JSON).log().all();
        requestSpecification.cookie("token", token);

        response = requestSpecification.when().log().all().delete();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(201);

        assertThat(responseString).isEqualTo("Created");
    }

    @Description("TC#6 - Get the DeletedBooking -> GET")
    @Test
    public void test_Get_DeletedBooking(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.basePath("/booking/" + bookingId);
        requestSpecification.contentType(ContentType.JSON).log().all();

        response = requestSpecification.when().log().all().get();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(404);

        assertThat(responseString).isEqualTo("Not Found");
    }

    @Description("TC#7 - Get All Booking IDs -> GET")
    @Test
    public void test_Get_AllBookingIds(){
        requestSpecification = RestAssured.given();
        requestSpecification.baseUri(BASE_URL);
        requestSpecification.basePath("/booking");
        requestSpecification.contentType(ContentType.JSON).log().all();

        response = requestSpecification.when().log().all().get();
        String responseString = response.asString();
        System.out.println(responseString);

        validatableResponse = response.then().log().all();
        validatableResponse.statusCode(200);

        Gson gson = new Gson();
        ResponseAllBookingIds[] allBookingIdsArray = gson.fromJson(responseString, ResponseAllBookingIds[].class);

        List<ResponseAllBookingIds> allBookingIdsList = Arrays.asList(allBookingIdsArray);
        // Print all booking IDs for verification
//        for (ResponseAllBookingIds booking : allBookingIdsList) {
//            System.out.println("Booking ID: " + booking.getBookingid());
//        }

        // Store the first booking ID into the global variable
        if (!allBookingIdsList.isEmpty()){
            bookingId = allBookingIdsList.get(0).getBookingid();
            System.out.println(bookingId);
        }else {
            System.out.println("No Booking ID is found in the response");
        }

    }
}
