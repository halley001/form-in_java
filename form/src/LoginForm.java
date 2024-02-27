import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm extends JFrame implements ActionListener {
    private JPanel panel, panel1;
    private JLabel userLabel;
    private JLabel passwordLabel;
    private JTextField userName;
    private JPasswordField userPassword;
    private JButton submitButton;
    private JButton resetButton;


    //Constructor to initialize the component and add them to frame
    public LoginForm() {
        //Set the tittle of the frame
        super("Login Form");

        //Set the size and Layout of the frame
        setSize(450, 400);
        setLayout(new FlowLayout());

        //Set panel and set its layout
        panel = new JPanel();
        panel1 = new JPanel();
        panel.setLayout(new GridLayout(3, 2));

        //create labels and text feilds
        userLabel = new JLabel("Enter username");
        passwordLabel = new JLabel("Enter password");
        userName = new JTextField();
        userName.setSize(400,100);
        userName.setAlignmentX(Component.CENTER_ALIGNMENT);

        userPassword = new JPasswordField();
        userPassword.setSize(200,100);
        userPassword.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Create the button and Action Listeners
        submitButton = new JButton("Submit");
        submitButton.setSize(100,50);
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        submitButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.setSize(1000,50);
        resetButton.setAlignmentX(Component.CENTER_ALIGNMENT);
//        resetButton.addAncestorListener(this);

        //Add to pannel
        panel.add(userLabel);
        panel.add(passwordLabel);
        panel.add(userName);
        panel.add(userPassword);
        panel.add(submitButton);
        panel.add(resetButton);

        // Add panel to frame
        add(panel, BorderLayout.CENTER);

        //Add visibility and default exit on close
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //get object source of event
        Object source = e.getSource();

        //If the login button is clicked, get the credentials and display the message
        if (source == submitButton) {
            String username = userName.getText();
            String password = new String(userPassword.getPassword());

            if (username.equals("admin") && password.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login Successfully");
            } else {
                JOptionPane.showMessageDialog(this, "Please enter correct credentials");
            }

        }

        if (source == resetButton) {
            userName.setText("");
            userPassword.setText("");
        }

    }


    public static void main(String[] args) {
        LoginForm form = new LoginForm();


    }
}