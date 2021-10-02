package deck;

import java.util.ArrayList;
import java.util.Random;

public class Deck {

    private ArrayList<Card> deck = new ArrayList<>();
    Random random = new Random();

    public Deck() {
        loadDeck();
        shuffleDeck();
    }

    public Card dealCard(){
        return deck.remove(0);
    }

    public void shuffleDeck(){
        for(int x = (deck.size()); x > 0; x--){
            deck.add(deck.remove(random.nextInt(x)));
        }
    }

    public void loadDeck(){
        for(CardSuits s : CardSuits.values()){
            for(CardValue v: CardValue.values()){
                deck.add(new Card(s, v));
            }
        }
    }

    public void reShuffleDeck(){
        deck.clear();
        loadDeck();
        shuffleDeck();
    }



    public void printDeck(){
        for(Card c: deck){
            System.out.println(c);
        }
    }
    public ArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Card> deck) {
        this.deck = deck;
    }



}
