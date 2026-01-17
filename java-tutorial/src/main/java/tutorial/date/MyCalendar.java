package tutorial.date;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class MyCalendar {
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        int month = date.getMonthValue();
        int today = date.getDayOfMonth();

        // set to start of month
        date = date.minusDays(today - 1);
        DayOfWeek weekDay = date.getDayOfWeek();
        // 1 = Monday, 2 = Tuesday, etc
        int value = weekDay.getValue();

        System.out.println("Mon Tues Weds Thu Fri Sat Sun");
        for (int i = 1; i < value; i++) {
            System.out.print("    ");
        }
        while (date.getMonthValue() == month) {
            System.out.printf("%3d", date.getDayOfMonth());
            if (date.getDayOfMonth() == today) {
                System.out.print("*");
            } else {
                System.out.print(" ");
            }
            date = date.plusDays(1);
            if (date.getDayOfWeek().getValue() == 1) {
                System.out.println();
            }
        }
        if (date.getDayOfWeek().getValue() != 1) {
            System.out.println();
        }
    }
}
