package deck;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> hand = new ArrayList<>();
    boolean dealer;
    int handTotal;

    public Hand(boolean dealer){
        this.dealer = dealer;
    }

    public void printHand(){
        for(Card c: hand){
            System.out.println(c);
        }
    }
    public int checkTotal(){
        handTotal = 0;
        for (Card card : hand) {
            handTotal += card.getRankValue();
        }
        int aceOccurrences =0;

        for(Card c: hand){
            if(c.getRankValue() == 11){
                aceOccurrences++;
            }
        }

        while(aceOccurrences > 0 && handTotal > 21) {
            handTotal -= 10;
            aceOccurrences--;
        }
        return handTotal;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }

    public int getHandTotal() {
        return handTotal;
    }
}
