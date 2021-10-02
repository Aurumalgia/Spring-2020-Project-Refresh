public class RunKT723A {

    public RunKT723A(int rerunNumber){
        int bestRun = 0;
        KnightsTour723A[]kt = new KnightsTour723A[rerunNumber];


        kt[0] = new KnightsTour723A(4, 4);
        while (kt[0].makeMove()==true){
        }
        System.out.println("Run "+0+" moved "+(kt[0].getMoveCounter()-1)+" times. ");

        for(int x = 1; x< kt.length; x++){
            kt[x] = new KnightsTour723A(4, 4);
            while (kt[x].makeMove()==true){
            }
            if (kt[x].getMoveCounter() > kt[bestRun].getMoveCounter()) {
                bestRun = x;
            }
            System.out.println("Run "+x+" moved "+(kt[x].getMoveCounter()-1)+" times. ");
        }

        System.out.println("\n\nThe best run was run "+bestRun+", with "+kt[bestRun].getMoveCounter()+" moves. \n");
        kt[bestRun].printBoard();
    }
}
