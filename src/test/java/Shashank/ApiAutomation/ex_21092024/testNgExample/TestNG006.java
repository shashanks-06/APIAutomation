package Shashank.ApiAutomation.ex_21092024.testNgExample;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG006 {

    @Parameters("browser")
    @Test
    public void demo1(String browserName){
        System.out.println("Browser name is " + browserName);   // chrome or firefox
    }
}
