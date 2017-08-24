package CP1;

import java.util.ArrayList;

/* Represents the game state, including status, score, and the 4x4 grid 
 * of values. Knows how to shift the values around. Notifies all 
 * registered IChangeListeners if anything happens. 
 */
public class GameState 
{
	private int[][] values;
	private String status;
	private int score;
	
	public final static int LEFT = 0;
	public final static int UP = 1;
	public final static int RIGHT = 2;
	public final static int DOWN = 3;
	
	private ArrayList<IChangeListener> listeners;
	
	public GameState()
	{
		score = 0;
		status = "";
		values = new int[4][4];
		listeners = new ArrayList<IChangeListener>();
	}
	
	public String getStatus() { return status; }
	public int getScore() { return score; }
	
	public void addListener(IChangeListener listener)
	{
		listeners.add(listener);
	}
	
	public void newGame()
	{
		// fill values array with 16 random numbers from 0 - 15
		// change status, and update listeners 
		for (int i = 0; i < 4; i++){
			for (int j = 0; j < 4; j++){
				values[i][j] = (int) (Math.random() * 16);
			}
		}
		status = "New game started";
		updateListeners();
	}
	
	public void shift(int direction)
	{
		// change status and update listeners
		if (direction == LEFT)
			status = "Shifted tiles left";
		if (direction == RIGHT)
			status = "Shifted tiles right";
		if (direction == DOWN)
			status = "Shifted tiles down";
		if (direction == UP)
			status = "Shifted tiles up";
		updateListeners();
	}
	
	public int getValue(int r, int c)
	{
		// return the appropriate value
		return values[r][c];
	}
	
	private void updateListeners()
	{
		// for each item in the listeners list calls its redraw method
		for (IChangeListener i : listeners){
			i.redraw();
		}
	}
}