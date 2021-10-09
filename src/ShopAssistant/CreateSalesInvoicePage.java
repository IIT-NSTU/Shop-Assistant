
package ShopAssistant;

import Templates.InvoiceGeneratorTemplate;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class CreateSalesInvoicePage extends InvoiceGeneratorTemplate
{
    public CreateSalesInvoicePage(String keyword, String fileName) 
    {
        super(keyword, fileName);
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
        quantityTextField.addKeyListener(new KeyListener()
        { 
            public void keyTyped(KeyEvent ke){}    

            public void keyPressed(KeyEvent ke){}
         
            public void keyReleased(KeyEvent ke) 
            { 
                try{
                int totalStock = getTotalStock((String)modelNumberCombobox.getSelectedItem(),"PurchaseDatabase.txt","SalesDatabase.txt");
                int givenValue = Integer.parseInt(quantityTextField.getText());

                if(!isDigit(ke.getKeyChar()))
                {    
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits"); 
                    quantityTextField.setText("");
                }
                
                if(productTypeCombobox.getSelectedItem()==""||modelNumberCombobox.getSelectedItem()=="")
                {
                    JOptionPane.showMessageDialog(null, "Please Select Product Type and Model Number");
                    quantityTextField.setText("");
                }
                else{
                
                if(totalStock==0)
                {
                    JOptionPane.showMessageDialog(null, "You Have No More Item Left");
                    quantityTextField.setText("");
                }
                else
                {
                    if((totalStock-givenValue)>=0)
                    {
                        JOptionPane.showMessageDialog(null, "You Have "+(totalStock-givenValue)+" Item Left");                      
                        setPayment();
                        setDue();
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "There Will Not Any Item Left");                                                  
                        quantityTextField.setText("");
                    }
                }
                }  
                
                }catch(Exception e){}
            }               
        });
    
    }
    
    public static void main(String[] args) 
    {  
        CreateSalesInvoicePage frame = new CreateSalesInvoicePage("Customer","SalesDatabase.txt");
        frame.setVisible(true);
    }
}
