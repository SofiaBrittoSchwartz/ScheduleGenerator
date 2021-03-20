package org.sofia.generator;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

// create a Shift interface that makes the following required:
// shiftTimes, requirements
public abstract class Shift {
    TimeInterval shiftTimes; // will use LocalTime.of(int hour, int minute) to create the times
    ShiftRequirements requirements;
    List<Tutor> assignedTutors;

    public Shift(ShiftRequirements requirements, DayOfTheWeek day, LocalTime startTime, LocalTime endTime) {
        if(requirements == null) throw new IllegalArgumentException("requirements must be nonNull");
        this.requirements = requirements;
        this.shiftTimes = new TimeInterval(day, startTime, endTime);
        this.assignedTutors = new ArrayList<>();
    }

    public LocalTime getStartTime() {
        return shiftTimes.getStartTime();
    }

    public LocalTime getEndTime() {
        return shiftTimes.getEndTime();
    }

    /**
     * Put generic checks here
     */
    public final int addTutor(Tutor tutor) throws ShiftException {
        if(assignedTutors.size() >= requirements.maxTutors) throw new ShiftException(1, "No more tutors can be added to this shift.");
        if(assignedTutors.contains(tutor)) throw new ShiftException(2, "This tutor is already assigned to this shift.");
        return doAddTutor(tutor);
    }

    protected abstract int doAddTutor(Tutor tutor);

    public final boolean isValidShift() {
        boolean isValid = true;
        if(requirements == null || shiftTimes.getStartTime() == null || shiftTimes.getEndTime() == null) return false;

        long minInShift = Duration.between(shiftTimes.getStartTime(), shiftTimes.getEndTime()).getSeconds() / 60;
        isValid = isValid &&
                (minInShift <= requirements.maxMinutesPerShift) &&
                (minInShift <= requirements.minMinutesPerShift);

        isValid = isValid &&
                (requirements.minTutors <= assignedTutors.size()) &&
                (requirements.maxTutors >= assignedTutors.size());

        return isValid && validated();
    }

    public abstract boolean validated();

    public String toString() {
        StringBuilder builder = new StringBuilder();
        String valid = isValidShift() ? "✔" : "X";
        builder.append("\n" + valid + " " + shiftTimes.getDay() + "\t" + shiftTimes.getStartTime().toString() + " - " + shiftTimes.getEndTime().toString() + "\n");

        builder.append("tutors: ");
        if(assignedTutors.size() > 0) {
            builder.append(assignedTutors.get(0).name);

            for(int i = 1; i < assignedTutors.size(); i++) {
                builder.append(", " + assignedTutors.get(i).name);
            }
        }

        return builder.toString();
    }
}
