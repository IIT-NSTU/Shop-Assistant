
package Templates;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class StartingTemplate extends FrameSetup
{
    public JPanel mainPanel,picturePanel,componentPanel;
    public JLabel pictureLabel,nullLabel;
    
    /**
     *  Starting Template Constructor
     */
    public StartingTemplate()
    {  
        setContainer();
        setPanels();
        setPicturePanel();
    }
    
    /**
     *  Sets Container
     */
    public void setContainer()
    {
        //Method Overriding Contept Used
        
        super.setContainer();  //Here we get setContainer() of parrent class
        
        container.setLayout(new BorderLayout(100,30));
    }
    
    /**
     *  Sets Panels
     */
    public void setPanels()
    { 
        nullLabel = new JLabel();
        container.add(nullLabel,BorderLayout.EAST);
        
        nullLabel = new JLabel();
        container.add(nullLabel,BorderLayout.WEST);
        
        nullLabel = new JLabel();
        container.add(nullLabel,BorderLayout.NORTH);
        
        nullLabel = new JLabel();
        container.add(nullLabel,BorderLayout.SOUTH);
         
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,2));
        container.add(mainPanel,BorderLayout.CENTER);
         
        picturePanel = new JPanel();
        picturePanel.setBackground(Color.WHITE);
        mainPanel.add(picturePanel);
         
        componentPanel = new JPanel();
        componentPanel.setBackground(Color.WHITE);
        mainPanel.add(componentPanel);
    }
    
    /**
     * Sets Picture Panel
     */
    public void setPicturePanel()
    {        
        pictureLabel = new JLabel(displayPicture);
        picturePanel.add(pictureLabel);
    }   
}
