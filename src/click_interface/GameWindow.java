package click_interface;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import game_logic.Game;
import game_logic.Point;

public class GameWindow extends JFrame {

	private Game game; // creates new tic tac to game
	
	// declares constansts
	private static final Color MAIN_COLOR = new Color(68, 80, 105);
	private static final Color BACKGROUND_COLOR = new Color(91, 154, 139);
	private static final String DEFAULT_STRING = "Tic Tac Toe";

	// declares GUI elements
	private JPanel titlePanel; 
	private JPanel buttonPanel;
	private JLabel textField;
	private JButton newGameButton;
	private JButton[] buttons = new JButton[9]; 
	
	
	
	public GameWindow()
	{  
		game = new Game(true); // creates tic tac to game
		
		// sets up frame
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800,700);
		this.setResizable(false);
		this.setLayout( new BorderLayout());
	
		// sets up text field
		textField = new JLabel();
		textField.setBackground(BACKGROUND_COLOR);
		textField.setForeground(Color.black);
		textField.setHorizontalAlignment(JLabel.CENTER);
		textField.setText(DEFAULT_STRING);
		textField.setFont(new Font("Ink Free",Font.BOLD,30));
		textField.setOpaque(true);
		

		// sets up new game button
		newGameButton = new JButton();
		newGameButton.setBackground(MAIN_COLOR);
		newGameButton.setForeground(Color.black);
		newGameButton.setFocusable(false);
		newGameButton.setText("New Game");
		newGameButton.setFont(new Font("Ink Free",Font.BOLD,30));
		newGameButton.addActionListener(e -> onNewGameClick());
		
		// sets up titlePanel that holds newGameButton and textField
		titlePanel = new JPanel();
		titlePanel.setBounds(0,0,800,100);
		titlePanel.setLayout(new BorderLayout());
	
		// sets up buttonPanel that holds the button array in a grid
		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3,3,10,10));
		buttonPanel.setBackground(BACKGROUND_COLOR);
		
		// adds everything to the frame 
		makeButtons();
		titlePanel.add(textField,BorderLayout.CENTER);
		titlePanel.add(newGameButton,BorderLayout.EAST);
		this.add(titlePanel,BorderLayout.NORTH);
		this.add(buttonPanel,BorderLayout.CENTER);
		this.setVisible(true);
		
	}
	
	// Creates buttons and adds them to buttonPanel grid
	private void makeButtons()
	{
		for(int i = 0; i < 9; i++)
		{ 
			final int ID = i+1; // stores the buttons ID
			
			// set up JButton
			buttons[i] = new JButton(); 
			buttons[i].setFocusable(false);
			buttons[i].setBackground(MAIN_COLOR);
			buttons[i].setForeground(Color.black);
			buttons[i].setFont(new Font("Ink Free",Font.BOLD,60));
			buttons[i].addActionListener(e -> onTileClick(ID)); // makes button call onTileClick() when button pressed
			buttonPanel.add(buttons[i]);
		}
	
	}
	
	// checks if move is valid and if so then it executes the move
	private void onTileClick(int ID)
	{ 
	
		if(game.moveIfValid(ID)) {
			//buttons[ID-1].setText(game.getTurn());
			drawBoard();
			checkForWinner();
			
			
		}
	}
	
	private void drawBoard()
	{ 
		String[][] board = game.getBoard();
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[x].length; y++)
			{    if(board[x][y] != Game.NEUTRAL_STRING)
				buttons[Point.pointToInt(new Point(x,y))-1].setText(board[x][y]);
			}
		}
		textField.setText(game.getTurn() + "'s turn!");
	}
	
	// resets game 
	private void onNewGameClick()
	{
		game.defaultState();
		textField.setText(DEFAULT_STRING);
		for(int i = 0; i < 9; i++)
		{
			buttons[i].setText("");
		}
	
	}
	
	
	//checks if someone won or if there is a tie and displays if true
	private void checkForWinner()
	{
		if(game.gameState() == "tie") 
			textField.setText("Its a tie!!!");
		else if(game.gameState() != Game.NEUTRAL_STRING) 
			textField.setText(game.gameState() + " wins!");
	}
	
	
}
