
package Templates;

import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class Frame_Setup extends JFrame 
{
    public Container container;
    public JLabel null_label;
    
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
    public ImageIcon  clear_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/clear_button_icon.png"));
    public ImageIcon  add_to_cart_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/add_to_cart_button_icon.png"));
    public ImageIcon  print_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/print_button_icon.png"));
    public ImageIcon  delete_button_icon = new ImageIcon(this.getClass().getResource("/Pictures/delete_button_icon.png"));
    
    
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
    
    public String setDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy E hh:mm:ss a");
        Date date = new Date();
        return sdf.format(date);
    }
    
    public String[] setProductTypes()
    {
        String product_type[] = new String[80];
        
        try{                    
                BufferedReader br = new BufferedReader(new FileReader("Product_Type_&_Model_List.txt"));
                String s;
                int i = 1;
                product_type[0]="";
                while((s=br.readLine())!=null)
                {
                    product_type[i] = s.substring(0, s.indexOf(","));
                    i++;
                }
                
                int p = i;
                
                while(p<80)
                {
                    product_type[p] = "";
                    p++;
                }
                
            }catch(Exception ex){}
        
        return product_type;
    }
   
    public boolean isDigit(char character)
    {
       if(character!='0'&&character!='1'&&character!='2'&&character!='3'&&character!='4'&&character!='5'&&character!='6'&&character!='7'&&character!='8'&&character!='9'&&character!=(char)8&&character!=(char)10)
       {
           return false;
       }
       else
       {
           return true;
       }
    }
    
    public void setReminder()
    {
        JOptionPane.showMessageDialog(null, "Remember to Print");
    }
    
    public int getRemainingQuantity(String model_number,String filename)
    {
        boolean found = false;
        int model_count = 0,quantity_string_index;
        
        if(model_number==null||"".equals(model_number))
        {
            return 0;
        } 
        else
        {
        /*try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            int s = 0;
            String text="";
            
            while((s=br.read())!=-1)
            {
                text = text + (char)s;
            }
            
            Pattern pattern = Pattern.compile(model_number);
            Matcher matcher = pattern.matcher(text);
            
            while(matcher.find())
            {                
                String demo_text = text.substring(matcher.end()+1, matcher.end()+10);             
                
                String result = demo_text.substring(0, demo_text.indexOf(","));
                
                model_count = model_count+Integer.parseInt(result);               
                found = true;
            }
      
            }catch(Exception e){}
            
            if(!found)
            {
                return 0;
            }
            else
            {
                return model_count;
            }*/
                    
            try{
                
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String s;
                
                while((s=br.readLine())!=null)
                {
                    while(s.contains(model_number))
                    {
                        //if(s.contains(model_number))
                        //{
                            quantity_string_index = s.indexOf(model_number) + model_number.length() + 1;
                            String demo_string = s.substring(quantity_string_index, quantity_string_index+10);
                            model_count = model_count + Integer.parseInt(demo_string.substring(0, demo_string.indexOf(",")));
                            s = s.substring(s.indexOf(model_number) + model_number.length() + 1, s.length());
                       // }
                    }
                }
    
            }catch(Exception e) {}
              
        return model_count;
        
        }
    }
    
    public int getRemainingQuantityUsingDate(String date,String model_number,String filename)
    {
        int model_count = 0,quantity_string_index;
        
        if(model_number==null||"".equals(model_number))
        {
            return 0;
        } 
        else
        {    
            try{
                
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String s;
                
                while((s=br.readLine())!=null)
                {                    
                    if(s.contains(date))
                    {             
                        while(s.contains(model_number))
                        {
                            quantity_string_index = s.indexOf(model_number) + model_number.length() + 1;
                            String demo_string = s.substring(quantity_string_index, quantity_string_index+10);
                            model_count = model_count + Integer.parseInt(demo_string.substring(0, demo_string.indexOf(",")));
                            s = s.substring(s.indexOf(model_number) + model_number.length() + 1, s.length());  
                        }
                    }
                }
    
            }catch(Exception e) {}
              
        return model_count;
        
        }
    }
    
    
    public int getTotalStock(String model_number,String purchase_filename,String sales_filename)
    {  
        int product_sold = getRemainingQuantity(model_number,sales_filename);
        int product_purchased = getRemainingQuantity(model_number,purchase_filename);
        
        return (product_purchased-product_sold);
    }
    
    public String getPassword()
    {
        String password = "";
        
        try{
            
            BufferedReader br = new BufferedReader(new FileReader("User_Database.txt"));   
            String s = br.readLine();
            password = s.substring(9,s.length());
            br.close();        
            
        }catch(Exception e) {System.out.println(e);}
        
        return password;
    }
    
    
}
