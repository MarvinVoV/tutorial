package tutorial.newfeatures.java17;

import org.junit.jupiter.api.Test;

public class PatternMatchFeatures {
    public String testInstance(Object obj) {
        return switch (obj) {
            case Integer i -> "Integer:" + i;
            case String s -> "String:" + s;
            default -> "Unknown";
        };
    }

    @Test
    public void testPatternMatch() {
        String str = "hello";
        System.out.println(testInstance(str));
    }
}
