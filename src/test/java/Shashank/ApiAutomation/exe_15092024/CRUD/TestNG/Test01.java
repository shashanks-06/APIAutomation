package Shashank.ApiAutomation.exe_15092024.CRUD.TestNG;

import io.restassured.RestAssured;
import org.testng.annotations.Test;

public class Test01 {
    @Test
    public void test_get(){
        RestAssured
                .given()
                    .baseUri("https://api.zippopotam.us")
                    .basePath("/IN/444601")
                .when()
                    .log().all()
                    .get()
                .then()
                    .log().all()
                    .statusCode(200);
    }
}
