package Services;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import Models.Animal;
import Utils.DBConn;

public class AnimalService {

    private final DBConn conn;
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_TYPE = "type";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_BREED = "breed";
    private static final String COLUMN_SEX = "sex";
    private static final String COLUMN_WEIGHT = "weight";
    private static final String COLUMN_DESCRIPTION = "description";
    private static final String COLUMN_STATUS = "status";
    private static final String COLUMN_ADMISSION_DATE = "admission_date";

    public AnimalService() {
        this.conn = new DBConn();
    }

    public List<Animal> findAll() {
        List<Animal> animals = new ArrayList<>();

        String sql = "SELECT * FROM animals";
        try (Connection connection = conn.getConnectionDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Animal animal = new Animal(
                        resultSet.getInt(COLUMN_ID),
                        resultSet.getString(COLUMN_NAME),
                        resultSet.getString(COLUMN_TYPE),
                        resultSet.getInt(COLUMN_AGE),
                        resultSet.getString(COLUMN_BREED),
                        resultSet.getString(COLUMN_SEX),
                        resultSet.getFloat(COLUMN_WEIGHT),
                        resultSet.getString(COLUMN_DESCRIPTION),
                        resultSet.getString(COLUMN_STATUS),
                        resultSet.getString(COLUMN_ADMISSION_DATE)
                );
                animals.add(animal);
            }
        } catch (SQLException e) {
        }

        return animals;
    }

    public Animal findById(int id) {
        Animal animal = null;
        String sql = "SELECT * FROM animals WHERE id = ?";

        try (Connection connection = conn.getConnectionDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    animal = new Animal(
                            resultSet.getInt(COLUMN_ID),
                            resultSet.getString(COLUMN_NAME),
                            resultSet.getString(COLUMN_TYPE),
                            resultSet.getInt(COLUMN_AGE),
                            resultSet.getString(COLUMN_BREED),
                            resultSet.getString(COLUMN_SEX),
                            resultSet.getFloat(COLUMN_WEIGHT),
                            resultSet.getString(COLUMN_DESCRIPTION),
                            resultSet.getString(COLUMN_STATUS),
                            resultSet.getString(COLUMN_ADMISSION_DATE)
                    );
                }
            }
        } catch (SQLException e) {
        }

        return animal;
    }
}
