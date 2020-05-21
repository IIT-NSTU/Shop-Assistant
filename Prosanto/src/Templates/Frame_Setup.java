
package Templates;


import java.awt.Color;
import java.awt.Container;
import javax.swing.ImageIcon;
import javax.swing.JFrame;


public class Frame_Setup extends JFrame 
{
    Container container;
   
    
    public ImageIcon  display_picture = new ImageIcon(this.getClass().getResource("/Pictures/display_picture.png"));
    public ImageIcon  login_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/login_button_icon.png"));
    public ImageIcon  shop_name_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/shop_name_button_icon.png"));
    public ImageIcon  create_sales_invoice_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/create_sales_invoice_button_icon.png"));
    public ImageIcon  create_purchase_invoice_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/create_purchase_invoice_button_icon.png"));
    public ImageIcon  logout_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/logout_button_icon.png"));  
    public ImageIcon  home_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/home_button_icon.png"));
    public ImageIcon  add_cost_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/add_cost_button_icon.png"));
    public ImageIcon  daily_income_cost_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/daily_income_cost_button_icon.png"));
    public ImageIcon  stock_check_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/stock_check_button_icon.png"));
    public ImageIcon  due_check_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/due_check_button_icon.png"));
    public ImageIcon  staff_attendence_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/staff_attendence_button_icon.png"));
    public ImageIcon  settings_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/settings_button_icon.png"));
    
    
    public Frame_Setup()
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
        container.setBackground(Color.WHITE);
    }
    
    
    public void setAppIcon()
    {
        ImageIcon logo = new ImageIcon(this.getClass().getResource("/Pictures/shopping-cart.png"));
        this.setIconImage(logo.getImage());
    }
    
}
