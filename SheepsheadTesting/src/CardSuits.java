public enum CardSuits {
    HEARTS(CardStrength.FAILSUIT), CLUBS(CardStrength.FAILSUIT),
    DIAMONDS(CardStrength.TRUMPSUIT), SPADES(CardStrength.FAILSUIT);

    private CardStrength cardStrength;

    CardSuits(CardStrength cardStrength) {
        this.cardStrength = cardStrength;
    }

    public CardStrength getCardStrength() {
        return cardStrength;
    }
}
