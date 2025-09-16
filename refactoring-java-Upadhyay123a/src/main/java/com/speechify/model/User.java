package com.speechify.model;

import java.time.LocalDate;

public class User {
    private String id;
    private Client client;
    private LocalDate dateOfBirth;
    private String email;
    private String firstname;
    private String surname;
    private boolean hasCreditLimit;
    private Integer creditLimit;

    // Constructors
    public User() {}

    // Getters & Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getFirstname() { return firstname; }
    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getSurname() { return surname; }
    public void setSurname(String surname) { this.surname = surname; }

    public boolean isHasCreditLimit() { return hasCreditLimit; }
    public void setHasCreditLimit(boolean hasCreditLimit) { this.hasCreditLimit = hasCreditLimit; }

    public Integer getCreditLimit() { return creditLimit; }
    public void setCreditLimit(Integer creditLimit) { this.creditLimit = creditLimit; }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", firstname='" + firstname + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", client=" + client +
                ", hasCreditLimit=" + hasCreditLimit +
                (creditLimit != null ? ", creditLimit=" + creditLimit : "") +
                '}';
    }
}
