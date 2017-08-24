package karel;

import kareltherobot.*;

public class Harvester extends Robot
{
	public Harvester(int street, int avenue, Direction direction, int beepers)
    {
        super(street, avenue, direction, beepers);
    }

	public static void main(String[] args) 
	{
		World.readWorld("WorldFiles/fig3-2.kwld");
		World.setDelay(10);
        World.setVisible(true);

        Harvester karel = new Harvester(2, 2, East, 0);
        World.setDelay(10);
		
        karel.move();
        karel.pickGarden();
        karel.goToFinish();
        karel.turnOff();
	}

	public void turnRight()
	{
		turnLeft();
		turnLeft();
		turnLeft();
	}
	
	public void turn180()
	{
		turnLeft();
		turnLeft();
	}
	
	public void clearRow()
	{
		//pre: on the first beeper of a row
		//post: one corner past the end of the precondition row, holding all beepers from row
		while ( nextToABeeper() )
		{
			clearCorner();
		}
	}
	
	public void clearCorner()
	{
		//pre: on any corner
		//post: having moved one step forward holding all the beepers from precondition corner
		while ( nextToABeeper() )
		{
			pickBeeper();
		}
		move();
	}
	
	public void setRowWest()
	{
		//pre: having just finished picking a row from west to east
		//post: one row above precondition row on first corner facing west
		turnLeft();
		move();
		turnLeft();
		move();
	}
	
	public void setRowEast()
	{
		//pre: having just finished picking a row from east to west
		//post: one row above precondition row on first corner facing east
		turnRight();
		move();
		turnRight();
		move();
	}
	
	public void pickGarden()
	{
		//pre: on first corner of a row
		//post: robot off at origin facing north
		for (int i = 0; i < 3; i++)
		{
			clearRow();
			setRowWest();
			clearRow();
			setRowEast();
			
		}
	}
	
	public void goToFinish()
	{
		turn180();
		move();
		turnLeft();
		move();
		turnRight();
	}
	
	public void faceNorth()
	{
		//pre: on any corner
		//post: robot facing north
		while ( !facingNorth() )
		{
			turnLeft();
		}
	}
	
	public void goToWall()
	{
		//pre: on any corner facing any direction
		//post: either facing a wall, or driving forever in the precondition direction looking for a wall
		while ( frontIsClear() )
		{
			move();
		}
	}
}
