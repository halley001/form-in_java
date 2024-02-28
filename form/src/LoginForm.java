import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginForm extends JFrame implements ActionListener {

    // Declare the components and variables
    private JLabel usernameLabel, passwordLabel, messageLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, resetButton;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet result;

    String connectionUrl = "jdbc:mysql://127.0.0.1:3306/swing_demo";
    String sqlCreateTable = "SELECT * FROM USERS";


    // Define the constructor
    public LoginForm() {
// Set the title, size, layout and location of the frame
        super("Login Form");
        setSize(400, 300);
        setLayout(new GridLayout(4, 2));
        setLocationRelativeTo(null);

// Initialize the components
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        messageLabel = new JLabel();
        usernameField = new JTextField();
        passwordField = new JPasswordField();
        loginButton = new JButton("Login");
        resetButton = new JButton("Reset");

// Add the components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(loginButton);
        add(resetButton);
        add(messageLabel);

// Add action listeners to the buttons
        loginButton.addActionListener(this);
        resetButton.addActionListener(this);

// Make the frame visible
        setVisible(true);
    }

    // Define the main method
    public static void main(String[] args) {
// Create an object of the Login class
        LoginForm login = new LoginForm();
    }

    // Define the actionPerformed method
    public void actionPerformed(ActionEvent e) {
// Get the source of the event
        Object source = e.getSource();

// If the source is the login button
        if (source == loginButton) {
// Get the username and password from the text fields
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

// Validate the input
            if (username.equals(null) || password.equals(null)) {
// Display an error message
                messageLabel.setText("Please enter username and password");
                messageLabel.setForeground(Color.RED);
            } else {
// Try to connect to the database and verify the credentials
                try {
// Establish the connection with the database
                    connection = DriverManager.getConnection(connectionUrl, "root", "Password123.");

// Prepare the SQL query
                    statement = connection.prepareStatement(sqlCreateTable);

                    // Execute the query and get the result
                    result = statement.executeQuery();

// Set the parameters


// Check if the result is not empty
                    if (result.next()) {
                        int id = result.getInt("id");
                        String user_name = result.getString("username");
                        String user_password = result.getString("password");

                        //Check if credentials match that on database
                    if(username.equals(user_name) && password.equals(user_password))
                    {
                        // Display a success message
                        messageLabel.setText("Login successful");
                        messageLabel.setForeground(Color.GREEN);
                        // Close the current frame and open a new frame
                        dispose();
                        new Welcome(username);
                    }else {
// Display an error message
                        messageLabel.setText("Invalid username or password");
                        messageLabel.setForeground(Color.RED);
                    }


                    }

// Close the connection, statement and result
                    connection.close();
                    statement.close();
                    result.close();
                } catch (Exception ex) {
// Display an exception message
                    messageLabel.setText(ex.getMessage());
                    messageLabel.setForeground(Color.RED);
                }
            }
        }

// If the source is the reset button
        if (source == resetButton) {
// Clear the text fields and the message label
            usernameField.setText("");
            passwordField.setText("");
            messageLabel.setText("");
        }
    }
}

// Define the Welcome class that extends JFrame
class Welcome extends JFrame {

    // Declare the components
    private JLabel welcomeLabel;

    // Define the constructor
    public Welcome(String username) {
// Set the title, size, layout and location of the frame
        super("Welcome Form");
        setSize(300, 200);
        setLayout(new FlowLayout());
        setLocationRelativeTo(null);

// Initialize the components
        welcomeLabel = new JLabel("Welcome, " + username);

// Add the components to the frame
        add(welcomeLabel);

// Make the frame visible
        setVisible(true);
    }
}
