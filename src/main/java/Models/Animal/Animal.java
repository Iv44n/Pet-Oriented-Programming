package Models.Animal;

public class Animal extends AnimalBase {

    private final int id;

    public Animal(int id, String name, String type, int age, String breed, String sex, float weight, String description,
            String adoptionStatus, String dateOfAdmission, String image) {
        super(name, type, age, breed, sex, weight, description, adoptionStatus, dateOfAdmission, image);
        this.id = id;
    }

    // Getters
    public int getId() {
        return id;
    }

}
