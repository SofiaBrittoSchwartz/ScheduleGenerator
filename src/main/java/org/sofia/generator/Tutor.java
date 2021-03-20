package org.sofia.generator;

import org.sofia.generator.core.TimeInterval;

import java.util.List;

//TODO: Make this extend Employee abstract class that contains name and availabilities
public class Tutor {
    public String name;
    public List<TimeInterval> availabilities;
    public boolean isExperiencedTutor;
    public boolean hasCar;

    public Tutor(String name, boolean isExperiencedTutor, boolean hasCar, List<TimeInterval> availabilities) {
        this.name = name;
        this.isExperiencedTutor = isExperiencedTutor;
        this.hasCar = hasCar;
    }

    // TODO: Implement getAvailability(dayOfWeek) - Get tutor's availabilities on given day.

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("\n" + name + "\n\t");
        builder.append("isExperienced: " + (isExperiencedTutor ? "✔" : "X") + "\t");
        builder.append("hasCar: " + (hasCar ? "✔" : "X"));

        return builder.toString();
    }
}
