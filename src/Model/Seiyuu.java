package Model;

import java.time.*;
import javax.swing.*;

public class Seiyuu {
    private int seiyuuID;
    private LocalDate birthDate;
    private String firstName;
    private String lastName;
    private int age;
    private char sex;
    private String agency;

    public Seiyuu(int seiyuuID, LocalDate birthDate, String firstName, String lastName, int age, char sex, String agency) {
        this.seiyuuID = seiyuuID;
        this.birthDate = birthDate;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.sex = sex;
        this.agency = agency;
    }

    public int getSeiyuuID() {
        return seiyuuID;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public char getSex() {
        return sex;
    }

    public String getAgency() {
        return agency;
    }

    public void setSeiyuuID(int seiyuuID) {
        this.seiyuuID = seiyuuID;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public void setAgency(String agency) {
        this.agency = agency;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void displaySeiyuuInfo() {
        JOptionPane.showMessageDialog(null, "Seiyuu ID: " + seiyuuID + 
        "\nName: " + firstName + " " + lastName + "\nBirth Date: " + birthDate + 
        "\nAge: " + age + "\nSex: " + sex + "\nAgency: " + agency, "Seiyuu Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
