import { Card } from "./card";

class Player{
    public name: string ='';
    public id: string ='';
    // this is null when it isn't the curPlayer
    public cards: Card[];
    public numCards: number;
    public isTurn: boolean;
    constructor(playerId: string, name?: string ,cards?: Card[], numCards?: number) {
        this.id = playerId;
        this.name = name;
        this.cards = cards;     // this will set the cards to null if the parameter isn't there
        this.numCards = numCards;
        this.isTurn = false;
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