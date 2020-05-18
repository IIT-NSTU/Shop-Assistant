
package Templates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class Starting_Template extends Frame_Setup
{
    public JPanel main_panel,picture_panel,component_panel;
    public JLabel picture_label,null_label;
    
    
    public Starting_Template()
    {  
        setContainer();
        setPanels();
        setPicturePanel();
    }
    
    
    public void setContainer()
    {
        //Method Overriding Contept Used
        
        super.setContainer();  //Here we get setContainer() of parrent class
        
        container.setLayout(new BorderLayout(100,30));
        container.setBackground(Color.WHITE);
    }
    
    
    public void setPanels()
    { 
        null_label = new JLabel();
        container.add(null_label,BorderLayout.EAST);
        
        null_label = new JLabel();
        container.add(null_label,BorderLayout.WEST);
        
        null_label = new JLabel();
        container.add(null_label,BorderLayout.NORTH);
        
        null_label = new JLabel();
        container.add(null_label,BorderLayout.SOUTH);
         
        
        main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(1,2));
        container.add(main_panel,BorderLayout.CENTER);
        
        
        picture_panel = new JPanel();
        picture_panel.setBackground(Color.WHITE);
        main_panel.add(picture_panel);
        
        
        component_panel = new JPanel();
        component_panel.setBackground(Color.WHITE);
        main_panel.add(component_panel);
    }
    

    public void setPicturePanel()
    {        
        picture_label = new JLabel(display_picture);
        picture_panel.add(picture_label);
    }   
}
