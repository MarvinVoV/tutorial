package tutorial.newfeatures.java8;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;

public class Lambda {

    @Test
    @EnabledForJreRange(min = JRE.JAVA_8, max = JRE.JAVA_21)
    public void testLambda() {
        Runnable runnable = () -> System.out.println("Hello world");
        runnable.run();
    }


}
