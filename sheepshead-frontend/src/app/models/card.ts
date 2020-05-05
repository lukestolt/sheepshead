export class Card {
    public readonly suit: string;
    public readonly value: string;
    public isHovered?: boolean;
    constructor(suit: string, value: string) {
        this.suit = suit;
        this.value = value;
        this.isHovered = false;
    }
}

