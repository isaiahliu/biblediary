package org.trinity.biblediary.common.message.lookup;

import java.util.Calendar;
import java.util.Date;

import org.trinity.message.ILookupMessage;

public enum TimeZone implements ILookupMessage<LookupType> {
    UTC_WEST_12("W12", -20),
    UTC_WEST_11("W11", -19),
    UTC_WEST_10("W10", -18),
    UTC_WEST_9("W9", -17),
    UTC_WEST_8("W8", -16),
    UTC_WEST_7("W7", -15),
    UTC_WEST_6("W6", -14),
    UTC_WEST_5("W5", -13),
    UTC_WEST_4("W4", -12),
    UTC_WEST_3("W3", -11),
    UTC_WEST_2("W2", -10),
    UTC_WEST_1("W1", -9),
    UTC("UTC", -8),
    UTC_EAST_1("E1", -7),
    UTC_EAST_2("E2", -6),
    UTC_EAST_3("E3", -5),
    UTC_EAST_4("E4", -4),
    UTC_EAST_5("E5", -3),
    UTC_EAST_6("E6", -2),
    UTC_EAST_7("E7", -1),
    UTC_EAST_8("E8", 0),
    UTC_EAST_9("E9", 1),
    UTC_EAST_10("E10", 2),
    UTC_EAST_11("E11", 3),
    UTC_EAST_12("E12", 4);

    private final String messageCode;

    private int offsetHours;

    private TimeZone(final String messageCode, final int offsetHours) {
        this.messageCode = messageCode;
        this.offsetHours = offsetHours;
    }

    public Date getLocalTime() {
        return getLocalTime(new Date());
    }

    public Date getLocalTime(final Date date) {
        if (date == null) {
            return getLocalTime();
        }

        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.HOUR_OF_DAY, offsetHours);

        return calendar.getTime();
    }

    @Override
    public String getMessageCode() {
        return messageCode;
    }

    @Override
    public LookupType getMessageType() {
        return LookupType.TIME_ZONE;
    }
}
