package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scrap {
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		float[] mAccelerationValues = new float[3];
		float[] mEEGValues = new float[2];
		float mHeartRateValue = 0;
		float mPressureValue = 0;
		
		String inputLine = "sup=1.3";
		
		String tagRegex = "(accel|heartrate|eeg|pressure)=(.*)";
		Pattern pattern = Pattern.compile(tagRegex);
		Matcher matcher = pattern.matcher(inputLine);
		
		if (matcher.matches()) {
			String tag = matcher.group(1), value = matcher.group(2);
			
			String multipleValueRegex = "([\\d]+\\.[\\d]+),(([\\d]+\\.[\\d]+),?)+";
			pattern = Pattern.compile(multipleValueRegex);
			matcher = pattern.matcher(value);
			
			if(matcher.matches()) {
				String[] floats = value.split(",");

				switch(tag) {
					case "accel":
						mAccelerationValues[0] = Float.valueOf(floats[0]);
						mAccelerationValues[1] = Float.valueOf(floats[1]);
						mAccelerationValues[2] = Float.valueOf(floats[2]);
						break;
					case "eeg":
						mEEGValues[0] = Float.valueOf(floats[0]);
						mEEGValues[1] = Float.valueOf(floats[1]);
						break;
				}
			} else {
				switch(tag) {
					case "heartrate":
						mHeartRateValue = Float.valueOf(value);
						break;
					case "pressure":
						mPressureValue = Float.valueOf(value);
						break;
				}
			}
		}
		
		System.out.println("Accel: [" + mAccelerationValues[0] + ", " 
		                   + mAccelerationValues[1] + ", " + mAccelerationValues[2] + "]");
		System.out.println("Eeg: [" + mEEGValues[0] + ", " + mEEGValues[1] + "]");
		System.out.println("Heartrate: " + mHeartRateValue);
		System.out.println("Pressure: " + mPressureValue);

	}

}
