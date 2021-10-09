
package ShopAssistant;

import Templates.StartingTemplate;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.BorderFactory.createMatteBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class LoginPage extends StartingTemplate
{
    private JLabel headerLabel,usernameLabel,passwordLabel;
    private JTextField usernameTextField;
    private JPasswordField passwordField;
    private JButton loginButton;
    
    /**
     *  Login Page Constructor
     */
    public LoginPage()
    {
        setComponetPanel();
        setLoginFeatures();
    }
    
    /**
     *   Sets Component Panel
     */
    public void setComponetPanel()
    {
        componentPanel.setLayout(new GridLayout(11,1));
 
        headerLabel = new JLabel("User Login");
        headerLabel.setHorizontalAlignment(JLabel.CENTER);
        headerLabel.setFont(new Font("Arial",Font.BOLD,30));
        componentPanel.add(headerLabel);
        
        nullLabel = new JLabel();
        componentPanel.add(nullLabel);
        
        usernameLabel = new JLabel("Username");
        usernameLabel.setForeground(Color.GRAY);
        usernameLabel.setFont(new Font("Arial",Font.BOLD,20));
        componentPanel.add(usernameLabel);
        
        usernameTextField = new JTextField();
        usernameTextField.setFont(new Font("Arial",Font.BOLD,16));
        usernameTextField.setForeground(Color.black);
        usernameTextField.setBorder(createMatteBorder(0,0,2,0,Color.blue));
        componentPanel.add(usernameTextField);
       
        nullLabel = new JLabel();
        componentPanel.add(nullLabel);
        
        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.GRAY);
        passwordLabel.setFont(new Font("Arial",Font.BOLD,20));
        componentPanel.add(passwordLabel);
        
        passwordField = new JPasswordField();
        passwordField.setForeground(Color.black);
        passwordField.setFont(new Font("Arial",Font.BOLD,16));
        passwordField.setBorder(createMatteBorder(0,0,2,0,Color.blue));
        componentPanel.add(passwordField);
        
        nullLabel = new JLabel();
        componentPanel.add(nullLabel);
        nullLabel = new JLabel();
        componentPanel.add(nullLabel);
        
        loginButton = new JButton(loginButtonIcon);
        loginButton.setBorder(null);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setBackground(Color.white);
        componentPanel.add(loginButton);   
    }
    
    /**
     *  Sets Login Features
     */
    public void setLoginFeatures()
    {
        loginButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if("admin".equals(usernameTextField.getText())&&("admin".equals(passwordField.getText())||getPassword().equals(passwordField.getText())))
                {
                    dispose();
                    HomePage frame = new HomePage();
                    frame.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please enter valid username and password");
                }
            }
        });       
    }
}
