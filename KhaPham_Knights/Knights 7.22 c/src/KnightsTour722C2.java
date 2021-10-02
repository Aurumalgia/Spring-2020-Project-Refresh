import java.util.ArrayList;
import java.util.Arrays;

//This was my 2nd version of this class, hence the 2. I've deleted the original one due to it not working as intended.
public class KnightsTour722C2 {
    private int[][] board, accessNumbers;
    final int NUM_ROWS = 8, NUM_COLS = 8;
    private int moveCounter = 1;
    private int locRow, locCol;

    private final int [] H_SHIFT = {2, 1, -1, -2, -2, -1, 1, 2};
    private final int [] V_SHIFT = {-1, -2, -2, -1, 1, 2, 2, 1};

    public KnightsTour722C2(int startRow, int startCol){
        locCol = startCol;
        locRow = startRow;
        board = new int[NUM_ROWS][NUM_COLS];
        accessNumbers = new int[NUM_ROWS][NUM_COLS];
        placePiece(locRow, locCol);
        updateAccessNumber();
    }
    public void updateAccessNumber(){
        for(int row = 0; row < board.length; row++){
            for (int col = 0; col < board[row].length; col++){
                if(board[row][col] == 0){
                    ArrayList<Integer> moves = getAvailableMoves(row, col);
                    accessNumbers[row][col] =  moves.size();
                }
                else
                    //if the position is already filled, it will default to 9, a number that's impossible to have as an access number
                    accessNumbers[row][col] = 9;
            }
        }

    }


    public boolean placePiece(int row, int col){
        boolean piecePlaced = false;
        if(validMove(row, col)) {
            board[row][col] = moveCounter;
            moveCounter++;
            piecePlaced = true;
        }
        return piecePlaced;
    }
    public boolean makeMove(){
        boolean moveMade = false;
        ArrayList<Integer> moves = getAvailableMoves(locRow, locCol);


        if(moves.size() > 0) {
            int moveCheck;
            int move = moves.get(0);
            for (int x = 1; x < moves.size(); x++){
                moveCheck = moves.get(x);
                if(accessNumbers[locRow + V_SHIFT[moveCheck]][locCol + H_SHIFT[moveCheck]] < accessNumbers[locRow + V_SHIFT[move]][locCol + H_SHIFT[move]])
                    move = moveCheck;
            }
            moveMade=placePiece(locRow + V_SHIFT[move], locCol + H_SHIFT[move]);
            locRow = locRow + V_SHIFT[move];
            locCol = locCol + H_SHIFT[move];

        }


        return moveMade;
    }

    public ArrayList<Integer> getAvailableMoves(int row, int col){
        ArrayList<Integer> moves = new ArrayList<>();

        for (int i = 0; i < H_SHIFT.length; i++){
            int rowOffset = V_SHIFT[i]+row;
            int colOffset = H_SHIFT[i]+col;

            if (validMove(rowOffset, colOffset))
                moves.add(i);

        }
        return moves;

    }

    public boolean validMove(int row, int col){
        boolean validTop = row>=0;
        boolean validBottom = row < board.length;
        boolean validLeft = col>=0;

        return validTop && validBottom && validLeft && col < board[row].length && board[row][col] < 1;
    }

    public void printBoard(){
        for (int[] row: board)
            System.out.println(Arrays.toString(row));
        System.out.println("Access Numbers:");
        for (int[] row: accessNumbers)
            System.out.println(Arrays.toString(row));
        System.out.println();
    }

    public int getMoveCounter() {
        return moveCounter;
    }
}
