package com.luchoc.babysitter;

import java.util.Arrays;
import java.util.List;

/**
 * Class representing the calculator.
 * Immutable.
 */
public final class BabySitterCalculator {
    /**
     * The earliest start time.
     */
    private static final int START_LIMIT = 17;

    /**
     * The latest time to finish baby-sitting.
     */
    private static final int STOP_LIMIT = 4;

    /**
     * The rate from start to bedtime.
     */
    private static final int RATE_BEFORE_BED = 12;

    /**
     * The rate from bedtime to midnight.
     */
    private static final int RATE_AFTER_BED_BEFORE_MID = 8;

    /**
     * The adjusted time at midnight.
     */
    private static final int MIDNIGHT = 24;

    /**
     * The rate from midnight to end.
     */
    private static final int RATE_AFTER_MIN = 16;

    /**
     * The start time of the job.
     */
    private int startTime;

    /**
     * The bed time.
     */
    private int bedTime;

    /**
     * The stop time for the job.
     */
    private int stopTime;

    /**
     * Validates input times and sets them in the calculator.
     *
     * @param start The job start time.
     * @param bed   The bed time for the kids.
     * @param stop  The job stop time.
     */
    public void setTimes(final int start, final int bed, final int stop) {
        // validate inputs
        List<Integer> times = Arrays.asList(start, bed, stop);
        for (int time : times) {
            if (!(isMilitaryTime(time))) {
                throw new IllegalArgumentException(time
                        + " is not military time");
            }
        }

        // check limits
        if (start < START_LIMIT && start > STOP_LIMIT) {
            throw new IllegalArgumentException("Cannot schedule start time at "
                    + start);
        }

        if (stop > STOP_LIMIT && stop < START_LIMIT) {
            throw new IllegalArgumentException("Cannot schedule stop time at "
                    + stop);
        }

        // verify order of input times
        int startTimeWithOffset = applyOffset(start);
        int bedTimeWithOffset = applyOffset(bed);
        int stopTimeWithOffset = applyOffset(stop);

        if (!(startTimeWithOffset <= stopTimeWithOffset
                && startTimeWithOffset <= bedTimeWithOffset
                && bedTimeWithOffset <= stopTimeWithOffset)) {
            throw new IllegalArgumentException("Order of times should be"
                    + " start time <= bed time <= stop time");
        }

        // save inputs in the calculator
        this.startTime = start;
        this.bedTime = bed;
        this.stopTime = stop;
    }

    /**
     * Calculates the amount of money the babysitter will make for this job.
     *
     * @return the amount of money to charge as integer
     */
    public int calculate() {
        // add 24 to numbers less than 4 to make calculations easier
        int startWithOffset = applyOffset(startTime);
        int bedWithOffset = applyOffset(bedTime);
        int stopWithOffset = applyOffset(stopTime);

        int charge = 0;

        for (int time = startWithOffset; time < stopWithOffset; time++) {
            if (time < bedWithOffset) {
                charge += RATE_BEFORE_BED;
            } else if (time < MIDNIGHT) {
                charge += RATE_AFTER_BED_BEFORE_MID;
            } else {
                charge += RATE_AFTER_MIN;
            }
        }
        return charge;
    }

    /**
     * Verifies that time is in military format.
     *
     * @param time The time to validate.
     * @return whether or not time is in military format.
     */
    private boolean isMilitaryTime(final int time) {
        return time >= 0 && time < 24;
    }

    /**
     * Adds 24 to the time if the time is less than 4.
     * This makes calculations much easier.
     *
     * @param time the time to add the offset if needed.
     * @return the time with the offset applied as integer.
     */
    private int applyOffset(final int time) {
        int timeWithOffset;

        if (time <= STOP_LIMIT) {
            timeWithOffset = time + 24;
        } else {
            timeWithOffset = time;
        }
        return timeWithOffset;
    }
}
