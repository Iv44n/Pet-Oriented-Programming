package Dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Config.DBConn;
import Models.Animal;

public class AnimalDao {

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

    public AnimalDao() {
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
            System.err.println("Error al obtener animales: " + e.getMessage());
        }

        return animals;
    }

    public List<Animal> findByBreed(String breed) {
        List<Animal> animals = new ArrayList<>();

        // Verificar si la raza es nula o vacía
        if (breed == null || breed.trim().isEmpty()) {
            return animals; // Retorna lista vacía si el término de búsqueda es nulo o vacío
        }

        // Normaliza la entrada a minúsculas y quita espacios innecesarios
        breed = breed.trim().toLowerCase();

        // Consulta con LIKE para búsqueda incompleta
        String sql = "SELECT * FROM animals WHERE LOWER(breed) LIKE ?";

        try (Connection connection = conn.getConnectionDb(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Usar LIKE con comodines y búsqueda insensible a mayúsculas/minúsculas
            String searchPattern = "%" + breed + "%";  // Usar minúsculas y comodines
            preparedStatement.setString(1, searchPattern);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar animales por raza: " + e.getMessage());
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
            System.err.println("Error al obtener animal por ID: " + e.getMessage());
        }

        return animal;
    }

    public boolean saveAnimalAdopted(int animalId, int userId, String observations) {
        String updateAnimalSql = "UPDATE animals SET status = 'ADOPTED' WHERE id = ?";
        String insertAdoptionSql = "INSERT INTO adoptions (animal_id, user_id, adoption_date, observations) VALUES (?, ?, ?, ?)";
        boolean result = false;

        try (Connection connection = conn.getConnectionDb()) {
            // Desactivar el autocommit para realizar ambas operaciones dentro de una transacción
            connection.setAutoCommit(false);

            try (PreparedStatement updateAnimalStatement = connection.prepareStatement(updateAnimalSql); PreparedStatement insertAdoptionStatement = connection.prepareStatement(insertAdoptionSql)) {

                // Actualizar el estado del animal
                updateAnimalStatement.setInt(1, animalId);
                updateAnimalStatement.executeUpdate();

                // Insertar el registro en la tabla de adopciones
                insertAdoptionStatement.setInt(1, animalId);
                insertAdoptionStatement.setInt(2, userId);
                insertAdoptionStatement.setDate(3, Date.valueOf(LocalDate.now()));  // Fecha actual
                insertAdoptionStatement.setString(4, observations);
                insertAdoptionStatement.executeUpdate();

                // Confirmar la transacción
                connection.commit();
                result = true;
            } catch (SQLException e) {
                // Si hay algún error, revertir la transacción
                connection.rollback();
                System.err.println("Error al guardar la adopción: " + e.getMessage());
                result = false;
            }
            return result;
        } catch (SQLException e) {
            System.err.println("Error al actualizar el estado del animal: " + e.getMessage());
            return false;
        }
    }

}
