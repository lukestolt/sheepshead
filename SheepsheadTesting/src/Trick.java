import java.util.ArrayList;
import java.util.List;

public class Trick {
    int score;
    private List<Pair<Card, Player>> cards;
    private final int numPlayers;

    public Trick(int numPlayers) {
        this.numPlayers = numPlayers;
        cards = new ArrayList<>(numPlayers);
    }

//    public void addTrick(List<Card> playedCards) {
//        cards.addAll(playedCards);
//    }

    public void addCard(Card c,Player p) {
        if(cards.size() < numPlayers) {
            cards.add(new Pair<>(c,p));
            score += c.getVal().getPointValue().getPointValue();
        }
    }

    public Player getWinner() {
        Pair<Card, Player> max = cards.get(0);
        for (Pair<Card, Player> cpp: cards){
            if(){

            }
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        int cnt = 0;
        str.append("Trick: ");
        for(Pair<Card, Player> c : cards) {
            str.append(cnt).append(":").append(c.getK().toString());
            cnt++;
        }
        return str.toString();
    }
}
