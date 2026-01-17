package tutorial.newfeatures.java15;

import org.junit.jupiter.api.Test;

public class SealedClassFeatures {
    public abstract sealed class Shape permits Rectangle, Square {
        abstract boolean isSquare();
    }

    public final class Rectangle extends Shape {
        @Override
        public boolean isSquare() {
            return false;
        }
    }

    public final class Square extends Shape {
        @Override
        public boolean isSquare() {
            return true;
        }
    }

    @Test
    public void testSealedClass() {
        Shape shape = new Rectangle();
        System.out.println(shape.isSquare());

        Shape square = new Square();
        System.out.println(square.isSquare());
    }


}
