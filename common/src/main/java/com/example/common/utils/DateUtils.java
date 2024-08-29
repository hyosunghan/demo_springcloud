package com.example.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

@Slf4j
public class DateUtils {

    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    public static void main(String[] args) {
        LocalDateTime localDateTime = date2LocalDateTime(new Date());
        System.out.println(localDateTime);
        Date date = localDateTime2Date(localDateTime);
        System.out.println(date);
    }
}
