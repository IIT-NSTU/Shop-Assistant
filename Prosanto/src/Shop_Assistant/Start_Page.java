
package Shop_Assistant;

import Templates.Starting_Template;
import java.awt.Color;

public class Start_Page extends Starting_Template
{
    Start_Page()
    {
        setComponentPanel();
    }
    
    public void setComponentPanel()
    {
        component_panel.setBackground(Color.yellow);  
    }
    
    public static void main(String[] args) 
    {
        Start_Page frame = new Start_Page();
        frame.setVisible(true);   
    } 
      
}

