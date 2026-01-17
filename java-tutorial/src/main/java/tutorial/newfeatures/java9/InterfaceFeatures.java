package tutorial.newfeatures.java9;

public class InterfaceFeatures {

    public interface MyInterface {

        void hello();


        private static void myPrivateMethod() {
            System.out.println("Yay, I am private!");
        }
    }
}
