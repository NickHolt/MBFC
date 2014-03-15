package controller;

public class Scrap {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		for (int i = 3; i > 0; i--) {
			System.out.println(String.valueOf(i));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
