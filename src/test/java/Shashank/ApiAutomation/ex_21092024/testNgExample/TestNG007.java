package Shashank.ApiAutomation.ex_21092024.testNgExample;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG007 {

    @Test
    public void test1(){
//        Assert.assertTrue(true);
        Assert.assertTrue(false); // Even if this failed test3 will run anyhow
    }

    @Test(enabled = false)  // even if this test is passing but it will not run as it is not enabled
    public void test2(){                // it's like skipping the test case
        Assert.assertTrue(true);
    }

    @Test(alwaysRun = true)
    public void test3(){
        Assert.assertTrue(true);    // Even if this above tests failed it will run anyhow
    }
}
