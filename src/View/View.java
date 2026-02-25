package View;

import java.net.URL;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.SQLConnect;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.*;

public class View extends JFrame {

    private JPanel centerPanel;
    private JLabel welcomeLabel = new JLabel("Welcome to the Seiyuu Corner Portal!");
    private JLabel betaLabel = new JLabel("This app is still on beta test");

    private JButton viewAssignmentsButton;
    private JButton addAssignmentButton;
    private JButton removeAssignmentButton;
    private JButton addSeiyuuButton;
    private JButton editSeiyuuButton;
    private JButton addStaffButton;
    private JButton editStaffButton;

    private JButton viewAssignmentsButtonSQL;
    private JButton filterAssignmentsButtonSQL;
    private JButton addAssignmentButtonSQL;
    private JButton editAssignmentButtonSQL;
    private JButton deleteAssignmentButtonSQL;

    private String m1;
    private String m2;
    private JLabel month1 = new JLabel(m1);
    private JLabel month2 = new JLabel(m2);
    private JButton viewSeasonalSched;
    private JButton addSeasonalSched;
    private JButton removeSeasonalSched;

    private JButton viewCatalogAssignments;
    private JButton addCatalogAssignments;
    private JButton removeCatalogAssignments;

    private SQLConnect sqlConnect = new SQLConnect();
    
