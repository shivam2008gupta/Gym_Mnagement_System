
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class GymManagementSystem extends JFrame {

    // ================= DATABASE =================
    static final String URL =
            "jdbc:mysql://localhost:3306/gymfit?useSSL=false&serverTimezone=UTC";

    static final String USER = "root";

    // Apna MySQL password yahan likho
    static final String PASSWORD = "Shivam#2008";

    // ================= GUI =================
    JTable table;
    DefaultTableModel model;

    JLabel totalLabel;
    JLabel activeLabel;

    JTextField idField;
    JTextField nameField;
    JTextField ageField;
    JTextField phoneField;

    JComboBox<String> genderBox;
    JComboBox<String> planBox;
    JComboBox<String> statusBox;

    // ================= CONSTRUCTOR =================
    public GymManagementSystem() {

        setTitle("GYMFIT - Gym Management System");
        setSize(1150, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        createGUI();

        // Database initialize
        SwingUtilities.invokeLater(() -> {
            createTable();
            loadMembers();
        });
    }

    // ================= MAIN GUI =================
    private void createGUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        // =================================================
        // HEADER
        // =================================================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(
                new Color(15, 23, 42)
        );

        header.setPreferredSize(
                new Dimension(1150, 80)
        );

        JLabel title =
                new JLabel(
                        "  GYMFIT MANAGEMENT SYSTEM"
                );

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        26
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Gym Administration Dashboard   "
                );

        subtitle.setForeground(
                new Color(200, 200, 200)
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                subtitle,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        // =================================================
        // LEFT SIDEBAR
        // =================================================

        JPanel sidebar =
                new JPanel(
                        new GridLayout(8, 1, 0, 8)
                );

        sidebar.setPreferredSize(
                new Dimension(210, 0)
        );

        sidebar.setBackground(
                new Color(30, 41, 59)
        );

        JButton dashboardBtn =
                menuButton("Dashboard");

        JButton addBtn =
                menuButton("Add Member");

        JButton viewBtn =
                menuButton("View Members");

        JButton searchBtn =
                menuButton("Search Member");

        JButton deleteBtn =
                menuButton("Delete Member");

        JButton plansBtn =
                menuButton("Membership Plans");

        JButton refreshBtn =
                menuButton("Refresh");

        JButton exitBtn =
                menuButton("Exit");

        sidebar.add(dashboardBtn);
        sidebar.add(addBtn);
        sidebar.add(viewBtn);
        sidebar.add(searchBtn);
        sidebar.add(deleteBtn);
        sidebar.add(plansBtn);
        sidebar.add(refreshBtn);
        sidebar.add(exitBtn);

        mainPanel.add(
                sidebar,
                BorderLayout.WEST
        );

        // =================================================
        // CENTER
        // =================================================

        JPanel center =
                new JPanel(
                        new BorderLayout()
                );

        center.setBackground(
                new Color(248, 250, 252)
        );

        // =================================================
        // DASHBOARD CARDS
        // =================================================

        JPanel cards =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                20,
                                20
                        )
                );

        cards.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        cards.setBackground(
                new Color(248, 250, 252)
        );

        totalLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        activeLabel =
                new JLabel(
                        "0",
                        SwingConstants.CENTER
                );

        JLabel plansLabel =
                new JLabel(
                        "3",
                        SwingConstants.CENTER
                );

        cards.add(
                createCard(
                        "TOTAL MEMBERS",
                        totalLabel
                )
        );

        cards.add(
                createCard(
                        "ACTIVE MEMBERS",
                        activeLabel
                )
        );

        cards.add(
                createCard(
                        "MEMBERSHIP PLANS",
                        plansLabel
                )
        );

        center.add(
                cards,
                BorderLayout.NORTH
        );

        // =================================================
        // TABLE PANEL
        // =================================================

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        20,
                        20
                )
        );

        tablePanel.setBackground(
                new Color(248, 250, 252)
        );

        JLabel tableTitle =
                new JLabel(
                        "  MEMBERS LIST"
                );

        tableTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        tableTitle.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        0,
                        10,
                        0
                )
        );

        tablePanel.add(
                tableTitle,
                BorderLayout.NORTH
        );

        String[] columns = {
                "ID",
                "Name",
                "Age",
                "Gender",
                "Phone",
                "Plan",
                "Fee",
                "Status"
        };

        model =
                new DefaultTableModel(
                        columns,
                        0
                );

        table =
                new JTable(model);

        table.setRowHeight(30);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        table.getTableHeader().setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        table.setSelectionBackground(
                new Color(219, 234, 254)
        );

        table.setSelectionForeground(
                Color.BLACK
        );

        JScrollPane scroll =
                new JScrollPane(table);

        tablePanel.add(
                scroll,
                BorderLayout.CENTER
        );

        center.add(
                tablePanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        dashboardBtn.addActionListener(e -> {
            loadMembers();

            JOptionPane.showMessageDialog(
                    this,
                    "Welcome to GYMFIT Gym Management System!"
            );
        });

        addBtn.addActionListener(
                e -> addMember()
        );

        viewBtn.addActionListener(
                e -> loadMembers()
        );

        searchBtn.addActionListener(
                e -> searchMember()
        );

        deleteBtn.addActionListener(
                e -> deleteMember()
        );

        plansBtn.addActionListener(
                e -> showPlans()
        );

        refreshBtn.addActionListener(
                e -> loadMembers()
        );

        exitBtn.addActionListener(e -> {

            int result =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to exit?",
                            "Exit",
                            JOptionPane.YES_NO_OPTION
                    );

            if (
                    result ==
                            JOptionPane.YES_OPTION
            ) {
                System.exit(0);
            }
        });
    }

    // =================================================
    // SIDEBAR BUTTON
    // =================================================

    private JButton menuButton(String text) {

        JButton button =
                new JButton(text);

        button.setForeground(Color.WHITE);

        button.setBackground(
                new Color(51, 65, 85)
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        return button;
    }

    // =================================================
    // CARD
    // =================================================

    private JPanel createCard(
            String title,
            JLabel value) {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220)
                )
        );

        JLabel heading =
                new JLabel(
                        title,
                        SwingConstants.CENTER
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        heading.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        5,
                        5,
                        5
                )
        );

        value.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        panel.add(
                heading,
                BorderLayout.NORTH
        );

        panel.add(
                value,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =================================================
    // DATABASE CONNECTION
    // =================================================

    private Connection getConnection()
            throws SQLException {

        return DriverManager.getConnection(
                URL,
                USER,
                PASSWORD
        );
    }

    // =================================================
    // CREATE TABLE
    // =================================================

    private void createTable() {

        String sql =
                "CREATE TABLE IF NOT EXISTS members (" +
                        "id INT PRIMARY KEY," +
                        "name VARCHAR(100) NOT NULL," +
                        "age INT," +
                        "gender VARCHAR(20)," +
                        "phone VARCHAR(20)," +
                        "plan VARCHAR(30)," +
                        "fee DOUBLE," +
                        "status VARCHAR(20)" +
                        ")";

        try (
                Connection con =
                        getConnection();

                Statement st =
                        con.createStatement()
        ) {

            st.executeUpdate(sql);

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "MySQL Connection Error!\n\n"
                            + e.getMessage()
                            + "\n\n"
                            + "Check MySQL Server and password.",
                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =================================================
    // ADD MEMBER
    // =================================================

    private void addMember() {

        JPanel panel =
                new JPanel(
                        new GridLayout(
                                7,
                                2,
                                12,
                                12
                        )
                );

        panel.setPreferredSize(
                new Dimension(
                        400,
                        250
                )
        );

        idField =
                new JTextField();

        nameField =
                new JTextField();

        ageField =
                new JTextField();

        phoneField =
                new JTextField();

        genderBox =
                new JComboBox<>(
                        new String[]{
                                "Male",
                                "Female",
                                "Other"
                        }
                );

        planBox =
                new JComboBox<>(
                        new String[]{
                                "Monthly",
                                "Quarterly",
                                "Yearly"
                        }
                );

        statusBox =
                new JComboBox<>(
                        new String[]{
                                "Active",
                                "Inactive"
                        }
                );

        panel.add(
                new JLabel("Member ID:")
        );

        panel.add(idField);

        panel.add(
                new JLabel("Name:")
        );

        panel.add(nameField);

        panel.add(
                new JLabel("Age:")
        );

        panel.add(ageField);

        panel.add(
                new JLabel("Gender:")
        );

        panel.add(genderBox);

        panel.add(
                new JLabel("Phone:")
        );

        panel.add(phoneField);

        panel.add(
                new JLabel("Plan:")
        );

        panel.add(planBox);

        panel.add(
                new JLabel("Status:")
        );

        panel.add(statusBox);

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        panel,
                        "ADD NEW MEMBER",
                        JOptionPane.OK_CANCEL_OPTION,
                        JOptionPane.PLAIN_MESSAGE
                );

        if (
                result !=
                        JOptionPane.OK_OPTION
        ) {
            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            idField
                                    .getText()
                                    .trim()
                    );

            String name =
                    nameField
                            .getText()
                            .trim();

            int age =
                    Integer.parseInt(
                            ageField
                                    .getText()
                                    .trim()
                    );

            String gender =
                    genderBox
                            .getSelectedItem()
                            .toString();

            String phone =
                    phoneField
                            .getText()
                            .trim();

            String plan =
                    planBox
                            .getSelectedItem()
                            .toString();

            String status =
                    statusBox
                            .getSelectedItem()
                            .toString();

            double fee =
                    calculateFee(plan);

            String sql =
                    "INSERT INTO members " +
                            "(id,name,age,gender,phone,plan,fee,status) " +
                            "VALUES (?,?,?,?,?,?,?,?)";

            try (
                    Connection con =
                            getConnection();

                    PreparedStatement ps =
                            con.prepareStatement(sql)
            ) {

                ps.setInt(1, id);
                ps.setString(2, name);
                ps.setInt(3, age);
                ps.setString(4, gender);
                ps.setString(5, phone);
                ps.setString(6, plan);
                ps.setDouble(7, fee);
                ps.setString(8, status);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "Member Added Successfully!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loadMembers();
            }

        } catch (
                NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "ID and Age must be numbers!",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (
                SQLIntegrityConstraintViolationException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "This Member ID already exists!",
                    "Duplicate ID",
                    JOptionPane.ERROR_MESSAGE
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n"
                            + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =================================================
    // FEE
    // =================================================

    private double calculateFee(
            String plan) {

        if (
                plan.equals("Monthly")
        ) {
            return 1000;
        }

        if (
                plan.equals("Quarterly")
        ) {
            return 2500;
        }

        return 8000;
    }

    // =================================================
    // LOAD MEMBERS
    // =================================================

    private void loadMembers() {

        model.setRowCount(0);

        String sql =
                "SELECT * FROM members ORDER BY id";

        try (
                Connection con =
                        getConnection();

                Statement st =
                        con.createStatement();

                ResultSet rs =
                        st.executeQuery(sql)
        ) {

            int total = 0;
            int active = 0;

            while (rs.next()) {

                model.addRow(
                        new Object[]{
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("gender"),
                                rs.getString("phone"),
                                rs.getString("plan"),
                                "₹"
                                        + rs.getDouble("fee"),
                                rs.getString("status")
                        }
                );

                total++;

                if (
                        rs.getString(
                                "status"
                        ).equalsIgnoreCase(
                                "Active"
                        )
                ) {
                    active++;
                }
            }

            totalLabel.setText(
                    String.valueOf(total)
            );

            activeLabel.setText(
                    String.valueOf(active)
            );

        } catch (SQLException e) {

            totalLabel.setText("0");
            activeLabel.setText("0");
        }
    }

    // =================================================
    // SEARCH
    // =================================================

    private void searchMember() {

        String search =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Member ID or Name:"
                );

        if (
                search == null ||
                        search.trim().isEmpty()
        ) {
            return;
        }

        model.setRowCount(0);

        String sql =
                "SELECT * FROM members " +
                        "WHERE id = ? OR name LIKE ?";

        try (
                Connection con =
                        getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            try {

                ps.setInt(
                        1,
                        Integer.parseInt(
                                search.trim()
                        )
                );

            } catch (
                    NumberFormatException e) {

                ps.setInt(1, -1);
            }

            ps.setString(
                    2,
                    "%" +
                            search.trim()
                            +
                            "%"
            );

            ResultSet rs =
                    ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                model.addRow(
                        new Object[]{
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("age"),
                                rs.getString("gender"),
                                rs.getString("phone"),
                                rs.getString("plan"),
                                "₹"
                                        + rs.getDouble("fee"),
                                rs.getString("status")
                        }
                );

                found = true;
            }

            if (!found) {

                JOptionPane.showMessageDialog(
                        this,
                        "Member Not Found!"
                );
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Search Error:\n"
                            + e.getMessage()
            );
        }
    }

    // =================================================
    // DELETE
    // =================================================

    private void deleteMember() {

        String input =
                JOptionPane.showInputDialog(
                        this,
                        "Enter Member ID:"
                );

        if (input == null) {
            return;
        }

        try {

            int id =
                    Integer.parseInt(
                            input.trim()
                    );

            String sql =
                    "DELETE FROM members WHERE id=?";

            try (
                    Connection con =
                            getConnection();

                    PreparedStatement ps =
                            con.prepareStatement(sql)
            ) {

                ps.setInt(1, id);

                int rows =
                        ps.executeUpdate();

                if (rows > 0) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Member Deleted Successfully!"
                    );

                    loadMembers();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Member Not Found!"
                    );
                }
            }

        } catch (
                NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Member ID!"
            );

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Delete Error:\n"
                            + e.getMessage()
            );
        }
    }

    // =================================================
    // PLANS
    // =================================================

    private void showPlans() {

        String plans =
                "GYMFIT MEMBERSHIP PLANS\n\n"
                        +
                        "Monthly Plan   : ₹1000\n"
                        +
                        "Quarterly Plan : ₹2500\n"
                        +
                        "Yearly Plan    : ₹8000\n\n"
                        +
                        "FEATURES\n"
                        +
                        "• Gym Equipment Access\n"
                        +
                        "• Trainer Support\n"
                        +
                        "• Workout Area\n"
                        +
                        "• Fitness Tracking";

        JOptionPane.showMessageDialog(
                this,
                plans,
                "Membership Plans",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =================================================
    // MAIN
    // =================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(() -> {

            GymManagementSystem app =
                    new GymManagementSystem();

            app.setVisible(true);
        });
    }
}
