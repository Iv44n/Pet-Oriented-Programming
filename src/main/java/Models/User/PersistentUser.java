package Models.User;

public class PersistentUser extends BaseUser {

    private final int id;
    private final boolean isAdmin;

    public PersistentUser(int userId, String firts_name, String last_name, String username, String email, String phone, String address, boolean isAdmin) {
        super(firts_name, last_name, username, email, phone, address);
        this.id = userId;
        this.isAdmin = isAdmin;
    }

    public int getId() {
        return id;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
