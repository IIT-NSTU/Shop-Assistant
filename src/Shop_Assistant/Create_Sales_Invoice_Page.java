
package Shop_Assistant;

import Templates.Invoice_Generator_Template;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class Create_Sales_Invoice_Page extends Invoice_Generator_Template
{
    public Create_Sales_Invoice_Page(String keyword, String file_name) 
    {
        super(keyword, file_name);
        setPageButton();
        setQuantityTextFieldListener();
    }
    
    public void setPageButton()
    {
        getButton("Create Sales Invoice").setBackground(Color.LIGHT_GRAY);
        getButton("Create Sales Invoice").setForeground(Color.BLACK);
        getButton("Create Sales Invoice").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    public void setQuantityTextFieldListener()
    {
        quantity_textfield.addKeyListener(new KeyListener()
        { 
            public void keyTyped(KeyEvent ke){}    

            public void keyPressed(KeyEvent ke){}
         
            public void keyReleased(KeyEvent ke) 
            { 
                try{
                int total_stock = getTotalStock((String)model_number_combobox.getSelectedItem(),"Purchase_Database.txt","Sales_Database.txt");
                int given_value = Integer.parseInt(quantity_textfield.getText());

                if(!isDigit(ke.getKeyChar()))
                {    
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits"); 
                    quantity_textfield.setText("");
                }
                
                if(product_type_combobox.getSelectedItem()==""||model_number_combobox.getSelectedItem()=="")
                {
                    JOptionPane.showMessageDialog(null, "Please Select Product Type and Model Number");
                    quantity_textfield.setText("");
                }
                else{
                
                if(total_stock==0)
                {
                    JOptionPane.showMessageDialog(null, "You Have No More Item Left");
                    quantity_textfield.setText("");
                }
                else
                {
                    if((total_stock-given_value)>=0)
                    {
                        JOptionPane.showMessageDialog(null, "You Have "+(total_stock-given_value)+" Item Left");                      
                        setPayment();
                        setDue();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "There Will Not Any Item Left");                                                  
                        quantity_textfield.setText("");
                    }
                }
                }  
                
                }catch(Exception e){}
            }               
        });
    
    }
    
    public static void main(String[] args) 
    {  
        Create_Sales_Invoice_Page frame = new Create_Sales_Invoice_Page("Customer","Sales_Database.txt");
        frame.setVisible(true);
    }
}
