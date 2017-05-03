package data_structures_and_algorithms.chapter3.using_arrays;

/**
 * @author Serhii Kartashov
 * @since 1.0.0
 */
public class ScoreBoard {

    private int numEntries = 0; // number of actual entries
    private GameEntry[] board; // array of game entries (names & scores)

    /**
     * Constructs an empty scoreboard with the given capacity for storing entries.
     **/
    public ScoreBoard(int capacity) {
        board = new GameEntry[capacity];
    }

    public void add(GameEntry e) {
        int newScore = e.getScore();
        // is the new entry e really a high score?
        if (numEntries < board.length || newScore > board[numEntries - 1].getScore()) {
            if (numEntries < board.length) // no score drops from the board
                numEntries++; // so overall number increases
            // shift any lower scores rightward to make room for the new entry
            int j = numEntries - 1;
            while (j > 0 && board[j - 1].getScore() < newScore) {
                board[j] = board[j - 1]; // shift entry from j-1 to j
                j--; // and decrement j
            }
            board[j] = e; // when done, add new entry
        }
    }

    public GameEntry remove(int i) throws IndexOutOfBoundsException {
        if (i < 0 || i >= numEntries)
            throw new IndexOutOfBoundsException("Invalid index: " + i);
        GameEntry temp = board[i]; // save the object to be removed
        for (int j = i; j < numEntries - 1; j++) // count up from i (not down)
            board[j] = board[j + 1]; // move one cell to the left
        board[numEntries - 1] = null; // null out the old last score
        numEntries--;
        return temp; // return the removed object
    }

    public static void main(String[] args) {
        ScoreBoard scoreBoard = new ScoreBoard(5);
        scoreBoard.add(new GameEntry("Mike", 120));
        scoreBoard.add(new GameEntry("Paul", 2300));
        scoreBoard.add(new GameEntry("Jack", 2100));
        scoreBoard.add(new GameEntry("Anna", 300));
        scoreBoard.add(new GameEntry("Rose", 700));
        scoreBoard.add(new GameEntry("Maria", 900));
    }
}
