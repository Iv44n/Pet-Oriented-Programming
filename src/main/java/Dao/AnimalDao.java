package Dao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Config.DBConn;
import Models.Animal.Animal;
import Models.Animal.AnimalBase;

public class AnimalDao implements IAnimalDAO {

    private final DBConn conn;
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "animal_name";
    private static final String COLUMN_TYPE = "animal_type";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_BREED = "breed";
    private static final String COLUMN_SEX = "sex";
    private static final String COLUMN_WEIGHT = "animal_weight";
    private static final String COLUMN_DESCRIPTION = "animal_description";
    private static final String COLUMN_STATUS = "adoption_status";
    private static final String COLUMN_ADMISSION_DATE = "date_of_admission";
    private static final String COLUMN_IMAGE = "animal_image";

    public AnimalDao() {
        this.conn = new DBConn();
    }

    @Override
    public boolean addAnimal(AnimalBase animal) {
        String sql = "INSERT INTO animals (animal_name, animal_type, age, breed, sex, animal_weight, animal_description, adoption_status, date_of_admission, animal_image) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, CURRENT_DATE, ?)";

        try (Connection connection = conn.getConnectionDb();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, animal.getName());
            preparedStatement.setString(2, animal.getType());
            preparedStatement.setInt(3, animal.getAge());
            preparedStatement.setString(4, animal.getBreed());
            preparedStatement.setString(5, animal.getSex());
            preparedStatement.setFloat(6, animal.getWeight());
            preparedStatement.setString(7, animal.getDescription());
            preparedStatement.setString(8, animal.getAdoptionStatus());
            preparedStatement.setString(9, animal.getImage());

            int rowsAffected = preparedStatement.executeUpdate();

            return rowsAffected > 0;

        } catch (SQLException e) {
            System.err.println("Error al agregar el animal: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Animal> findAll(String type, String status) {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals";

        boolean statusIsEmpty = status == null || status.trim().isEmpty();
        boolean typeIsEmpty = type == null || type.trim().isEmpty();

        if (!statusIsEmpty) {
            sql += " WHERE adoption_status = ?";

            if (!typeIsEmpty) {
                sql += " AND animal_type = ?";
            }
        }

        if (!typeIsEmpty && statusIsEmpty) {
            sql += " WHERE animal_type = ?";
        }

        try (Connection connection = conn.getConnectionDb();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            if (!statusIsEmpty) {
                preparedStatement.setString(1, status);
                if (!typeIsEmpty) {
                    preparedStatement.setString(2, type);
                }
            }

            if (!typeIsEmpty && statusIsEmpty) {
                preparedStatement.setString(1, type);
            }

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
                            resultSet.getString(COLUMN_ADMISSION_DATE),
                            resultSet.getString(COLUMN_IMAGE));
                    animals.add(animal);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener animales: " + e.getMessage());
        }

        return animals;
    }

    @Override
    public List<Animal> findByBreed(String breed, String type, String status) {
        List<Animal> animals = new ArrayList<>();

        if (breed == null || breed.trim().isEmpty()) {
            return null;
        }

        breed = breed.trim().toLowerCase();

        boolean statusIsEmpty = status == null || status.trim().isEmpty();
        boolean typeIsEmpty = type == null || type.trim().isEmpty();

        String sql = "SELECT * FROM animals WHERE LOWER(breed) LIKE ?";

        if (!statusIsEmpty) {
            sql += " AND adoption_status = ?";
        }

        if (!typeIsEmpty) {
            sql += " AND animal_type = ?";
        }

        try (Connection connection = conn.getConnectionDb();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            // Usar LIKE con comodines y búsqueda insensible a mayúsculas/minúsculas
            String searchPattern = "%" + breed + "%"; // Usar minúsculas y comodines
            preparedStatement.setString(1, searchPattern);

            if (!statusIsEmpty) {
                preparedStatement.setString(2, status);
            }

            if (!typeIsEmpty && !statusIsEmpty) {
                preparedStatement.setString(3, type);
            } else if (!typeIsEmpty && statusIsEmpty) {
                preparedStatement.setString(2, type);
            }

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
                            resultSet.getString(COLUMN_ADMISSION_DATE),
                            resultSet.getString(COLUMN_IMAGE));
                    animals.add(animal);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar animales por raza: " + e.getMessage());
        }

