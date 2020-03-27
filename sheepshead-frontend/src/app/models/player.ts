import { Card } from "./card";

class Player{
    public name: string;
    public id: string
    // this is null when it isn't the curPlayer
    public cards: Card[];
    public numCards: number;
    constructor(playerId: string, cards?: Card[]) {
        this.id = playerId;
        this.cards = cards;     // this will set the cards to null if the parameter isn't there
    }

    // public addCards(cards: Card[]): void {
    //     this.cards = cards;
    // }

    // public getCards(): Card[]
    // {
    //     return this.cards;
    // }
}

export {Player}