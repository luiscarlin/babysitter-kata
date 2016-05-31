package com.luchoc.babysitter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BabySitterCalculatorTest {
    
    BabySitterCalculator calc;
    
    @Before
    public void setup() { 
        calc = new BabySitterCalculator();
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculatorThrowsExceptionWhenStartTimeBefore17() {
        calc.setTimes(16, 18, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculatorThrowsExceptionWhenStopTimeAfter4() {
        calc.setTimes(18, 0, 5);
    }
    
    @Test
    public void whenSitterWorksFromStartToBedTheyGet12DollarsPerHour() { 
        calc.setTimes(18, 19, 19);
        assertEquals(12, calc.calculate());
    }
    
    @Test
    public void calculatorDoesNotFailWhenStartTimeAfterMidnight() {
        calc.setTimes(2,3,3);
    }
    
    @Test
    public void calculatorDoesNotFailWhenStopTimeBeforeMidnight() { 
        calc.setTimes(18, 19, 23);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculatorThrowsExceptionWhenNotUsingMilitaryTime() { 
        calc.setTimes(-1,  0,  34); 
    }
}
