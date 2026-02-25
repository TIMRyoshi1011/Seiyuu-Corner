package Model;

import javax.swing.*;

public class Staff {
    private int staffID;
    private String firstName;
    private String lastName;
    private String position;

    public Staff(int staffID, String firstName, String lastName, String position) {
        this.staffID = staffID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
    }

    public int getStaffID() {
        return staffID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setStaffId(int staffID) {
        this.staffID = staffID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void displayStaffInfo() {
        JOptionPane.showMessageDialog(null, "Staff ID: " + staffID +
        "\nName: " + firstName + " " + lastName + "\nPosition: " + position, "Staff Details", JOptionPane.INFORMATION_MESSAGE);
    }
}
