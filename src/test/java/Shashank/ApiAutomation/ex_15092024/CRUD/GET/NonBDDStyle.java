package Shashank.ApiAutomation.ex_15092024.CRUD.GET;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class NonBDDStyle {
    static RequestSpecification r = RestAssured.given();    // This will be automatically created only once
    public static void main(String[] args) {

        r.baseUri("https://api.zippopotam.us");     // This will execute only once

        test_nonBDD_1();
        test_nonBDD_2();
    }

    private static  void test_nonBDD_1(){
//        r.relaxedHTTPSValidation("TLS"); //--> if facing https related issues
        r.basePath("/IN/444601");
        r.when().get();
        r.then().log().all().statusCode(200);
    }
    private static  void test_nonBDD_2(){
        r.basePath("/IN/-1");
        r.when().get();
        r.then().log().all().statusCode(404);
    }
}
