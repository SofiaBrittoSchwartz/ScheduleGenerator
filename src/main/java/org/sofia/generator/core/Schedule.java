package org.sofia.generator.core;

import org.sofia.generator.TutoringShift;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Schedule {
    List<List<Shift>> shifts;

    public Schedule() {
        this.shifts = generateDefaultSchedule();
        System.out.println(toString());
    }

    private List<List<Shift>> generateDefaultSchedule() {
        List<List<Shift>> s = new ArrayList<>(7);
        ShiftRequirements requirements = new ShiftRequirements();
        requirements.setShiftLength(1.0, 2.0);
        requirements.setNumTutors(1, 3);

        int startDay = 1;
        int endDay = 6;
        int startTime = 8;
        int endTime = 17;
        int baseShiftLengthInHours = 1;

        for(int i = 0; i < 7; i++) {
            s.add(new ArrayList<>());
            for(int j = startTime; j < endTime; j += baseShiftLengthInHours) {
                s.get(i).add(
                        // TODO: Don't want to use TutoringShift here.
                        //  Should make Schedule class abstract and require TutoringSchedule to implement this?
                        new TutoringShift(
                                DayOfTheWeek.getDayByNumber(i),
                                LocalTime.of(j, 0),
                                LocalTime.of(j + baseShiftLengthInHours, 0),
                                requirements
                        )
                );
            }
        }
        return s;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(List<Shift> day : shifts){
            for(Shift s : day) {
                builder.append(s.toString() + "\n");
            }
        }

        return builder.toString();
    }
}
