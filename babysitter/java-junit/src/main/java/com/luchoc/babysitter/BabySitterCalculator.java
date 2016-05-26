package com.luchoc.babysitter;

public class BabySitterCalculator {
    
    private static final int START_LIMIT = 17;
    private static final int STOP_LIMIT = 4; 
    private static final int RATE_BEFORE_BED = 12;
    
    public void validateTimes(int startTime, int stopTime) {
        
        if (! isMilitaryTime(startTime)) { 
            throw new IllegalArgumentException(startTime + " is not military time");
        }
        
        if (! isMilitaryTime(stopTime)) { 
            throw new IllegalArgumentException(stopTime + " is not military time");
        }
        
        if (startTime < START_LIMIT && startTime > STOP_LIMIT) { 
            throw new IllegalArgumentException("Cannot schedule at " + startTime);
        }
        
        if (stopTime > STOP_LIMIT && stopTime < START_LIMIT) { 
            throw new IllegalArgumentException("Cannot schedule at " + stopTime);
        }
    }

    public int charge(int startTime, int bedTime, int stopTime) {
        
        validateTimes(startTime, stopTime);
        
        int charge = 0; 
        
        charge += (bedTime - startTime) * RATE_BEFORE_BED;
        
        return charge;
    }
    
    private boolean isMilitaryTime(int time) { 
        return time >= 0 && time < 24;
    }
}
