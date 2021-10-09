
package Main;

import ShopAssistant.LoginPage;
import Templates.StartingTemplate;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import static javax.swing.BorderFactory.createMatteBorder;
import javax.swing.JLabel;
import javax.swing.JProgressBar;

public class MainPage extends StartingTemplate
{
    public JLabel welcomeLabel,projectNameLabel,teamNameLabel,progressLabel;
    public JProgressBar progressbar;
    
    /**
     *  Main Page Constructor
     */
    MainPage()
    {
        setComponentPanel();
    }
    
    /**
     *  Sets Component Panel
     */
    public void setComponentPanel()
    {
        componentPanel.setLayout(new GridLayout(12,1));

        nullLabel = new JLabel();
        componentPanel.add(nullLabel); 
        
        welcomeLabel = new JLabel("Welcome To");
        welcomeLabel.setForeground(Color.BLACK);
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial",Font.BOLD,18));
        componentPanel.add(welcomeLabel);
        
        
        nullLabel = new JLabel();
        componentPanel.add(nullLabel);
        
        projectNameLabel = new JLabel("SHOP MANAGEMENT SYSTEM");
        projectNameLabel.setHorizontalAlignment(JLabel.CENTER);
        projectNameLabel.setForeground(Color.BLACK);
        projectNameLabel.setFont(new Font("Arial",Font.BOLD,22));
        componentPanel.add(projectNameLabel);
        
        teamNameLabel = new JLabel("By Team Xenon");
        teamNameLabel.setForeground(Color.BLACK);
        teamNameLabel.setHorizontalAlignment(JLabel.RIGHT);
        teamNameLabel.setFont(new Font("Arial",Font.BOLD,14));
        componentPanel.add(teamNameLabel);
            
        nullLabel = new JLabel();
        componentPanel.add(nullLabel);    
        nullLabel = new JLabel();
        componentPanel.add(nullLabel);
        
        progressbar = new JProgressBar();
        progressbar.setBackground(Color.WHITE);
        progressbar.setForeground(Color.BLUE);
        progressbar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        progressbar.setBorder(createMatteBorder(0,0,20,0,Color.WHITE));
        componentPanel.add(progressbar);
        
        progressLabel = new JLabel("0%");
        progressLabel.setForeground(Color.BLACK);
        progressLabel.setHorizontalAlignment(JLabel.CENTER);
        progressLabel.setFont(new Font("Arial",Font.BOLD,16));
        componentPanel.add(progressLabel); 
        
        nullLabel = new JLabel();
        componentPanel.add(nullLabel); 
        nullLabel = new JLabel();
        componentPanel.add(nullLabel);  
    }
    
    
    /**
     * @param args 
     */
    public static void main(String[] args) 
    {
        MainPage frame = new MainPage();
        frame.setUndecorated(true);
        frame.setSize(870,450);
        frame.setVisible(true); 
       
        try{
        
        for(int i = 0; i <= 18 ; i++)
        {
            Thread.sleep(80);
            frame.progressLabel.setText(i+"%");
            frame.progressbar.setValue(i);
        }
        
        for(int i = 18; i <= 87 ; i++)
        {
            Thread.sleep(20);
            frame.progressLabel.setText(i+"%");
            frame.progressbar.setValue(i);
        }
        
        for(int i = 87; i <= 100 ; i++)
        {
            Thread.sleep(100);
            frame.progressLabel.setText(i+"%");
            frame.progressbar.setValue(i);
        }
        
        Thread.sleep(500);
        
        }catch(Exception e){System.out.println(e);}
            
        frame.dispose();
        LoginPage loginFrame = new LoginPage();
        loginFrame.setVisible(true);
    }     
}
