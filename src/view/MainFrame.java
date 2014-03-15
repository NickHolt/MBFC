package view;

/** The main GUI component for a game of Mind Body Fitness Challenge. All view updates should
 *  be performed through this class.
 *  
 *  Currently for testing purposes only.
 * 
 * @author nickholt
 */
public class MainFrame {
	private ProgressBar mProgressBarOne, mProgressBarTwo;
	private TextPrompt mTextPrompt;
	
	public MainFrame() {
		mProgressBarOne = new ProgressBar();
		mProgressBarTwo = new ProgressBar();
		
		mTextPrompt = new TextPrompt();
	}
	
	public void putText(String string) {
		System.out.println(string);
		// TODO
	}

}
