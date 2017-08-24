package CP2;

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
		this.setSize(400, 400);
		
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
		int val;
		for (int i = 0; i < 16; i++){
			val = grid.getValue(i/4, i % 4);
			if (val == 0){
				tiles[i].setText("");
				tiles[i].setBackground(Color.LIGHT_GRAY);
				tiles[i].setForeground(Color.DARK_GRAY);
			}
			else{
				tiles[i].setText("" + val);
				if (val == 2)
					tiles[i].setBackground(new Color(255, 204, 255));
				if (val == 4)
					tiles[i].setBackground(new Color(255, 153, 204));
				if (val == 8)
					tiles[i].setBackground(new Color(255, 128, 128));
				if (val == 16)
					tiles[i].setBackground(new Color(204, 102, 255));
				if (val == 32)
					tiles[i].setBackground(new Color(255, 102, 153));
				if (val == 64)
					tiles[i].setBackground(new Color(255, 77, 77));
				if (val == 128)
					tiles[i].setBackground(new Color(255, 51, 153));
				if (val == 256)
					tiles[i].setBackground(new Color(230, 100, 190));
				if (val == 512)
					tiles[i].setBackground(new Color(240, 128, 255));
				if (val == 1024)
					tiles[i].setBackground(new Color(204, 0, 67));
				if (val == 2048){
					tiles[i].setBackground(new Color(255, 0, 0));
					tiles[i].setForeground(Color.WHITE);
				}
			
			}
		}
		
	}

	private JPanel buildBottomPanel() 
	{
		// create a JPanel and use the instance variable status to add a label
		// to the bottom panel
		JPanel p = new JPanel();
		p.setBackground(Color.DARK_GRAY);
		
		status = new JLabel(grid.getStatus());
		status.setFont(new Font("Lucida Console", Font.BOLD, 18));
		status.setForeground(Color.LIGHT_GRAY);
		p.add(status);
		return p;
	}

	private JPanel buildTopPanel(ActionHandler handler) 
	{
		// create JPanel and add a JButton to it for "New game"
		// also use the instance variable score to add a label
		JPanel p = new JPanel();
		p.setBackground(Color.DARK_GRAY);
		p.setLayout(new BorderLayout());
		
		JButton button = new JButton("New game");
		button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 3));
		button.setFont(new Font("Lucida Console", Font.BOLD, 18));
		button.setBackground(Color.DARK_GRAY);
		button.setForeground(Color.LIGHT_GRAY);
		button.setLayout(new BorderLayout());
		p.add(button, BorderLayout.WEST);
		
		score = new JLabel("Score: " + grid.getScore());
		score.setFont(new Font("Lucida Console", Font.BOLD, 18));
		score.setForeground(Color.LIGHT_GRAY);
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
		p.setBackground(Color.DARK_GRAY);
		p.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 8));
		GridLayout layout = new GridLayout(4, 4);
		layout.setHgap(5); layout.setVgap(5);
		p.setLayout(layout);
		
		
		
		for (int i = 0; i < 16; i++){
			tiles[i] = new JLabel();
			Font myFont = new Font("Lucida Console", Font.BOLD, 22);
			tiles[i].setFont(myFont);
			tiles[i].setForeground(Color.DARK_GRAY);
			tiles[i].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
			tiles[i].setVerticalAlignment(SwingConstants.CENTER);
			tiles[i].setHorizontalAlignment(SwingConstants.CENTER);
			tiles[i].setOpaque(true);
			tiles[i].setBackground(Color.WHITE);
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