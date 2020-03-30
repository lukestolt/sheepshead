import java.util.ArrayList;
import java.util.List;

public class Trick {
    private List<Card> cards;

    public Trick() {
        cards = new ArrayList<>();
    }

    public void addTrick(List<Card> playedCards) {
        cards.addAll(playedCards);
    }

    public void addCard(Card c) {
        cards.add(c);
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
