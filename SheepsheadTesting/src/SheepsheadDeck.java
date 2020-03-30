import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SheepsheadDeck {
    public static final int DECKSIZE = 32;

    private List<Card> deck;

    public SheepsheadDeck() {
        deck = new ArrayList<>(DECKSIZE);
        Deck.initDeck(deck, SheepsheadCardValue.values(), CardSuits.values());
    }

    public List<Card> getDeck() {
        return deck;
    }

    public void shuffle() {
        Deck.shuffle(deck);
    }

    public List<Card> deal(Game game) {
        List<Player> players = game.getPlayers();
        for(Player p: players) {
            dealHand(p,players.size());
        }
        // Return remaining cards in the deck as the
        // blind
        return deck;
    }

    public void dealHand(Player player, int numPlayers) {
        Random rand = new Random();
        Hand hand = new Hand(numPlayers);
        Card[] cards = new Card[DECKSIZE/numPlayers];
        for (int i = 0; i < 6; i++) {
            int randPos = rand.nextInt(deck.size());

            while(deck.get(randPos) == null) {
                randPos = rand.nextInt(deck.size());
            }
            cards[i] = deck.get(randPos);
            deck.remove(randPos);
        }
        hand.setHand(cards);
        player.setHand(hand);
    }
}
