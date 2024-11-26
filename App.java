import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Custom exceptions
class InvalidDonationAmountException extends Exception {
    public InvalidDonationAmountException(String message) {
        super(message);
    }
}

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

// Define Displayable interface
interface Displayable {
    void displayInfo();
}

class Person implements Displayable {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void displayInfo() {
        System.out.println("Name: " + name);
    }
}

class Student extends Person {
    private String email;
    private int graduationYear;
    private String studentId;
    private double gpa;
    private double scholarshipAmount;

    public Student(String name, String email, int graduationYear, String studentId, double gpa, double scholarshipAmount) throws InvalidScholarshipAmountException, InvalidIdFormatException {
        super(name);
        if (scholarshipAmount < 0) {
            throw new InvalidScholarshipAmountException("Scholarship amount cannot be negative.");
        }
        if (!studentId.startsWith("IT")) {
            throw new InvalidIdFormatException("Student ID must start with 'IT'.");
        }
        this.email = email;
        this.graduationYear = graduationYear;
        this.studentId = studentId;
        this.gpa = gpa;
        this.scholarshipAmount = scholarshipAmount;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Email: " + email);
        System.out.println("Graduation Year: " + graduationYear);
        System.out.println("GPA: " + gpa);
        System.out.println("Scholarship Amount: " + scholarshipAmount);
    }

    public String getStudentId() {
        return studentId;
    }

    public String getStudentDetails() {
        return "Name: " + getName() + "\nEmail: " + email + "\nGraduation Year: " + graduationYear + "\nGPA: " + gpa + "\nScholarship Amount: " + scholarshipAmount + "\n";
    }
}

class Donor extends Person {
    private double donationAmount;

    public Donor(String name, double donationAmount) throws InvalidDonationAmountException {
        super(name);
        if (donationAmount < 0) {
            throw new InvalidDonationAmountException("Donation amount cannot be negative.");
        }
        this.donationAmount = donationAmount;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Donation Amount: " + donationAmount);
    }

    public String getDonorDetails() {
        return "Name: " + getName() + "\nDonation Amount: " + donationAmount + "\n";
    }
}

class AlumniEvent {
    private String eventName;
    private String date;
    private String location;
    private Student[] attendees;
    private int maxAttendees;
    private int attendeeCount;

    public AlumniEvent(String eventName, String date, String location, int maxAttendees) {
        this.eventName = eventName;
        this.date = date;
        this.location = location;
        this.maxAttendees = maxAttendees;
        this.attendees = new Student[maxAttendees];
        this.attendeeCount = 0;
    }

    public boolean addAttendee(Student student) {
        if (attendeeCount < maxAttendees) {
            attendees[attendeeCount] = student;
            attendeeCount++;
            return true;
        }
        return false; // Event is full
    }

    public void displayEventDetails() {
        System.out.println("Event Name: " + eventName);
        System.out.println("Date: " + date);
        System.out.println("Location: " + location);
        System.out.println("Attendees:");
        for (int i = 0; i < attendeeCount; i++) {
            attendees[i].displayInfo(); // Polymorphic call
        }
    }

    public String getEventDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Event Name: " + eventName + "\nDate: " + date + "\nLocation: " + location + "\nAttendees:\n");
        for (int i = 0; i < attendeeCount; i++) {
            details.append(attendees[i].getStudentDetails() + "\n");
        }
        return details.toString();
    }
}

class ScholarshipManager {
    private String[] studentIds = new String[10];
    private String[] studentNames = new String[10];
    private Student[] students = new Student[10];
    private String[] donorNames = new String[5];
    private Donor[] donors = new Donor[5];
    private int studentCount = 0;
    private int donorCount = 0;

    public void addStudent(Student s) {
        if (studentCount < students.length) {
            students[studentCount] = s;
            studentIds[studentCount] = s.getStudentId();
            studentNames[studentCount] = s.getName();
            studentCount++;
        }
    }

