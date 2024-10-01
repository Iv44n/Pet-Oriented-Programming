package Models.User;

public class PersistentUser extends BaseUser {

    private final int id;

    public PersistentUser(int userId, String firts_name, String last_name, String username, String email, String phone, String address) {
        super(firts_name, last_name, username, email, phone, address);
        this.id = userId;
    }

    public int getId() {
        return id;
    }
}
