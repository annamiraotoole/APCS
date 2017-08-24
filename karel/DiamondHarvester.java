
//taken and edited from superharvester

package karel;

import kareltherobot.*;

public class DiamondHarvester extends Robot
{
	public DiamondHarvester(int street, int avenue, Direction direction, int beepers)
    {
        super(street, avenue, direction, beepers);
    }

	public static void main(String[] args) 
	{
		World.readWorld("WorldFiles/fig3-5.kwld");
		World.setDelay(10);
        World.setVisible(true);

        DiamondHarvester karel = new DiamondHarvester(2, 2, East, 0);
        World.setDelay(10);
		
        //goes to first corner
        karel.move();
        karel.move();
        karel.move();
        karel.move();
        //picks garden
        karel.pickGarden();
        karel.goToOrigin();
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
		//post: one stair up/down (depending on the precondition row) past the end of the precondition row, holding all beepers from row
		while ( nextToABeeper() )
		{
			clearCorner();
			turnLeft();
			move();
			turnRight();
			move();
		}
	}
	
	public void clearCorner()
	{
		//pre: on any corner
		//post: holding all the beepers from precondition corner
		while ( nextToABeeper() )
		{
			pickBeeper();
		}
	}
	
	public void setRowSouthwest()
	{
		//pre: having just finished picking a row from southwest to northeast
		//post: first corner of southwest row facing west
		turn180();
		move();
		move();
	}
	
	public void setRowNortheast()
	{
		//pre: having just finished picking a row from northeast to southwest
		//post: on first corner of northeast row facing east
		turnRight();
		move();
		move();
		turnRight();
	}
	
	/* not used in Harvester (originally from superharvest)
	 * public void checkForRow()
	{
		//pre: on first corner of a row, right after setting up to pick a row
		//post: there is a beeper-- nothing happens, no beeper-- robot off at the origin facing north
		if ( !nextToABeeper() )
		{
			goToOrigin();
		}
	}
	*/
	
	
	public void pickGarden()
	{
		//pre: on first corner of a row
		//post: robot holding all beepers
		for (int i = 0; i < 2; i++)
		{
			clearRow();
			setRowSouthwest();
			clearRow();
			setRowNortheast();
			
		}
	}

	public void goToOrigin()
	{
		//pre: in a world with no obstacles other than west wall and south wall
		//post: robot off at origin facing north
		faceNorth();
		turnLeft();
		goToWall();
		turnLeft();
		goToWall();
		turn180();
		turnOff();
	}
	
	/* not used; from Harvester
	public void goToFinish()
	{
		turn180();
		move();
		turnLeft();
		move();
		turnRight();
	}
	*/
	
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
