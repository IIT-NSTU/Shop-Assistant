
package Templates;


import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Frame_Setup extends JFrame 
{
    Container container;
   
    ImageIcon display_picture = new ImageIcon(this.getClass().getResource("/Pictures/display_picture.png"));
    
    
    Frame_Setup()
    {
        setFrame();
        setContainer();
        setAppIcon();
    }
    
    public void setFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("SHOP ASSISTANT");
    }
    
    public void setContainer()
    {
        container = this.getContentPane();
    }
    
    
    public void setAppIcon()
    {
        ImageIcon logo = new ImageIcon(this.getClass().getResource("/Pictures/shopping-cart.png"));
        this.setIconImage(logo.getImage());
    }
    
}
