package tutorial.newfeatures.java11;

import org.junit.jupiter.api.Test;

public class StringFeatures {
    @Test
    public void testString() {
        System.out.println("Marco".isBlank());
        System.out.println("Mar\nco".lines().count());
        System.out.println("Marco  ".strip());
    }
}
