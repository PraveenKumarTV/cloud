import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

class InvalidScholarshipAmountException extends Exception {
    public InvalidScholarshipAmountException(String message) {
        super(message);
    }
}

class InvalidIdFormatException extends Exception {
    public InvalidIdFormatException(String message) {
        super(message);
    }
}

public class ScholarshipApplicationForm {

    private JFrame frame;
    private JTextField nameField, emailField, studentIdField, scholarshipAmountField;
    private JTextArea summaryArea;
    private JButton submitButton;

    public ScholarshipApplicationForm() {
        frame = new JFrame("Scholarship Application Form");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);

        JLabel nameLabel = new JLabel("Name:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel studentIdLabel = new JLabel("Student ID:");
        JLabel scholarshipAmountLabel = new JLabel("Scholarship Amount:");

        nameField = new JTextField(20);
        emailField = new JTextField(20);
        studentIdField = new JTextField(20);
        scholarshipAmountField = new JTextField(20);

        summaryArea = new JTextArea(5, 30);
        summaryArea.setEditable(false);

        submitButton = new JButton("Submit");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(emailLabel);
        frame.add(emailField);
        frame.add(studentIdLabel);
        frame.add(studentIdField);
        frame.add(scholarshipAmountLabel);
        frame.add(scholarshipAmountField);
        frame.add(submitButton);
        frame.add(summaryArea);

        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleSubmit();
            }
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void handleSubmit() {
        String name = nameField.getText();
        String email = emailField.getText();
        String studentId = studentIdField.getText();
        String scholarshipAmountStr = scholarshipAmountField.getText();

        try {
            if (name.isEmpty() || email.isEmpty() || studentId.isEmpty() || scholarshipAmountStr.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double scholarshipAmount = Double.parseDouble(scholarshipAmountStr);
            if (scholarshipAmount < 0) {
                throw new InvalidScholarshipAmountException("Scholarship amount cannot be negative.");
            }

            if (!studentId.startsWith("IT")) {
                throw new InvalidIdFormatException("Student ID must start with 'IT'.");
            }

            String summary = "Application Summary:\n" +
                    "Name: " + name + "\n" +
                    "Email: " + email + "\n" +
                    "Student ID: " + studentId + "\n" +
                    "Scholarship Amount: " + scholarshipAmount;

            summaryArea.setText(summary);
            saveToFile(name, email, studentId, scholarshipAmount);

        }catch (InvalidScholarshipAmountException | InvalidIdFormatException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void saveToFile(String name, String email, String studentId, double scholarshipAmount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("students.txt", true))) {
            writer.write(studentId + "," + name + "," + email + "," + scholarshipAmount);
            writer.newLine();
            JOptionPane.showMessageDialog(frame, "Application submitted successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving data", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new ScholarshipApplicationForm();
    }
}
