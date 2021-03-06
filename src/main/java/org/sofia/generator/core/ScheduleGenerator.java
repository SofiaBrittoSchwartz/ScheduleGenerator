package org.sofia.generator.core;

import org.sofia.generator.Tutor;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScheduleGenerator {
    public static void main(String[] args) {
        List<Tutor> tutors = new ArrayList<>();
        Tutor tutor1 = new Tutor("Alice", true, true, new ArrayList<>());
    }

    private static HashMap generateDefaultAvailabilities() {
        HashMap avails = new HashMap();
        for(int i = 0; i < 7; i++) {
            for(int j = 8; j < 17; j++) {
                avails.put(
                        new TimeInterval(
                                i,
                                LocalTime.of(j, 0),
                                LocalTime.of(j+1, 0)
                        ),
                        true
                );
            }
        }
        return avails;
    }
}
