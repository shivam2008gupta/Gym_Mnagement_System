# Gym_Mnagement_System
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

class Member {
    int id;
    String name;
    int age;
    String gender;
    String phone;
    String plan;
    double fee;
    String status;

    Member(int id, String name, int age, String gender,
           String phone, String plan, double fee, String status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.plan = plan;
        this.fee = fee;
        this.status = status;
    }
}

public class GymManagementSystem extends JFrame {

    ArrayList<Member> members = new ArrayList<>();

    JTextField idField, nameField, ageField, phoneField, searchField;
    JComboBox<String> genderBox, planBox, statusBox;
    JTable table;
    DefaultTableModel model;

    JLabel totalMembersLabel;

    public GymManagementSystem() {

        setTitle("GYMFIT - Gym Management System");
        setSize(1100, 650);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(245, 245, 245));

        // ================= HEADER =================
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(30, 30, 30));
        header.setPreferredSize(new Dimension(1100, 70));

        JLabel title = new JLabel("  GYMFIT MANAGEMENT SYSTEM");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Arial", Font.BOLD, 25));

        JLabel subtitle = new JLabel("Gym Administration Dashboard  ");
        subtitle.setForeground(Color.LIGHT_GRAY);
        subtitle.setFont(new Font("Arial", Font.PLAIN, 14));

        header.add(title, BorderLayout.WEST);
        header.add(subtitle, BorderLayout.EAST);

        mainPanel.add(header, BorderLayout.NORTH);

        // ================= LEFT MENU =================
        JPanel menu = new JPanel();
        menu.setLayout(new GridLayout(7, 1, 5, 5));
        menu.setPreferredSize(new Dimension(190, 0));
        menu.setBackground(new Color(45, 45, 45));

        JButton dashboardBtn = createMenuButton("Dashboard");
        JButton addBtn = createMenuButton("Add Member");
        JButton viewBtn = createMenuButton("View Members");
        JButton searchBtn = createMenuButton("Search Member");
        JButton deleteBtn = createMenuButton("Delete Member");
        JButton plansBtn = createMenuButton("Membership Plans");
        JButton exitBtn = createMenuButton("Exit");

        menu.add(dashboardBtn);
        menu.add(addBtn);
        menu.add(viewBtn);
        menu.add(searchBtn);
        menu.add(deleteBtn);
        menu.add(plansBtn);
        menu.add(exitBtn);

        mainPanel.add(menu, BorderLayout.WEST);

        // ================= CENTER =================
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);

        // Dashboard cards
        JPanel cards = new JPanel(new GridLayout(1, 3, 15, 15));
        cards.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        cards.setBackground(Color.WHITE);

        totalMembersLabel = new JLabel("0", SwingConstants.CENTER);
        JLabel activeLabel = new JLabel("0", SwingConstants.CENTER);
        JLabel plansLabel = new JLabel("3", SwingConstants.CENTER);

        cards.add(createCard("TOTAL MEMBERS", totalMembersLabel));
        cards.add(createCard("ACTIVE MEMBERS", activeLabel));
        cards.add(createCard("PLANS", plansLabel));

        centerPanel.add(cards, BorderLayout.NORTH);

        // ================= TABLE =================
        String[] columns = {
                "ID", "Name", "Age", "Gender",
                "Phone", "Plan", "Fee", "Status"
        };

        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);

        table.setRowHeight(28);
        table.setFont(new Font("Arial", Font.PLAIN, 13));
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 13));

        JScrollPane scrollPane = new JScrollPane(table);

        centerPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        add(mainPanel);

        // ================= BUTTON ACTIONS =================

        dashboardBtn.addActionListener(e -> showDashboard());

        addBtn.addActionListener(e -> addMemberDialog());

        viewBtn.addActionListener(e -> refreshTable());

        searchBtn.addActionListener(e -> searchMember());

        deleteBtn.addActionListener(e -> deleteMember());

        plansBtn.addActionListener(e -> showPlans());

        exitBtn.addActionListener(e -> {
            int result = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );

            if (result == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }

    // ================= MENU BUTTON =================

    private JButton createMenuButton(String text) {

        JButton button = new JButton(text);

        button.setForeground(Color.WHITE);
        button.setBackground(new Color(55, 55, 55));
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);

        return button;
    }

    // ================= DASHBOARD CARD =================

    private JPanel createCard(String title, JLabel value) {

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(235, 235, 235));

        JLabel heading = new JLabel(title, SwingConstants.CENTER);
        heading.setFont(new Font("Arial", Font.BOLD, 14));

        value.setFont(new Font("Arial", Font.BOLD, 30));

        panel.add(heading, BorderLayout.NORTH);
        panel.add(value, BorderLayout.CENTER);

        return panel;
    }

    // ================= ADD MEMBER =================

    private void addMemberDialog() {

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        idField = new JTextField();
        nameField = new JTextField();
        ageField = new JTextField();
        phoneField = new JTextField();

        genderBox = new JComboBox<>(
                new String[]{"Male", "Female", "Other"}
        );

        planBox = new JComboBox<>(
                new String[]{"Monthly", "Quarterly", "Yearly"}
        );

        statusBox = new JComboBox<>(
                new String[]{"Active", "Inactive"}
        );

        panel.add(new JLabel("Member ID:"));
        panel.add(idField);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        panel.add(new JLabel("Age:"));
        panel.add(ageField);

        panel.add(new JLabel("Gender:"));
        panel.add(genderBox);

        panel.add(new JLabel("Phone:"));
        panel.add(phoneField);

        panel.add(new JLabel("Plan:"));
        panel.add(planBox);

        panel.add(new JLabel("Status:"));
        panel.add(statusBox);

        int result = JOptionPane.showConfirmDialog(
                this,
                panel,
                "Add New Member",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {

            try {

                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderBox.getSelectedItem().toString();
                String phone = phoneField.getText();
                String plan = planBox.getSelectedItem().toString();
                String status = statusBox.getSelectedItem().toString();

                double fee = calculateFee(plan);

                Member member = new Member(
                        id,
                        name,
                        age,
                        gender,
                        phone,
                        plan,
                        fee,
                        status
                );

                members.add(member);

                refreshTable();

                JOptionPane.showMessageDialog(
                        this,
                        "Member added successfully!"
                );

            } catch (Exception ex) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please enter valid information!",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // ================= FEE =================

    private double calculateFee(String plan) {

        if (plan.equals("Monthly")) {
            return 1000;
        } else if (plan.equals("Quarterly")) {
            return 2500;
        } else {
            return 8000;
        }
    }

    // ================= REFRESH TABLE =================

    private void refreshTable() {

        model.setRowCount(0);

        for (Member m : members) {

            model.addRow(new Object[]{
                    m.id,
                    m.name,
                    m.age,
                    m.gender,
                    m.phone,
                    m.plan,
                    "₹" + m.fee,
                    m.status
            });
        }

        totalMembersLabel.setText(
                String.valueOf(members.size())
        );
    }

    // ================= SEARCH =================

    private void searchMember() {

        String search = JOptionPane.showInputDialog(
                this,
                "Enter Member ID or Name:"
        );

        if (search == null || search.trim().isEmpty()) {
            return;
        }

        model.setRowCount(0);

        boolean found = false;

        for (Member m : members) {

            if (String.valueOf(m.id).equalsIgnoreCase(search)
                    || m.name.equalsIgnoreCase(search)) {

                model.addRow(new Object[]{
                        m.id,
                        m.name,
                        m.age,
                        m.gender,
                        m.phone,
                        m.plan,
                        "₹" + m.fee,
                        m.status
                });

                found = true;
            }
        }

        if (!found) {

            JOptionPane.showMessageDialog(
                    this,
                    "Member not found!"
            );
        }
    }

    // ================= DELETE =================

    private void deleteMember() {

        String input = JOptionPane.showInputDialog(
                this,
                "Enter Member ID to delete:"
        );

        if (input == null) {
            return;
        }

        try {

            int id = Integer.parseInt(input);

            boolean removed = false;

            for (int i = 0; i < members.size(); i++) {

                if (members.get(i).id == id) {

                    members.remove(i);
                    removed = true;
                    break;
                }
            }

            if (removed) {

                refreshTable();

                JOptionPane.showMessageDialog(
                        this,
                        "Member deleted successfully!"
                );

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Member not found!"
                );
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid Member ID!"
            );
        }
    }

    // ================= PLANS =================

    private void showPlans() {

        String plans =
                "GYMFIT MEMBERSHIP PLANS\n\n" +
                        "Monthly Plan    : ₹1000\n" +
                        "Quarterly Plan  : ₹2500\n" +
                        "Yearly Plan     : ₹8000\n\n" +
                        "Features:\n" +
                        "• Gym Equipment Access\n" +
                        "• Trainer Support\n" +
                        "• Workout Area\n" +
                        "• Fitness Tracking";

        JOptionPane.showMessageDialog(
                this,
                plans,
                "Membership Plans",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // ================= DASHBOARD =================

    private void showDashboard() {

        refreshTable();

        JOptionPane.showMessageDialog(
                this,
                "Welcome to GYMFIT Gym Management System!"
        );
    }

    // ================= MAIN =================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            GymManagementSystem app =
                    new GymManagementSystem();

            app.setVisible(true);
        });
    }
}
