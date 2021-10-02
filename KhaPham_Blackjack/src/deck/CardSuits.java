package deck;

public enum CardSuits {
    HEART("Hearts"), CLUB("Clubs"), SPADE("Spades"), DIAMOND("Diamonds");

    String suit;
    CardSuits(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }
}
