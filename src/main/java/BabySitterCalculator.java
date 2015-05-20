
public class BabySitterCalculator {
	 
	private static final int EARLIEST_START_TIME = 17;
	private final int RATE_BEFORE_BED = 12; 
	private final int RATE_AFTER_BED = 8; 
	private final int RATE_AFTER_MIDNIGHT = 16; 
	
	
	public int calculate(int startTime, int endTime, int bedTime) {
		
		if ((startTime < EARLIEST_START_TIME) && (startTime > 4)) {
			throw new IllegalArgumentException("Invalid start time.");
		}
		
		int period = 0;
		
		if (bedTime >= startTime) { 
			period = calculatePeriod(bedTime, startTime); 
		}
		
		int periodAfterBedTime = calculatePeriod(endTime, bedTime); 
		
		int periodAfterMidnight = 0;
		
		if (endTime <= 4) { 
			periodAfterMidnight = calculatePeriod(endTime, 0); 
		}
		
		int amountAfterBedTime = applyRateForAfterBedTime(periodAfterBedTime); 
			
		return amountAfterBedTime + (period * RATE_BEFORE_BED) + (periodAfterMidnight * RATE_AFTER_MIDNIGHT);
	}


	private int calculatePeriod(int endTime, int bedTime) {
		int periodAfterBedTime = endTime - bedTime;
		
		return periodAfterBedTime;
	}


	private int applyRateForAfterBedTime(int periodAfterBedTime) {
		int amountAfterBedTime;
		// apply rate for after bed time
		amountAfterBedTime = periodAfterBedTime * RATE_AFTER_BED;
		return amountAfterBedTime;
	}
	
}
