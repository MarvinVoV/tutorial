package tutorial.newfeatures.java9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class StreamFeatures {
    /**
     * In Java9, the <code>takeWhile</code> method allows us to take elements from a stream while a specified
     * condition holds true. When the condition becomes false for the first time, the <code>takeWhile</code>
     * operation stops, and the resulting stream will contain only the elements that satisfied the condition
     * up to that point.
     */
    @Test
    @EnabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_21)
    public void testStream() {
        List<Integer> numbers = List.of(2, 4, 6, 8, 7, 10, 12, 14);
        List<Integer> evenNumbers = numbers.stream().takeWhile(num -> num % 2 == 0).toList();
        System.out.println("Elements that satisfy the condition: " + evenNumbers);
    }

    @Test
    @EnabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_21)
    public void testOptional() {
        Optional<String> optionalWithValue = Optional.of("Hello, World!");

        Consumer<String> valueAction = (String s) -> System.out.println("Value is present: " + s);

        Runnable emptyAction = () -> System.out.println("Value is absent");

        // Using ifPresentOrElse
        optionalWithValue.ifPresentOrElse(
                valueAction,  // Action if a value is present
                emptyAction   // Action if the Optional is empty
        );

        // Create an empty Optional
        Optional<String> optionalEmpty = Optional.empty();

        // Using ifPresentOrElse with an empty Optional
        optionalEmpty.ifPresentOrElse(
                valueAction,  // Action if a value is present (won't be executed in this case)
                emptyAction   // Action if the Optional is empty
        );
    }
}

