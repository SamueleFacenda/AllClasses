package all.quarta.prove;

public class MainTestEred {
    static class A{
        public void fai(Object a){
            System.out.println("object");
        }
        public void fai(A a){
            System.out.println("A");
            //System.out.println(a.getClass().toString());
        }
    }
    static class B extends A{
        public void fai(B a){
            System.out.println("B");
            //System.out.println(a.getClass().toString());
        }
    }
    static class C extends B{
        public void fai(C a){
            System.out.println("C");
            //System.out.println(a.getClass().toString());
        }
    }

    public static void main(String[] args) {
        new A().fai(null);
        new B().fai(null);
        new C().fai(null);
    }
}
