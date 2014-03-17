package controller;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** The interface between Mind Body Fitness Challenge software and hardware. This class is responsible
 * for retrieving data from the Galileo board as well as sending data such as challenge prompts.
 * 
 * @author nickholt
 */
public class GalileoInterfacer implements SerialPortEventListener {
	/** Milliseconds to block while waiting for port open */
	private static final int TIME_OUT = 2000;
	/** Default bits per second for COM port. */
	private static final int DATA_RATE = 9600;
	/** The port we're normally going to use. */
	private static final String PORT_NAMES[] = { 
			"/dev/tty.usbserial-A9007UX1", // Mac OS X
			"/dev/ttyUSB0", // Linux
			"COM1",
			"COM2",
			"COM3", // Windows
			"COM4",
			"COM5",
			"COM6",
			"COM7",
			"COM8"
	};
	/** Ports currently in use. */
	private static ArrayList<CommPortIdentifier> sPortsInUse 
	                                             = new ArrayList<CommPortIdentifier>();
	
	private SerialPort mSerialPort;
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader mInput;
	/** The output stream to the port */
	private OutputStream mOutput;
	/** The writer for the output stream */
	private PrintWriter mPrintWriter;
	/** The current port ID. */
	private CommPortIdentifier mPortId;
	/** The last recorded values for sensor readings. */
	private float mBeatValue, mPressureValue, mHeartRateValue;
	private float[] mAccelerationValues, mEEGValues;
	
	public GalileoInterfacer() {
		mAccelerationValues = new float[3];
		mEEGValues = new float[2];
	}
	
	public void initialize() {
		mPortId = null;
		@SuppressWarnings("rawtypes")
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
	
		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					if (sPortsInUse.contains(currPortId)) {
						continue;
					}
					
					mPortId = currPortId;
					break;
				}
			}
		}
		if (mPortId == null) {
			System.out.println("Could not find COM port.");
			return;
		}
	
		try {
			// open serial port, and use class name for the appName.
			mSerialPort = (SerialPort) mPortId.open(this.getClass().getName(),
					TIME_OUT);
	
			// set port parameters
			mSerialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
	
			// open the streams
			mInput = new BufferedReader(new InputStreamReader(mSerialPort.getInputStream()));
			mOutput = mSerialPort.getOutputStream();
			mPrintWriter = new PrintWriter(mOutput);
	
			// add event listeners
			mSerialPort.addEventListener(this);
			mSerialPort.notifyOnDataAvailable(true);
			
			// add port IDs to used list
			sPortsInUse.add(mPortId);
		} catch (Exception e) {
			System.err.println(e.toString());
		}
	}
	
	/**
	 * This should be called when you stop using the port.
	 * This will prevent port locking on platforms like Linux.
	 */
	public synchronized void close() {
		if (mSerialPort != null) {
			mSerialPort.removeEventListener();
			mSerialPort.close();
		}

		sPortsInUse.remove(mPortId);
	}
	
	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				parseString(mInput.readLine());
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
	}
	
	/** Parse a serial input line to determine sensor readings.
	 * 
	 * @param inputLine The serial input line. 
	 */
	public void parseString(String inputLine) {
		System.out.println(inputLine);
		String tagRegex = "(beat|accel|heartrate|eeg|pressure)=(.*)";
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
					case "beat":
						mBeatValue = Float.valueOf(value);
						break;
					case "heartrate":
						mHeartRateValue = Float.valueOf(value);
						break;
					case "pressure":
						mPressureValue = Float.valueOf(value);
						break;
				}
			}
		}
	}
	
	public void writeToGalileo(String s) {
		mPrintWriter.write(s);
	}

	public float getPressureValue() {
		return mPressureValue;
	}

	public float getHeartRateValue() {
		return mHeartRateValue;
	}

	public float[] getAccelerationValues() {
		return mAccelerationValues;
	}
	
	public float getXAccelerationValue() {
		return mAccelerationValues[0];
	}
	
	public float getYAccelerationValue() {
		return mAccelerationValues[1];
	}
	
	public float getZAccelerationValue() {
		return mAccelerationValues[2];
	}

	public float[] getEEGValues() {
		return mEEGValues;
	}
	
	public float getAttentionValue() {
		return mEEGValues[0];
	}
	
	public float getMeditationValue() {
		return mEEGValues[1];
	}

	public float getBeatValue() {
		return mBeatValue;
	}
	
	public static void main(String[] args) throws Exception {
		GalileoInterfacer main = new GalileoInterfacer();
		main.initialize();
		Thread t=new Thread() {
			public void run() {
				//the following line will keep this app alive for 1000 seconds,
				//waiting for events to occur and responding to them (printing incoming messages to console).
				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
			}
		};
		t.start();
		System.out.println("Started");
	}
}
