package game_logic;

public class Bot {
  public Bot() {}

  public static String botSymbol = Game.O_STRING;
  public static String playerSymbol = Game.X_STRING;

  public static Point randomMove(Game game) {
    String[][] board = game.getBoard();
    while (game.gameState() == Game.NEUTRAL_STRING) {
      int x = Point.randomPointValue();
      int y = Point.randomPointValue();
      if (board[x][y] == Game.NEUTRAL_STRING)
        return new Point(x, y);
    }

    // if reaches this point it is error
    return new Point(0, 0);
  }

  public static Point move(Game game) {
    int bestScore = -20;
    Point bestMove = new Point(0, 0);
    String[][] board = game.getBoard();

    for (int x = 0; x < board.length; x++) {
      for (int y = 0; y < board.length; y++) {
        if (board[x][y] == Game.NEUTRAL_STRING) {
          board[x][y] = botSymbol;
          int score = minimax(board, -20, 20, false);
          board[x][y] = Game.NEUTRAL_STRING;

          if (score > bestScore) {
            bestScore = score;
            bestMove = new Point(x, y);
          }
        }

      }
    }
    return bestMove;
  }
  public static int count = 0;
  private static int minimax(String[][] board, int alpha, int beta, boolean maximizer) {

    if (Game.gameState(board) != Game.NEUTRAL_STRING) {
      return evaluateBoard(board);
    }

    if (maximizer) {
      int bestScore = -20;

      for (int x = 0; x < board.length; x++) {
        for (int y = 0; y < board[x].length; y++) {
          if (board[x][y] == Game.NEUTRAL_STRING) {
            board[x][y] = botSymbol;
            int score = minimax(board, alpha, beta, false);
            board[x][y] = Game.NEUTRAL_STRING;
            bestScore = Math.max(score, bestScore);
            alpha = Math.max(alpha, score);
            if (beta < alpha)
              return bestScore;

          }
        }

      }
      return bestScore;
    } else if (!maximizer) {
      int bestScore = 20;

      for (int x = 0; x < board.length; x++) {
        for (int y = 0; y < board[x].length; y++) {
          if (board[x][y] == Game.NEUTRAL_STRING) {
            board[x][y] = playerSymbol;

            int score = minimax(board, alpha, beta, true);
            board[x][y] = Game.NEUTRAL_STRING;
            bestScore = Math.min(score, bestScore);
            beta = Math.min(beta, score);
            if (beta < alpha)
              return bestScore;

          }
        }

      }
      return bestScore;
    } else {
      return 0;
    }

  }

  

  public static int evaluateBoard(String[][] board) {
    
    if (Game.gameState(board) == playerSymbol) {
      return -10;
    } else if (Game.gameState(board) == botSymbol) {

      return 10;
    }
    return 0;
  }

}
