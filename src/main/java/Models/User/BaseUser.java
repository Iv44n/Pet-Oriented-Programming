package Models.User;

public class BaseUser {

    private String firts_name;
    private String last_name;
    private String username;
    private String email;
    private String phone;
    private String address;

    public BaseUser(String first_name, String last_name, String username, String email, String phone, String address) {
        this.firts_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
    }

    public String getFirts_name() {
        return firts_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
