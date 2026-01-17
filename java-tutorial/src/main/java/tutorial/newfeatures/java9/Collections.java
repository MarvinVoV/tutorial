package tutorial.newfeatures.java9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledForJreRange;
import org.junit.jupiter.api.condition.JRE;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Collections {
    @Test
    @EnabledForJreRange(min = JRE.JAVA_9, max = JRE.JAVA_21)
    public void testCollections() {
        List<String> list = List.of("1", "2", "3");
        list.forEach(System.out::println);

        Set<String> set = Set.of("1", "2", "3");
        set.forEach(System.out::println);

        Map<String, String> map = Map.of("1", "2", "3", "4");
        map.forEach((k, v) -> System.out.println(k + ":" + v));


    }
}
