package CP2;

import java.util.ArrayList;
import java.util.Arrays;

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
	
	public GameState(int[][] initialValues){
		listeners = new ArrayList<IChangeListener>();
		values = new int[4][4];
		for (int r = 0; r < 4; r++){
			for (int c = 0; c < 4; c++){
				values[r][c] = initialValues[r][c];
			}
		}
	}
	
	public String getStatus() { return status; }
	public int getScore() { return score; }
	
	public void addListener(IChangeListener listener)
	{
		listeners.add(listener);
	}
	
	public void newGame()
	{
		// pick two random spots in array to place initial twos
		// change status, and update listeners 
		for (int r = 0; r < 4; r++){
			for (int c = 0; c < 4; c++){
				values[r][c] = 0;
			}
		}
		
		addTwoToGrid();
		addTwoToGrid();
		
		score = 0;
		status = "New game started";
		updateListeners();
	}
	
	public void addTwoToGrid(){
		if (gridFull()){
			return;
		}
		int x = (int) (Math.random() * 4);
		int y = (int) (Math.random() * 4);
		while (values[x][y] != 0){
			x = (int) (Math.random() * 4); 
			y = (int) (Math.random() * 4);
		}
		values[x][y] = 2;
	}
	
	public boolean gridFull(){
		for (int r = 0; r < 4; r++){
			for (int c = 0; c < 4; c++){
				if (values[r][c] == 0)
					return false;
			}
		}
		return true;
	}
	
	public void gameOverStatusChange(){
		status = "Game over!";
	}
	
	public boolean gameOver(){
		if (gridFull() && !possibleMove())
			return true;
		return false;
	}
	
	public boolean possibleMove(){
		if (!gridFull())
			return true;
		for (int r = 0; r < 4; r++){
			for (int c = 0; c < 3; c++){
				if (values[r][c] == (values[r][c + 1]))
					return true;
			}
		}
		for (int c = 0; c < 4; c++){
			for (int r = 0; r < 3; r++){
				if (values[r][c] == (values[r + 1][c]))
					return true;
			}
		}
		return false;
	}
	
	public void shift(int direction)
	{
		int[][] initial = {values[0].clone(), values[1].clone(),
				values[2].clone(), values[3].clone()};
		
		// change status and update listeners
		if (direction == LEFT){
			status = "Shifted tiles left";
			combineX(direction);
		}
		
		else if (direction == RIGHT){
			status = "Shifted tiles right";
			combineX(direction);
		}
		
		else if (direction == UP){
			status = "Shifted tiles up";
			combineY(direction);
		}
			
		else if (direction == DOWN){
			status = "Shifted tiles down";
			combineY(direction);
		}
		
		if (!Arrays.deepEquals(values, initial)){
			addTwoToGrid();
		}
		if (gameOver()){
			gameOverStatusChange();
		}
		
		updateListeners();
	}
	
	public void displayValues()
	{
		System.out.println(Arrays.toString(values[0]));
		System.out.println(Arrays.toString(values[1]));
		System.out.println(Arrays.toString(values[2]));
		System.out.println(Arrays.toString(values[3]));
		System.out.println();
	}
	
	public void combineX(int direction){
		for (int r = 0; r < 4; r++){
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			if (direction == LEFT){
				for (int c = 0; c < 4; c++){
					if (values[r][c] != 0)
						list.add(values[r][c]);
				}
			}
			if (direction == RIGHT){
				for (int c = 3; c >= 0; c--){
					if (values[r][c] != 0)
						list.add(values[r][c]);
				}
			}
			
			for (int i = 0; i < list.size() - 1; i++){
				if (list.get(i).equals(list.get(i + 1))){
					list.remove(i + 1);
					list.set(i, list.get(i) * 2);
					score += list.get(i);
				}
			}
			
			if (direction == LEFT)
				for (int i = 0; i < 4; i++){
					if (i < list.size())
						values[r][i] = list.get(i);
					else
						values[r][i] = 0;
				}
			if (direction == RIGHT){
				for (int i = 3; i >= 0; i--){
					if ((3 - i) < list.size())
						values[r][i] = list.get(3 - i);
					else
						values[r][i] = 0;
				}
			}
		
		}
	}
	
	public void combineY(int direction){
		for (int c = 0; c < 4; c++){
			ArrayList<Integer> list = new ArrayList<Integer>();
			if (direction == UP){
				for (int r = 0; r < 4; r++){
					if (values[r][c] != 0)
						list.add(values[r][c]);
				}
			}
			if (direction == DOWN){
				for (int r = 3; r >= 0; r--){
					if (values[r][c] != 0)
						list.add(values[r][c]);
				}
			}
			
			for (int i = 0; i < list.size() - 1; i++){
				if (list.get(i).equals(list.get(i + 1))){
					list.remove(i + 1);
					list.set(i, list.get(i) * 2);
					score += list.get(i);
				}
			}
			
			if (direction == UP){
				for (int i = 0; i < 4; i++){
					if (i < list.size())
						values[i][c] = list.get(i);
					else
						values[i][c] = 0;
				}
			}
			if (direction == DOWN){
				for (int i = 3; i >= 0; i--){
					if ((3 - i) < list.size())
						values[i][c] = list.get(3 - i);
					else
						values[i][c] = 0;
				}
			}
		
		}
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