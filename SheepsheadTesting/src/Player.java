import java.util.ArrayList;
import java.util.List;

public class Player {
    private User user;
    private Hand hand;
    private List<Trick> tricks;

    public Player(User user) {
        this.user = user;
        tricks = new ArrayList<>();
    }

//    public Player() {
//        this.user = new GuestUser();
//    }

    public User getUser() {
        return user;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void wonTrick(Trick trick) {
        tricks.add(trick);
    }

    @Override
    public String toString() {
        return user.toString();
    }

    public Card playCard(int cardNum) {
        Card c = hand.getCards()[cardNum];
        if(c != null) {
            // card exits
            // checkFollowSuit/validate card
            hand.getCards()[cardNum] = null;
        }
        // invalid card
        System.out.println(hand.toString());
        return c;
    }

    public boolean hasCards() {
        return hand.getCards().length != 0;
    }
}
