
package Shop_Assistant;

import Templates.Starting_Template;
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

public class Login_Page extends Starting_Template
{
    private JLabel header_label,username_label,password_label;
    private JTextField username_textfield;
    private JPasswordField password_field;
    private JButton login_button;
    
    public Login_Page()
    {
        setComponetPanel();
        setActionListeners();
    }
    
    
    public void setComponetPanel()
    {
        component_panel.setLayout(new GridLayout(11,1));
 
        header_label = new JLabel("User Login");
        header_label.setHorizontalAlignment(JLabel.CENTER);
        header_label.setFont(new Font("Arial",Font.BOLD,30));
        component_panel.add(header_label);
        
        null_label = new JLabel();
        component_panel.add(null_label);
        
        username_label = new JLabel("Username");
        username_label.setForeground(Color.GRAY);
        username_label.setFont(new Font("Arial",Font.BOLD,20));
        component_panel.add(username_label);
        
        username_textfield = new JTextField();
        username_textfield.setFont(new Font("Arial",Font.BOLD,16));
        username_textfield.setForeground(Color.black);
        username_textfield.setBorder(createMatteBorder(0,0,2,0,Color.blue));
        component_panel.add(username_textfield);
       
        null_label = new JLabel();
        component_panel.add(null_label);
        
        password_label = new JLabel("Password");
        password_label.setForeground(Color.GRAY);
        password_label.setFont(new Font("Arial",Font.BOLD,20));
        component_panel.add(password_label);
        
        password_field = new JPasswordField();
        password_field.setForeground(Color.black);
        password_field.setFont(new Font("Arial",Font.BOLD,16));
        password_field.setBorder(createMatteBorder(0,0,2,0,Color.blue));
        component_panel.add(password_field);
        
        null_label = new JLabel();
        component_panel.add(null_label);
        null_label = new JLabel();
        component_panel.add(null_label);
        
        login_button = new JButton(login_button_icon);
        login_button.setBorder(null);
        login_button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        login_button.setBackground(Color.white);
        component_panel.add(login_button);   
    }
    
    public void setActionListeners()
    {
        login_button.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if("Prosanto".equals(username_textfield.getText())&&"1234".equals(password_field.getText()))
                {
                    dispose();
                    Home_Page frame = new Home_Page();
                    frame.setVisible(true);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please enter valid username and password");
                    System.out.println(""+username_textfield.getText());
                }
            }
        });       
    }
}
