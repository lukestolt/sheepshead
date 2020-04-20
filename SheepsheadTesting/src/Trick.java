import java.util.ArrayList;
import java.util.List;

public class Trick {
    private int score = 0;
    private List<Card> cards;
    private final int numPlayers;

    public Trick(int numPlayers) {
        this.numPlayers = numPlayers;
        cards = new ArrayList<>(numPlayers);
    }

//    public void addTrick(List<Card> playedCards) {
//        cards.addAll(playedCards);
//    }

    public void addCard(Card c) {
        if(cards.size() != numPlayers) {
            cards.add(c);
            score += c.getVal().getPointValue().getPointValue();
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int cnt = 0;
        str.append("Trick: ");
        for(Card c: cards) {
            str.append(cnt).append(":").append(c.toString());
            cnt++;
        }
        return str.toString();
    }
}
