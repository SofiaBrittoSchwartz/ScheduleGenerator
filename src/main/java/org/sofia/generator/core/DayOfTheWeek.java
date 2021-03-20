package org.sofia.generator;

public enum DayOfTheWeek {
    SUNDAY,
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY;

    public int getDayNum() {
        return -1;
    }

    /**
     * @param dayNum The numerical representation of the day of the week. Sunday is 0, Monday is 1, etc.
     * @return The corresponding day of the week.
     */
    public static DayOfTheWeek getDayByNumber(int dayNum) {
        return values()[dayNum];
    }
}
