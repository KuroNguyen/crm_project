package com.myclass.common;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Author: Nguyễn Chánh Trực
 * Created: 1/1/2021
 * Updated: 1/1/2021
 */
public class DateUtils {
    static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    static final DateTimeFormatter stringFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    /**
     * Convert from Date data to String format.
     *
     * @param date
     * @return date string
     */
    public static String dateToString (LocalDate date) {
//        System.out.println("DateToString - Input: " + date);
        if (date != null) {
//            System.out.println("DateToString - Output: " + stringFormat.format(date));
            return stringFormat.format(date);
        }
        return "";

    }

    /**
     * Convert from String data to Date format.
     *
     * @param dateString
     * @return LocalDate
     */
    public static LocalDate stringToDate (String dateString) {
//        System.out.println("Input: " + dateString);
//        System.out.println("Output: " + LocalDate.parse(dateString, dateFormat));
        return LocalDate.parse(dateString, dateFormat);
    }
}
