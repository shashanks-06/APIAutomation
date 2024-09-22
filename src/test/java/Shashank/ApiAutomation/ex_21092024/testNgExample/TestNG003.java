package Shashank.ApiAutomation.ex_21092024.testNgExample;


import org.testng.annotations.Test;

import static org.testng.TestRunner.PriorityWeight.priority;

public class TestNG003 {

    @Test(priority = 1)
    public void test01(){
        System.out.println("1");
    }

    @Test(priority = 2)
    public void test02(){
        System.out.println("3");
    }

    @Test(priority = 0)
    public void test03(){
        System.out.println("2");
    }
}
