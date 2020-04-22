public class TestGameRunner implements Runnable{

    private Game game;
    private boolean active;

    private void gameLoop() {
        if(game != null && active) {
            assignDealer();
            deal();
            selectPicker();

            // begin play with player left of dealer
            do {

                // something to determine trick winner
                checkTrickWinner(playHands());
                if(!game.getDealer().hasCards()) {
                    active = false;
                }
            } while(active);
            checkWinner();
            recordStats();
            game = null;
        }
    }

    private void checkWinner() {

    }

    private void assignDealer() {
        game.initDealer();
    }

    private void deal() {
        game.dealHands();
    }

    private void selectPicker() {
        game.getPicker();
    }

    private Trick playHands() {
        // Must follow suit if possible
        return game.playHands();
    }

    private void checkTrickWinner(Trick trick) {
        // declare winner from hands in play and give trick to that player
        System.out.println();
        System.out.println();
        System.out.println(trick.score);
        System.out.println(trick);
        System.out.println();
        System.out.println();

    }

    private void recordStats() {

    }

    public boolean assignGame(Game game) {
        // Do something here to assign game to this runner
        // Not sure how this should act
        if(!active) {
            this.game = game;
            active = true;
            return true;
        }
        return false;

    }

    @Override
    public void run() {
        gameLoop();
    }
}
