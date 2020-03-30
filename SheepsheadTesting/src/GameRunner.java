public class GameRunner implements Runnable {

    private Game game;

    private void gameLoop() {
        boolean active = true;
        if(game != null) {
            assignDealer();
            deal();
            selectPicker();

            // begin play with player left of dealer
            do {
                playHands();
                // something to determine trick winner
                checkWinner();
            } while(active);
            recordStats();
            game = null;
        }
    }

    private void assignDealer() {
        game.initDealer();
    }

    private void deal() {
        game.dealHands();
    }

    private void recordStats() {

    }

    public boolean assignGame(Game game) {
        // Do something here to assign game to this runner
        // Not sure how this should act

        return false;

    }

    private void checkWinner() {
        // declare winner from hands in play and give trick to that player

    }

    private void selectPicker() {
        game.getPicker();
    }

    private void playHands() {
        // Must follow suit if possible
    }

    @Override
    public void run() {

    }
}
