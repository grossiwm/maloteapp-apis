package com.gabrielrossilopes.appmalote.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtils {


    public static String dataFormatada(LocalDateTime dateTime) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y h:m:s");
        return dateTime.format(formatter);
    }
}
