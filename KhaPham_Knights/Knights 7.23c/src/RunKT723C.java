public class RunKT723C {

    public RunKT723C(){
        boolean fullRun = false;
        int runCounter = 0;
        KnightsTour723C[] knights = new KnightsTour723C[1];
        long startTime = System.nanoTime(), elapsedTime;
        while(fullRun == false) {
            knights[0]= new KnightsTour723C(4, 4);
            while(knights[0].makeMove() == true){

            }
            if (knights[0].getMoveCounter() == 64+1){
                fullRun = true;
            }
            System.out.println(runCounter);
            runCounter++;
        }
        elapsedTime = System.nanoTime() - startTime;

        System.out.println("A solution has been found by brute forcing! After "+runCounter+" tries and "+((float) elapsedTime/1000000000)+" seconds, a solution is: ");
        knights[0].printBoard();
    }
}
