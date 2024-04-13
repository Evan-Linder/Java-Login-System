import java.awt.*;


import javax.swing.*;

public class LoginForm extends JFrame {
    // global variables
    final private Font mainFont = new Font(null, Font.BOLD, 18);
    JTextField tfEmail;
    JPasswordField pfPassword;

    public void initalize() { // initalize the form
        // form panel
        JLabel lbloginForm = new JLabel("Login Form", SwingConstants.CENTER);
        lbloginForm.setFont(mainFont);

        JLabel lbEmail = new JLabel("Email");
        lbEmail.setFont(mainFont);

        tfEmail = new JTextField();
        tfEmail.setFont(mainFont);

        JLabel lbPassword = new JLabel("Password");
        lbPassword.setFont(mainFont);

        pfPassword = new JPasswordField();
        pfPassword.setFont(mainFont);

        // create form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(0,1,10,10));
        // add elements to panel
        formPanel.add(lbloginForm);
        formPanel.add(lbEmail);
        formPanel.add(tfEmail);
        formPanel.add(lbPassword);
        formPanel.add(pfPassword);


        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // close form
        setSize(400,500); // set window size
        setMinimumSize(new Dimension(350,400)); // set window dimensions
        setLocationRelativeTo(null); // center window
        setContentPane(formPanel); // set form panel as content pane
        setVisible(true); // make frame visible
        
    }

public static void main(String[] args) {
    LoginForm loginForm = new LoginForm();
    loginForm.initalize();
    }
}