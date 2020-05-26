
package Shop_Assistant;


import Templates.Invoice_Generator_Template;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class Create_Purchase_Invoice_Page extends Invoice_Generator_Template
{
    public Create_Purchase_Invoice_Page(String keyword, String file_name) 
    {
        super(keyword, file_name);
        setPageButton();
        setQuantityTextFieldListener();
    }
    
    public void setPageButton()
    {
        getButton("Create Purchase Invoice").setBackground(Color.LIGHT_GRAY);
        getButton("Create Purchase Invoice").setForeground(Color.BLACK);
        getButton("Create Purchase Invoice").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    public void setQuantityTextFieldListener()
    {
        quantity_textfield.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){}    

            public void keyPressed(KeyEvent ke) {} 
            
            public void keyReleased(KeyEvent ke) 
            {
                if(!isDigit(ke.getKeyChar()))
                {    
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits"); 
                    quantity_textfield.setText("");
                }
                
                if(product_type_combobox.getSelectedItem()==""||model_number_combobox.getSelectedItem()=="")
                {
                   JOptionPane.showMessageDialog(null, "Please Enter Product Type and Model Number");
                   quantity_textfield.setText("");
                }
                else
                {
                    setPayment();
                    setDue();
                }
            }
        
        });
    }
    
    
    public static void main(String[] args) 
    {
        Create_Purchase_Invoice_Page frame = new Create_Purchase_Invoice_Page("Company","Purchase_Database.txt");
        frame.setVisible(true);
    }
}
