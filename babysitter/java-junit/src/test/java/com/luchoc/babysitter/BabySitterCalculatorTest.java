package com.luchoc.babysitter;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit Tests for the Babysitter Calculator class.
 */
public class BabySitterCalculatorTest {

    /**
     * The shared calculator instance.
     */
    private BabySitterCalculator calc;

    /**
     * Setup for other tests.
     */
    @Before
    public final void setup() {
        calc = new BabySitterCalculator();
    }

    /**
     * Test that sitter cannot start before 5PM.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculatorThrowsExceptionWhenStartTimeBefore17() {
        calc.setTimes(16, 18, 0);
    }

    /**
     * Test that sitter cannot finish after 4AM.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculatorThrowsExceptionWhenStopTimeAfter4() {
        calc.setTimes(18, 0, 5);
    }

    /**
     * Test that rate for working from start to bed is 12 dollars/hour.
     */
    @Test
    public final void whenSitterWorksFromStartToBedTheyGet12DollarsPerHour() {
        calc.setTimes(18, 19, 19);
        assertEquals(12, calc.calculate());
    }

    /**
     * Test that calculator can handle start times after midnight.
     */
    @Test
    public final void calculatorDoesNotFailWhenStartTimeAfterMidnight() {
        calc.setTimes(2, 3, 3);
    }

    /**
     * Test that calculator can handle whEn the stop time is before midnight.
     */
    @Test
    public final void calculatorDoesNotFailWhenStopTimeBeforeMidnight() {
        calc.setTimes(18, 19, 23);
    }

    /**
     * Test that calculator returns error when a time is negative.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculatorThrowsExceptionWhenATimeIsNegative() {
        calc.setTimes(-1,  0, 3);
    }

    /**
     * Test that calculator returns error when time is above 24.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculatorThrowsExceptionWhenaATimeIsAbove24() {
        calc.setTimes(17,  0, 25);
    }

    /**
     * Test that calculator returns error when time is negative and above 24.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculatorThrowsExceptionWhenTimeIsNegativeAndAbove24() {
        calc.setTimes(-1,  0, 25);
    }

    /**
     * Test that rate from bed to midnight is 8 dollars/hour.
     */
    @Test
    public final void whenSitterWorksFromBedtimeToMidRateIs8DollarsPerHour() {
        calc.setTimes(18, 18, 0);
        assertEquals(6 * 8, calc.calculate());
    }

    /**
     * Test that calculator returns error when bedtime is before start time.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculatorThrowsExceptionWhenBedTimeIsBeforeStartTime() {
        calc.setTimes(20, 19, 0);
    }

    /**
     * Test that calculator returns error when stop time is before start time.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculatorErrorWhenStopTimeimeIsBeforeStartTime() {
        calc.setTimes(21, 21, 20);
    }

    /**
     * Test that calculator returns error when stop time is before bed time.
     */
    @Test(expected = IllegalArgumentException.class)
    public final void calculatorThrowsExceptionWhenStopTimeIsBeforeBedTime() {
        calc.setTimes(21, 23, 22);
    }

    /**
     * Test that rate from midnight to end of job is 16 dollars/hour.
     */
    @Test
    public final void whenSitterWorksFromMidnightToEndGet16DollarsPerHour() {
        calc.setTimes(0, 0, 4);
        assertEquals(16 * 4, calc.calculate());
    }

    /**
     * Test a job.
     * start - bed  -> 3 hours at 12 dollars/hour
     * bed - mid    -> 4 hours at 8 dollars/hour
     * mid - end    -> 1 hour at 16 dollars/hour
     */
    @Test
    public final void whenSitterWorksFullJobTheyGetCorrectAmountForAllRates() {
        calc.setTimes(17, 20, 1);
        assertEquals(3 * 12 + 4 * 8 + 16, calc.calculate());
    }

    /**
     * Test a job.
     * start - bed  -> 3 hours at 12 dollars/hour
     * bed - end    -> 2 hours at 8 dollars/hour
     */
    @Test
    public final void whenSitterFinishesBeforeMidnightCorrectRatesApply() {
        // start - bed -> 3 hours (12)
        // bed - mid -> 2 hours (8)
        // mid - end -> 1 hour (16)
        calc.setTimes(17, 20, 22);
        assertEquals(3 * 12 + 2 * 8, calc.calculate());
    }
}
