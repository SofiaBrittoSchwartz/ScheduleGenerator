package org.sofia.generator;

public class ShiftRequirements {
    // should this be static?
    // shift length is stored in minutes instead of hours to allow for further flexibility
    // and to make future calculations simpler.
    int minMinutesPerShift;
    int maxMinutesPerShift;
    int minTutors;
    int maxTutors;

    // move these to Shift class?
    int minCars = 1;
    int minExperiencedTutors = 1;

    public void setShiftLength(double minHoursPerShift, double maxHoursPerShift) {
        this.minMinutesPerShift = (int)(minHoursPerShift * 60);
        this.maxMinutesPerShift = (int)(maxHoursPerShift * 60);
    }

    public void setShiftLength(int minMinutesPerShift, int maxMinutesPerShift) {
        this.minMinutesPerShift = minMinutesPerShift;
        this.maxMinutesPerShift = maxMinutesPerShift;
    }

    public void setNumTutors(int minTutors, int maxTutors) {
        this.minTutors = minTutors;
        this.maxTutors = maxTutors;
    }
}
