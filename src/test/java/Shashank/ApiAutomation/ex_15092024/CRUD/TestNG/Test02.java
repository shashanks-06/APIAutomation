package Shashank.ApiAutomation.ex_15092024.CRUD.TestNG;

import org.testng.annotations.*;

public class Test02 {
    @BeforeSuite
    public void demo1(){
        System.out.println("BeforeSuite");
        System.out.println("Read the data from the MySQL");
    }

    @BeforeTest
    public void demo2(){
        System.out.println("BeforeTest");
        System.out.println("Data in Matrix, TC Before");
    }

    @BeforeClass
    public void demo3(){
        System.out.println("BeforeClass");
    }

    @BeforeMethod
    public void demo4(){
        System.out.println("BeforeMethod");
    }

    @Test
    public void demo5(){
        System.out.println("Test");
    }

    @AfterMethod
    public void demo6(){
        System.out.println("AfterMethod");
    }

    @AfterClass
    public void demo7(){
        System.out.println("AfterClass");
    }

    @AfterTest
    public void demo8(){
        System.out.println("AfterTest");
    }

    @AfterSuite
    public void demo9(){
        System.out.println("AfterSuite");
        System.out.println("Close everything, Delete all the temp files");
    }
}
