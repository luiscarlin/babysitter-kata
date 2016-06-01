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
    public void calculatorThrowsExceptionWhenATimeIsNegative() { 
        calc.setTimes(-1,  0, 3); 
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculatorThrowsExceptionWhenaATimeIsAbove24() { 
        calc.setTimes(17,  0, 25); 
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculatorThrowsExceptionWhenBothTimeIsNegativeAndAbove24() { 
        calc.setTimes(-1,  0, 25); 
    }
    
    @Test
    public void whenSitterWorksFromBedtimeToMidnightTheyReceive8DollarsPerHour() { 
        calc.setTimes(18, 18, 0);
        assertEquals(6 * 8, calc.calculate());
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculatorThrowsExceptionWhenBedTimeIsBeforeStartTime() { 
        calc.setTimes(20, 19, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculatorThrowsExceptionWhenStopTimeimeIsBeforeStartTime() { 
        calc.setTimes(21, 21, 20);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculatorThrowsExceptionWhenStopTimeIsBeforeBedTime() { 
        calc.setTimes(21, 23, 22);
    }
    
    @Test
    public void whenSitterWorksFromMidnighttoStopTimeTheyReceive16DollardsPerHour() {
        calc.setTimes(0, 0, 4);
        assertEquals(16 * 4, calc.calculate());
    }
}
