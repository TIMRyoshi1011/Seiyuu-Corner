package Model;

import dao.SQLConnect;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableModel;

import java.sql.*;
import javax.swing.*;

public class Model {

    private static int seiyuuId = 0;
    private static int staffId = 0;
    private static int initialSeiyuuCount;
    private static int initialStaffCount;

    private List <Seiyuu> seiyuuList = new ArrayList<>();
    private List <Staff> staffList = new ArrayList<>();
    private List <String> seiyuuNames = new ArrayList<>();
    private HashMap<Integer, List<Integer>> assignment = new HashMap<>();

    private SQLConnect sqlConnect = new SQLConnect();

    private String[] staff = {"Ken", "Jerome", "Ghifary", "Wednesday", "Mynhi", "Pearse", "Marcus"};

    public Model() {
        getSeiyuuNames();
        initialSeiyuuCount = getSeiyuuCount();
        initialStaffCount = getStaffCount();
    }

    public Seiyuu[] getSeiyuuArray() {
        return seiyuuList.toArray(new Seiyuu[0]);
    }

    public Staff[] getStaffArray() {
        return staffList.toArray(new Staff[0]);
    }

    public List<Seiyuu> getSeiyuuList() {
        return seiyuuList;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public List<String> getSeiyuuNamesList() {
        return seiyuuNames;
    }

    public String[] getSeiyuuNamesArray() {
        return seiyuuNames.toArray(new String[0]);
    }

    public String[] getStaffNamesArray() {
        return staff;
    }

    // ==================================== Vanilla OOP ==================================== //

    public JScrollPane viewAssignments() {
        String[] columnNames = {
            "Assigned",
            "Birthday",
            "Name",
            "Age",
            "Sex",
            "Agency"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        for (Integer staffId : assignment.keySet()) {
            for (Integer seiyuuId : assignment.get(staffId)) {
                Object[] rowData = { 
                    staffList.get(staffId - 1).getFirstName() + " " + staffList.get(staffId - 1).getLastName(), 
                    seiyuuList.get(seiyuuId - 1).getBirthDate(),
                    seiyuuList.get(seiyuuId - 1).getFirstName() + " " + seiyuuList.get(seiyuuId - 1).getLastName(),
                    seiyuuList.get(seiyuuId - 1).getAge(),
                    seiyuuList.get(seiyuuId - 1).getSex(),
                    seiyuuList.get(seiyuuId - 1).getAgency()
                };
                model.addRow(rowData);
            }
        }

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        return scrollPane;
    }

    public void addAssignment(int staffId, int seiyuuId) {
        assignment
        .computeIfAbsent(staffId, k -> new ArrayList<>())
        .add(seiyuuId);
        JOptionPane.showMessageDialog(null, seiyuuList.get(seiyuuId - 1).getFirstName() + " " + seiyuuList.get(seiyuuId - 1).getLastName() 
                                            + " assigned to " + staffList.get(staffId - 1).getFirstName() + " " + staffList.get(staffId - 1).getLastName());
        System.out.println("HashTable: " + assignment);
    }

    public void removeAssignment(int staffId, int seiyuuId) {
        if (!assignment.containsKey(staffId)) {
            JOptionPane.showMessageDialog(null,"Removal failed: staffId not found.");
            return;
        }

        List<Integer> list = assignment.get(staffId);

        boolean removed = list.remove(Integer.valueOf(seiyuuId));

        if (!removed) {
            JOptionPane.showMessageDialog(null,"Removal failed: seiyuu not assigned to this staff.");
            return;
        }

        // If list becomes empty, remove the staff entry entirely
        if (list.isEmpty()) {
            assignment.remove(staffId);
        }

        JOptionPane.showMessageDialog(
            null,
            seiyuuList.get(seiyuuId - 1).getFirstName() + " " +
            seiyuuList.get(seiyuuId - 1).getLastName() +
            " removed from " +
            staffList.get(staffId - 1).getFirstName() + " " +
            staffList.get(staffId - 1).getLastName()
        );

        System.out.println("HashTable: " + assignment);
    }

    public void addSeiyuuM(String birthDate, String firstName, String lastName, int age, char sex, String agency) {
        seiyuuId++;
        LocalDate parsedBirthDate = LocalDate.parse(birthDate);
        Seiyuu newSeiyuu = new Seiyuu(seiyuuId, parsedBirthDate, firstName, lastName, age, sex, agency);
        newSeiyuu.displaySeiyuuInfo();
        seiyuuList.add(newSeiyuu);
        // addSeiyuuInDB(seiyuuId);
    }

    protected void addSeiyuuInDB(int seiyuuIndex) { // Adding to DB

        String query = "INSERT INTO seiyuu (SEIYUU_ID, BIRTHDAY, FIRST_NAME, LAST_NAME, AGE, SEX, AGENCY) " + 
                        "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = sqlConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, initialSeiyuuCount + seiyuuIndex);
            pstmt.setString(2, String.valueOf(seiyuuList.get(seiyuuIndex - 1).getBirthDate()));
            pstmt.setString(3, seiyuuList.get(seiyuuIndex - 1).getFirstName());
            pstmt.setString(4, seiyuuList.get(seiyuuIndex - 1).getLastName());
            pstmt.setInt(5, seiyuuList.get(seiyuuIndex - 1).getAge());
            pstmt.setString(6, String.valueOf(seiyuuList.get(seiyuuIndex - 1).getSex()));
            pstmt.setString(7, seiyuuList.get(seiyuuIndex - 1).getAgency());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Seiyuu added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editSeiyuuM(int seiyuuID, String birthDate, String firstName, String lastName, String age, String sex, String agency) {
        LocalDate parsedBirthDate = LocalDate.parse(birthDate);
        Seiyuu selectedSeiyuu = null;
        // StringBuilder sql = new StringBuilder("UPDATE seiyuu SET ");
        // boolean firstField = true;

        for (Seiyuu seiyuu : seiyuuList) {
            if (seiyuu.getSeiyuuID() == seiyuuID) {
                selectedSeiyuu = seiyuu;
                break;
            }
        }

        if (selectedSeiyuu != null) {
            if (!birthDate.isEmpty()) {
                selectedSeiyuu.setBirthDate(parsedBirthDate);
                // sql.append("birthday = ?");
                // firstField = false;
            }
            if (!firstName.isEmpty()) {
                selectedSeiyuu.setFirstName(firstName);
                // if (!firstField) sql.append(", ");
                // sql.append("first_name = ?");
                // firstField = false;
            }
            if (!lastName.isEmpty()) {
                selectedSeiyuu.setLastName(lastName);
                // if (!firstField) sql.append(", ");
                // sql.append("last_name = ?");
                // firstField = false;
            }
            if (!age.isEmpty()) {
                selectedSeiyuu.setAge(Integer.parseInt(age));
                // if (!firstField) sql.append(", ");
                // sql.append("age = ?");
                // firstField = false;
            }
            if (!sex.isEmpty()) {
                selectedSeiyuu.setSex(sex.charAt(0));
                // if (!firstField) sql.append(", ");
                // sql.append("sex = ?");
                // firstField = false;
            }
            if (!agency.isEmpty()) {
                selectedSeiyuu.setAgency(agency);
                // if (!firstField) sql.append(", ");
                // sql.append("agency = ?");
                // firstField = false;
            }
            // Display updated seiyuu's information
            selectedSeiyuu.displaySeiyuuInfo();

            // sql.append(" WHERE seiyuu_id = ?");

            // if (birthDate.isEmpty() && firstName.isEmpty() && lastName.isEmpty() && age.isEmpty() && sex.isEmpty() && agency.isEmpty()) {
            //     JOptionPane.showMessageDialog(null, "No fields to update.");
            //     return;
            // }

            // try (Connection conn = sqlConnect.getConnection();
            //     PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            //     int paramIndex = 1;

            //     if (!birthDate.isEmpty()) {
            //         pstmt.setString(paramIndex++, birthDate);
            //     }
            //     if (!firstName.isEmpty()) {
            //         pstmt.setString(paramIndex++, firstName);
            //     }
            //     if (!lastName.isEmpty()) {
            //         pstmt.setString(paramIndex++, lastName);
            //     }
            //     if (!age.isEmpty()) {
            //         pstmt.setString(paramIndex++, age);
            //     }
            //     if (!sex.isEmpty()) {
            //         pstmt.setString(paramIndex++, sex);
            //     }
            //     if (!agency.isEmpty()) {
            //         pstmt.setString(paramIndex++, agency);
            //     }

            //     pstmt.setInt(paramIndex, seiyuuID + initialSeiyuuCount);

            //     int rowsUpdated = pstmt.executeUpdate();
            //     if (rowsUpdated > 0) {
            //         JOptionPane.showMessageDialog(null, "Seiyuu updated successfully.");
            //     } else {
            //         JOptionPane.showMessageDialog(null, "Seiyuu ID not found.");
            //     }

            // } catch (SQLException e) {
            //     e.printStackTrace();
            // }
        }
    }

    public void addStaffM(String firstName, String lastName, String position) {
        staffId++;
        Staff newStaff = new Staff(staffId, firstName, lastName, position);
        newStaff.displayStaffInfo();
        staffList.add(newStaff);
        // addStaffInDB(staffId);
    }

    protected void addStaffInDB(int staffIndex) { // Adding to DB

        String query = "INSERT INTO staff (STAFF_ID, FIRST_NAME, LAST_NAME, POSITION) " +
                        "VALUES (?, ?, ?, ?)";

        try {
            Connection conn = sqlConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, initialStaffCount + staffIndex);
            pstmt.setString(2, staffList.get(staffIndex - 1).getFirstName());
            pstmt.setString(3, staffList.get(staffIndex - 1).getLastName());
            pstmt.setString(4, staffList.get(staffIndex - 1).getPosition());
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Staff added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void editStaffM(int staffID, String firstName, String lastName, String position) {
        Staff selectedStaff = null;
        // StringBuilder sql = new StringBuilder("UPDATE staff SET ");
        // boolean firstField = true;

        for (Staff staff : staffList) {
            if (staff.getStaffID() == staffID) {
                selectedStaff = staff;
                break;
            }
        }

        if (selectedStaff != null) {
            if (!firstName.isEmpty()) {
                selectedStaff.setFirstName(firstName);
                // sql.append("first_name = ?");
                // firstField = false;
            }
            if (!lastName.isEmpty()) {
                selectedStaff.setLastName(lastName);
                // if (!firstField) sql.append(", ");
                // sql.append("last_name = ?");
                // firstField = false;
            }
            if (!position.isEmpty()) {
                selectedStaff.setPosition(position);
                // if (!firstField) sql.append(", ");
                // sql.append("position = ?");
                // firstField = false;
            }

            // Display updated staff's information
            selectedStaff.displayStaffInfo();

            // sql.append(" WHERE staff_id = ?");

            // if (firstName.isEmpty() && lastName.isEmpty() && position.isEmpty()) {
            //     JOptionPane.showMessageDialog(null, "No fields to update.");
            //     return;
            // }

            // try (Connection conn = sqlConnect.getConnection();
            //     PreparedStatement pstmt = conn.prepareStatement(sql.toString())) {

            //     int paramIndex = 1;

            //     if (!firstName.isEmpty()) {
            //         pstmt.setString(paramIndex++, firstName);
            //     }
            //     if (!lastName.isEmpty()) {
            //         pstmt.setString(paramIndex++, lastName);
            //     }
            //     if (!position.isEmpty()) {
            //         pstmt.setString(paramIndex++, position);
            //     }

            //     pstmt.setInt(paramIndex, staffID + initialStaffCount);

            //     int rowsUpdated = pstmt.executeUpdate();
            //     if (rowsUpdated > 0) {
            //         JOptionPane.showMessageDialog(null, "Staff updated successfully.");
            //     } else {
            //         JOptionPane.showMessageDialog(null, "Staff ID not found.");
            //     }

            // } catch (SQLException e) {
            //     e.printStackTrace();
            // }
        }
    }

    // ==================================== SQL Assignments ==================================== //

    public String filteredAssignment() {
        String query = "SELECT s.first_name AS 'Assigned', su.Birthday, CONCAT(su.first_name, ' ', su.last_name) AS Name, su.Age, su.Sex, su.Agency, su.Music_Label " + // , su.Japanese_Name, su.Seiyuu_Pics, su.Twitter, su.Instagram, su.Notes
                        "FROM staff_assignment sa JOIN staff s ON sa.staff_id = s.staff_id " + 
                        "JOIN seiyuu su ON sa.seiyuu_id = su.seiyuu_id " +
                        "WHERE sa.staff_id = ? " +
                        "ORDER BY su.seiyuu_id; ";
        return query;
    }

    public void addAssignmentInDB(int staffId, int seiyuuId) {
        String query = "INSERT INTO staff_assignment VALUES (?, ?)";

        try {
            Connection conn = sqlConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, staffId);
            pstmt.setInt(2, seiyuuId);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Assignment added successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateAssignmentInDB(int staffId, int seiyuuId) {
        String query = "UPDATE staff_assignment SET staff_id = ? WHERE seiyuu_id = ?";

        try (Connection conn = sqlConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, staffId);
            pstmt.setInt(2, seiyuuId);

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Assignment updated successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Assignment updated unsuccessfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteAssignmentInDB(int staffId, int seiyuuId) {
        String query = "DELETE FROM staff_assignment WHERE staff_id = ? AND seiyuu_id = ?";

        try (Connection conn = sqlConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, staffId);
            pstmt.setInt(2, seiyuuId);

            int rowsDeleted = pstmt.executeUpdate();

            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Assignment deleted successfully.");
            } else {
                JOptionPane.showMessageDialog(null, "Assignment deleted unsuccessfully.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void viewSeasonalAssignments() {
        Connection conn = sqlConnect.getConnection();
        String query = "SELECT * FROM seasonal_assignment;";
        StringBuilder result = new StringBuilder();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                int staffId = rs.getInt("STAFF_ID");
                String day = rs.getString("DAY_OF_WEEK");
                
                result.append(staff[staffId - 1] + " - " + day + "\n");
            }

            JOptionPane.showMessageDialog(null, "STAFF - DAY_OF_WEEK\n" + result.toString(), "Assignment", JOptionPane.INFORMATION_MESSAGE);
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makeSeasonalCalendar1(String seiyuu1, String seiyuu2, String seiyuu3, String seiyuu4, String seiyuu5, String seiyuu6, String seiyuu7) {
        String query = "INSERT INTO seasonal_calendar VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = sqlConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, seiyuu1);
            pstmt.setString(2, seiyuu2);
            pstmt.setString(3, seiyuu3);
            pstmt.setString(4, seiyuu4);
            pstmt.setString(5, seiyuu5);
            pstmt.setString(6, seiyuu6);
            pstmt.setString(7, seiyuu7);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Calendar 1 created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void makeSeasonalCalendar2(String seiyuu1, String seiyuu2, String seiyuu3, String seiyuu4, String seiyuu5, String seiyuu6, String seiyuu7) {
        String query = "INSERT INTO seasonal_calendar_2 VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = sqlConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, seiyuu1);
            pstmt.setString(2, seiyuu2);
            pstmt.setString(3, seiyuu3);
            pstmt.setString(4, seiyuu4);
            pstmt.setString(5, seiyuu5);
            pstmt.setString(6, seiyuu6);
            pstmt.setString(7, seiyuu7);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Calendar 2 created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSeasonalAssignment(int staffId, String day) {
        String query = "INSERT INTO seasonal_assignment VALUES (?, ?)";

        try {
            Connection conn = sqlConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setInt(1, staffId);
            pstmt.setString(2, day);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Seasonal assignment created successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSeasonal1() {
        String sql = "DELETE FROM seasonal_calendar";

        try (Connection conn = sqlConnect.getConnection();
            Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSeasonal2() {
        String sql = "DELETE FROM seasonal_calendar_2";

        try (Connection conn = sqlConnect.getConnection();
            Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteSeasonal3() {
        String sql = "DELETE FROM seasonal_assignment";

        try (Connection conn = sqlConnect.getConnection();
            Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Sesonal has been reset");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCatalogAssignment(String anime, int staffId) {
        String query = "INSERT INTO catalog_assignment (ANIME, STAFF_ID) VALUES (?, ?)";

        try {
            Connection conn = sqlConnect.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query);

            pstmt.setString(1, anime);
            pstmt.setInt(2, staffId);
            pstmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Catalog assigned successfully!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteCatalogs() {
        String sql = "DELETE FROM catalog_assignment";

        try (Connection conn = sqlConnect.getConnection();
            Statement stmt = conn.createStatement()) {

            stmt.executeUpdate(sql);
            JOptionPane.showMessageDialog(null, "Catalogs has been reset");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void getSeiyuuNames() {
        Connection conn = sqlConnect.getConnection();
        String query = "SELECT * FROM seiyuu";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                String fName = rs.getString("FIRST_NAME");
                String lName = rs.getString("LAST_NAME");
                seiyuuNames.add(fName + " " + lName);
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getSeiyuuCount() {
        Connection conn = sqlConnect.getConnection();
        String query = "SELECT COUNT(*) AS COUNT FROM seiyuu;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                int count = rs.getInt("COUNT");

                return count;
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getStaffCount() {
        Connection conn = sqlConnect.getConnection();
        String query = "SELECT COUNT(*) AS COUNT FROM staff;";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while(rs.next()) {
                int count = rs.getInt("COUNT");

                return count;
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
