package tutorial.newfeatures.java14;

import org.junit.jupiter.api.Test;

public class PatternMatchFeatures {
    @Test
    public void testPatternMatch() {
        Object obj = "hello";
        // Old
        if (obj instanceof String) {
            String s = (String) obj;
            System.out.println(s);
        }

        // New
        if (obj instanceof String s) {
            System.out.println(s);
        }
    }
}
