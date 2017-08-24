package karel;

import kareltherobot.*;

public class StairSweeper extends UrRobot
{
    public StairSweeper(int street, int avenue, Direction dir, int beeps)
    {
        super(street, avenue, dir, beeps);
    }

    public void turnRight()
    {
        turnLeft();
        turnLeft();
        turnLeft();
    }

    public void climbStair()
    {
        turnLeft();
        move();
        turnRight();
        move();
        pickBeeper();
    }

    public static void main(String [] args)
    {
        World.readWorld("WorldFiles/fig3-1.kwld");
        World.setVisible(true);

        StairSweeper Alex = new StairSweeper(1, 1, East, 0);
        World.setDelay(20);

        Alex.climbStair();
        Alex.climbStair();
        Alex.climbStair();
        Alex.turnOff();
    }
}