package model;

public class User {
    private int userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String phoneNumber;
    private String designation;
    private String password;
    private String status;
    public static User instance = new User();

    public User() {
    }


    public User(int userId, String userName, String firstName, String lastName, String email, String address, String phoneNumber, String designation, String status) {
        this.userId = userId;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.designation = designation;
        this.status = status;
    }

    public User(String userName, String firstName, String lastName, String email, String address, String phoneNumber, String designation, String password, String status) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.designation = designation;
        this.password = password;
        this.status = status;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public static User getInstance() {
        return instance;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static void setInstance(User instance) {
        User.instance = instance;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public String toString() {
        return userName + " : " + email + " : " + status;
    }
}
