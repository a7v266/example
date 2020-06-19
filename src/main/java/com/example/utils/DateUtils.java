package com.example.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.TimeZone;

public class DateUtils {

    public static final TimeZone UTC_TIME_ZONE = TimeZone.getTimeZone("UTC");
    public static final TimeZone MSK_TIME_ZONE = TimeZone.getTimeZone("Europe/Moscow");
    public static final ZoneId MSK_ZONE_ID = ZoneId.of("Europe/Moscow");
    public static final ZoneOffset MSK_ZONE_OFFSET = ZoneOffset.of("+3");


    public static Instant getInstant(Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Instant) {
            return (Instant) object;
        }
        if (object instanceof Timestamp) {
            return ((Timestamp) object).toInstant();
        }
        if (object instanceof LocalDateTime) {
            return ((LocalDateTime) object).toInstant(MSK_ZONE_OFFSET);
        }
        return null;
    }
}
