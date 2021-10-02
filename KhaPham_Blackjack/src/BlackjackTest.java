import deck.Deck;

public class BlackjackTest {
    public static void main(String[] args) throws InterruptedException {
        Deck d = new Deck();
        View v = new View();
        Game g = new Game(v, d, 100);
        g.runBlackJack();
    }
}
