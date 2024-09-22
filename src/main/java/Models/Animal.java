package Models;

public class Animal {

    private final int id;
    private String name;
    private String type;
    private int age;
    private String breed;
    private String sex;
    private float weight;
    private String description;
    private String adoptionStatus;
    private final String dateOfAdmission;

    public Animal(int id, String name, String type, int age, String breed, String sex, float weight, String description, String adoptionStatus, String dateOfAdmission) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.age = age;
        this.breed = breed;
        this.sex = sex;
        this.weight = weight;
        this.description = description;
        this.adoptionStatus = adoptionStatus;
        this.dateOfAdmission = dateOfAdmission;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public String getSex() {
        return sex;
    }

    public float getWeight() {
        return weight;
    }

    public String getDescription() {
        return description;
    }

    public String getAdoptionStatus() {
        return adoptionStatus;
    }

    public String getDateOfAdmission() {
        return dateOfAdmission;
    }

    //Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAdoptionStatus(String adoptionStatus) {
        this.adoptionStatus = adoptionStatus;
    }
}
