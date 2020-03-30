import java.util.ArrayList;
import java.util.List;

public class Driver {

    public static void main(String[] args) {

        // Create Players
        List<Player> players = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            players.add(new Player(new User(i)));
        }

        // Create Game
        Game g = new Game(5);

        // Add players to the game
        players.forEach(g::addPlayer);

        TestGameRunner tgr = new TestGameRunner();
        tgr.assignGame(g);

        Thread t = new Thread(tgr);
        t.start();


    }
}


