package Controller;

import Model.*;
import View.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class Controller implements ActionListener {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;

        view.setActionListener(this);
    }

    // ==================================== Vanilla OOP ==================================== //

    private void viewAssignments(){
        view.addTable(model.viewAssignments());
    }

    private void addAssignment() {
        JFrame addAssignmentFrame = new JFrame("Add Assignment");
        addAssignmentFrame.setSize(230, 170);
        addAssignmentFrame.setLayout(new FlowLayout());
        addAssignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addAssignmentFrame.setResizable(false);

        String[] staffList = new String[model.getStaffList().size()];
        for (int i = 0; i < model.getStaffList().size(); i++) 
            staffList[i] = model.getStaffList().get(i).getFirstName();

        String[] seiyuuList = new String[model.getSeiyuuList().size()];
        for (int i = 0; i < model.getSeiyuuList().size(); i++) {
            Seiyuu s = model.getSeiyuuList().get(i);   
            seiyuuList[i] = s.getFirstName() + " " + s.getLastName();
        }
        JLabel label1 = new JLabel("Select Staff:");
        JComboBox<String> staffL = new JComboBox<>(staffList);
        JLabel label2 = new JLabel("Select Seiyuu:");
        JComboBox<String> seiyuuL = new JComboBox<>(seiyuuList);
        JButton assignButton = new JButton("Assign");

        assignButton.addActionListener(e -> {
            addAssignmentFrame.dispose();

            int staffIndex = staffL.getSelectedIndex();
            int seiyuuIndex = seiyuuL.getSelectedIndex();

            model.addAssignment(staffIndex + 1, seiyuuIndex + 1);

        //             Staff selectedStaff = getStaffList().get(staffId);
        // Seiyuu selectedSeiyuu = getSeiyuuList().get(seiyuuId);
            //selectedStaff.assignSeiyuu(selectedSeiyuu); 
            //selectedSeiyuu.assignStaff(selectedStaff); 
            //JOptionPane.showMessageDialog(null, selectedSeiyuu.getFirstName() + " " + selectedSeiyuu.getLastName() + " assigned to " + selectedStaff.getFirstName() + " " + selectedStaff.getLastName());
        });

        addAssignmentFrame.add(label1);
        addAssignmentFrame.add(staffL);
        addAssignmentFrame.add(label2);
        addAssignmentFrame.add(seiyuuL);
        addAssignmentFrame.add(assignButton);

        addAssignmentFrame.setLocationRelativeTo(null);
        addAssignmentFrame.setVisible(true);
    }

    private void removeAssignment() {
        JFrame removeAssignmentFrame = new JFrame("Remove Assignment");
        removeAssignmentFrame.setSize(230, 170);
        removeAssignmentFrame.setLayout(new FlowLayout());
        removeAssignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        removeAssignmentFrame.setResizable(false);

        String[] staffList = new String[model.getStaffList().size()];
        for (int i = 0; i < model.getStaffList().size(); i++) 
            staffList[i] = model.getStaffList().get(i).getFirstName();

        String[] seiyuuList = new String[model.getSeiyuuList().size()];
        for (int i = 0; i < model.getSeiyuuList().size(); i++) {
            Seiyuu s = model.getSeiyuuList().get(i);   
            seiyuuList[i] = s.getFirstName() + " " + s.getLastName();
        }
        JLabel label1 = new JLabel("Select Staff:");
        JComboBox<String> staffL = new JComboBox<>(staffList);
        JLabel label2 = new JLabel("Select Seiyuu:");
        JComboBox<String> seiyuuL = new JComboBox<>(seiyuuList);
        JButton removeButton = new JButton("Remove");

        removeButton.addActionListener(e -> {
            removeAssignmentFrame.dispose();

            int staffIndex = staffL.getSelectedIndex();
            int seiyuuIndex = seiyuuL.getSelectedIndex();

            model.removeAssignment(staffIndex + 1, seiyuuIndex + 1);

        //             Staff selectedStaff = getStaffList().get(staffId);
        // Seiyuu selectedSeiyuu = getSeiyuuList().get(seiyuuId);
            //selectedStaff.assignSeiyuu(selectedSeiyuu); 
            //selectedSeiyuu.assignStaff(selectedStaff); 
            //JOptionPane.showMessageDialog(null, selectedSeiyuu.getFirstName() + " " + selectedSeiyuu.getLastName() + " assigned to " + selectedStaff.getFirstName() + " " + selectedStaff.getLastName());
        });

        removeAssignmentFrame.add(label1);
        removeAssignmentFrame.add(staffL);
        removeAssignmentFrame.add(label2);
        removeAssignmentFrame.add(seiyuuL);
        removeAssignmentFrame.add(removeButton);

        removeAssignmentFrame.setLocationRelativeTo(null);
        removeAssignmentFrame.setVisible(true);
    }

    private void addSeiyuu() {
        JFrame addSeiyuuFrame = new JFrame("Add Seiyuu");
        addSeiyuuFrame.setSize(215, 350);
        addSeiyuuFrame.setLayout(new FlowLayout());
        addSeiyuuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addSeiyuuFrame.setResizable(false);

        JLabel label1 = new JLabel("Enter birth date (YYYY-MM-DD):");
        JTextField bDate = new JTextField(20);
        JLabel label2 = new JLabel("Enter First Name:");
        JTextField fName = new JTextField(20);
        JLabel label3 = new JLabel("Enter Last Name:");
        JTextField lName = new JTextField(20);
        JLabel label4 = new JLabel("Enter Age");
        JTextField age = new JTextField(20);
        JLabel label5 = new JLabel("Select Sex:");
        Character[] sex = {'M', 'F'};
        JComboBox<Character> sexCh = new JComboBox<>(sex);
        JLabel label6 = new JLabel("Enter Agency:");
        JTextField agency = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {

            addSeiyuuFrame.dispose();

            String getBDate = bDate.getText();
            String getFName = fName.getText();
            String getLName = lName.getText();
            int getAge = Integer.parseInt(age.getText());
            char getSex = (Character)sexCh.getSelectedItem();
            String getAgency = agency.getText();

            model.addSeiyuuM(getBDate, getFName, getLName, getAge, getSex, getAgency);
        });
        
        addSeiyuuFrame.add(label1);
        addSeiyuuFrame.add(bDate);
        addSeiyuuFrame.add(label2);
        addSeiyuuFrame.add(fName);
        addSeiyuuFrame.add(label3);
        addSeiyuuFrame.add(lName);
        addSeiyuuFrame.add(label4);
        addSeiyuuFrame.add(age);
        addSeiyuuFrame.add(label5);
        addSeiyuuFrame.add(sexCh);
        addSeiyuuFrame.add(label6);
        addSeiyuuFrame.add(agency);
        addSeiyuuFrame.add(submitButton);

        addSeiyuuFrame.setLocationRelativeTo(null);
        addSeiyuuFrame.setVisible(true);
    }

    private void editSeiyuu() {
        JFrame editSeiyuuFrame = new JFrame("Edit Seiyuu");
        editSeiyuuFrame.setSize(235, 375);
        editSeiyuuFrame.setLayout(new FlowLayout());
        editSeiyuuFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editSeiyuuFrame.setResizable(false);

        JLabel label0 = new JLabel("Select Seiyuu:");
        JComboBox<Seiyuu> comboBox =
            new JComboBox<>(model.getSeiyuuArray());
        JButton selectButton = new JButton("Select");

         selectButton.addActionListener(e -> {
            Seiyuu selectedSeiyuu = (Seiyuu) comboBox.getSelectedItem();
            if (selectedSeiyuu != null) {

                JLabel label0pt5 = new JLabel("Leave blank if no update" );
                JLabel label0pt75 = new JLabel("(except for birthdate)" );
                JLabel label1 = new JLabel("Enter New birth date (YYYY-MM-DD):");
                JTextField bDate = new JTextField(20);
                JLabel label2 = new JLabel("Enter New First Name:");
                JTextField fName = new JTextField(20);
                JLabel label3 = new JLabel("Enter New Last Name:");
                JTextField lName = new JTextField(20);
                JLabel label4 = new JLabel("Enter New Age");
                JTextField age = new JTextField(20);
                JLabel label5 = new JLabel("Select Sex:");
                Character[] sex = {'M', 'F'};
                JComboBox<Character> sexCh = new JComboBox<>(sex);
                JLabel label6 = new JLabel("Enter New Agency:");
                JTextField agency = new JTextField(20);
                JButton submitButton = new JButton("Submit");

                editSeiyuuFrame.getContentPane().removeAll();
                editSeiyuuFrame.add(label0pt5);
                editSeiyuuFrame.add(label0pt75);
                editSeiyuuFrame.add(label1);
                editSeiyuuFrame.add(bDate);
                editSeiyuuFrame.add(label2);
                editSeiyuuFrame.add(fName);
                editSeiyuuFrame.add(label3);
                editSeiyuuFrame.add(lName);
                editSeiyuuFrame.add(label4);
                editSeiyuuFrame.add(age);
                editSeiyuuFrame.add(label5);
                editSeiyuuFrame.add(sexCh);
                editSeiyuuFrame.add(label6);
                editSeiyuuFrame.add(agency);
                editSeiyuuFrame.add(submitButton);
                editSeiyuuFrame.revalidate();
                editSeiyuuFrame.repaint();

                submitButton.addActionListener(f -> {
                    editSeiyuuFrame.dispose();

                    int seiyuuID = selectedSeiyuu.getSeiyuuID();
                    String getBDate = bDate.getText();
                    String getFName = fName.getText();
                    String getLName = lName.getText();
                    String getAge = age.getText();
                    String getSexStr = (String) sexCh.getSelectedItem().toString();
                    String getAgency = agency.getText();

                    model.editSeiyuuM(seiyuuID, getBDate, getFName, getLName, getAge, getSexStr, getAgency);
                });
            }
        });
        
        editSeiyuuFrame.add(label0);
        editSeiyuuFrame.add(comboBox);
        editSeiyuuFrame.add(selectButton);

        editSeiyuuFrame.setLocationRelativeTo(null);
        editSeiyuuFrame.setVisible(true);
    }

    private void addStaff() {
        JFrame addStaffFrame = new JFrame("Add Staff");
        addStaffFrame.setSize(215, 220);
        addStaffFrame.setLayout(new FlowLayout());
        addStaffFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addStaffFrame.setResizable(false);

        JLabel label1 = new JLabel("Enter First Name:");
        JTextField fName = new JTextField(20);
        JLabel label2 = new JLabel("Enter Last Name:");
        JTextField lName = new JTextField(20);
        JLabel label3 = new JLabel("Enter Position");
        JTextField pos = new JTextField(20);
        JButton submitButton = new JButton("Submit");

        submitButton.addActionListener(e -> {

            addStaffFrame.dispose();

            String getFName = fName.getText();
            String getLName = lName.getText();
            String getPos = pos.getText();

            model.addStaffM(getFName, getLName, getPos);
        });
        
        addStaffFrame.add(label1);
        addStaffFrame.add(fName);
        addStaffFrame.add(label2);
        addStaffFrame.add(lName);
        addStaffFrame.add(label3);
        addStaffFrame.add(pos);
        addStaffFrame.add(submitButton);

        addStaffFrame.setLocationRelativeTo(null);
        addStaffFrame.setVisible(true);
    }

    private void editStaff() {
        JFrame editStaffFrame = new JFrame("Edit Staff");
        editStaffFrame.setSize(235, 235);
        editStaffFrame.setLayout(new FlowLayout());
        editStaffFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editStaffFrame.setResizable(false);

        JLabel label0 = new JLabel("Select Staff:");
        JComboBox<Staff> staffL =
            new JComboBox<>(model.getStaffArray());
        JButton selectButton = new JButton("Select");

        selectButton.addActionListener(e -> {
            Staff selectedStaff = (Staff) staffL.getSelectedItem();
            if (selectedStaff != null) {

                JLabel label0pt5 = new JLabel("Leave blank if no update" );
                JLabel label1 = new JLabel("Enter New First Name:");
                JTextField fName = new JTextField(20);
                JLabel label2 = new JLabel("Enter New Last Name:");
                JTextField lName = new JTextField(20);
                JLabel label3 = new JLabel("Enter New Position");
                JTextField pos = new JTextField(20);
                JButton submitButton = new JButton("Submit");

                editStaffFrame.getContentPane().removeAll();
                editStaffFrame.add(label0pt5);
                editStaffFrame.add(label1);
                editStaffFrame.add(fName);
                editStaffFrame.add(label2);
                editStaffFrame.add(lName);
                editStaffFrame.add(label3);
                editStaffFrame.add(pos);
                editStaffFrame.add(submitButton);
                editStaffFrame.revalidate();
                editStaffFrame.repaint();

                submitButton.addActionListener(f -> {
                    editStaffFrame.dispose();

                    int staffID = selectedStaff.getStaffID();
                    String getFName = fName.getText();
                    String getLName = lName.getText();
                    String getPos = pos.getText();

                    model.editStaffM(staffID, getFName, getLName, getPos);
                });
            }
        });
        
        editStaffFrame.add(label0);
        editStaffFrame.add(staffL);
        editStaffFrame.add(selectButton);

        editStaffFrame.setLocationRelativeTo(null);
        editStaffFrame.setVisible(true);
    }

    // ==================================== SQL Assignments ==================================== //

    private void viewAssignmentsinSQL() {
        String query = "SELECT CONCAT(s.first_name) AS ASSIGNED, su.Birthday, CONCAT(su.first_name, ' ', su.last_name) AS NAME, su.Age, su.Sex, su.Agency, su.Music_Label " + // , su.Japanese_Name, su.Seiyuu_Pics, su.Twitter, su.Instagram, su.Notes
                        "FROM staff_assignment sa JOIN staff s ON sa.staff_id = s.staff_id " + 
                        "JOIN seiyuu su ON sa.seiyuu_id = su.seiyuu_id " +
                        "ORDER BY su.seiyuu_id; ";
        view.displayTable(query);
    }

    private void filterAssignmentsinSQL() {
        JFrame filterFrame = new JFrame("Filter Assignment");
        filterFrame.setSize(250, 120);
        filterFrame.setLayout(new FlowLayout());
        filterFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        filterFrame.setResizable(false);

        JLabel label1 = new JLabel("Select Staff:");
        JComboBox<String> staffL = new JComboBox<>(model.getStaffNamesArray());
        JButton selectButton = new JButton("Select");

        selectButton.addActionListener(e -> {
            filterFrame.dispose();

            int i = staffL.getSelectedIndex();

            view.displayTableifUnknown(model.filteredAssignment(), i + 1);
        });

        filterFrame.add(label1);
        filterFrame.add(staffL);
        filterFrame.add(selectButton);

        filterFrame.setLocationRelativeTo(null);
        filterFrame.setVisible(true);
    }

    private void addAssignmentinSQL() {
        JFrame addAssignmentFrame = new JFrame("Add Assignment");
        addAssignmentFrame.setSize(450, 120);
        addAssignmentFrame.setLayout(new FlowLayout());
        addAssignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addAssignmentFrame.setResizable(false);

        String[] seiyuu = model.getSeiyuuNamesArray();

        JLabel label1 = new JLabel("Assign Assignment of ");
        JComboBox<String> staffL = new JComboBox<>(model.getStaffNamesArray());
        JLabel label2 = new JLabel("to ");
        JComboBox<String> seiyuuL = new JComboBox<>(seiyuu);
        JButton addButton = new JButton("Assign");

        addButton.addActionListener(e -> {

            addAssignmentFrame.dispose();

            int staffIndex = staffL.getSelectedIndex();
            int seiyuuIndex = seiyuuL.getSelectedIndex();
            model.addAssignmentInDB(staffIndex + 1, seiyuuIndex + 1);
            
        });

        addAssignmentFrame.add(label1);
        addAssignmentFrame.add(staffL);
        addAssignmentFrame.add(label2);
        addAssignmentFrame.add(seiyuuL);
        addAssignmentFrame.add(addButton);

        addAssignmentFrame.setLocationRelativeTo(null);
        addAssignmentFrame.setVisible(true);
    }

    private void updateAssignmentinSQL() {
        JFrame updateAssignmentFrame = new JFrame("Update Assignment");
        updateAssignmentFrame.setSize(450, 120);
        updateAssignmentFrame.setLayout(new FlowLayout());
        updateAssignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateAssignmentFrame.setResizable(false);

        String[] seiyuu = model.getSeiyuuNamesArray();

        JLabel label1 = new JLabel("Assign Assignment of ");
        JComboBox<String> staffL = new JComboBox<>(model.getStaffNamesArray());
        JLabel label2 = new JLabel("to ");
        JComboBox<String> seiyuuL = new JComboBox<>(seiyuu);
        JButton updateButton = new JButton("Update");

        updateButton.addActionListener(e -> {

            updateAssignmentFrame.dispose();

            int staffIndex = staffL.getSelectedIndex();
            int seiyuuIndex = seiyuuL.getSelectedIndex();
            model.updateAssignmentInDB(staffIndex + 1, seiyuuIndex + 1);
            
        });

        updateAssignmentFrame.add(label1);
        updateAssignmentFrame.add(staffL);
        updateAssignmentFrame.add(label2);
        updateAssignmentFrame.add(seiyuuL);
        updateAssignmentFrame.add(updateButton);

        updateAssignmentFrame.setLocationRelativeTo(null);
        updateAssignmentFrame.setVisible(true);
    }

    private void deleteAssignmentinSQL() {
        JFrame deleteAssignmentFrame = new JFrame("Delete Assignment");
        deleteAssignmentFrame.setSize(450, 120);
        deleteAssignmentFrame.setLayout(new FlowLayout());
        deleteAssignmentFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        deleteAssignmentFrame.setResizable(false);

        String[] seiyuu = model.getSeiyuuNamesArray();

        JLabel label1 = new JLabel("Delete Assignment of ");
        JComboBox<String> staffL = new JComboBox<>(model.getStaffNamesArray());
        JLabel label2 = new JLabel("to ");
        JComboBox<String> seiyuuL = new JComboBox<>(seiyuu);
        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(e -> {

            deleteAssignmentFrame.dispose();

            int staffIndex = staffL.getSelectedIndex();
            int seiyuuIndex = seiyuuL.getSelectedIndex();
            model.deleteAssignmentInDB(staffIndex + 1, seiyuuIndex + 1);
            
        });

        deleteAssignmentFrame.add(label1);
        deleteAssignmentFrame.add(staffL);
        deleteAssignmentFrame.add(label2);
        deleteAssignmentFrame.add(seiyuuL);
        deleteAssignmentFrame.add(deleteButton);

        deleteAssignmentFrame.setLocationRelativeTo(null);
        deleteAssignmentFrame.setVisible(true);
    }

    private void viewSeasonal() {
        String query1 = "SELECT * FROM seasonal_calendar";
        String query2 = "SELECT * FROM seasonal_calendar_2";
        view.addSeasonalTable(query1, query2);
        model.viewSeasonalAssignments();
    }

    private void addSeasonal() {
        JFrame addSeasonalFrame = new JFrame("Add Seasonal");
        addSeasonalFrame.setSize(420, 110);
        addSeasonalFrame.setLayout(new FlowLayout());
        addSeasonalFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addSeasonalFrame.setResizable(false);

        JLabel label1 = new JLabel("Choose: ");
        JButton calendarButton = new JButton("Make Calendar");
        JButton chooseMonth = new JButton("Choose Month");
        JButton assignButton = new JButton("Assign Seasonal");

        chooseMonth.addActionListener(e -> {
            addSeasonalFrame.dispose();

            JFrame chooseMonthFrame = new JFrame("Choose Month");
            chooseMonthFrame.setSize(250, 110);
            chooseMonthFrame.setLayout(new FlowLayout());
            chooseMonthFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            chooseMonthFrame.setResizable(false);

            JLabel label12 = new JLabel("Choose Month: ");
            String[] months = {"February", "March", "May", "June", "August", "September", "November", "December"};
            JComboBox<String> month = new JComboBox<>(months);
            JButton place1 = new JButton("1st Month");
            JButton place2 = new JButton("2nd Month");

            place1.addActionListener(f -> {
                chooseMonthFrame.dispose();

                String place = (String) month.getSelectedItem();
                view.setMonth1(place);
            });

            place2.addActionListener(f -> {
                chooseMonthFrame.dispose();

                String place = (String) month.getSelectedItem();
                view.setMonth2(place);
            });

            chooseMonthFrame.add(label12);
            chooseMonthFrame.add(month);
            chooseMonthFrame.add(place1);
            chooseMonthFrame.add(place2);

            chooseMonthFrame.setLocationRelativeTo(null);
            chooseMonthFrame.setVisible(true);
        });

        calendarButton.addActionListener(e -> {

            addSeasonalFrame.dispose();

            JFrame makeCalendarFrame = new JFrame("Make Calendar");
            makeCalendarFrame.setSize(310, 300);
            makeCalendarFrame.setLayout(new FlowLayout());
            makeCalendarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            makeCalendarFrame.setResizable(false);

            JLabel label2 = new JLabel("Format: Day - Seiyuu Name");
            JLabel label2pt5 = new JLabel("Assign Seiyuu: leave BLANK if day is unavailable");

            JLabel label3 = new JLabel("Sunday: ");
            JTextField seiyuu1 = new JTextField(20);
            JLabel label4 = new JLabel("Monday: ");
            JTextField seiyuu2 = new JTextField(20);
            JLabel label5 = new JLabel("Tuesday: ");
            JTextField seiyuu3 = new JTextField(20);
            JLabel label6 = new JLabel("Wednesday: ");
            JTextField seiyuu4 = new JTextField(20);
            JLabel label7 = new JLabel("Thursday: ");
            JTextField seiyuu5 = new JTextField(20);
            JLabel label8 = new JLabel("Friday: ");
            JTextField seiyuu6 = new JTextField(20);
            JLabel label9 = new JLabel("Saturday: ");
            JTextField seiyuu7 = new JTextField(20);

            JButton c1Button = new JButton("Calendar 1");
            JButton c2Button = new JButton("Calendar 2");

            c1Button.addActionListener(f -> {

                makeCalendarFrame.dispose();

                String s1 = seiyuu1.getText();
                String s2 = seiyuu2.getText();
                String s3 = seiyuu3.getText();
                String s4 = seiyuu4.getText();
                String s5 = seiyuu5.getText();
                String s6 = seiyuu6.getText();
                String s7 = seiyuu7.getText();

                if(s1.isEmpty())
                    s1 = null;
                else if(s2.isEmpty())
                    s2 = null;
                else if(s3.isEmpty())
                    s3 = null;
                else if(s4.isEmpty())
                    s4 = null;
                else if(s5.isEmpty())
                    s5 = null;
                else if(s6.isEmpty())
                    s6 = null;
                else if(s7.isEmpty())
                    s7 = null;

                model.makeSeasonalCalendar1(s1, s2, s3, s4, s5, s6, s7);
                
            });

            c2Button.addActionListener(f -> {

                makeCalendarFrame.dispose();

                String s1 = seiyuu1.getText();
                String s2 = seiyuu2.getText();
                String s3 = seiyuu3.getText();
                String s4 = seiyuu4.getText();
                String s5 = seiyuu5.getText();
                String s6 = seiyuu6.getText();
                String s7 = seiyuu7.getText();

                if(s1.isEmpty())
                    s1 = null;
                else if(s2.isEmpty())
                    s2 = null;
                else if(s3.isEmpty())
                    s3 = null;
                else if(s4.isEmpty())
                    s4 = null;
                else if(s5.isEmpty())
                    s5 = null;
                else if(s6.isEmpty())
                    s6 = null;
                else if(s7.isEmpty())
                    s7 = null;

                model.makeSeasonalCalendar2(s1, s2, s3, s4, s5, s6, s7);
                
            });

            makeCalendarFrame.add(label2);
            makeCalendarFrame.add(label2pt5);
            makeCalendarFrame.add(label3);
            makeCalendarFrame.add(seiyuu1);
            makeCalendarFrame.add(label4);
            makeCalendarFrame.add(seiyuu2);
            makeCalendarFrame.add(label5);
            makeCalendarFrame.add(seiyuu3);
            makeCalendarFrame.add(label6);
            makeCalendarFrame.add(seiyuu4);
            makeCalendarFrame.add(label7);
            makeCalendarFrame.add(seiyuu5);
            makeCalendarFrame.add(label8);
            makeCalendarFrame.add(seiyuu6);
            makeCalendarFrame.add(label9);
            makeCalendarFrame.add(seiyuu7);
            makeCalendarFrame.add(c1Button);
            makeCalendarFrame.add(c2Button);

            makeCalendarFrame.setLocationRelativeTo(null);
            makeCalendarFrame.setVisible(true);
            
        });

        assignButton.addActionListener(e -> {

            addSeasonalFrame.dispose();

            JFrame addSeasonalAFrame = new JFrame("Add Assignment");
            addSeasonalAFrame.setSize(320, 110);
            addSeasonalAFrame.setLayout(new FlowLayout());
            addSeasonalAFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            addSeasonalAFrame.setResizable(false);

            JLabel label10 = new JLabel("Assign");
            JComboBox<String> staff = new JComboBox<>(model.getStaffNamesArray());
            JLabel label11 = new JLabel("on");
            String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
            JComboBox<String> daysOfWeek = new JComboBox<>(days);
            JButton assignB = new JButton("Assign");

            assignB.addActionListener(f -> {

                addSeasonalAFrame.dispose();

                int staffId = staff.getSelectedIndex();
                String day = (String) daysOfWeek.getSelectedItem();

                model.addSeasonalAssignment(staffId + 1, day);
            });

            addSeasonalAFrame.add(label10);
            addSeasonalAFrame.add(staff);
            addSeasonalAFrame.add(label11);
            addSeasonalAFrame.add(daysOfWeek);
            addSeasonalAFrame.add(assignB);

            addSeasonalAFrame.setLocationRelativeTo(null);
            addSeasonalAFrame.setVisible(true);
        });

        addSeasonalFrame.add(label1);
        addSeasonalFrame.add(chooseMonth);
        addSeasonalFrame.add(calendarButton);
        addSeasonalFrame.add(assignButton);


        addSeasonalFrame.setLocationRelativeTo(null);
        addSeasonalFrame.setVisible(true);
    }

    private void viewCatalogs() {
        String query = "SELECT CONCAT(s.FIRST_NAME, ' ', s.LAST_NAME) AS Assigned, " +
                        "ca.ANIME FROM catalog_assignment ca JOIN staff s ON ca.STAFF_ID = s.STAFF_ID;";
        view.displayTable(query);
    }

    private void addCatalogs() {
        JFrame addCatalogFrame = new JFrame("Add Catalog");
        addCatalogFrame.setSize(420, 110);
        addCatalogFrame.setLayout(new FlowLayout());
        addCatalogFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        addCatalogFrame.setResizable(false);

        JLabel label1 = new JLabel("Assign: ");
        JComboBox<String> staffL = new JComboBox<>(model.getStaffNamesArray());
        JLabel label2 = new JLabel("in ");
        JTextField anime = new JTextField(20);
        JButton assign = new JButton("Assign");

        assign.addActionListener(e -> {
            addCatalogFrame.dispose();

            int staffId = staffL.getSelectedIndex();
            String animeName = anime.getText();

            model.addCatalogAssignment(animeName, staffId + 1);
        });

        addCatalogFrame.add(label1);
        addCatalogFrame.add(staffL);
        addCatalogFrame.add(label2);
        addCatalogFrame.add(anime);
        addCatalogFrame.add(assign);

        addCatalogFrame.setLocationRelativeTo(null);
        addCatalogFrame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("View Assignments")) {
            viewAssignments();
        }
        else if (e.getActionCommand().equals("Add Assignment")) {
            addAssignment();
            view.resetView();
        }
        else if (e.getActionCommand().equals("Remove Assignment")) {
            removeAssignment();
            view.resetView();
        }
        else if (e.getActionCommand().equals("Add Seiyuu")) {
            addSeiyuu();
            view.resetView();
        }
        else if (e.getActionCommand().equals("Edit Seiyuu")) {
            editSeiyuu();
            view.resetView();
        }
        else if (e.getActionCommand().equals("Add Staff")) {
            addStaff();
            view.resetView();
        }
        else if (e.getActionCommand().equals("Edit Staff")) {
            editStaff();
            view.resetView();
        }
        else if (e.getActionCommand().equals("View All Assignments")) {
            viewAssignmentsinSQL();
        }
        else if (e.getActionCommand().equals("Filter Assignments")) {
            filterAssignmentsinSQL();
        }
        else if (e.getActionCommand().equals("Add Bday Assignment")) {
            addAssignmentinSQL();
        }
        else if (e.getActionCommand().equals("Edit Bday Assignment")) {
            viewAssignmentsinSQL();
            updateAssignmentinSQL();
        }
        else if (e.getActionCommand().equals("Delete Bday Assignment")) {
            deleteAssignmentinSQL();
        }
        else if (e.getActionCommand().equals("View Seasonal Schedule")) {
            viewSeasonal();
        }
        else if (e.getActionCommand().equals("Make Seasonal Schedule")) {
            addSeasonal();
        }
        else if (e.getActionCommand().equals("Remove Seasonal Schedule")) {
            view.deleteMonth1();
            model.deleteSeasonal1();
            view.deleteMonth2();
            model.deleteSeasonal2();
            model.deleteSeasonal3();
            view.resetView();
        }
        else if (e.getActionCommand().equals("View Catalog Assignments")) {
            viewCatalogs();
        }
        else if (e.getActionCommand().equals("Add Catalog Assignments")) {
            addCatalogs();
        }
        else if (e.getActionCommand().equals("Delete Catalog Assignments")) {
            model.deleteCatalogs();
            view.resetView();
        }
    }
}
