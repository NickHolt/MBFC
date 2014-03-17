package view;

import java.io.Closeable;
import java.util.regex.Pattern;

import jssc.SerialPort;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class LedMatrix implements Closeable {
    public static final int COLOR_RED = 0b1111100000000000;
    public static final int COLOR_YELLOW = 0b1111111111100000;
    public static final int COLOR_GREEN = 0b0000011111100000;
    public static final int COLOR_CYAN = 0b0000011111111111;
    public static final int COLOR_BLUE = 0b0000000000011111;
    public static final int COLOR_MAGENTA = 0b1111100000011111;
    public static final int COLOR_BLACK = 0b0000000000000000;
    public static final int COLOR_WHITE = 0b1111111111111111;
    public static final int COLOR_GRAY_1 = 0b0010000100000100;
    public static final int COLOR_GRAY_2 = 0b0100001000001000;
    public static final int COLOR_GRAY_4 = 0b1000010000010000;
    
    public static final int WIDTH = 32;
    public static final int HEIGHT = 16;
    
    public SerialPort serialPort;
    
    private int textSize = 1;
    private int fgColor = COLOR_WHITE;
    private int bgColor = COLOR_BLACK;
    
    public LedMatrix() {
    	initializeSerial();
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
    
    public void setTextColor(int fgColor) {
    	if(this.fgColor != fgColor) {
	    	this.fgColor = fgColor;
	        sendCommand(String.format("f%d", fgColor));
    	}
    }
    
    public void setTextColor(int fgColor, int bgColor) {
    	if(this.fgColor != fgColor || this.bgColor != bgColor) {
	    	this.fgColor = fgColor;
	    	this.bgColor = bgColor;
	        sendCommand(String.format("b%d %d", fgColor, bgColor));
    	}
    }
    
    public void setTextSize(int textSize) {
    	if(this.textSize != textSize) {
	    	this.textSize = textSize;
	        sendCommand(String.format("s%d", textSize));
    	}
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
    
    public int getTextWidth(String str) {
    	return str.length() * 6 * textSize - 1;
    }
    
    public void drawText(String text, int x, int y) {
    	setCursor(x, y);
    	print(text);
    }
    
    public void drawTextCentered(String text, int x, int y) {
    	setCursor(x - getTextWidth(text) / 2, y);
    	print(text);
    }
    
    public void drawTextRight(String text, int x, int y) {
    	setCursor(x - getTextWidth(text), y);
    	print(text);
    }
    
    public void clear() {
    	fillScreen(bgColor);
    }
    
    public void scroll(String text, int y, int delayLength) {
    	int minX = -getTextWidth(text);
    	for(int x = WIDTH; x >= minX; x--) {
    		fillScreen(bgColor);
    		drawText(text, x, y);
    		try {
    			Thread.sleep(delayLength);
    		} catch(InterruptedException e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    public static void main(String[] args) {
        LedMatrix matrix = new LedMatrix();
        
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        matrix.setTextWrap(false);
        matrix.setTextColor(COLOR_GRAY_1, COLOR_BLACK);
		matrix.fillScreen(LedMatrix.COLOR_BLACK);
		while(true) {
			matrix.scroll("Scrolling", 0, 100);
		}
        /*
        matrix.setTextSize(2);
        
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {
            e.printStackTrace();
        }
        
        for(int i = 3; i > 0; i--) {
            matrix.fillScreen(COLOR_BLACK);
            matrix.setCursor(11, 1);
            matrix.print(String.valueOf(i));
            
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        matrix.fillScreen(COLOR_BLACK);
        matrix.setTextColor(COLOR_GREEN);
        matrix.setTextSize(1);
        matrix.setCursor(0, 0);
        matrix.print("Start");*/

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
    
    private void initializeSerial() {
    	String[] serialPortNames = SerialPortList.getPortNames("/dev/", Pattern.compile("tty.(serial|usbserial|usbmodem).*"));
        if(serialPortNames.length > 0) {
        	serialPort = new SerialPort(serialPortNames[0]);
        } else {
        	serialPort = new SerialPort("COM8");
        }
        
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