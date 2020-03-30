import java.net.ServerSocket;
import java.util.*;

public class Game {

    private final String uuid;
    private List<Player> players;
    private SheepsheadDeck deck;
    private List<Card> blind;
    private Player dealer;
    private Player picker;
    private ServerSocket gamesSocket;
    private final int MAX_PLAYERS;
    private static boolean start;

    public Game(int numPlayers) {
        uuid = UUID.randomUUID().toString();
        MAX_PLAYERS = numPlayers;
        players = new ArrayList<>();
        deck = new SheepsheadDeck();
    }

    public boolean addPlayer(Player player) {

        if(players.size() >= MAX_PLAYERS) {
            return false;
        } else {
            players.add(player);
            return true;
        }
    }

    public void start() {
        if(!start) {
            start = true;
        }
    }

    public Player getDealer() {
        return dealer;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public String getUID() {
        return uuid;
    }

    public <T> Comparable getCreated() {
        return null;
    }

    @Override
    public String toString(){
        return players.toString();
    }

    public void dealHands() {
        blind = deck.deal(this);
    }

    public void initDealer() {
        int x = Util.getRandomIntBound(MAX_PLAYERS);
        System.out.println("Dealer is Player: " + x);
        dealer = players.get(x);
    }

    public Player getPicker() {
        //TODO need to figure out how to assign picker
        // Via sockets
        Scanner sc = new Scanner(System.in);
        int ind = players.indexOf(dealer);
        int cnt = 0;
        for(int i = ind; cnt < MAX_PLAYERS; i = nextPlayer(i)) {
            System.out.println(players.get(i).getHand().toString());
            System.out.println("Become Picker (y/n)?");
            String inp = sc.nextLine();
            if(inp.equals("y")) {
                System.out.println();
                System.out.println("Player: " + players.get(i).getUser().toString() + " is Picker");
                return players.get(i);
            }
            cnt++;
        }
        // Default to left of dealer
        System.out.println("Player: " + players.get(players.indexOf(dealer)).getUser().toString() + " is Picker");
        return players.get(nextPlayer(players.indexOf(dealer)));
    }

    private int nextPlayer(int index) {
        if(index+1 < MAX_PLAYERS) {
            return index+1;
        } else {
            return 0;
        }
    }

    public Trick playHands() {
        Scanner sc = new Scanner(System.in);
        Trick trick = new Trick();
        int ind = nextPlayer(players.indexOf(dealer));
        int cnt = 0;
        for(int i = ind; cnt < MAX_PLAYERS; i = nextPlayer(i)) {
            Card c;
            do {
                System.out.println(players.get(i).getHand().toString());
                System.out.println("Play a card (Must follow suit if can): ");
                int card = sc.nextInt();
                c = players.get(i).playCard(card);
            } while(c == null);
            trick.addCard(c);
            cnt++;
            System.out.println();
        }
        System.out.println(trick);
        return trick;
    }
}
