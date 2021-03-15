package org.sofia.generator;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TutoringShift extends Shift{

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
     * @return number representing the success or failure of adding the tutor. 1 for success, -1 for failure
     */
    @Override
    public int doAddTutor(Tutor tutor) {
        // if tutors are still needed for this shift and this tutor hasn't been added to this shift yet
        if(assignedTutors.size() < requirements.maxTutors && !assignedTutors.contains(tutor)) {
            assignedTutors.add(tutor);
            // if too many inexperienced tutors and can't add another inexperienced tutor, throw new ShiftException with corresponding errorCode (maybe make this into an Enum)
            if(tutor.isExperiencedTutor) experiencedTutors.add(tutor);
            if(tutor.hasCar) tutorsWithCars.add(tutor);
            return 1;
        }

        return -1;
    }

    @Override
    public boolean validated() {
        return requirements.minCars <= tutorsWithCars.size()
                && requirements.minExperiencedTutors <= experiencedTutors.size();
    }
}