    public View() {
        super("Seiyuu Corner");
        setLayout(new BorderLayout());
        setSize(1280, 670);
        setVisible(true);
        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE); 
    }

    public void initComponents() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(255, 200, 0));
        headerPanel.setPreferredSize(new Dimension(100, 100));
        headerPanel.setLayout(new FlowLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(139, 128, 0));
        leftPanel.setPreferredSize(new Dimension(200, 100));
        leftPanel.setLayout(new FlowLayout());
        // leftPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(139, 128, 0));
        rightPanel.setPreferredSize(new Dimension(200, 100));
        rightPanel.setLayout(new FlowLayout());
        // rightPanel.setLayout(new GridLayout(0, 1, 10, 10));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setBackground(new Color(255, 200, 0));
        bottomPanel.setPreferredSize(new Dimension(100, 35));
        bottomPanel.setLayout(new FlowLayout());

        JLabel oopLabel = new JLabel("Vanilla Java OOP");
        oopLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        leftPanel.add(oopLabel);

        viewAssignmentsButton = new JButton("View Assignments");
        viewAssignmentsButton.setPreferredSize(new Dimension(170, 50));
        leftPanel.add(viewAssignmentsButton);

        addAssignmentButton = new JButton("Add Assignment");
        addAssignmentButton.setPreferredSize(new Dimension(170, 50));
        leftPanel.add(addAssignmentButton);

        removeAssignmentButton = new JButton("Remove Assignment");
        removeAssignmentButton.setPreferredSize(new Dimension(170, 50));
        leftPanel.add(removeAssignmentButton);

        addSeiyuuButton = new JButton("Add Seiyuu");
        addSeiyuuButton.setPreferredSize(new Dimension(170, 50));
        leftPanel.add(addSeiyuuButton);

        editSeiyuuButton = new JButton("Edit Seiyuu");
        editSeiyuuButton.setPreferredSize(new Dimension(170, 50));
        leftPanel.add(editSeiyuuButton);

        addStaffButton = new JButton("Add Staff");
        addStaffButton.setPreferredSize(new Dimension(170, 50));
        leftPanel.add(addStaffButton);

        editStaffButton = new JButton("Edit Staff");
        editStaffButton.setPreferredSize(new Dimension(170, 50));
        leftPanel.add(editStaffButton);

        JLabel sqlLabel = new JLabel("SQL Supported");
        sqlLabel.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        rightPanel.add(sqlLabel);

        viewAssignmentsButtonSQL = new JButton("View All Assignments");
        viewAssignmentsButtonSQL.setPreferredSize(new Dimension(170, 50));
        rightPanel.add(viewAssignmentsButtonSQL);

        filterAssignmentsButtonSQL = new JButton("Filter Assignments");
        filterAssignmentsButtonSQL.setPreferredSize(new Dimension(170, 50));
        rightPanel.add(filterAssignmentsButtonSQL);

        addAssignmentButtonSQL = new JButton("Add Bday Assignment");
        addAssignmentButtonSQL.setPreferredSize(new Dimension(170, 50));
        rightPanel.add(addAssignmentButtonSQL);

        editAssignmentButtonSQL = new JButton("Edit Bday Assignment");
        editAssignmentButtonSQL.setPreferredSize(new Dimension(170, 50));
        rightPanel.add(editAssignmentButtonSQL);

        deleteAssignmentButtonSQL = new JButton("Delete Bday Assignment");
        deleteAssignmentButtonSQL.setPreferredSize(new Dimension(170, 50));
        rightPanel.add(deleteAssignmentButtonSQL);

        viewSeasonalSched = new JButton("View Seasonal Schedule");
        viewSeasonalSched.setPreferredSize(new Dimension(170, 50));
        rightPanel.add(viewSeasonalSched);

        addSeasonalSched = new JButton("Make Seasonal Schedule");
        addSeasonalSched.setPreferredSize(new Dimension(170, 50));
        rightPanel.add(addSeasonalSched);

        removeSeasonalSched = new JButton("Remove Seasonal Schedule");
        removeSeasonalSched.setPreferredSize(new Dimension(170, 50));
        rightPanel.add(removeSeasonalSched);

        viewCatalogAssignments = new JButton("View Catalog Assignments");
        bottomPanel.add(viewCatalogAssignments);

        addCatalogAssignments = new JButton("Add Catalog Assignments");
        bottomPanel.add(addCatalogAssignments);

        removeCatalogAssignments = new JButton("Delete Catalog Assignments");
        bottomPanel.add(removeCatalogAssignments);

        centerPanel = new JPanel();
        centerPanel.setBackground(new Color(255, 255, 0));
        centerPanel.setLayout(new GridLayout(0, 1, 2, 10));

        welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        centerPanel.add(welcomeLabel);

        betaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        centerPanel.add(betaLabel);

        URL imageUrl = View.class.getResource("/Properties/SC.jpg");
        if (imageUrl == null) {
            System.out.println("Image not found! Check path.");
            return;
        }

        ImageIcon sc = new ImageIcon(imageUrl);
        Image img = sc.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
        ImageIcon rSC = new ImageIcon(img);
        JLabel imageLabel = new JLabel(rSC);
        headerPanel.add(imageLabel);

        JLabel titleLabel = new JLabel("Seiyuu Corner Portal");
        titleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        headerPanel.add(titleLabel);

        this.add(headerPanel, BorderLayout.NORTH);
        this.add(bottomPanel, BorderLayout.SOUTH);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(centerPanel, BorderLayout.CENTER);
    }

    public void setActionListener(ActionListener listener) {
        viewAssignmentsButton.addActionListener(listener);
        addAssignmentButton.addActionListener(listener);
        removeAssignmentButton.addActionListener(listener);
        addSeiyuuButton.addActionListener(listener);
        editSeiyuuButton.addActionListener(listener);
        addStaffButton.addActionListener(listener);
        editStaffButton.addActionListener(listener);
        viewAssignmentsButtonSQL.addActionListener(listener);
        filterAssignmentsButtonSQL.addActionListener(listener);
        addAssignmentButtonSQL.addActionListener(listener);
        editAssignmentButtonSQL.addActionListener(listener);
        deleteAssignmentButtonSQL.addActionListener(listener);
        viewSeasonalSched.addActionListener(listener);
        addSeasonalSched.addActionListener(listener);
        removeSeasonalSched.addActionListener(listener);
        viewCatalogAssignments.addActionListener(listener);
        addCatalogAssignments.addActionListener(listener);
        removeCatalogAssignments.addActionListener(listener);
    }

    public void resetView() {
        centerPanel.removeAll();

        welcomeLabel.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        centerPanel.add(welcomeLabel);

        betaLabel.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        centerPanel.add(betaLabel);

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void addTable(JScrollPane scrollPane) {
        centerPanel.removeAll();
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void displayTable(String query) {
        Connection conn = sqlConnect.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get metadata for column names
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }

            // Read rows into DefaultTableModel
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }

            // Display in JTable
            JTable table = new JTable(model);
            JScrollPane scrollPane = new JScrollPane(table);

            addTable(scrollPane);

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayTableifUnknown(String query, Integer index) {
        Connection conn = sqlConnect.getConnection();
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, index);

            try (ResultSet rs = pstmt.executeQuery()) {

                // Get metadata for column names
                ResultSetMetaData meta = rs.getMetaData();
                int columnCount = meta.getColumnCount();
                String[] columnNames = new String[columnCount];

                for (int i = 0; i < columnCount; i++) {
                    columnNames[i] = meta.getColumnLabel(i + 1);
                }

                // Create table model (read-only)
                DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                    @Override
                    public boolean isCellEditable(int row, int column) {
                        return false;
                    }
                };

                // Populate table model
                while (rs.next()) {
                    Object[] rowData = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        rowData[i] = rs.getObject(i + 1);
                    }
                    model.addRow(rowData);
                }

                // Display in JTable
                JTable table = new JTable(model);
                JScrollPane scrollPane = new JScrollPane(table);

                addTable(scrollPane);
                rs.close();
                pstmt.close();
                conn.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private DefaultTableModel makeSeasonalTable(String query) {
        Connection conn = sqlConnect.getConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            // Get metadata for column names
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = rsmd.getColumnName(i);
            }

            // Read rows into DefaultTableModel
            DefaultTableModel model = new DefaultTableModel(columnNames, 0) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            };
        
            while (rs.next()) {
                Object[] rowData = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    rowData[i - 1] = rs.getObject(i);
                }
                model.addRow(rowData);
            }
            rs.close();
            stmt.close();
            conn.close();

            return model;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void addSeasonalTable(String query1, String query2) {
        DefaultTableModel model1 = makeSeasonalTable(query1);
        DefaultTableModel model2 = makeSeasonalTable(query2);

        if (model1 == null || model2 == null) {
            return;
        }

        JTable table1 = new JTable(model1);
        JTable table2 = new JTable(model2);

        JScrollPane scrollPane1 = new JScrollPane(table1);
        JScrollPane scrollPane2 = new JScrollPane(table2);

        centerPanel.removeAll();

        // Just add them normally (GridLayout handles positioning)
        month1.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        centerPanel.add(month1);
        centerPanel.add(scrollPane1);  // Top

        month2.setFont(new Font("Segoe UI", Font.PLAIN, 48));
        centerPanel.add(month2);
        centerPanel.add(scrollPane2);  // Bottom

        centerPanel.revalidate();
        centerPanel.repaint();
    }

    public void setMonth1(String month) {
        this.m1 = month;
        month1.setText(month);
    }

    public void setMonth2(String month) {
        this.m2 = month;
        month2.setText(month);
    }

    public void deleteMonth1(){
        month1.setText(null);
    }

    public void deleteMonth2(){
        month2.setText(null);
    }
}
