package com.parabank.models;

/**
 * OOP Concept: Encapsulation
 * Represents a Banking Customer entity with private fields,
 * accessible exclusively via public getters and setters.
 */
public class Customer {

    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private String phone;
    private String ssn;
    private String username;
    private String password;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String address, String city,
                    String state, String zipCode, String phone, String ssn,
                    String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.phone = phone;
        this.ssn = ssn;
        this.username = username;
        this.password = password;
    }

    // Getters and Setters
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getZipCode() { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getSsn() { return ssn; }
    public void setSsn(String ssn) { this.ssn = ssn; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + firstName + " " + lastName + '\'' +
                ", username='" + username + '\'' +
                ", ssn='***-**-" + (ssn != null && ssn.length() >= 4 ? ssn.substring(ssn.length() - 4) : "XXXX") + '\'' +
                '}';
    }
}
