package converter;

import com.opencsv.bean.AbstractBeanField;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LocalDateTimeConverter extends AbstractBeanField {

    private DateTimeFormatter formatter;

    public LocalDateTimeConverter() {
        Locale.setDefault(Locale.ENGLISH);
        formatter = DateTimeFormatter.ofPattern("M/d/yyyy h:mm:ss.SSSSSSSSS a");
    }

    @Override
    protected Object convert(String s) {
        return LocalDateTime.parse(s, formatter);
    }
}
