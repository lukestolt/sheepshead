import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {

    private List<Card> deck;

    public Deck() {
        deck = new ArrayList<>(52);
        initDeck(deck, SheepsheadCardValue.values(), CardSuits.values());
    }

    public static void initDeck(List<Card> deck, SheepsheadCardValue[] values, CardSuits[] suits) {
        for (CardSuits suit : suits) {
            for (SheepsheadCardValue value : values) {
                deck.add(new Card(value, suit));
            }
        }
    }

    public List<Card> getDeck() {
        return deck;
    }

    /**
     * Shuffles a deck of cards
     * @param d Deck of cards to shuffle
     */
    public static void shuffle(List<Card> d) {
        Random rand = new Random();

        for(int i = 0; i < 256; i++) {
            int r1 = rand.nextInt(d.size());
            int r2 = rand.nextInt(d.size());

            Card temp = d.get(r1);
            d.set(r1, d.get(r2));
            d.set(r2, temp);
        }
    }
}
