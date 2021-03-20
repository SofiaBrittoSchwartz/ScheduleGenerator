package org.sofia.generator

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.sofia.generator.core.TimeInterval
import java.time.LocalTime

class TimeIntervalTest {

    companion object {
        val interval1 = TimeInterval(
            1,
            LocalTime.of(10, 0),
            LocalTime.of(11, 30)
        )
        val interval2 = TimeInterval(
            1,
            LocalTime.of(10, 10),
            LocalTime.of(11, 0)
        )
        val interval3 = TimeInterval(
            2,
            LocalTime.of(10, 10),
            LocalTime.of(11, 0)
        )
        val interval4 = TimeInterval(
            2,
            LocalTime.of(11, 30),
            LocalTime.of(12, 0)
        )
    }

    @Test
    fun testIncludes() {
        assertTrue(interval1.includes(interval2))
        assertFalse(interval2.includes(interval1))
        assertFalse(interval1.includes(interval3))
    }

    @Test
    fun testFindOverlap() {
        val actualOverlap1 = TimeInterval.findOverlap(interval1, interval2)
        val actualOverlap2 = TimeInterval.findOverlap(interval2, interval1)

        val expectedOverlap1 = TimeInterval(
            1,
            LocalTime.of(10, 10),
            LocalTime.of(11, 0)
        )

        assertEquals(expectedOverlap1.startTime, actualOverlap1.startTime)
        assertEquals(expectedOverlap1.endTime, actualOverlap1.endTime)

        // order doesn't matter for finding overlap
        assertEquals(actualOverlap1.startTime, actualOverlap2.startTime)
        assertEquals(actualOverlap1.endTime, actualOverlap2.endTime)

        // will return null if intervals don't overlap
        assertNull(TimeInterval.findOverlap(interval3, interval4))
        assertNull(TimeInterval.findOverlap(interval4, interval3))

        // will return null if time intervals are on different days
        assertNull(TimeInterval.findOverlap(interval1, interval3))
    }
}
