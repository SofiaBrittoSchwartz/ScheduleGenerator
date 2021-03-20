package org.sofia.generator.core;

import org.sofia.generator.Tutor;

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

    public Shift(ShiftRequirements requirements, DayOfTheWeek day, LocalTime startTime, LocalTime endTime) throws IllegalArgumentException{
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

    public List<Tutor> getAssignedTutors() {
        return assignedTutors;
    }

    public ShiftRequirements getRequirements() {
        return requirements;
    }

    public final void addAll(List<Tutor> tutors) {
        for(Tutor tutor: tutors) {
            addTutor(tutor);
        }
    }

    /**
     * Put generic checks here
     */
    public final void addTutor(Tutor tutor) throws ShiftException {
        if(assignedTutors.size() >= requirements.maxTutors) throw new ShiftException(1, "This shift is full. No more tutors can be added.");
        // maybe just return if tutor already in the shift instead of throwing error - this should never occur anyway...
        if(assignedTutors.contains(tutor)) throw new ShiftException(2, "This tutor is already assigned to this shift.");
        doAddTutor(tutor);
        assignedTutors.add(tutor);
    }

    protected abstract void doAddTutor(Tutor tutor);

    public final void removeTutor(Tutor tutor) {
        // TODO Implement removeTutor
    }

    protected abstract void doRemoveTutor(Tutor tutor);

    public final boolean isValidShift() {
        boolean isValid = true;
        if(requirements == null || shiftTimes.getStartTime() == null || shiftTimes.getEndTime() == null) return false;

        long minInShift = Duration.between(shiftTimes.getStartTime(), shiftTimes.getEndTime()).getSeconds() / 60;
        isValid = isValid &&
                (minInShift <= requirements.maxMinutesPerShift) &&
                (minInShift >= requirements.minMinutesPerShift);

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
