package Shashank.ApiAutomation.ex_21092024.testNgExample;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG004 {

    @Test(groups = {"qa", "sanity", "preprod"})
    public void sanity(){
        System.out.println("Sanity");
        System.out.println("QA");
        Assert.assertTrue(true);
    }

    @Test(groups = {"qa", "preprod" , "reg"})
    public void regRun(){
        System.out.println("Regression");
        Assert.assertTrue(false);
    }

    @Test(groups = {"dev", "stage"})
    public void smoke(){
        System.out.println("Smoke");
        Assert.assertTrue(false);
    }
}
