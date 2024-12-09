package Controllers;

import Models.Animal;
import java.util.List;

import Dao.AnimalDao;

public class AnimalController {

    private final AnimalDao animalService;

    public AnimalController() {
        this.animalService = new AnimalDao();
    }

    public List<Animal> getAllAnimals() {
        return animalService.findAll();
    }

    public Animal getAnimalById(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor a 0");
        }

        return animalService.findById(id);
    }

    public List<Animal> getAnimalsByBreed(String breed) {
        if (breed == null || breed.trim().isEmpty()) {
            throw new IllegalArgumentException("La raza no puede estar vacia");
        }

        return animalService.findByBreed(breed);
    }

    public boolean saveAnimalAdopted(int animalId, int userId, String observations) {
        if (animalId <= 0 || userId <= 0) {
            throw new IllegalArgumentException("Los parámetros deben ser mayores a 0 y la observación no puede estar vacia");
        }

        return animalService.saveAnimalAdopted(animalId, userId, observations);
    }
}
