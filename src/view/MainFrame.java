package view;

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
