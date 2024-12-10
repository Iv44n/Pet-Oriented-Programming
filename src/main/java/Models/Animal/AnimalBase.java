package Models.Animal;

public class AnimalBase {

  private String name;
  private String type;
  private int age;
  private String breed;
  private String sex;
  private float weight;
  private String description;
  private String adoptionStatus;
  private final String dateOfAdmission;
  private String image;

  public AnimalBase(String name, String type, int age, String breed, String sex, float weight, String description,
      String adoptionStatus, String dateOfAdmission, String image) {
    this.name = name;
    this.type = type;
    this.age = age;
    this.breed = breed;
    this.sex = sex;
    this.weight = weight;
    this.description = description;
    this.adoptionStatus = adoptionStatus;
    this.dateOfAdmission = dateOfAdmission;
    this.image = image;
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

  public String getImage() {
    return image;
  }

  // Setters
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

  public void setImage(String image) {
    this.image = image;
  }
}
