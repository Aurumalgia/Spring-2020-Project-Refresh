import deck.Hand;

import java.text.DecimalFormat;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class View {
    Scanner scanner = new Scanner(System.in);
    Scanner scanner2 = new Scanner(System.in);
    public View(){}

    public void startGameOutput(){
        System.out.printf("\nWelcome to Kha's Blackjack Table!%nThe rules here are simple: Try to hit 21 without going over.%n" +
                "Aces are 11 or 1, whatever works best for you. Face cards are 10. Try to beat me!%n" +
                "You will start with 100 gold pieces. Bet as many as you want. If you hit zero, you automatically lose.%n" +
                "Have Fun!%n%nPress enter to start the game...");
        scanner.nextLine();
    }

    public void dealerNatural(Hand hand) {
        System.out.println("The Dealer checks his face-down card...");
        if (hand.checkTotal() == 21){
            System.out.println("The Dealer has got a Natural! You must also get a Natural (An ace and a 10-card) in order to win. ");
        }
        else{
            System.out.println("The Dealer did not get a Natural. Phew. ");
        }
        System.out.println("Press enter to continue....");
        scanner.nextLine();
    }
    public void showPlayerHand(Hand hand) throws InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\nYou have "+hand.getHand().size()+" cards:");
        hand.printHand();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\nYour hand's total is "+hand.getHandTotal()+". ");
    }

    public void showDealerCard(Hand hand) {
        System.out.println("\nThe Dealer's first card is: \n"+hand.getHand().get(0).toString());
    }

    public void showDealerHand(Hand hand) throws InterruptedException {
        System.out.println("\nThe Dealer has "+hand.getHand().size()+" cards:");
        hand.printHand();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\nThe Dealer's hand's total is "+hand.getHandTotal()+". ");
        if (hand.getHandTotal() > 21) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("The Dealer has been busted! ");
        }
    }

    public void showTotal(Hand playerHand, Hand dealerHand){
        System.out.println("\nYour hand's total is "+playerHand.getHandTotal()+".");
        System.out.println("The Dealer's hand's total is "+dealerHand.getHandTotal()+". \n");


    }

    public void Action(boolean drewCard, Hand hand, String whosTurn) throws InterruptedException {
        if (drewCard) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("\n"+whosTurn+" drew a " + hand.getHand().get(hand.getHand().size() - 1));
        }
        else {
            System.out.println("\n" + whosTurn + " chose to stand. ");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public void endOfPlayerTurn(Hand hand) throws InterruptedException {
        if (hand.getHandTotal()>21)
            System.out.println("Your hand has been busted... ");
        TimeUnit.SECONDS.sleep(1);
        System.out.println("\n\nDealer's Turn.");
    }

    public boolean hitMeCheck() throws InterruptedException {
        boolean drawACard = false, validInput = false;
        String hitMe;
        TimeUnit.SECONDS.sleep(1);

        while (!validInput) {
            System.out.println("Enter 'Hit me' or 'Hit' if you would like to draw a card. Otherwise, enter 'stand'.");
            hitMe = scanner.nextLine().toLowerCase().trim();

            switch (hitMe) {
                case "hit me", "hit", "h" -> {
                    drawACard = true;
                    validInput = true;
                }
                case "st", "stand" -> validInput = true;
                default -> System.out.println("Invalid input.");
            }
        }
        if (drawACard)
            return true;
        else
            return hitMeCheck();
    }

    public void victoryScreen(int playerVictory){
        switch (playerVictory) {
            case 0 -> System.out.println("You win! ");
            case 1 -> System.out.println("It seems to be a tie. ");
            case 2 -> System.out.println("You lose! Better luck next time. ");
        }
    }

    public boolean playAgain(int round, int playerGold, double winRate){
        DecimalFormat df = new DecimalFormat("00.0");
        System.out.println("Round "+round+" has ended. \nGold Remaining: "+playerGold+" gold.\nWinrate: "+df.format(winRate)+"%. \nWould you like to play another round? ");
        String newGame;
        newGame = scanner.nextLine().trim().toLowerCase();
        boolean validInput = false, replay;
        replay = false;
        while(!validInput){
            switch (newGame) {
                case "yes", "y" -> {
                    replay = true;
                    validInput = true;
                }
                case "n", "no" -> validInput = true;
                default -> System.out.println("Invalid input. Please enter 'Yes' or 'No'");
            }
        }
        return replay;
    }

    public void endOfGame(int round, int playerGold, double winRate){
        DecimalFormat df = new DecimalFormat("00.0");
        System.out.println("The game has ended");
        System.out.println("Final Round: "+round);
        System.out.println("Ending Gold: "+playerGold);
        System.out.println("Winrate: "+df.format(winRate)+"%");
    }

    public int startGame(int round, int playerGold, double winRate) throws InterruptedException {
        DecimalFormat df = new DecimalFormat("00.0");
        clrScrn();
        System.out.println("Beginning of Round "+round);
        System.out.println("Your current win rate is "+df.format(winRate)+"%.");
        System.out.println("You have "+playerGold+" gold to bet.");
        TimeUnit.SECONDS.sleep(1);
        boolean validInput = false, correctBet = false;
        int bet;
        String input;

        do {
            System.out.println("\nHow much would you like to bet this round?");
            bet = betAmount();
            while (bet <= 0 || bet > playerGold) {
                System.out.println("Your number is invalid. Please enter in a number from 1 to " + playerGold);
                bet = betAmount();
            }
            do {
                System.out.println("You want to bet " + bet + " gold, is that correct?");
                input = scanner.nextLine().toLowerCase().trim();
                switch (correctBet(input)) {
                    case 0 -> {
                        validInput = true;
                        correctBet = true;
                    }
                    case 1 -> validInput = true;
                    case 2 -> validInput = false;
                }
            } while (!validInput);
        }while(!correctBet);
        return bet;

    }
    public int correctBet(String input){
        switch (input) {
            case "yes":
            case "y":
                return 0;
            case "n":
            case "no":
                return 1;
            default:
                System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
                return 2;
        }
    }

    public int betAmount(){
        boolean validInput = false;
        int bet = 0;
        while(!validInput){
            try{
                bet = scanner2.nextInt();
                validInput = true;
            }
            catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter the amount you'd like to bet. It must be a whole number. ");
                scanner2.next();
            }
        }
        return bet;
    }

    public void clrScrn(){
        for(int i = 0; i < 20; i++)
            System.out.print("\n");
    }
}
