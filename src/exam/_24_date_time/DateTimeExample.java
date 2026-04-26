package exam._24_date_time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeExample {
    public static void main(String[] args) {
        parseAndFormatDate();
        calculateDateDiff();
        calculateDateTimeDiff();
    }

    private static void parseAndFormatDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse("2026-04-26", formatter);
        System.out.println(date.plusDays(7).format(formatter));
    }

    private static void calculateDateDiff() {
        LocalDate start = LocalDate.of(2026, 4, 1);
        LocalDate end = LocalDate.of(2026, 4, 26);
        long days = ChronoUnit.DAYS.between(start, end);
        System.out.println(days);
    }

    private static void calculateDateTimeDiff() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime start = LocalDateTime.parse("2026-04-26 10:00:00", formatter);
        LocalDateTime end = LocalDateTime.parse("2026-04-26 12:30:00", formatter);

        Duration duration = Duration.between(start, end);
        System.out.println(duration.toMinutes());
    }
}
