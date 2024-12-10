package Dao;

import java.util.List;

import Models.Animal.Animal;
import Models.Animal.AnimalBase;

public interface IAnimalDAO {

  boolean addAnimal(AnimalBase animal);

  List<Animal> findAll(String type, String status);

  List<Animal> findByBreed(String breed, String type, String status);

  Animal findById(int id, String status);

  List<Animal> findByType(String type, String status);

  boolean saveAnimalAdopted(int animalId, int userId, String observations);

  boolean updateAnimal(Animal animal);
}
