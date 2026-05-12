package Entity;

import Behaviour.Displayable;
import Behaviour.Editable;
import java.time.LocalDate;
import java.util.Objects;


public class Person implements Displayable, Editable {
    private String id;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private String phoneNumber;
    private String email;
    private String address;


    public Person(String id, String firstName, LocalDate dateOfBirth, String lastName, String gender, String phoneNumber, String email, String address) {
        this.id = id;
        this.firstName = firstName;
        this.dateOfBirth = dateOfBirth;
        this.lastName = lastName;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.address = address;
    }


    public Person() {
    }



    @Override
    public void displayInfo() {
        System.out.println("\n--- Full Profile ---");
        System.out.println("ID: " + id);
        System.out.println("Full Name: " + firstName + " " + lastName);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Gender: " + gender);
        System.out.println("Phone Number: " + phoneNumber);
        System.out.println("Email: " + email);
        System.out.println("Address: " + address);
    }

    @Override
    public void displaySummary() {
        System.out.println("Summary: [" + id + "] " + firstName + " " + lastName + " (" + gender + ")");
    }



    @Override
    public void edit(Object updatedData) {

        if (updatedData instanceof Person) {
            Person p = (Person) updatedData;
            this.firstName = p.firstName;
            this.lastName = p.lastName;
            this.phoneNumber = p.phoneNumber;
            this.email = p.email;
            this.address = p.address;
            System.out.println("Person information updated successfully.");
        }
    }

    @Override
    public boolean validate() {
        // التحقق من صحة البيانات الأساسية (مثل وجود بريد إلكتروني صحيح)
        if (email == null || !email.contains("@")) {
            System.out.println("Validation Failed: Invalid Email format.");
            return false;
        }
        if (phoneNumber == null || phoneNumber.length() < 8) {
            System.out.println("Validation Failed: Phone number too short.");
            return false;
        }
        return true;
    }



    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }



    @Override
    public String toString() {
        return "Person{" + "id='" + id + "', firstName='" + firstName + "', lastName='" + lastName + "'}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(id, person.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}