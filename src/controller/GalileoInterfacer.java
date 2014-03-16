package controller;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Enumeration;


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
			"COM3", // Windows
	};
	
	private SerialPort mSerialPort;
	/**
	* A BufferedReader which will be fed by a InputStreamReader 
	* converting the bytes into characters 
	* making the displayed results codepage independent
	*/
	private BufferedReader mInput;
	/** The output stream to the port */
	private OutputStream mOutput;
	/** The last recorded values for sensor readings. */
	private float mPressureValue, mHeartRateValue, mReactionTimeValue;
	private float[] mAccelerationValues, mEEGValues;
	
	public void initialize() {
		CommPortIdentifier portId = null;
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
	
		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					break;
				}
			}
		}
		if (portId == null) {
			System.out.println("Could not find COM port.");
			return;
		}
	
		try {
			// open serial port, and use class name for the appName.
			mSerialPort = (SerialPort) portId.open(this.getClass().getName(),
					TIME_OUT);
	
			// set port parameters
			mSerialPort.setSerialPortParams(DATA_RATE,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE);
	
			// open the streams
			mInput = new BufferedReader(new InputStreamReader(mSerialPort.getInputStream()));
			mOutput = mSerialPort.getOutputStream();
	
			// add event listeners
			mSerialPort.addEventListener(this);
			mSerialPort.notifyOnDataAvailable(true);
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
	}
	
	/**
	 * Handle an event on the serial port. Read the data and print it.
	 */
	public synchronized void serialEvent(SerialPortEvent oEvent) {
		if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
			try {
				// TODO: USE REGEX TO PLACE VALUES
				String inputLine=mInput.readLine();
				System.out.println(inputLine);
			} catch (Exception e) {
				System.err.println(e.toString());
			}
		}
		// Ignore all the other eventTypes, but you should consider the other ones.
	}

	public float getPressureValue() {
		return mPressureValue;
	}

	public float getHeartRateValue() {
		return mHeartRateValue;
	}

	public float getReactionTimeValue() {
		return mReactionTimeValue;
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
}
