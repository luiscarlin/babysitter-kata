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
        calc.charge(16, 18, 0);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void calculatorThrowsExceptionWhenStopTimeAfter4() {
        calc.charge(18, 0, 5);
    }
    
    @Test
    public void whenSitterWorksFromStartToBedTheyGet12DollarsPerHour() { 
        assertEquals(12, calc.charge(18, 19, 0));
    }
}
