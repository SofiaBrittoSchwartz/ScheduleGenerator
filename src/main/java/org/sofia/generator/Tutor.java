package org.sofia.generator;

import java.util.HashMap;
import java.util.List;

public class Tutor {
    public String name;
    public List<TimeInterval> availabilities;
    /*
    * [0] - TimeInterval(day, startTime, endTime) - with Comparable functionality, in future could include timeZone
    * [1] - List (to hide implementation details)
    */
    public boolean isExperiencedTutor;
    public boolean hasCar;

    public Tutor(String name, boolean isExperiencedTutor, boolean hasCar) {
        this.name = name;
        this.isExperiencedTutor = isExperiencedTutor;
        this.hasCar = hasCar;
    }

    // why not make this part of constructor?
    public void setAvailabilities(List<TimeInterval> availabilities) {
        this.availabilities = availabilities;
    }

    // getAvailability(dayOfWeek) - get info from availabilities
}
