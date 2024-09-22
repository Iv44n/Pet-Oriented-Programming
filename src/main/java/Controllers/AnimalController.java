package Controllers;

import Services.AnimalService;
import Models.Animal;
import java.util.List;

public class AnimalController {

    private final AnimalService animalService;

    public AnimalController() {
        this.animalService = new AnimalService();
    }

    public List<Animal> getAllAnimals() {
        return animalService.findAll();
    }

    public Animal getAnimalById (int id){
        return animalService.findById(id);
    }
}
