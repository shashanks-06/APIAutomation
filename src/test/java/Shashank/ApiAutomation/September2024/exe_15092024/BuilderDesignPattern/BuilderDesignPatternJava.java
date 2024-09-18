package Shashank.ApiAutomation.September2024.exe_15092024.BuilderDesignPattern;

public class BuilderDesignPatternJava {
    // Change return type of each method as a Class type
    // "this" always points to the current/calling object. Returning the same to have same reference

    public BuilderDesignPatternJava Floor1(){
        System.out.println("Floor 1 is done");
        return this;
    }
    public BuilderDesignPatternJava Floor2(String param){
        System.out.println("Floor 2 is done");
        return this;
    }
    public BuilderDesignPatternJava Floor3(){
        System.out.println("Floor 3 is done");
        return this;
    }

    public static void main(String[] args) {
        BuilderDesignPatternJava bdpj = new BuilderDesignPatternJava();
        bdpj.Floor1().Floor2("Shashank").Floor3();
    }
}
