import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class exp11a {

    private JFrame frame;
    private JTextField nameField, addressField;
    private JComboBox<String> itemComboBox;
    private JTextArea orderSummary;
    private JButton submitButton;

    public exp11a() {
        frame = new JFrame("Online Shopping Application");
        frame.setLayout(new FlowLayout());
        frame.setSize(400, 300);

        JLabel nameLabel = new JLabel("Name:");
        JLabel addressLabel = new JLabel("Address:");
        JLabel itemLabel = new JLabel("Select Item:");

        nameField = new JTextField(20);
        addressField = new JTextField(20);

        String[] items = {"Laptop", "Smartphone", "Tablet", "Headphones"};
        itemComboBox = new JComboBox<>(items);

        orderSummary = new JTextArea(5, 30);
        orderSummary.setEditable(false);

        submitButton = new JButton("Submit");

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(addressLabel);
        frame.add(addressField);
        frame.add(itemLabel);
        frame.add(itemComboBox);
        frame.add(submitButton);
        frame.add(orderSummary);

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
        String address = addressField.getText();
        String item = itemComboBox.getSelectedItem().toString();

        if (name.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            String orderDetails = "Order Summary:\n" +
                    "Name: " + name + "\n" +
                    "Address: " + address + "\n" +
                    "Item: " + item;
            orderSummary.setText(orderDetails);
        }
    }

    public static void main(String[] args) {
        new exp11a();
    }
}
