public class Hand {
    private Card[] hand;

    public Hand(int numPlayers) {
        hand = new Card[SheepsheadDeck.DECKSIZE/numPlayers];
    }

    public Card[] getCards() {
        return hand;
    }

    public void setHand(Card[] cards) {
        hand = cards;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        int cnt = 0;
        for(Card c: hand) {
            if(c == null){
                stringBuilder.append(cnt).append(":").append(" ");
                cnt++;
                continue;
            }
            stringBuilder.append(cnt).append(":").append(c.toString()).append(" ");
            cnt++;
        }
        return stringBuilder.toString();
    }

}
