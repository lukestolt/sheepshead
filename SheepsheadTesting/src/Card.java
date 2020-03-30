
public final class Card {
    private final SheepsheadCardValue val;
    private final CardSuits suit;

    public Card(SheepsheadCardValue val, CardSuits suit) {
        this.val = val;
        this.suit = suit;
    }

    public static boolean isTrumpSuit(Card card) {
        return card.getVal().getCardStrength() == CardStrength.TRUMPSUIT
                || card.getSuit().getCardStrength() == CardStrength.TRUMPSUIT;
    }

    public SheepsheadCardValue getVal() {
        return val;
    }

    public CardSuits getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return "<" + suit.toString() + "," + val.toString() + ">";
    }
}
