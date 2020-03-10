import { Card } from "./card";

class Player{
    public readonly name: string;
    private cards: Card[];
    constructor(name: string, cards?: Card[]) {
        this.name = name;
    }

    public addCards(cards: Card[]): void {
        this.cards = cards;
    }

    public getCards(): Card[]
    {
        return this.cards;
    }
}

export {Player}