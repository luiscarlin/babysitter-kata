package com.luchoc.babysitter;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class BabySitterCalculatorTest {
	
	BabySitterCalculator babySitterCal;

	@Before
	public void setup() { 
		babySitterCal = new BabySitterCalculator(); 
	}
	
	@Test
	public void whenTheBabysitterIsCreateABabysitterObjectIGetOne() {
		babySitterCal = new BabySitterCalculator();
		assertNotNull(babySitterCal); 
	}
	
	@Test
	public void whenTheBabySitterWorksOneHourBeforeBedtimeTheyReceiveTwelveDollars() { 
		assertEquals(12, babySitterCal.calculate(17, 18, 18)); 	
	}
	
	@Test
	public void whenTheBabySitterWorksTwoHoursBeforeBedtimeTheyReceive24Dollars() {
		assertEquals(24, babySitterCal.calculate(17, 19, 19));
	}
	
	@Test (expected=IllegalArgumentException.class)
	public void whenTheBabySitterStartsWorkBefore17ItShouldNotBeAllowed() {
		babySitterCal.calculate(16, 19, 19);
	}
	
	@Test
	public void whenTheBabySitterWorksForOneHourBeforeBedTimeTheyReceiveEightDollars() { 
		assertEquals(8, babySitterCal.calculate(22, 23, 22));
	}
	
	@Test
	public void whenTheBabySitterWorksForTwoHoursBeforeBedTimeTheyReceiveSixteenDollars() { 
		assertEquals(16, babySitterCal.calculate(21, 23, 21));
	}
	
	@Test
	public void whenTheBabySitterWorksForOneHourBeforeBedtimeAndOneHourAfterBedtimeThenTheyShouldReceive20Dollars() {
		assertEquals(20, babySitterCal.calculate(17, 19, 18));
	}
	
	@Test(expected = AssertionError.class)
	public void whenTheBabySitterWorksForOneHourBetweenMidnightAndEndOfJObThenTheyShouldReceive16Dollars() {
		assertEquals(16, babySitterCal.calculate(0, 1, 18));
	}
}