        return animals;
    }

    @Override
    public Animal findById(int id, String status) {
        Animal animal = null;
        String sql = "SELECT * FROM animals WHERE id = ?";

        boolean statusIsEmpty = status == null || status.trim().isEmpty();

        if (!statusIsEmpty) {
            sql += " AND adoption_status = ?";
        }

        try (Connection connection = conn.getConnectionDb();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);

            if (!statusIsEmpty) {
                preparedStatement.setString(2, status);
            }
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
                            resultSet.getString(COLUMN_ADMISSION_DATE),
                            resultSet.getString(COLUMN_IMAGE));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener animal por ID: " + e.getMessage());
        }

        return animal;
    }

    @Override
    public List<Animal> findByType(String type, String status) {
        List<Animal> animals = new ArrayList<>();
        String sql = "SELECT * FROM animals WHERE animal_type = ?";

        if (status != null && !status.trim().isEmpty()) {
            sql += " AND adoption_status = ?";
        }

        try (Connection connection = conn.getConnectionDb();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, type);

            if (status != null && !status.trim().isEmpty()) {
                preparedStatement.setString(2, status);
            }

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
                            resultSet.getString(COLUMN_ADMISSION_DATE),
                            resultSet.getString(COLUMN_IMAGE));
                    animals.add(animal);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener animales por tipo: " + e.getMessage());
        }

        return animals;
    }

    @Override
    public boolean saveAnimalAdopted(int animalId, int userId, String observations) {
        String updateAnimalSql = "UPDATE animals SET adoption_status = 'ADOPTED' WHERE id = ?";
        String insertAdoptionSql = "INSERT INTO adoptions (animal_id, user_id, adoption_date, observations) VALUES (?, ?, ?, ?)";
        boolean result = false;

        try (Connection connection = conn.getConnectionDb()) {
            // Desactivar el autocommit para realizar ambas operaciones dentro de una
            // transacción
            connection.setAutoCommit(false);

            try (PreparedStatement updateAnimalStatement = connection.prepareStatement(updateAnimalSql);
                    PreparedStatement insertAdoptionStatement = connection.prepareStatement(insertAdoptionSql)) {

                // Actualizar el estado del animal
                updateAnimalStatement.setInt(1, animalId);
                updateAnimalStatement.executeUpdate();

                // Insertar el registro en la tabla de adopciones
                insertAdoptionStatement.setInt(1, animalId);
                insertAdoptionStatement.setInt(2, userId);
                insertAdoptionStatement.setDate(3, Date.valueOf(LocalDate.now())); // Fecha actual
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

    @Override
    public boolean updateAnimal(Animal animal) {
        if (animal.getId() <= 0) {
            throw new IllegalArgumentException("El ID del animal no es válido.");
        }

        StringBuilder sql = new StringBuilder("UPDATE animals SET ");
        List<Object> parameters = new ArrayList<>();

        if (animal.getName() != null && !animal.getName().isEmpty()) {
            sql.append("animal_name = ?, ");
            parameters.add(animal.getName());
        }
        if (animal.getType() != null && !animal.getType().isEmpty()) {
            sql.append("animal_type = ?, ");
            parameters.add(animal.getType());
        }
        if (animal.getAge() > 0) {
            sql.append("age = ?, ");
            parameters.add(animal.getAge());
        }
        if (animal.getBreed() != null && !animal.getBreed().isEmpty()) {
            sql.append("breed = ?, ");
            parameters.add(animal.getBreed());
        }
        if (animal.getSex() != null && !animal.getSex().isEmpty()) {
            sql.append("sex = ?, ");
            parameters.add(animal.getSex());
        }
        if (animal.getWeight() > 0) {
            sql.append("animal_weight = ?, ");
            parameters.add(animal.getWeight());
        }
        if (animal.getDescription() != null && !animal.getDescription().isEmpty()) {
            sql.append("animal_description = ?, ");
            parameters.add(animal.getDescription());
        }
        if (animal.getAdoptionStatus() != null && !animal.getAdoptionStatus().isEmpty()) {
            sql.append("adoption_status = ?, ");
            parameters.add(animal.getAdoptionStatus());
        }
        if (animal.getImage() != null && !animal.getImage().isEmpty()) {
            sql.append("animal_image = ?, ");
            parameters.add(animal.getImage());
        }

        if (parameters.isEmpty()) {
            throw new IllegalArgumentException("No se proporcionaron campos para actualizar.");
        }

        // Eliminar la última coma y espacio, luego agregar WHERE
        sql.setLength(sql.length() - 2);
        sql.append(" WHERE id = ?");
        parameters.add(animal.getId());

        try (Connection connection = conn.getConnectionDb();
                PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {

            // Asignar parámetros a la consulta
            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar el animal: " + e.getMessage());
            return false;
        }
    }

}