    public void addDonor(Donor d) {
        if (donorCount < donors.length) {
            donors[donorCount] = d;
            donorNames[donorCount] = d.getName();
            donorCount++;
        }
    }

    public Student getStudent(String id) {
        for (int i = 0; i < studentCount; i++) {
            if (studentIds[i].equals(id)) {
                return students[i];
            }
        }
        return null;
    }

    public Donor getDonor(String name) {
        for (int i = 0; i < donorCount; i++) {
            if (donorNames[i].equals(name)) {
                return donors[i];
            }
        }
        return null;
    }
}

public class App {
    private static ScholarshipManager manager;
    private static AlumniEvent alumniEvent;

    public static void main(String[] args) {
        manager = new ScholarshipManager();
        alumniEvent = new AlumniEvent("2024 Alumni Reunion", "Sep 25, 2024", "KK Auditorium", 10);

        try {
            // Adding multiple students and donors
            Student student1 = new Student("Praveen", "praveen@gmail.com", 2020, "IT001", 9.8, 1000.00);
            Student student2 = new Student("Raju", "raju@gmail.com", 2021, "IT002", 9.9, 1500.00);
            manager.addStudent(student1);
            manager.addStudent(student2);

            Donor donor1 = new Donor("96Reblend", 5000);
            Donor donor2 = new Donor("TechCompany", 10000);
            manager.addDonor(donor1);
            manager.addDonor(donor2);

            alumniEvent.addAttendee(student1);
            alumniEvent.addAttendee(student2);

            // Set up the GUI
            JFrame frame = new JFrame("Scholarship Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);

            // Create the main panel with GridBagLayout
            JPanel panel = new JPanel();
            panel.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.fill = GridBagConstraints.HORIZONTAL;

            // Create components
            JLabel studentIdLabel = new JLabel("Enter Student ID:");
            JTextField studentIdField = new JTextField(20);
            JLabel donorNameLabel = new JLabel("Enter Donor Name:");
            JTextField donorNameField = new JTextField(20);
            JButton displayStudentButton = new JButton("Display Student Details");
            JButton displayDonorButton = new JButton("Display Donor Details");
            JButton displayEventButton = new JButton("Display Event Details");
            JTextArea resultArea = new JTextArea(10, 40);
            resultArea.setEditable(false);
            JScrollPane scrollPane = new JScrollPane(resultArea);

            // Add components to the GridBagLayout
            gbc.gridx = 0;
            gbc.gridy = 0;
            panel.add(studentIdLabel, gbc);
            gbc.gridx = 1;
            panel.add(studentIdField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 1;
            panel.add(donorNameLabel, gbc);
            gbc.gridx = 1;
            panel.add(donorNameField, gbc);

            gbc.gridx = 0;
            gbc.gridy = 2;
            panel.add(displayStudentButton, gbc);
            gbc.gridx = 1;
            panel.add(displayDonorButton, gbc);

            gbc.gridx = 0;
            gbc.gridy = 3;
            panel.add(displayEventButton, gbc);

            // Set the layout for the frame
            frame.getContentPane().add(panel, BorderLayout.NORTH);
            frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

            // Action for displaying student details
            displayStudentButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String studentId = studentIdField.getText().trim();
                    Student student = manager.getStudent(studentId);
                    if (student != null) {
                        resultArea.setText(student.getStudentDetails());
                    } else {
                        resultArea.setText("Student not found.");
                    }
                }
            });

            // Action for displaying donor details
            displayDonorButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String donorName = donorNameField.getText().trim();
                    Donor donor = manager.getDonor(donorName);
                    if (donor != null) {
                        resultArea.setText(donor.getDonorDetails());
                    } else {
                        resultArea.setText("Donor not found.");
                    }
                }
            });

            // Action for displaying event details
            displayEventButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    resultArea.setText(alumniEvent.getEventDetails());
                }
            });

            // Make frame visible
            frame.setVisible(true);

        } catch (InvalidScholarshipAmountException | InvalidDonationAmountException | InvalidIdFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
