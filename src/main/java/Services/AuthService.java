package Services;

import Models.User.PersistentUser;
import Models.User.UserForCreation;
import Utils.DBConn;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class AuthService {

    private final DBConn conn;
    private PersistentUser user;

    public AuthService() {
        this.conn = new DBConn();
    }

    public PersistentUser getUser() {
        return user;
    }

    public PersistentUser signUp(UserForCreation userForCreation) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, user_password, address) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        if (userForCreation == null || userForCreation.getUsername() == null || userForCreation.getEmail() == null || userForCreation.getPassword() == null) {
            throw new IllegalArgumentException("Los campos obligatorios no pueden estar vacíos");
        }

        try (Connection connection = conn.getConnectionDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, userForCreation.getFirts_name());
            preparedStatement.setString(2, userForCreation.getLast_name());
            preparedStatement.setString(3, userForCreation.getUsername());
            preparedStatement.setString(4, userForCreation.getEmail());
            preparedStatement.setString(5, userForCreation.getPasswordHash());
            preparedStatement.setString(6, userForCreation.getAddress());
            int affectedRows = preparedStatement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Error al crear el usuario, no se insertaron filas");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                } else {
                    throw new SQLException("No se generó un ID para el nuevo usuario");
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

        if (generatedId == -1) {
            throw new RuntimeException("Error al crear el usuario");
        } else {
            this.user = new PersistentUser(generatedId, userForCreation.getFirts_name(), userForCreation.getLast_name(), userForCreation.getUsername(), userForCreation.getEmail(), userForCreation.getPhone(), userForCreation.getAddress());
        }

        return user;
    }

    public PersistentUser signInByEmail(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("El correo y la contraseña no pueden estar vacíos");
        }

        try (Connection connection = conn.getConnectionDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    boolean isMatchPassword = BCrypt.checkpw(password, resultSet.getString("user_password"));

                    if (isMatchPassword) {
                        this.user = new PersistentUser(
                                resultSet.getInt("id"),
                                resultSet.getString("first_name"),
                                resultSet.getString("last_name"),
                                resultSet.getString("username"),
                                resultSet.getString("email"),
                                resultSet.getString("phone"),
                                resultSet.getString("address")
                        );
                    } else {
                        throw new RuntimeException("La contraseña es incorrecta");
                    }
                } else {
                    throw new RuntimeException("el usuario no existe");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        return user;
    }

    public boolean signOut() {
        this.user = null;
        return true;
    }
}
