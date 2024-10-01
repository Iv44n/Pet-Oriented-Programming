package Controllers;

import Models.User.PersistentUser;
import Models.User.UserForCreation;
import Services.AuthService;

public class AuthController {

    private final AuthService authService;

    public AuthController() {
        this.authService = new AuthService();
    }

    public PersistentUser signIn() {
        return authService.signInByEmail("username", "password");
    }

    public PersistentUser signUp() {
        return authService.signUp(new UserForCreation("password", "first_name", "last_name", "username", "email", "phone", "address"));
    }
}
