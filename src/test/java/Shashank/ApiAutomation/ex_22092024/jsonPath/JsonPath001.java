package Shashank.ApiAutomation.ex_22092024.jsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class JsonPath001 {
    String token;
    Integer bookingId;

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

//        System.out.println(res.asString());

        JsonPath jsonPath = new JsonPath(res.asString());
        String bookingId = jsonPath.getString("bookingid");
        String firstName = jsonPath.getString("booking.firstname");
        String checkout = jsonPath.getString("booking.bookingdates.checkout");

        System.out.println(bookingId + " " + firstName + " " + checkout);    //-> 3048 Shashank 2019-01-01

        assertThat(bookingId).isNotNull().isNotBlank().isGreaterThan("0");
        assertThat(firstName).isNotEmpty().isEqualTo("Shashank");
        assertThat(checkout).isNotNull().isNotBlank();


        // Get Validatable response to perform validation
//        ValidatableResponse valRes = res.then().log().all();
//        valRes.statusCode(200);


    }

}
