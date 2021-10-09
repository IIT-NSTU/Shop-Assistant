
package ShopAssistant;

import Templates.DashBoardTemplate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class StockCheckPage extends DashBoardTemplate
{
    public JPanel outputPanel;
    public JLabel productTypeLabel,modelNumberLabel;
    public JTextField showQuantityTextField;
    public JComboBox productTypeCombobox,modelNumberCombobox;
    
    public StockCheckPage()
    {
        setPageButton();
        setMainPanel();
        setOutputPanel();
        setStockCheckFeatures();
    }
    
    public void setPageButton()
    {
        getButton("Stock Check").setBackground(Color.LIGHT_GRAY);
        getButton("Stock Check").setForeground(Color.BLACK);
        getButton("Stock Check").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    public void setMainPanel()
    {
        mainPanel.setLayout(new BorderLayout(120,50));

        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.NORTH);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.EAST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.WEST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.SOUTH);
        
        outputPanel = new JPanel();
        outputPanel.setLayout(new GridLayout(6,1));
        outputPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        outputPanel.setBackground(Color.WHITE);
        mainPanel.add(outputPanel,BorderLayout.CENTER);
    }
    
    public void setOutputPanel()
    {    
        productTypeLabel = new JLabel("Product Type");
        productTypeLabel.setBackground(Color.WHITE);
        productTypeLabel.setFont(new Font("Arial",Font.BOLD,16));
        outputPanel.add(productTypeLabel);
        
        productTypeCombobox = new JComboBox(setProductTypes());
        productTypeCombobox.setFont(new Font("Arial",Font.BOLD,16));
        outputPanel.add(productTypeCombobox);
        
        modelNumberLabel = new JLabel("Model Number");
        modelNumberLabel.setBackground(Color.WHITE);
        modelNumberLabel.setFont(new Font("Arial",Font.BOLD,16));        
        outputPanel.add(modelNumberLabel);
        
        modelNumberCombobox = new JComboBox();
        modelNumberCombobox.setEnabled(false);
        modelNumberCombobox.setFont(new Font("Arial",Font.BOLD,16));
        outputPanel.add(modelNumberCombobox);
        
        showQuantityTextField = new JTextField();
        showQuantityTextField.setFont(new Font("Arial",Font.BOLD,16));
        showQuantityTextField.setEditable(false);
        showQuantityTextField.setBackground(Color.WHITE);
        outputPanel.add(showQuantityTextField); 
    }
    
    public void setStockCheckFeatures()
    {
        productTypeCombobox.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
 
                modelNumberCombobox.setEnabled(true);
                modelNumberCombobox.removeAllItems();
                modelNumberCombobox.addItem("");
                
                try{                    
                BufferedReader br = new BufferedReader(new FileReader("ProductType&ModelList.txt"));
                String s,result = "",modelNumber = "";
     
                while((s=br.readLine())!=null)
                {
                    if(productTypeCombobox.getSelectedItem().equals(s.substring(0, s.indexOf(","))))
                    {
                        result = s.substring(s.indexOf(",")+1, s.length());    
                    }
                }
         
                char demo[] = result.toCharArray();
                
                int i = 0,modelCount = 0,stock;
              
                while(i!=demo.length)
                {
                    modelNumber = modelNumber + demo[i];
                    if(demo[i]=='.')
                    {
                        stock = getTotalStock(modelNumber,"PurchaseDatabase.txt","SalesDatabase.txt");
                        
                        if(stock!=0)
                        {
                            modelCount = modelCount + stock;
                            modelNumberCombobox.addItem(modelNumber+" Available Item "+stock+" pcs");
                        }
                        
                        modelNumber = "";
                    }
                    i++;
                }
                
                showQuantityTextField.setText("Total "+productTypeCombobox.getSelectedItem()+" is "+modelCount);
                                             
            }catch(Exception ex){System.out.println(ex);}
               
            }
  
        });

    }
    
    public static void main(String[] args) 
    {
        StockCheckPage frame = new StockCheckPage();
        frame.setVisible(true);
    }
}
