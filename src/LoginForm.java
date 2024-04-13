import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

public class LoginForm extends JFrame {
    // global variables
    final private Font mainFont = new Font(null, Font.BOLD, 18);
    JTextField tfEmail;
    JPasswordField pfPassword;

    public void initalize() { // initalize the form
        // create labels and text fields
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

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(mainFont);
        btnLogin.addActionListener(new ActionListener() {
            
            // event listener for the login button
            @Override
            public void actionPerformed(ActionEvent e) {
                // read email and password
                String email = tfEmail.getText();
                String password = String.valueOf(pfPassword.getPassword());
                
                // if user is found return data of that user. Otherwise return null.
                User user = getAuthenticatedUser(email, password);

                if (user!= null) { // check if user data is found
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.initalize(user);
                    dispose();
                }
                else { // display error message
                    JOptionPane.showMessageDialog(LoginForm.this,
                    "Email or Password Invalid",
                    "Try again",
                    JOptionPane.ERROR_MESSAGE);
                }

            }
        });

        // create cancel button
        JButton btnCancel = new JButton("Cancel");
        btnCancel.setFont(mainFont);

        // add listener for cancel button
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        // create buttons panel
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new GridLayout(1,2,10,0));

        // add buttons to panel
        buttonsPanel.add(btnLogin);
        buttonsPanel.add(btnCancel);

        // initalize the frame
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(formPanel, BorderLayout.NORTH);
        mainPanel.add(buttonsPanel, BorderLayout.SOUTH);

        setTitle("Login Form");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // close form
        setSize(400,500); // set window size
        setMinimumSize(new Dimension(350,400)); // set window dimensions
        setLocationRelativeTo(null); // center window
        setContentPane(mainPanel); // set form panel as content pane
        setVisible(true); // make frame visible
        
    }

    private User getAuthenticatedUser(String email, String password) {
        User user = null;

        // connect to db
        final String DB_URL = "jdbc:mysql://localhost:3306/loginusers";
        final String USERNAME = "root";
        final String PASSWORD = "";

        try {
            // establish conn with db
            Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            
            String sql = "SELECT * FROM user WHERE email=? AND password=?";
            // allows to excute sql query
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            // execute statement 
            ResultSet resultSet = preparedStatement.executeQuery();
            
            // create user object if user is found
            if (resultSet.next()) {
                user = new User();
                user.name = resultSet.getString("name");
                user.email = resultSet.getString("email");
                user.phone = resultSet.getString("phone");
                user.address = resultSet.getString("address");
                user.password = resultSet.getString("password");
            }
            // close conn with db
            preparedStatement.close();
            conn.close();
        } catch (Exception e) {
            System.out.println("Database conn failed");
        }

        // return user object
        return user;
    }

    public static void main(String[] args) {
        LoginForm loginForm = new LoginForm();
        loginForm.initalize();
    }
    
}