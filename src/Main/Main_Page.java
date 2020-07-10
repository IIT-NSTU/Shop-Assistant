
package Main;

import Shop_Assistant.Login_Page;
import Templates.Starting_Template;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import static javax.swing.BorderFactory.createMatteBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class Main_Page extends Starting_Template
{
    private JLabel welcome_label,project_name_label,team_name_label,progress_label;
    private JProgressBar progressbar;
    
    
    Main_Page()
    {
        setComponentPanel();
    }
    
    public void setComponentPanel()
    {
        component_panel.setLayout(new GridLayout(12,1));

        null_label = new JLabel();
        component_panel.add(null_label); 
        
        welcome_label = new JLabel("Welcome To");
        welcome_label.setForeground(Color.BLACK);
        welcome_label.setHorizontalAlignment(JLabel.CENTER);
        welcome_label.setFont(new Font("Arial",Font.BOLD,18));
        component_panel.add(welcome_label);
        
        
        null_label = new JLabel();
        component_panel.add(null_label);
        
        project_name_label = new JLabel("SHOP MANAGEMENT SYSTEM");
        project_name_label.setHorizontalAlignment(JLabel.CENTER);
        project_name_label.setForeground(Color.BLACK);
        project_name_label.setFont(new Font("Arial",Font.BOLD,22));
        component_panel.add(project_name_label);
        
        team_name_label = new JLabel("By Team Xenon");
        team_name_label.setForeground(Color.BLACK);
        team_name_label.setHorizontalAlignment(JLabel.RIGHT);
        team_name_label.setFont(new Font("Arial",Font.BOLD,14));
        component_panel.add(team_name_label);
            
        null_label = new JLabel();
        component_panel.add(null_label);    
        null_label = new JLabel();
        component_panel.add(null_label);
        
        progressbar = new JProgressBar();
        progressbar.setBackground(Color.WHITE);
        progressbar.setForeground(Color.BLUE);
        progressbar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        progressbar.setBorder(createMatteBorder(0,0,20,0,Color.WHITE));
        component_panel.add(progressbar);
        
        progress_label = new JLabel("0%");
        progress_label.setForeground(Color.BLACK);
        progress_label.setHorizontalAlignment(JLabel.CENTER);
        progress_label.setFont(new Font("Arial",Font.BOLD,16));
        component_panel.add(progress_label); 
        
        null_label = new JLabel();
        component_panel.add(null_label); 
        null_label = new JLabel();
        component_panel.add(null_label);  
    }
    
    public static void main(String[] args) 
    {
        Main_Page frame = new Main_Page();
        frame.setUndecorated(true);
        frame.setSize(870,450);
        frame.setVisible(true); 
       
        try{
        
        for(int i = 0; i <= 18 ; i++)
        {
            Thread.sleep(80);
            frame.progress_label.setText(i+"%");
            frame.progressbar.setValue(i);
        }
        
        for(int i = 18; i <= 87 ; i++)
        {
            Thread.sleep(20);
            frame.progress_label.setText(i+"%");
            frame.progressbar.setValue(i);
        }
        
        for(int i = 87; i <= 100 ; i++)
        {
            Thread.sleep(100);
            frame.progress_label.setText(i+"%");
            frame.progressbar.setValue(i);
        }
        
        Thread.sleep(500);
        
        }catch(Exception e){System.out.println(e);}
            
        frame.dispose();
        Login_Page login_frame = new Login_Page();
        login_frame.setVisible(true);
    }     
}
