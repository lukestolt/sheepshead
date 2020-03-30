public class User {
    private int userNum;
    public User(int num) {
        userNum = num;
    }

    @Override
    public String toString() {
        return "User " + userNum;
    }
}
