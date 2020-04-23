package dev.anindya.helloworld.database;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import java.util.Date;

public class DateConverterTest {

    @Test
    public void toDate() {
        final Long testLong = 123456789L;
        assertEquals(new Date(testLong), DateConverter.toDate(testLong));
    }

    @Test
    public void toTimestamp() {
        final Date date = new Date();
        assertEquals(new Long(date.getTime()), DateConverter.toTimestamp(date));
    }
}