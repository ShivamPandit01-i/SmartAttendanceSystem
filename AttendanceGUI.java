import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AttendanceGUI {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}

class LoginFrame extends JFrame {
    JTextField userField;
    JPasswordField passField;

    LoginFrame() {
        setTitle("Login - Smart Attendance System");
        setSize(300, 180);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        userField = new JTextField();
        panel.add(userField);

        panel.add(new JLabel("Password:"));
        passField = new JPasswordField();
        panel.add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.addActionListener(e -> {
            String user = userField.getText();
            String pass = new String(passField.getPassword());
            if (user.equals("admin") && pass.equals("1234")) {
                dispose();
                new DashboardFrame();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials");
            }
        });

        panel.add(new JLabel());
        panel.add(loginBtn);
        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }
}

class DashboardFrame extends JFrame {
    DashboardFrame() {
        setTitle("Smart Attendance Dashboard");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton btnStart = new JButton("Start Face Recognition");
        btnStart.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Launching camera for face recognition...");
        });

        JButton btnTrain = new JButton("Train Faces");
        btnTrain.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Starting training...");
        });

        add(btnStart);
        add(btnTrain);
        setVisible(true);
    }
}