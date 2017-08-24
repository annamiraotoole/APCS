package karel;

import kareltherobot.*;

public class Hurdles extends Robot
{
	public Hurdles(int street, int avenue, Direction direction, int beepers)
    {
        super(street, avenue, direction, beepers);
    }
	public static void main(String[] args) 
	{
		World.readWorld("WorldFiles/fig6-22.kwld");
		World.setDelay(5);
        World.setVisible(true);
        
        Hurdles karel = new Hurdles (1, 1, East, 0);
        
        karel.followWallOnRightCont();
        karel.turnOff();

	}
	
	public void turnRight()
	{
		turnLeft();
		turnLeft();
		turnLeft();
	}
	
	public boolean rightIsBlocked()
	{
		turnRight();
		if ( frontIsClear() )
		{
			turnLeft();
			return false;
		}
		else
		{
			turnLeft();
			return true;
		}
	}
	
	public void followWallOnRight()
	{
		//pre: initial starting position, wall/hurdle on right, one step past end of hurdle facing north
		//post: will have moved one step along the wall/hurdle
		if ( rightIsBlocked() )
		{
			if ( frontIsClear() )
			{
				move();
			}
			else
			{
				turnLeft();
			}
		}
		else
		{
			turnRight();
			move();
		}
	}
	
	public void followWallOnRightCont()
	{
		//pre: initial starting position, wall/hurdle on right, one step past end of hurdle facing north
		//post: robot stopped next to a beeper that was sitting along the wall,
		//post cont.: or still following the wall on the right
		while ( !nextToABeeper() )
		{
			followWallOnRight();
		}
	}
}
