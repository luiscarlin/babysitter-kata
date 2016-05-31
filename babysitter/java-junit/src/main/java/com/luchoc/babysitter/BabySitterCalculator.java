package com.luchoc.babysitter;

import java.util.Arrays;
import java.util.List;

public class BabySitterCalculator {
    
    private static final int START_LIMIT = 17;
    private static final int STOP_LIMIT = 4; 
    private static final int RATE_BEFORE_BED = 12;
    private static final int OFFSET_FOR_CALCULATIONS = 24;
    
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
        int startTimeWithOffset = startTime + (startTime <= STOP_LIMIT ? OFFSET_FOR_CALCULATIONS : 0);
        int bedTimeWithOffset = bedTime + (bedTime <= STOP_LIMIT ? OFFSET_FOR_CALCULATIONS : 0); 
        int stopTimeWithOffset = stopTime + (stopTime <= STOP_LIMIT ? OFFSET_FOR_CALCULATIONS : 0); 
        
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
        int charge = 0; 
        
        charge += (bedTime - startTime) * RATE_BEFORE_BED;
        
        return charge;
    }
    
    private boolean isMilitaryTime(int time) { 
        return time >= 0 && time < 24;
    }
}
