public class Knights722Test {
    public static void main(String[] args) {
        KnightsTour722 kt = new KnightsTour722(4, 4);
        while (kt.makeMove() == true){
            kt.printBoard();
        }

    }
}
