import java.awt.*;
import javax.swing.*;


public class MainFrame extends JFrame{
    public void initalize(User user) {
        // create info panel
        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new GridLayout(0,2,5,5));

        // add elements to panel
        infoPanel.add(new JLabel("Name"));
        infoPanel.add(new JLabel(user.name));
        infoPanel.add(new JLabel("Email"));
        infoPanel.add(new JLabel(user.email));
        infoPanel.add(new JLabel("Phone"));
        infoPanel.add(new JLabel(user.phone));
        infoPanel.add(new JLabel("Address"));
        infoPanel.add(new JLabel(user.address));





        // set window constants.
        setTitle("DashBoard");
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setSize(1110, 650);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
}
