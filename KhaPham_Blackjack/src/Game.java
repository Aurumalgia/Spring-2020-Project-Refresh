import deck.*;

import java.util.concurrent.TimeUnit;

public class Game {
    Hand playerHand = new Hand(false);
    Hand dealerHand = new Hand(true);
    View v;
    Deck d;
    int playerGold, playerTotal, dealerTotal, round = 1, playerWins, ties, bet;
    boolean busted = false, hold = false, continuePlaying = true;
    public Game(View v, Deck d, int startingGold){
        this.v = v;
        this.d = d;
        playerGold = startingGold;
    }

    public void runBlackJack() throws InterruptedException {
        v.startGameOutput();
        while(continuePlaying) {
            bet = v.startGame(round, playerGold, calculateWinRate());
            if (startingHand()){
                v.showPlayerHand(playerHand);
                checkVictor();
            }
            else {
                v.showPlayerHand(playerHand);
                playerMove();
                if (playerHand.getHandTotal() > 21){
                    v.victoryScreen(2);
                    playerGold -= bet;

                }
                else {
                    dealerMove();
                    checkVictor();
                }
            }
            if (playerGold <= 0){
                continuePlaying = false;
            }
            else if (!v.playAgain(round, playerGold, calculateWinRate())){
                continuePlaying = false;
            }
            else {
                round++;
                clearHand();
            }
        }
        v.endOfGame(round, playerGold, calculateWinRate());
    }

    public void clearHand(){
        playerHand.getHand().clear();
        dealerHand.getHand().clear();
        hold = false;
        busted = false;
    }

    public void dealerMove(){
        busted = false;
        hold = false;
        v.showDealerHand(dealerHand);
        while(busted == false && hold == false && dealerHand.checkTotal() < 17){
            if (d.getDeck().size() == 0){
                d.reShuffleDeck();
            }
            dealerHand.getHand().add(d.dealCard());
            v.Action(true, dealerHand, "The Dealer");
        }
        v.Action(false, dealerHand, "The Dealer");

        v.showTotal(playerHand, dealerHand);


    }

    public double calculateWinRate(){
        double winrate;
        if (round>1) {
            winrate = (playerWins * 100) / (round);
        }
        else
            winrate = 0;
        return winrate;
    }

    public void playerMove() throws InterruptedException {
        while (busted == false && hold == false) {
            if(v.hitMeCheck()){
                if (d.getDeck().size() == 0){
                    d.reShuffleDeck();
                }
                v.Action(true, playerHand, "You");
                playerHand.getHand().add(d.dealCard());
                playerTotal = playerHand.checkTotal();
                busted = checkBusted(playerTotal);
                v.showPlayerHand(playerHand);
            }
            else {
                v.Action(false, playerHand, "You");
                hold = true;
            }
        }
        v.endOfPlayerTurn(playerHand);


    }

    public void checkVictor(){
        int playerVictoryOptions = 1;
        playerTotal = playerHand.getHandTotal();
        dealerTotal = dealerHand.getHandTotal();

        if(playerTotal > dealerTotal && playerTotal <= 21){
            playerVictoryOptions = 0;
            playerGold += bet;
            playerWins++;
        }
        else if (playerTotal < dealerTotal && dealerTotal <= 21){
            playerVictoryOptions = 2;
            playerGold -= bet;
        }
        else if (playerTotal == dealerTotal){
            playerVictoryOptions = 1;
        }
        else if(playerTotal > 21 && dealerTotal>21){
            playerVictoryOptions = 1;
        }

        v.victoryScreen(playerVictoryOptions);

    }

    public boolean startingHand() throws InterruptedException {
        boolean dealerNatural = false;

        if (d.getDeck().size() < 4){
            d.reShuffleDeck();
        }
        for (int x = 1; x <= 2; x++) {
            playerHand.getHand().add(d.dealCard());
            dealerHand.getHand().add(d.dealCard());
        }
        playerTotal = playerHand.checkTotal();
        dealerTotal = dealerHand.checkTotal();

        v.showDealerCard(dealerHand);
        if (dealerHand.getHand().get(0).getRankValue() >= 10){
            v.dealerNatural(dealerHand);

        }
        if (dealerTotal == 21){
            hold = true;
            v.endOfPlayerTurn(playerHand);
            dealerNatural = true;
        }
        return dealerNatural;
    }

    public boolean checkBusted(int total){
        if (total > 21){
            return true;
        }
        return false;
    }

}
