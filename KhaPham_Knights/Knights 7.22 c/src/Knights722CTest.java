public class Knights722CTest {
    public static void main(String[] args) {
        boolean fullRun;
        int fullRunCounter = 0;
        for(int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                KnightsTour722C2 kt = new KnightsTour722C2(row, col);
                while (kt.makeMove() == true) {
                    kt.updateAccessNumber();
                }
                if (kt.getMoveCounter() -1 == 64) {
                    fullRun = true;
                    fullRunCounter++;
                }
                else
                    fullRun = false;
                System.out.printf("\nStarting Position: %d, %d\nMoves Completed: %d\nCompleted Tour: %b\n", row, col, kt.getMoveCounter()-1, fullRun);
                //kt.printBoard();
            }
        }
        System.out.println("Full Tours Completed: "+fullRunCounter);
    }
}

