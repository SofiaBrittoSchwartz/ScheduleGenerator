package org.sofia.generator;

import org.jetbrains.annotations.NotNull;

import java.time.LocalTime;

public class TimeInterval {
    private final DayOfTheWeek day;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public TimeInterval(DayOfTheWeek day, LocalTime startTime, LocalTime endTime) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public TimeInterval(int dayNum, LocalTime startTime, LocalTime endTime) {
        this.day = DayOfTheWeek.getDayByNumber(dayNum); // make dynamic (remove hardcoded value here)
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public DayOfTheWeek getDay() {
        return day;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    /**
     * Compares the start and end times to determine if the given TimeInterval, o, exists within the
     * current TimeInterval.
     * @param o The TimeInterval that may or may not be included in the current TimeInterval.
     * @return true or false if o is included in the current TimeInterval.
     */
    public boolean includes(@NotNull TimeInterval o) {
        return day.equals(o.day) && startTime.compareTo(o.getStartTime()) <= 0 && endTime.compareTo(o.getEndTime()) >= 0;
    }

    /**
     * Creates a new TimeInterval for the time overlap between the two TimeIntervals. If the two intervals
     * do not overlap, it will return null.
     * @param interval1 The first interval used to find the overlap
     * @param interval2 The second interval used to find the overlap
     * @return new TimeInterval representing the overlap between interval1 and interval2
     */
    public static TimeInterval findOverlap(@NotNull TimeInterval interval1, @NotNull TimeInterval interval2) {
        if(!interval1.day.equals(interval2.day)) return null;

        // if the two intervals don't overlap return null
        if(interval1.startTime.isAfter(interval2.endTime) || interval1.endTime.isBefore(interval2.startTime)) return null;

        LocalTime newStartTime = interval1.startTime.compareTo(interval2.startTime) >= 0 ? interval1.startTime : interval2.startTime;
        LocalTime newEndTime = interval1.endTime.compareTo(interval2.endTime) <= 0 ? interval1.endTime : interval2.endTime;

        return new TimeInterval(interval1.day, newStartTime, newEndTime);
    }
}
