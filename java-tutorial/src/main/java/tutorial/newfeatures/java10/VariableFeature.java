package tutorial.newfeatures.java10;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import static org.junit.jupiter.api.condition.JRE.*;

public class VariableFeature {

    @Test
    @EnabledForJreRange(min = JAVA_10, max = JAVA_21)
    public void testVariable() {
        var name = "Tom";
        System.out.println(name);
        System.out.println(name.getClass().getSimpleName());
    }
}
