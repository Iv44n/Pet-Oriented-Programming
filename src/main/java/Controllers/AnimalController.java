package Controllers;

import java.io.ObjectInputFilter.Status;
import java.util.List;

import Dao.AnimalDao;
import Models.Animal.Animal;
import Models.Animal.AnimalBase;

public class AnimalController {

    private final AnimalDao animalService;

    public AnimalController() {
        this.animalService = new AnimalDao();
    }

    public boolean addAnimal(AnimalBase animal) {
        return animalService.addAnimal(animal);
    }

    public List<Animal> getAllAnimals(String type, String status) {
        return animalService.findAll(type, status);
    }

    public Animal getAnimalById(int id, String status) {
        if (id <= 0) {
            throw new IllegalArgumentException("El id debe ser mayor a 0");
        }

        return animalService.findById(id, status);
    }

    public List<Animal> getAnimalsByBreed(String breed, String type, String status) {
        if (breed == null || breed.trim().isEmpty()) {
            throw new IllegalArgumentException("La raza no puede estar vacia");
        }

        return animalService.findByBreed(breed, type, status);
    }

    public List<Animal> getAnimalsByType(String type, String status) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("El tipo no puede estar vacio");
        }

        return animalService.findByType(type, status);
    }

    public boolean saveAnimalAdopted(int animalId, int userId, String observations) {
        if (animalId <= 0 || userId <= 0) {
            throw new IllegalArgumentException(
                    "Los parámetros deben ser mayores a 0 y la observación no puede estar vacia");
        }

        return animalService.saveAnimalAdopted(animalId, userId, observations);
    }

    public boolean updateAnimal(Animal animal) {
        return animalService.updateAnimal(animal);
    }
}
