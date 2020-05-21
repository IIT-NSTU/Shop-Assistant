
package Templates;

import Shop_Assistant.Add_Cost_Page;
import Shop_Assistant.Create_Purchase_Invoice_Page;
import Shop_Assistant.Create_Sales_Invoice_Page;
import Shop_Assistant.Daily_Income_Cost_Page;
import Shop_Assistant.Due_Check_Page;
import Shop_Assistant.Home_Page;
import Shop_Assistant.Login_Page;
import Shop_Assistant.Settings_Page;
import Shop_Assistant.Staff_Attendence_Page;
import Shop_Assistant.Stock_Check_Page;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


public class DashBoard_Template extends Frame_Setup 
{
    public JPanel horizontal_panel,vertical_panel;
    public JButton shop_name_button,create_sales_invoice_button,create_purchase_invoice_button,logout_button,home_button,add_cost_button,daily_income_cost_button,stock_check_button,due_check_button,staff_attendence_button,settings_button;
    
    
    public JButton horizontal_panel_buttons[] = {shop_name_button,create_sales_invoice_button,create_purchase_invoice_button,logout_button};
    public JButton vertical_panel_buttons[] = {home_button,add_cost_button,daily_income_cost_button,stock_check_button,due_check_button,staff_attendence_button,settings_button};
    
    
    public DashBoard_Template()
    {
        setContainer();
        setPanels();
        setActionListeners();
    }
    
    public void setContainer()
    {
        //Method Overriding Contept Used
        
        super.setContainer();  //Here we get setContainer() of parrent class
        
        container.setLayout(new BorderLayout());
    }
    
    public void setPanels()
    {
        horizontal_panel = new JPanel();
        horizontal_panel.setLayout(new GridLayout(1,3));
        horizontal_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        container.add(horizontal_panel,BorderLayout.NORTH);
        setHorizontal_Panel_Components();
        
        vertical_panel = new JPanel();
        vertical_panel.setLayout(new GridLayout(7,1));
        vertical_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        container.add(vertical_panel,BorderLayout.WEST);
        setVertical_Panel_Components();
    }
    
    public void setHorizontal_Panel_Components()
    {
        String Button_Names[] = {"Shop Name","Create Sales Invoice","Create Purchase Invoice","Log Out"};
        
        for(int i = 0; i < horizontal_panel_buttons.length ; i++)
        {
            horizontal_panel_buttons[i] = new JButton(Button_Names[i]);
            horizontal_panel_buttons[i].setName(Button_Names[i]);
            horizontal_panel_buttons[i].setFont(new Font("Arial",Font.PLAIN,16));
            horizontal_panel_buttons[i].setBackground(Color.DARK_GRAY);
            horizontal_panel_buttons[i].setForeground(Color.WHITE);
            horizontal_panel_buttons[i].setFocusPainted(false);
            horizontal_panel.add(horizontal_panel_buttons[i]);
        }
        
        horizontal_panel_buttons[3].setIcon(logout_button_icon);
    }    
        
    public void setVertical_Panel_Components()
    {
        ImageIcon Icons[] = {home_button_icon,add_cost_button_icon,daily_income_cost_button_icon,stock_check_button_icon,due_check_button_icon,staff_attendence_button_icon,settings_button_icon};
        String Button_Names[] = {"Home","Add Cost","Daily Income Cost","Stock Check","Due Check","Staff Attendence","Settings"};
        
        for(int i = 0 ; i < vertical_panel_buttons.length ; i++)
        {
            vertical_panel_buttons[i] = new JButton(Button_Names[i]);
            vertical_panel_buttons[i].setName(Button_Names[i]);
            vertical_panel_buttons[i].setFont(new Font("Arial",Font.PLAIN,16));
            vertical_panel_buttons[i].setBackground(Color.DARK_GRAY);
            vertical_panel_buttons[i].setForeground(Color.WHITE);
            vertical_panel_buttons[i].setFocusPainted(false);
            vertical_panel_buttons[i].setIcon(Icons[i]);
            vertical_panel.add(vertical_panel_buttons[i]);
        }
    }
    
    public void setActionListeners()
    {        
        getButton("Create Sales Invoice").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();             
                Create_Sales_Invoice_Page frame = new Create_Sales_Invoice_Page();
                frame.setVisible(true);
            }
        });
         
        getButton("Create Purchase Invoice").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Create_Purchase_Invoice_Page frame = new Create_Purchase_Invoice_Page();
                frame.setVisible(true);  
            }
        }); 
        
        getButton("Log Out").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Login_Page frame = new Login_Page();
                frame.setVisible(true);
            }
        });
        
        getButton("Home").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Home_Page frame = new Home_Page();
                frame.setVisible(true);
            }
        });
        
        getButton("Add Cost").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Add_Cost_Page frame = new Add_Cost_Page();
                frame.setVisible(true);
            }
        });
        
        getButton("Daily Income Cost").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Daily_Income_Cost_Page frame = new Daily_Income_Cost_Page();
                frame.setVisible(true);
            }
        });
        
        
        getButton("Due Check").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Due_Check_Page frame = new Due_Check_Page();
                frame.setVisible(true);
            }
        });
         
        getButton("Stock Check").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Stock_Check_Page frame = new Stock_Check_Page();
                frame.setVisible(true);
            }
        });
        
        getButton("Staff Attendence").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Staff_Attendence_Page frame = new Staff_Attendence_Page();
                frame.setVisible(true);
            }
        });
        
        getButton("Settings").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                dispose();
                Settings_Page frame = new Settings_Page();
                frame.setVisible(true);
            }
        });
         
    }
    
    public JButton getButton(String button_name)
    {
        int i,j; JButton button = new JButton();

        for(i = 0; i < horizontal_panel_buttons.length ; i++)
        {
            if(horizontal_panel_buttons[i].getName().equals(button_name))
            {
                button = horizontal_panel_buttons[i];
                break;
            }
        }
        
        for(j = 0; j < vertical_panel_buttons.length ; j++)
        {   
            if(vertical_panel_buttons[j].getName().equals(button_name))
            {
                button = vertical_panel_buttons[j];
            } 
        }
        
        return button;
    }
    
    public static void main(String[] args) 
    {
        DashBoard_Template frame = new DashBoard_Template();
        frame.setVisible(true);
    }
}
