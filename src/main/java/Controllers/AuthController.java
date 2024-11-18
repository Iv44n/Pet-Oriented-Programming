package Controllers;

import Models.User.PersistentUser;
import Models.User.UserForCreation;
import Services.AuthService;

public class AuthController {

    private final AuthService authService;

    public AuthController() {
        this.authService = new AuthService();
    }

    public PersistentUser signIn(String email, String password) {
        return authService.signInByEmail(email, password);
    }

    public PersistentUser signUp(String first_name, String last_name, String username, String password, String email, String phone, String address) {
        return authService.signUp(new UserForCreation(password, first_name, last_name, username, email, phone, address));
    }

    public boolean signOut() {
        return authService.signOut();
    }
}
