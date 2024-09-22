package Shashank.ApiAutomation.ex_21092024.testNgExample;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG002 {

//    THIS IS JUST AN EXAMPLE

    String token;
    Integer bookingId;

    @BeforeTest
    public String getToken(){
        token = "xyz";
        return token;
    }

// In @BeforeTest there's no preference, it can run anyone randomly first and then others

    @BeforeTest
    public void getTokenAndBookingId(){
        token = getToken();
//        POST REQUEST ->
//        POST CODE ->
        bookingId = 123;
    }

    @Test
    public void putReq(){
        System.out.println(bookingId);
        System.out.println(token);
    }
}
