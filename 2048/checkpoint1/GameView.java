package CP1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GameView extends JFrame implements IChangeListener
{
	private JLabel status;
	private JLabel score;
	private GameState grid;
	private JLabel[] tiles;
	
	public GameView(GameState g)
	{
		// set the value for the instance variable grid
		grid = g;
		// this next line registers the GameView with the GameState
		grid.addListener(this);

		// create a new ActionHandler object +
		ActionHandler handler = new ActionHandler(g);
		
		// set the title and size
		this.setTitle("Annamira's Twos Game");
		this.setSize(300, 300);
		
		//initialize JLabel[] tiles
		tiles = new JLabel[16];
		
		// set the layout to a BorderLayout
		BorderLayout layout = new BorderLayout();
		this.setLayout(layout);
		
		// build a top panel and add it to the NORTH of the BoardLayout
		this.add(buildTopPanel(handler), BorderLayout.NORTH);
		
		// build a center panel and add it to the CENTER of the BoardLayout
		this.add(buildCenterPanel(handler), BorderLayout.CENTER);
		
		// build a bottom panel and add it to the BOTTOM of the BoardLayout
		this.add(buildBottomPanel(), BorderLayout.SOUTH);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void display()
	{
		setVisible(true);
	}
	
	@Override
	public void redraw()
	{
		// set the text of these labels: status, score, all the tiles
		status.setText(grid.getStatus());
		score.setText("Score: " + grid.getScore());
		int pos = 0;
		for (int r = 0; r < 4; r++){
			for (int c = 0; c < 4; c++){
				tiles[pos].setText("" + grid.getValue(r, c));
				pos++;
			}
		}
	}

	private JPanel buildBottomPanel() 
	{
		// create a JPanel and use the instance variable status to add a label
		// to the bottom panel
		JPanel p = new JPanel();
		status = new JLabel(grid.getStatus());
		p.add(status);
		return p;
	}

	private JPanel buildTopPanel(ActionHandler handler) 
	{
		// create JPanel and add a JButton to it for "New game"
		// also use the instance variable score to add a label
		JPanel p = new JPanel();
		p.setLayout(new BorderLayout());
		
		JButton button = new JButton("New game");
		p.add(button, BorderLayout.WEST);
		
		score = new JLabel("Score: " + grid.getScore());
		p.add(score, BorderLayout.EAST); 
		
		// the next line registers the button if it were called "button"  
		button.addActionListener(handler);
		
		return p;
	}
	
	private JPanel buildCenterPanel(ActionHandler handler)
	{
		// create a JPanel with a GridLayout
		// use the instance variable tiles to fill the grid with labels
		JPanel p = new JPanel();
		GridLayout layout = new GridLayout(4, 4);
		p.setLayout(layout);
		
		for (int i = 0; i < 16; i++){
			tiles[i] = new JLabel();
			tiles[i].setVerticalAlignment(SwingConstants.CENTER);
			tiles[i].setHorizontalAlignment(SwingConstants.CENTER);
			p.add(tiles[i]);
		}
		
		// the next line sets up the arrow keys using the method we gave you
		bindArrows(handler, p);
		
		return p;
	}

	// might need to give the students this method instead of making them 
	// learn about key bindings and anonymous inner classes
	private void bindArrows(ActionHandler handler, JPanel panel) 
	{
		String[] commands = {"left arrow", "up arrow", "right arrow", "down arrow"};
		for (int i = 0; i < 4; i++)
		{
			int copy = i;
			KeyStroke key = KeyStroke.getKeyStroke(KeyEvent.VK_LEFT + i, 0);
			Action action = new AbstractAction()
			{
				public void actionPerformed(ActionEvent e)
				{
					handler.handleArrowPress(GameState.LEFT + copy);
				}
			};
			panel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(key, commands[i]);
			panel.getActionMap().put(commands[i], action);
		}
	}
}