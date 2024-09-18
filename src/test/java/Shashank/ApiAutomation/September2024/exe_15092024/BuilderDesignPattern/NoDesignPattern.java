package Shashank.ApiAutomation.September2024.exe_15092024.BuilderDesignPattern;

public class NoDesignPattern {
    public void stage1(){
        System.out.println("Stage 1");
    }
    public void stage2(String str){
        System.out.println("Stage 2");
    }
    public void stage3(){
        System.out.println("Stage 3");
    }

    public static void main(String[] args) {
        NoDesignPattern ndp = new NoDesignPattern();
        ndp.stage1();
        ndp.stage2("Shashank");
        ndp.stage3();
    }
}
