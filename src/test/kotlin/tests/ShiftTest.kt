package org.sofia.generator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.sofia.generator.core.DayOfTheWeek
import org.sofia.generator.core.ShiftException
import org.sofia.generator.core.ShiftRequirements
import java.lang.IllegalArgumentException
import java.time.LocalTime

class ShiftTest {
    companion object {

        fun setShiftRequirements(): ShiftRequirements? {
            val sr = ShiftRequirements()
            sr.setNumTutors(1,3)
            sr.setShiftLength(1.0, 2.0)
            return sr
        }

        val shift1 = TutoringShift(
            DayOfTheWeek.MONDAY,
            LocalTime.of(10, 20),
            LocalTime.of(11, 20),
            setShiftRequirements()
        )

        val shift2 = TutoringShift(
            DayOfTheWeek.MONDAY,
            LocalTime.of(10, 20),
            LocalTime.of(12, 20),
            setShiftRequirements()
        )

        val shift3 = TutoringShift(
            DayOfTheWeek.FRIDAY,
            LocalTime.of(10, 20),
            LocalTime.of(12, 20),
            setShiftRequirements()
        )
    }

    @AfterEach
    fun clearTutors() {
        shift1.assignedTutors.clear()
        shift1.experiencedTutors.clear()
        shift1.tutorsWithCars.clear()

        shift2.assignedTutors.clear()
        shift2.experiencedTutors.clear()
        shift2.tutorsWithCars.clear()

        shift3.assignedTutors.clear()
        shift3.experiencedTutors.clear()
        shift3.tutorsWithCars.clear()
    }

    @Test
    fun shiftIsValid() {
        shift1.addTutor(
            Tutor("Felipe", true, true, null)
        )
        assertTrue(shift1.isValidShift(), "shift1 should be valid")

        assertFalse(shift2.isValidShift(), "shift2 should be invalid because it doesn't have any assignedTutors")
        shift2.addTutor(Tutor("Geraldinho", true, false, null))
        assertFalse(shift2.isValidShift(), "There isn't a tutor with a car.")

        shift3.addTutor(Tutor("Hamza", false, true, null))
        assertFalse(shift3.isValidShift(), "There isn't an experienced tutor.")
        shift3.addTutor(Tutor("Ignatius", true, false, null))
        assertTrue(shift3.isValidShift(), "All requirements should be met.")
    }

    @Test
    fun illegalArgumentExceptionTest() {
        assertThrows(IllegalArgumentException::class.java) {
            TutoringShift(
                DayOfTheWeek.MONDAY,
                LocalTime.of(10, 20),
                LocalTime.of(11, 20),
                null
            )
        }
    }

    @Test
    fun addTutorTest() {
        val tutors = ArrayList<Tutor>()
        tutors.add(Tutor("Abeline", false, false, null))
        tutors.add(Tutor("Beatrice", false, false, null))
        shift1.addAll(tutors)

        // Throws exception because shift requires at least one experienced tutor
        assertThrows(ShiftException::class.java) {
            shift1.addTutor(Tutor("Calliope", false, true, null))
        }

        // Throws exception because shift requires at least one tutor with a car
        assertThrows(ShiftException::class.java) {
            shift1.addTutor(Tutor("Devine", true, false, null))
        }

        shift1.addTutor(Tutor("Excelsior", true, true, null))
        assertTrue(shift1.isValidShift())
    }
}
