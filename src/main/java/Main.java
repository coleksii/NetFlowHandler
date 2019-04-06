import services.MainEngine;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {

        Locale.setDefault(Locale.ENGLISH);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm:ss.SSSSSSSSS a");
        LocalDateTime start = LocalDateTime.parse("7/13/2018 5:23:22.000000000 PM", formatter);
        LocalDateTime end = LocalDateTime.parse("7/18/2018 5:16:29.000000000 PM", formatter);


        MainEngine engine = new MainEngine();
        engine.startEngine(start, end);
    }
}
