package Controllers;

import Dao.AuthDao;
import Models.User.PersistentUser;
import Models.User.UserForCreation;

public class AuthController {

    private final AuthDao authService;

    public AuthController() {
        this.authService = new AuthDao();
    }

    public PersistentUser signIn(String email, String password) {
        validateNotEmpty(email, "El correo");
        validateNotEmpty(password, "La contraseña");
        return authService.signInByEmail(email, password);
    }

    public PersistentUser signUp(String firstName, String lastName, String username, String password,
            String email, String phone, String address) {

        validateNotEmpty(firstName, "El nombre");
        validateNotEmpty(lastName, "El apellido");
        validateNotEmpty(username, "El username");
        validateNotEmpty(password, "La contraseña");
        validateNotEmpty(email, "El correo");
        validateNotEmpty(address, "La dirección");
        validateEmail(email);

        UserForCreation newUser = new UserForCreation(password, firstName, lastName, username, email, phone, address);
        return authService.signUp(newUser);
    }

    public boolean signOut() {
        return authService.signOut();
    }

    private void validateNotEmpty(String value, String fieldName) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " no puede estar vacío.");
        }
    }

    private void validateEmail(String email) {
        String emailRegex = "^[\\w-.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        if (!email.matches(emailRegex)) {
            throw new IllegalArgumentException("El correo no es válido.");
        }
    }
}
