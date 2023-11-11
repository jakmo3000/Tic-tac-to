package game_logic;

public class Game {

private String[][] board;
private String turn;
private boolean isBotGame;
private boolean botTurn = false;


public static final String X_STRING = "X";
public static final String O_STRING = "O";
public static final String NEUTRAL_STRING = "-";
public static final String TIE_STRING = "tie";



public String[][] getBoard()
{
	return board;
}

public String getTurn()
{
	return turn;
}


// sets board to default state when Game object is instantiated
	public Game(boolean isBotgame)
	{
		this.isBotGame = isBotgame;
		defaultState();
	}



	public Game(Game game)
	{
		loadFrom(game);
	}

	public Game(Game game, boolean isBotGame)
	{   this.isBotGame = isBotGame;
		loadFrom(game);
	}

	public void loadFrom(Game game)
	{
		for(int x = 0; x < game.getBoard().length; x++)
		{
			for(int y = 0; y < game.getBoard()[x].length; y++)
			{
				this.board[x][y] = game.getBoard()[x][y];
			}
		}
	}
	
	// switches turn
	public void switchTurn()
	{
		turn = (turn == X_STRING) ? O_STRING : X_STRING;
	}
	
	// sets board to default state
	public void defaultState() 
	{
		board = new String[3][3];
		for(int x = 0; x < 3; x++)
		{
			for(int y = 0; y < 3; y++)
			{
				board[x][y] = NEUTRAL_STRING;
			}
		}	
		turn = X_STRING;
		botTurn = false;
	}
	
		
	// if the move is valid the move executes and returns true otherwise it returns false
	public boolean moveIfValid(int locAsInt)
	{
		Point loc =	Point.intToPoint(locAsInt);
		
		if(board[loc.getX()][loc.getY()] == NEUTRAL_STRING && checkForWinner() == false && botTurn == false ) {
			board[loc.getX()][loc.getY()] = getTurn();
			this.switchTurn();
			botTurn = isBotGame? true: false;
			
			botMove();
			return true;
		} 
		
		return false;
			
	}


	private void botMove()
	{
		Point loc = Bot.move(this);
		if(board[loc.getX()][loc.getY()] == NEUTRAL_STRING && checkForWinner() == false) {
			board[loc.getX()][loc.getY()] = getTurn();
			botTurn = isBotGame? true: false;
			
			this.switchTurn();
			botTurn = false;
		} 
		
		
	}
	
	public boolean moveIfValid(Point loc)
	{
		if(board[loc.getX()][loc.getY()] == NEUTRAL_STRING && checkForWinner() == false && botTurn == false ) {
			board[loc.getX()][loc.getY()] = getTurn();
			botTurn = isBotGame? true: false;
			
			this.switchTurn();
			return true;
		} 
		
		return false;
	}
	
	


	public String gameState()
	{
		// horizontal win cases
		if(board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != NEUTRAL_STRING)
			return board[0][0];
		
		else if(board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != NEUTRAL_STRING)
			return board[1][0]; 
		
		else if(board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != NEUTRAL_STRING)
			return board[2][0];
		
		// vertical win cases
		else if(board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != NEUTRAL_STRING)
			return board[0][0];
		
		else if(board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != NEUTRAL_STRING)
			return board[0][1];
		
		else  if(board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != NEUTRAL_STRING)
			return board[0][2];
		
		// diagonal win cases
		else if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != NEUTRAL_STRING)
			return board[0][0];
		else if(board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != NEUTRAL_STRING)
			return board[0][2];
		
		// is tie
		else if(isTie())
			return TIE_STRING;
		
		// board still going
		else
			return NEUTRAL_STRING;
		
	}





	public static String gameState(String[][] board)
	{
		// horizontal win cases
		if(board[0][0] == board[0][1] && board[0][0] == board[0][2] && board[0][0] != NEUTRAL_STRING)
			return board[0][0];
		
		else if(board[1][0] == board[1][1] && board[1][0] == board[1][2] && board[1][0] != NEUTRAL_STRING)
			return board[1][0]; 
		
		else if(board[2][0] == board[2][1] && board[2][0] == board[2][2] && board[2][0] != NEUTRAL_STRING)
			return board[2][0];
		
		// vertical win cases
		else if(board[0][0] == board[1][0] && board[0][0] == board[2][0] && board[0][0] != NEUTRAL_STRING)
			return board[0][0];
		
		else if(board[0][1] == board[1][1] && board[0][1] == board[2][1] && board[0][1] != NEUTRAL_STRING)
			return board[0][1];
		
		else  if(board[0][2] == board[1][2] && board[0][2] == board[2][2] && board[0][2] != NEUTRAL_STRING)
			return board[0][2];
		
		// diagonal win cases
		else if(board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] != NEUTRAL_STRING)
			return board[0][0];
		else if(board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] != NEUTRAL_STRING)
			return board[0][2];
		
		// is tie
		else if(isTie(board))
			return TIE_STRING;
		
		// board still going
		else
			return NEUTRAL_STRING;
		
	}
	
	
	
	public boolean checkForWinner()
	{
		if(gameState() == X_STRING || gameState() == O_STRING)
			return true;
		else
			return false;
	}
	private static boolean isTie(String[][] board) 
	{
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[x].length; y++)
			{
				if(board[x][y] == NEUTRAL_STRING)
					return false;
			}
		}
		return true;
	}
	
	private boolean isTie() 
	{
		for(int x = 0; x < board.length; x++)
		{
			for(int y = 0; y < board[x].length; y++)
			{
				if(board[x][y] == NEUTRAL_STRING)
					return false;
			}
		}
		return true;
	}


	// prints board in console
	public void printboard()
	{
		String turn = getTurn();
		
		for(int x = 0; x < board.length; x++)
		{ 
			for(int y = 0; y < board[x].length; y++)
			{
				System.out.print(" " + board[x][y] + " ");
			}
			System.out.println("");
		}
		
		System.out.println("It is " + turn + "'s turn");
	}

	public static void printboard(String[][] board)
	{
		
		
		for(int x = 0; x < board.length; x++)
		{ 
			for(int y = 0; y < board[x].length; y++)
			{
				System.out.print(" " + board[x][y] + " ");
			}
			System.out.println("");
		}
		
	
	}
	
			
}
