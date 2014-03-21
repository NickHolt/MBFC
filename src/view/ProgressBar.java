package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

/** A GUI component that graphically displays the progress of the player. The percentage of the
 *  bar that's filled is equal to the global score of the player divided by the maximum possible score.
 *  
 *  Currently for testing purposes only.
 * 
 * @author nickholt
 */
public class ProgressBar extends JPanel {
	private static final long serialVersionUID = 1L;
	
	private Color mBarColor, mTextColor;
	private float mPercentFilled;
	
	public ProgressBar(Color barColor, Color textColor) {
		setBarColor(barColor);
		setTextColor(textColor);
		setPercentFilled(0.0f);
	}
	
	public ProgressBar() {
		this(Color.BLACK, Color.WHITE);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		
		g.setColor(mBarColor);
		g.fillRect(0, 0, (int) (getWidth() * mPercentFilled), getHeight());
	}

	public float getPercentFilled() {
		return mPercentFilled;
	}

	public void setPercentFilled(float percentFilled) {
		mPercentFilled = percentFilled;
	}

	public Color getBarColor() {
		return mBarColor;
	}

	public void setBarColor(Color color) {
		mBarColor = color;
	}

	public Color getTextColor() {
		return mTextColor;
	}

	public void setTextColor(Color textColor) {
		mTextColor = textColor;
	}

}
