package org.sofia.generator;

import org.sofia.generator.core.DayOfTheWeek;
import org.sofia.generator.core.Shift;
import org.sofia.generator.core.ShiftException;
import org.sofia.generator.core.ShiftRequirements;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TutoringShift extends Shift {

    List<Tutor> tutorsWithCars;
    List<Tutor> experiencedTutors;

    public TutoringShift(DayOfTheWeek day, LocalTime startTime, LocalTime endTime, ShiftRequirements requirements) {
        super(requirements, day, startTime, endTime);
        tutorsWithCars = new ArrayList<>();
        experiencedTutors = new ArrayList<>();
    }

    /**
     * Throws ShiftException if adding tutor is invalid. Return type void and throw exception if fails
     * @param tutor
     */
    @Override
    public void doAddTutor(Tutor tutor) {
        if(isLastTutorSpot()) {
            if(experiencedTutors.isEmpty() && !tutor.isExperiencedTutor) {
                // invalid addition exception
                throw new ShiftException(3, "This shift has only spot left and no experienced tutors. Adding this tutor, who is inexperienced, would make this shift invalid.");
            }

            if(tutorsWithCars.isEmpty() && !tutor.hasCar) {
                // invalid addition exception or illegal shift state violation?
                throw new ShiftException(4, "This shift has only spot left and no tutors with cars. This tutor does not have a car and adding them would make this shift invalid.");
            }
        }

        if(tutor.isExperiencedTutor) {
            experiencedTutors.add(tutor);
        }

        if(tutor.hasCar) {
            tutorsWithCars.add(tutor);
        }
    }

    @Override
    protected void doRemoveTutor(Tutor tutor) {
        // TODO Implement doRemoveTutor
    }

    boolean isLastTutorSpot() {
        return getAssignedTutors().size() == getRequirements().getMaxTutors() - 1;
    }

    @Override
    public boolean validated() {
        return getRequirements().minCars <= tutorsWithCars.size()
                && getRequirements().minExperiencedTutors <= experiencedTutors.size();
    }
}
