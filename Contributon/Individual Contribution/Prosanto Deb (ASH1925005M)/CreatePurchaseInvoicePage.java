
package ShopAssistant;

import Templates.InvoiceGeneratorTemplate;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

public class CreatePurchaseInvoicePage extends InvoiceGeneratorTemplate
{
    /**
     * Create Purchase Invoice Page Constructor
     * 
     * @param keyword KeyWord Determines Sales or Purchase Invoice
     * @param fileName Sales or Purchase FileName
     */
    public CreatePurchaseInvoicePage(String keyword, String fileName) 
    {
        super(keyword, fileName);
        setPageButton();
        setQuantityTextFieldListener();
    }
    
    /**
     * This Method Sets Button Using Different BackGround and ForeGround Color
     */
    public void setPageButton()
    {
        getButton("Create Purchase Invoice").setBackground(Color.LIGHT_GRAY);
        getButton("Create Purchase Invoice").setForeground(Color.BLACK);
        getButton("Create Purchase Invoice").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    /**
     * This Method Sets Quantity TextField Listener
     */
    public void setQuantityTextFieldListener()
    {
        quantityTextField.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){}    

            public void keyPressed(KeyEvent ke) {} 
            
            public void keyReleased(KeyEvent ke) 
            {
                if(!isDigit(ke.getKeyChar()))
                {    
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits"); 
                    quantityTextField.setText("");
                }
                
                if(productTypeCombobox.getSelectedItem()==""||modelNumberCombobox.getSelectedItem()=="")
                {
                   JOptionPane.showMessageDialog(null, "Please Enter Product Type and Model Number");
                   quantityTextField.setText("");
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
        CreatePurchaseInvoicePage frame = new CreatePurchaseInvoicePage("Company","PurchaseDatabase.txt");
        frame.setVisible(true);
    }
}
