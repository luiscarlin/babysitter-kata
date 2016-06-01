package com.luchoc.babysitter;

import java.util.Arrays;
import java.util.List;

public class BabySitterCalculator {
    private static final int START_LIMIT = 17;
    private static final int STOP_LIMIT = 4; 
    private static final int RATE_BEFORE_BED = 12;
    private static final int RATE_AFTER_BED_BEFORE_MID = 8;
    private static final int OFFSET_FOR_CALCULATIONS = 24;
    private static final int MIDNIGHT = 24;
    private static final int RATE_AFTER_MIN = 16;
    
    private int startTime; 
    private int bedTime; 
    private int stopTime;
    
    public void setTimes(int startTime, int bedTime, int stopTime) { 
        
        // validate inputs
        List<Integer> times = Arrays.asList(startTime, bedTime, stopTime);
        
        for (int time : times) { 
            if (!(isMilitaryTime(time))) { 
                throw new IllegalArgumentException(time + " is not military time");
            }
        }
        
        // check limits 
        if (startTime < START_LIMIT && startTime > STOP_LIMIT) { 
            throw new IllegalArgumentException("Cannot schedule start time at " + startTime);
        }
        
        if (stopTime > STOP_LIMIT && stopTime < START_LIMIT) { 
            throw new IllegalArgumentException("Cannot schedule stop time at " + stopTime);
        }
        
        // verify order
        int startTimeWithOffset = applyOffset(startTime);
        int bedTimeWithOffset = applyOffset(bedTime);
        int stopTimeWithOffset = applyOffset(stopTime); 
        
        if (!(startTimeWithOffset <= stopTimeWithOffset && 
                startTimeWithOffset <= bedTimeWithOffset &&
                bedTimeWithOffset <= stopTimeWithOffset)) { 
            throw new IllegalArgumentException("Order of times should be start time <= bed time <= stop time");
        }
        
        // save inputs
        this.startTime = startTime;
        this.bedTime = bedTime;
        this.stopTime = stopTime;
    }

    public int calculate() {
        
        // add 24 to numbers less than 4 to make calculations easier
        int startTimeWithOffset = applyOffset(startTime);
        int bedTimeWithOffset = applyOffset(bedTime);
        int stopTimeWithOffset = applyOffset(stopTime); 
        
        int charge = 0; 
        
        for (int currentTime = startTimeWithOffset; currentTime < stopTimeWithOffset; currentTime++) {
            if (currentTime < bedTimeWithOffset) { 
                charge += RATE_BEFORE_BED;
            }
            else if (currentTime < MIDNIGHT) { 
                charge += RATE_AFTER_BED_BEFORE_MID;
            }
            else { 
                charge += RATE_AFTER_MIN;
            }
        }
        return charge;
    }
    
    private boolean isMilitaryTime(int time) { 
        return time >= 0 && time < 24;
    }
    
    private int applyOffset(int time) { 
        return time + (time <= STOP_LIMIT ? OFFSET_FOR_CALCULATIONS : 0);
    }
}
