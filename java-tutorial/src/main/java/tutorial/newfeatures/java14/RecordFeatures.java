package tutorial.newfeatures.java14;

import org.junit.jupiter.api.Test;

public class RecordFeatures {

    record Point(int x, int y) {}

    @Test
    public void testRecord() {
        Point p = new Point(1, 2);
        System.out.println(p.x);
        System.out.println(p.y);
    }
}
