class Card {
    public readonly suit: string;
    public readonly value: string;
    constructor(suit: string, value: string) {
        this.suit = suit;
        this.value = value;
    }
}

export { Card }