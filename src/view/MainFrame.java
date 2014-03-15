package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;

/** The main GUI component for a game of Mind Body Fitness Challenge. All view updates should
 *  be performed through this class.
 *  
 *  Currently for testing purposes only.
 * 
 * @author nickholt
 */
public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private ProgressBar mProgressBarOne, mProgressBarTwo;
	private JLabel mTextPrompt;
	
	public MainFrame() {
		mProgressBarOne = new ProgressBar();
		mProgressBarTwo = new ProgressBar();
		
		mTextPrompt = new JLabel();
	}
	
	public void initialize() {
        setTitle("Mind Body Fitness Challenge");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);
    
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        c.weighty = 0.3;
        c.weightx = 1.0;
        c.gridheight = 1;
        c.gridwidth = 1;
        
        c.gridx = 0;
        c.gridy = 1;
        add(mTextPrompt, c);
        
        c.anchor = GridBagConstraints.LINE_START;
        c.fill = GridBagConstraints.BOTH;
        
        c.gridx = 0;
        c.gridy = 0;
        add(mProgressBarOne, c);
        
        c.gridx = 0;
        c.gridy = 2;
        add(mProgressBarTwo, c);
        
        setSize(1000, 300);
	}
	
	public void putText(String string) {
		mTextPrompt.setText(string);
	}
	
	public void setPlayerOnePercentageFill(float percent) {
		mProgressBarOne.setPercentFilled(percent);
	}
	
	public void setPlayerTwoPercentageFill(float percent) {
		mProgressBarTwo.setPercentFilled(percent);
	}

}
