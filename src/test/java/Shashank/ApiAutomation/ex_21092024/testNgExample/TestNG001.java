package Shashank.ApiAutomation.ex_21092024.testNgExample;

import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TestNG001 {

//    Command for Allure Reporting
//    allure serve allure-results/

    @Severity(SeverityLevel.NORMAL)
    @Description("Verify that true == true")
    @Test
    public void testCase01(){
        Assert.assertEquals(true, true);
    }

    @Severity(SeverityLevel.BLOCKER)
    @Description("Verify that true != false")
    @Test
    public void testCase02(){
        Assert.assertEquals(true, false);
    }
}
