
package ShopAssistant;

import Templates.DashBoardTemplate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class AdvancedStockCheckPage extends DashBoardTemplate
{
    public JPanel outputPanel,datePanel;
    public JLabel selectLabel,dayLabel,monthLabel,yearLabel,productTypeLabel,modelNumberLabel;
    public JComboBox dayCombobox,monthCombobox,yearCombobox,productTypeCombobox,modelNumberCombobox;
    public JTextField showQuantityTextField;
    public JButton printButton;
    public JTextArea printTextarea = new JTextArea("");
    
    public String day[] = new String[32];
    public String month[] = new String[13];
    public String year[] = new String[102];
    
    /**
     *  Advanced Stock Check Constructor
     */
    public AdvancedStockCheckPage()
    {
        setPageButton();
        setMainPanel();
        setOutputPanel();
        setAdvancedStockCheckFeatures();
    }
    
    /**
     * This Method Sets Button Using Different BackGround and ForeGround Color
     */
    public void setPageButton()
    {
        getButton("Advanced Stock Check").setBackground(Color.LIGHT_GRAY);
        getButton("Advanced Stock Check").setForeground(Color.BLACK);
        getButton("Advanced Stock Check").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    /**
     * This Method Sets Input Panel Components
     */
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
        outputPanel.setLayout(new GridLayout(8,1));
        outputPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        outputPanel.setBackground(Color.WHITE);
        mainPanel.add(outputPanel,BorderLayout.CENTER);
    }
    
    /**
     * This Method Sets Output Panel Components
     */
    public void setOutputPanel()
    {   
        datePanel = new JPanel();
        datePanel.setBackground(Color.WHITE);
        datePanel.setLayout(new GridLayout(2,3));
        outputPanel.add(datePanel);
          
        dayLabel = new JLabel("Day");
        dayLabel.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(dayLabel);
        
        monthLabel = new JLabel("Month");
        monthLabel.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(monthLabel);
        
        yearLabel = new JLabel("Year");
        yearLabel.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(yearLabel);
        
        setManualDate();
        
        dayCombobox = new JComboBox(day);
        dayCombobox.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(dayCombobox);
        
        monthCombobox = new JComboBox(month);
        monthCombobox.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(monthCombobox);
        
        yearCombobox = new JComboBox(year);
        yearCombobox.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(yearCombobox);
        
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
        
        modelNumberCombobox = new JComboBox(setProductTypes());
        modelNumberCombobox.setEnabled(true);
        modelNumberCombobox.setFont(new Font("Arial",Font.BOLD,16));
        outputPanel.add(modelNumberCombobox);
        
        showQuantityTextField = new JTextField();
        showQuantityTextField.setFont(new Font("Arial",Font.BOLD,14));
        showQuantityTextField.setEditable(false);
        showQuantityTextField.setBackground(Color.WHITE);
        outputPanel.add(showQuantityTextField);
        
        printButton = new JButton("Print");
        printButton.setFont(new Font("Arial",Font.BOLD,16));
        printButton.setFocusPainted(false);
        outputPanel.add(printButton);
        
    }
    
    /**
     *  This Method Helps To Generate Date Manually
     */
    public void setManualDate()
    {
        int i;
        
        day[0]="";
        i=1;
        while(i!=day.length)
        {
            if(i<10)
            {
                day[i] = "0"+Integer.toString(i);
            }
            else
            {
                day[i] = Integer.toString(i);
            }
            i++;
        }
        
        i=1;
        month[0]="";
        while(i!=month.length)
        {
            if(i<10)
            {
                month[i] = "0"+Integer.toString(i);
            }
            else
            {
                month[i] = Integer.toString(i);
            }
            i++;
        }
        
        
        i=1;
        int j=2020;
        year[0]="";
        while(i!=year.length)
        {
            year[i] = Integer.toString(j);
            i++;
            j++;
        }
    
    }
    
    /**
     *  This Method Sets Advanced Stock Check Features
     */
    public void setAdvancedStockCheckFeatures()
    {
         productTypeCombobox.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(!dayCombobox.getSelectedItem().equals("")&&!monthCombobox.getSelectedItem().equals("")&&!yearCombobox.getSelectedItem().equals("")&&!productTypeCombobox.getSelectedItem().equals(""))
                {
                    setModelNumberCombobox(dayCombobox.getSelectedItem().toString()+"/"+monthCombobox.getSelectedItem().toString()+"/"+yearCombobox.getSelectedItem().toString());
                }
                else if(!monthCombobox.getSelectedItem().equals("")&&!yearCombobox.getSelectedItem().equals("")&&!productTypeCombobox.getSelectedItem().equals(""))
                {
                    setModelNumberCombobox(monthCombobox.getSelectedItem().toString()+"/"+yearCombobox.getSelectedItem().toString());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Date and Product Type");
                }
            }
  
        }); 
         
        printButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(printTextarea.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Get Some Stock Information");
                }
                else 
                {
                    try {
                        printTextarea.print(); 
                    } catch (PrinterException ex) {
                    }   
                }  
            }
         
        }); 
        
    }
    
    public void setModelNumberCombobox(String date)
    {
        modelNumberCombobox.setEnabled(true);
        modelNumberCombobox.removeAllItems();
        modelNumberCombobox.addItem("");
        printTextarea.setText("Information For This Period : "+date+"\n\n");

        int salesModelCount=0,salesStock,purchaseModelCount=0,purchaseStock;

        try{
            
            BufferedReader br = new BufferedReader(new FileReader("ProductType&ModelList.txt"));
            String s,result = "",modelNumber ="";
     
            while((s=br.readLine())!=null)
            {
                if(productTypeCombobox.getSelectedItem().equals(s.substring(0, s.indexOf(","))))
                {
                    result = s.substring(s.indexOf(",")+1, s.length());    
                }
            }
         
            char demo[] = result.toCharArray();
            int i=0;
                
            while(i!=demo.length)
            {
                modelNumber = modelNumber + demo[i];
                if(demo[i]=='.')
                {
                    salesStock = getRemainingQuantityUsingDate(date,modelNumber,"SalesDatabase.txt");
                    purchaseStock = getRemainingQuantityUsingDate(date,modelNumber,"PurchaseDatabase.txt");
                            
                    if(salesStock!=0)
                    {
                        salesModelCount = salesModelCount + salesStock;
                        modelNumberCombobox.addItem(modelNumber); 
                        modelNumberCombobox.addItem("Sales "+salesStock+" Pcs\n"); 
                        
                        printTextarea.append(modelNumber);
                        printTextarea.append("Sales "+salesStock+" Pcs\n");
                    }
                    if(purchaseStock!=0)
                    {
                        purchaseModelCount = purchaseModelCount + purchaseStock;
                        modelNumberCombobox.addItem(modelNumber); 
                        modelNumberCombobox.addItem("Purchase "+purchaseStock+" Pcs\n"); 
                        
                        printTextarea.append(modelNumber);
                        printTextarea.append("Purchase "+purchaseStock+" Pcs\n");
                    }        
                    modelNumber = "";
                }
                i++;
            }
                    
            showQuantityTextField.setText("Total "+productTypeCombobox.getSelectedItem()+" Sold : "+salesModelCount+" Pcs Purchased : "+purchaseModelCount+" Pcs\n");
            printTextarea.append("Total "+productTypeCombobox.getSelectedItem()+" Sold : "+salesModelCount+" Pcs Purchased : "+purchaseModelCount+" Pcs\n");

            }catch(Exception ex){System.out.println(ex);}
    }
    
    public static void main(String[] args) 
    {
        AdvancedStockCheckPage  frame = new AdvancedStockCheckPage();
        frame.setVisible(true);
    }
}
