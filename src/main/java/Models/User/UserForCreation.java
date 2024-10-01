package Models.User;

import org.mindrot.jbcrypt.BCrypt;

public class UserForCreation extends BaseUser {

    private String password;
    private final String passwordHash;

    public UserForCreation(String password, String firts_name, String last_name, String username, String email, String phone, String address) {
        super(firts_name, last_name, username, email, phone, address);
        this.password = password;
        this.passwordHash = BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordHash() {
        return passwordHash;
    }
}
