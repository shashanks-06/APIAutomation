package Shashank.ApiAutomation;

import io.restassured.RestAssured;

public class Test001 {
    public static void main(String[] args) {
        System.out.println("Rest Assured Test CASE");
        System.out.println("GET Request Demo");

        // Gherkins Syntax
//        given() - url, headers, body or payload
//        when() - http methods - get, post, patch, put, delete
//        then() - verify the response - er == ar

        RestAssured
                .given()
                    .baseUri("https://restful-booker.herokuapp.com")
                    .basePath("/booking/1").log().all()
                .when()
                    .get()
                .then().log().all()         // .log().all() -> to print the logs
                    .statusCode(200);   // No output if testcase is passing
        //          .statusCode(201);       Expected status code <201> but was <200>
            }
}
