/**
 *
 * @author PS19229 - TRAN HOANG THUY VAN
 */
package com.org.app.util;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;


public abstract class CalendarAndClock extends TimerTask{
    public CalendarAndClock() {

    }
    

    public static class Clock {
        final static DateTimeFormatter format = DateTimeFormatter.ofPattern("hh':'mm':'ss");        
        public static String getTime() {
            LocalTime now = LocalTime.now();
            return now.format(format);
        }
        
        public static String getAMPM() {
            return LocalTime.now().format(DateTimeFormatter.ofPattern("a"));
        }
    }
    
    
    public static class Calendar {
        final static DateTimeFormatter format = DateTimeFormatter.ofPattern("dd'-'MM'-'yyyy");
        public static String getDate() {
            LocalDate date = LocalDate.now();
            String d = getDateOfWeek(date.getDayOfWeek().getValue() - 1)+", ";
            return d + date.format(format);
        }
        
        public static String getE(LocalDate date) {
            String d = getDateOfWeek(date.getDayOfWeek().getValue() - 1);
            return d;
        }
        
        
        private static String getDateOfWeek(int i) {
            String[] d = {"Thứ Hai","Thứ Ba","Thứ Tư", "Thứ Năm","Thứ Sáu","Thứ Bảy", "Chủ Nhật"};
            return d[i];
        }
    }
     
    public static void main(String[] args) {
        LocalDate date = LocalDate.now();
        System.out.println(date);
        System.out.println(date.getDayOfWeek().getValue() - 1);
        System.out.println(Calendar.getDate());
        System.out.println("ngày thứ: " + Calendar.getDateOfWeek(6));
        
        LocalDate localDate1 = LocalDate.parse("2022-04-08");
        System.out.println("localDate1: " + localDate1); // 2011-11-20
        System.out.println("localDate1: " + Calendar.getE(localDate1));
    }
}
