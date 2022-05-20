package all.prove;

import java.util.ArrayList;

public class VerificaTest {
    static class A{}
    static class B extends A{}
    static class C extends B{}

    public static void main(){
        C gamma = new C();
        B beta = gamma;
        A alpha = gamma;
        System.out.println(alpha instanceof A);


        System.out.println((new ArrayList<String>()) instanceof Object);
    }
}
