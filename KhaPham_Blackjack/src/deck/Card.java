package deck;

public class Card {
    private CardSuits suit;
    private CardValue value;


    public Card(CardSuits suit, CardValue value) {
        this.suit = suit;
        this.value = value;
    }
    public int getRankValue(){
        return value.getCardValue();
    }

    public CardSuits getSuit() {
        return suit;
    }

    public void setSuit(CardSuits suit) {
        this.suit = suit;
    }

    public CardValue getValue() {
        return value;
    }

    public void setValue(CardValue value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value.getCardName() + " of " + suit.getSuit();
    }
}
