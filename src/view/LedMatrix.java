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
    
    private float brightness = 1.0f;
    
    public LedMatrix() {
    	initializeSerial();
    }
    
    public void drawProgressBars(float pOnePercent, float pTwoPercent, int pOneColor, int pTwoColor) {
    	fillRect(0, 12, (int) (LedMatrix.WIDTH * pOnePercent), 2, applyBrightness(pOneColor));
		fillRect(0, 14, (int) (LedMatrix.WIDTH * pTwoPercent), 2, applyBrightness(pTwoColor));
    }
    
    public void drawPixel(int x, int y, int color) {
        sendCommand(String.format("p%d %d %d", x, y, applyBrightness(color)));
    }
    
    public void drawLine(int x0, int y0, int x1, int y1, int color) {
        sendCommand(String.format("l%d %d %d %d %d", x0, y0, x1, y1, applyBrightness(color)));
    }
    
    public void drawRect(int x0, int y0, int w, int h, int color) {
        sendCommand(String.format("r%d %d %d %d %d", x0, y0, w, h, applyBrightness(color)));
    }
    
    public void fillRect(int x0, int y0, int w, int h, int color) {
        sendCommand(String.format("R%d %d %d %d %d", x0, y0, w, h, applyBrightness(color)));
    }
    
    public void drawCircle(int x0, int y0, int radius, int color) {
        sendCommand(String.format("c%d %d %d %d", x0, y0, radius, applyBrightness(color)));
    }
    
    public void fillCircle(int x0, int y0, int radius, int color) {
        sendCommand(String.format("C%d %d %d %d", x0, y0, radius, applyBrightness(color)));
    }
    
    public void setCursor(int x0, int y0) {
        sendCommand(String.format("m%d %d", x0, y0));
    }
    
    public void setTextColor(int fgColor) {
    	if(this.fgColor != fgColor) {
	    	this.fgColor = fgColor;
	        sendCommand(String.format("f%d", applyBrightness(fgColor)));
    	}
    }
    
    public void setTextColor(int fgColor, int bgColor) {
    	if(this.fgColor != fgColor || this.bgColor != bgColor) {
	    	this.fgColor = fgColor;
	    	this.bgColor = bgColor;
	        sendCommand(String.format("b%d %d", fgColor, applyBrightness(bgColor)));
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
        sendCommand(String.format("F%d", applyBrightness(color)));
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
    
    public float getBrightness() {
    	return brightness;
    }
    
    public void setBrightness(float brightness) {
    	this.brightness = brightness;
    }
    
    private int applyBrightness(int color) {
    	int red = (color >>> 11) & 0b11111;
    	int green = (color >>> 5) & 0b111111;
    	int blue = color & 0b11111;
    	
    	red *= brightness;
    	green *= brightness;
    	blue *= brightness;
    	
    	return red << 11 | green << 5 | blue;
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
    
    /**
     * Test code, please do not remove
     * @param args
     */
    public static void main(String[] args) {
    	LedMatrix matrix = new LedMatrix();
    	matrix.setBrightness(0.2f);
    	
    	try {
    		Thread.sleep(2000);
    	} catch(InterruptedException e) {
    		//ignore
    	}
    	
    	matrix.setTextWrap(false);
    	
    	
    	
    	
    	/*matrix.setTextColor(COLOR_WHITE, COLOR_BLUE);
    	
    	try {
    		Thread.sleep(3000);
    	} catch(InterruptedException e) {
    		//ignore
    	}
    	
    	matrix.scroll("Hey", 0, 100);*/
    }
}