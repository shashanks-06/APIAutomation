package Shashank.ApiAutomation.ex_21092024.testNgExample;

import org.testng.Assert;
import org.testng.annotations.Test;

// dependsUpon

public class TestNG005 {

    @Test
    public void serverStartedOk(){
        System.out.println("serverStartedOk"); // if this fails then the below method which is depend upon this
        Assert.assertTrue(false);   // this method will not run (ignored)
    }

    @Test(dependsOnMethods = "serverStartedOk")
    public void method1(){
        System.out.println("method1");
    }
}
