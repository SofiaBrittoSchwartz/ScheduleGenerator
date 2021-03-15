package org.sofia.generator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalArgumentException
import java.time.LocalTime

class ShiftTest {
    @Test
    fun shiftIsValid() {
        val sr = ShiftRequirements()
        sr.setShiftLength(1.0, 2.0)
        sr.setNumTutors(1,3)
        val shift1 = TutoringShift(
            DayOfTheWeek.MONDAY,
            LocalTime.of(10, 20),
            LocalTime.of(11, 20),
            sr
        )
        shift1.addTutor(Tutor("Sofia", true, true))
        val shift2 = TutoringShift(
            DayOfTheWeek.MONDAY,
            LocalTime.of(10, 20),
            LocalTime.of(12, 40),
            sr
        )
        assertTrue(shift1.isValidShift(), "shift1 should be valid")
        assertFalse(shift2.isValidShift(), "shift2 should not be valid")
        assertThrows(IllegalArgumentException::class.java) {
            TutoringShift(
                DayOfTheWeek.MONDAY,
                LocalTime.of(10, 20),
                LocalTime.of(11, 20),
                null
            )
        }
        print(shift1.toString())
    }

}
