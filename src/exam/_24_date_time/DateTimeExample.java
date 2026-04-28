package exam._24_date_time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeExample {
    public static void main(String[] args) {
        System.out.println(addDays("2026-04-26", 7));
        System.out.println(daysBetween("2026-04-01", "2026-04-26"));
        System.out.println(minutesBetween("2026-04-26 10:00:00", "2026-04-26 12:30:00"));
    }

    public static String addDays(String dateText, long days) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(dateText, formatter);
        return date.plusDays(days).format(formatter);
    }

    public static long daysBetween(String startText, String endText) {
        LocalDate start = LocalDate.parse(startText);
        LocalDate end = LocalDate.parse(endText);
        return ChronoUnit.DAYS.between(start, end);
    }

    public static long minutesBetween(String startText, String endText) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse(startText, formatter);
        LocalDateTime end = LocalDateTime.parse(endText, formatter);

        Duration duration = Duration.between(start, end);
        return duration.toMinutes();
    }
}
