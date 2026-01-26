package memorygame;

import java.util.Random;

public class Panel {
	
	private int n = 4;
	
	private int Panel [][] = new int [n][n];
	
	public int[][] getPanel() {
		return Panel;
	}
	
	public void setPanel(int[][] panel) {
		Panel = panel;
		Random rand = new Random();
		for (int num = 0; num < 8; num++) {
			for (int rep = 0; rep < 2; rep++) {
				int row, column;
				do {
					row = rand.nextInt(4);
					column = rand.nextInt(4);
				} while (Panel[row][column] != 0);
					Panel[row][column] = num;
			}
		}
	}

}
