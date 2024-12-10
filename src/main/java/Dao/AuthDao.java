package Dao;

import Models.User.PersistentUser;
import Models.User.UserForCreation;

import org.mindrot.jbcrypt.BCrypt;

import Config.DBConn;

import java.sql.*;

public class AuthDao implements IAuthDAO {

    private final DBConn conn;
    private PersistentUser user;

    private final String COLUMN_ID = "id";
    private final String COLUMN_FIRST_NAME = "first_name";
    private final String COLUMN_LAST_NAME = "last_name";
    private final String COLUMN_USERNAME = "username";
    private final String COLUMN_EMAIL = "email";
    private final String COLUMN_ADDRESS = "user_address";
    private final String COLUMN_PHONE = "phone_number";
    private final String COLUMN_ROLE = "role";

    public AuthDao() {
        this.conn = new DBConn();
    }

    @Override
    public PersistentUser getUser() {
        return user;
    }

    @Override
    public PersistentUser signUp(UserForCreation userForCreation) {
        String sql = "INSERT INTO users (first_name, last_name, username, email, user_password, user_address) VALUES (?, ?, ?, ?, ?, ?)";
        int generatedId = -1;

        try (Connection connection = conn.getConnectionDb();
                PreparedStatement preparedStatement = connection.prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, userForCreation.getFirts_name());
            preparedStatement.setString(2, userForCreation.getLast_name());
            preparedStatement.setString(3, userForCreation.getUsername());
            preparedStatement.setString(4, userForCreation.getEmail());
            preparedStatement.setString(5, userForCreation.getPasswordHash());
            preparedStatement.setString(6, userForCreation.getAddress());

            if (preparedStatement.executeUpdate() == 0) {
                throw new SQLException("Error al crear el usuario, no se insertaron filas");
            }

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    generatedId = generatedKeys.getInt(1);
                }
            }

            this.user = new PersistentUser(generatedId, userForCreation.getFirts_name(),
                    userForCreation.getLast_name(), userForCreation.getUsername(),
                    userForCreation.getEmail(), userForCreation.getPhone(),
                    userForCreation.getAddress(), false);

        } catch (SQLException e) {
            throw new RuntimeException("Error al crear el usuario: " + e.getMessage());
        }

        return user;
    }

    @Override
    public PersistentUser signInByEmail(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("El correo y la contraseña no pueden estar vacíos");
        }

        try (Connection connection = conn.getConnectionDb();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    boolean isMatchPassword = BCrypt.checkpw(password, resultSet.getString("user_password"));

                    if (isMatchPassword) {
                        this.user = new PersistentUser(
                                resultSet.getInt(COLUMN_ID),
                                resultSet.getString(COLUMN_FIRST_NAME),
                                resultSet.getString(COLUMN_LAST_NAME),
                                resultSet.getString(COLUMN_USERNAME),
                                resultSet.getString(COLUMN_EMAIL),
                                resultSet.getString(COLUMN_PHONE),
                                resultSet.getString(COLUMN_ADDRESS),
                                resultSet.getString(COLUMN_ROLE).equals("ADMIN"));
                    }
                } else {
                    throw new RuntimeException("El correo no está registrado");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        if (user == null) {
            throw new RuntimeException("El correo o la contraseña es incorrecta");
        }

        return user;
    }

    @Override
    public PersistentUser signInAsAdmin(String email, String password) {
        String sql = "SELECT * FROM users WHERE email = ?";

        if (email == null || email.isEmpty() || password == null || password.isEmpty()) {
            throw new IllegalArgumentException("El correo y la contraseña no pueden estar vacíos");
        }

        try (Connection connection = conn.getConnectionDb();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, email);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    boolean isMatchPassword = BCrypt.checkpw(password, resultSet.getString("user_password"));

                    if (isMatchPassword) {
                        // Verificar si el usuario es un administrador
                        if ("ADMIN".equals(resultSet.getString("role"))) {
                            this.user = new PersistentUser(
                                    resultSet.getInt(COLUMN_ID),
                                    resultSet.getString(COLUMN_FIRST_NAME),
                                    resultSet.getString(COLUMN_LAST_NAME),
                                    resultSet.getString(COLUMN_USERNAME),
                                    resultSet.getString(COLUMN_EMAIL),
                                    resultSet.getString(COLUMN_PHONE),
                                    resultSet.getString(COLUMN_ADDRESS),
                                    resultSet.getString(COLUMN_ROLE).equals("ADMIN"));
                        } else {
                            throw new RuntimeException("El usuario no es un administrador");
                        }
                    }
                } else {
                    throw new RuntimeException("El correo no está registrado");
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }

        if (user == null) {
            throw new RuntimeException("El correo o la contraseña es incorrecta");
        }

        return user;
    }

    @Override
    public boolean signOut() {
        this.user = null;
        return true;
    }
}
