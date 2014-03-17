package view;

import java.io.Closeable;

import jssc.SerialPort;
import jssc.SerialPortException;

public class LedMatrix implements Closeable {
	public static final int COLOR_RED = 0b1111100000000000;
	public static final int COLOR_YELLOW = 0b1111111111100000;
	public static final int COLOR_GREEN = 0b0000011111100000;
	public static final int COLOR_CYAN = 0b0000011111111111;
	public static final int COLOR_BLUE = 0b0000000000011111;
	public static final int COLOR_MAGENTA = 0b1111100000011111;
	public static final int COLOR_BLACK = 0b0000000000000000;
	public static final int COLOR_WHITE = 0b1111111111111111;
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 16;
	
	private SerialPort serialPort;
	
	public LedMatrix(String portName) {
		initializeSerial(portName);
	}
	
	public void drawPixel(int x, int y, int color) {
		sendCommand(String.format("p%d %d %d", x, y, color));
	}
	
	public void drawLine(int x0, int y0, int x1, int y1, int color) {
		sendCommand(String.format("l%d %d %d %d %d", x0, y0, x1, y1, color));
	}
	
	public void drawRect(int x0, int y0, int w, int h, int color) {
		sendCommand(String.format("r%d %d %d %d %d", x0, y0, w, h, color));
	}
	
	public void fillRect(int x0, int y0, int w, int h, int color) {
		sendCommand(String.format("R%d %d %d %d %d", x0, y0, w, h, color));
	}
	
	public void drawCircle(int x0, int y0, int radius, int color) {
		sendCommand(String.format("c%d %d %d %d", x0, y0, radius, color));
	}
	
	public void fillCircle(int x0, int y0, int radius, int color) {
		sendCommand(String.format("C%d %d %d %d", x0, y0, radius, color));
	}
	
	public void setCursor(int x0, int y0) {
		sendCommand(String.format("m%d %d", x0, y0));
	}
	
	public void setTextColor(int color) {
		sendCommand(String.format("f%d", color));
	}
	
	public void setTextColor(int color, int backgroundColor) {
		sendCommand(String.format("b%d %d", color, backgroundColor));
	}
	
	public void setTextSize(int size) {
		sendCommand(String.format("s%d", size));
	}
	
	public void setTextWrap(boolean wrap) {
		sendCommand(String.format("w%d", wrap ? 1 : 0));
	}
	
	public void print(String text) {
		sendCommand("P" + text + "\0");
	}
	
	public void fillScreen(int color) {
		sendCommand(String.format("F%d", color));
	}
	
	public static void main(String[] args) {
		LedMatrix matrix = new LedMatrix("/dev/cu.usbmodem1411");
		
		try {
			Thread.sleep(2000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		/*matrix.fillScreen(COLOR_BLACK);
		matrix.setCursor(0, 0);
		matrix.setTextColor(COLOR_CYAN);
		matrix.print("Java!");*/
		
		//int[] colors = new int[]{COLOR_RED, COLOR_GREEN, COLOR_BLUE};
		while(true) {
			for(int i = 0; i <= WIDTH; i++) {
				matrix.fillScreen(COLOR_BLACK);
				/*matrix.setTextColor(colors[i % colors.length]);
				matrix.setCursor(0, 0);
				matrix.print("Java!");*/
				matrix.drawRect(0, 0, i, 2, COLOR_RED);
				
				
				try {
					Thread.sleep(5);
				} catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		/*try {
			Thread.sleep(1000);
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		matrix.doStuff();
		
		System.out.println("Unblock");
		
		matrix.close();*/
		
	}
	
	private void sendCommand(String command) {
		try {
			serialPort.writeString(command);
			serialPort.writeByte((byte)' ');
		} catch(SerialPortException e) {
			e.printStackTrace();
		}
	}
	
	private void initializeSerial(String portName) {
		serialPort = new SerialPort(portName);
		
	    try {
	        serialPort.openPort();
	        serialPort.setParams(115200, 8, 1, 0);
	        //Preparing a mask. In a mask, we need to specify the types of events that we want to track.
	        //Well, for example, we need to know what came some data, thus in the mask must have the
	        //following value: MASK_RXCHAR. If we, for example, still need to know about changes in states 
	        //of lines CTS and DSR, the mask has to look like this: SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR
	        int mask = SerialPort.MASK_RXCHAR;
	        //Set the prepared mask
	        serialPort.setEventsMask(mask);
	        //Add an interface through which we will receive information about events
	        //serialPort.addEventListener(new SerialPortReader());
	    } catch (SerialPortException e) {
	        System.out.println(e);
	    }
	}
	
	public void close() {
		try {
			serialPort.closePort();
		} catch(SerialPortException e) {
			e.printStackTrace();
		}
	}
}
