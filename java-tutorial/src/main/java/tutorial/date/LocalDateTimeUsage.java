package tutorial.date;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUsage {
    @Test
    public void testUsage() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        System.out.println(now.getHour());

        LocalDateTime later = now.plusHours(1);
        System.out.println(later);


        String timeStr = later.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(timeStr);

        LocalDateTime time = LocalDateTime.parse("2023-10-30 23:40:25",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(time);
    }
}
