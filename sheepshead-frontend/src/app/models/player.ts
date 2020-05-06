import { Card } from "./card";

class Player{
    public name: string ='';
    public id: string ='';
    // this is null when it isn't the curPlayer
    public cards: Card[];
    public numCards: number;
    public isTurn: boolean;
    public numTricks: number = 0;
    constructor(playerId: string, name?: string ,cards?: Card[], numCards?: number) {
        this.id = playerId;
        this.name = name;
        this.cards = cards;
        this.numCards = numCards;
        this.isTurn = false;
    }
}

export {Player}