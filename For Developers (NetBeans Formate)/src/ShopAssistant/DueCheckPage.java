
package ShopAssistant;

import Templates.DashBoardTemplate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class DueCheckPage extends DashBoardTemplate
{
    public JPanel outputPanel;
    public JLabel dateLabel,customerDueLabel,companyDueLabel,customerDueInfoLabel,companyDueInfoLabel,amountLabel;
    public JTextField dateTextField,customerDueTextField,companyDueTextField,customerAmountTextField,companyAmountTextField;
    public JComboBox customerDueIdCombobox,companyDueIdCombobox;
    public JButton customerSubmitButton,companySubmitButton;
    
    /**
     *  Due Check Page Constructor
     */
    public DueCheckPage()
    {
        setPageButton();
        setMainPanel();
        setOutputPanel();
        setDueCheckFeatures();
    }
    
    /**
     * This Method Sets Button Using Different BackGround and ForeGround Color
     */
    public void setPageButton()
    {
        getButton("Due Check").setBackground(Color.LIGHT_GRAY);
        getButton("Due Check").setForeground(Color.BLACK);
        getButton("Due Check").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    /**
     * This Method Sets Main Panel
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
        outputPanel.setLayout(new GridLayout(8,2));
        outputPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        outputPanel.setBackground(Color.WHITE);
        mainPanel.add(outputPanel,BorderLayout.CENTER);
    }
    
    
    /**
     * This Method Sets Output Panel
     */
    public void setOutputPanel()
    { 
        dateLabel = new JLabel("Date & Time");
        dateLabel.setBackground(Color.WHITE);
        dateLabel.setFont(new Font("Arial",Font.BOLD,14));
        outputPanel.add(dateLabel);       
 
        
        dateTextField = new JTextField(setDate());
        dateTextField.setBackground(Color.WHITE);
        dateTextField.setEditable(false);
        dateTextField.setFont(new Font("Arial",Font.BOLD,14));
        outputPanel.add(dateTextField);
       
         
        customerDueLabel = new JLabel("Customer Total Due");
        customerDueLabel.setBackground(Color.WHITE);
        customerDueLabel.setFont(new Font("Arial",Font.BOLD,14));
        outputPanel.add(customerDueLabel);
        
        
        companyDueLabel = new JLabel("Company Total Due");
        companyDueLabel.setBackground(Color.WHITE);
        companyDueLabel.setFont(new Font("Arial",Font.BOLD,14));
        outputPanel.add(companyDueLabel);
        
        
        customerDueTextField = new JTextField();
        customerDueTextField.setFont(new Font("Arial",Font.BOLD,14));
        customerDueTextField.setEditable(false);
        customerDueTextField.setBackground(Color.WHITE);
        outputPanel.add(customerDueTextField);
        
        
        companyDueTextField = new JTextField();
        companyDueTextField.setFont(new Font("Arial",Font.BOLD,14));
        companyDueTextField.setEditable(false);
        companyDueTextField.setBackground(Color.WHITE);
        outputPanel.add(companyDueTextField);
        
        
        customerDueInfoLabel = new JLabel("Customer Due ID's");
        customerDueInfoLabel.setBackground(Color.WHITE);
        customerDueInfoLabel.setFont(new Font("Arial",Font.BOLD,14));
        outputPanel.add(customerDueInfoLabel);
        
        
        companyDueInfoLabel = new JLabel("Compnay Due ID's");
        companyDueInfoLabel.setBackground(Color.WHITE);
        companyDueInfoLabel.setFont(new Font("Arial",Font.BOLD,14));
        outputPanel.add(companyDueInfoLabel);
        
        
        customerDueIdCombobox = new JComboBox();
        customerDueIdCombobox.setFont(new Font("Arial",Font.BOLD,14));
        outputPanel.add(customerDueIdCombobox);
        setCustomerDue();
        
        
        companyDueIdCombobox = new JComboBox();
        companyDueIdCombobox.setFont(new Font("Arial",Font.BOLD,14));
        outputPanel.add(companyDueIdCombobox);
        setCompanyDue();
        
        
        amountLabel = new JLabel("Amount");
        amountLabel.setBackground(Color.WHITE);
        amountLabel.setFont(new Font("Arial",Font.BOLD,14));        
        outputPanel.add(amountLabel);
        
        
        amountLabel = new JLabel("Amount");
        amountLabel.setBackground(Color.WHITE);
        amountLabel.setFont(new Font("Arial",Font.BOLD,14));        
        outputPanel.add(amountLabel);
        
        
        customerAmountTextField = new JTextField();
        customerAmountTextField.setFont(new Font("Arial",Font.BOLD,14));
        customerAmountTextField.setEnabled(false);
        customerAmountTextField.setBackground(Color.WHITE);
        outputPanel.add(customerAmountTextField); 
        
        
        companyAmountTextField = new JTextField();
        companyAmountTextField.setFont(new Font("Arial",Font.BOLD,14));
        companyAmountTextField.setEnabled(false);
        companyAmountTextField.setBackground(Color.WHITE);
        outputPanel.add(companyAmountTextField); 
        
        
        customerSubmitButton = new JButton("Submit");
        customerSubmitButton.setFont(new Font("Arial",Font.BOLD,14));
        customerSubmitButton.setFocusPainted(false);
        outputPanel.add(customerSubmitButton); 
        
        
        companySubmitButton = new JButton("Submit");
        companySubmitButton.setFont(new Font("Arial",Font.BOLD,14));
        companySubmitButton.setFocusPainted(false);
        outputPanel.add(companySubmitButton);
        
    }
    
    /**
     * This Method Sets Customer Due
     */
    public void setCustomerDue()
    {
        int totalDue = 0;
        customerDueIdCombobox.addItem("");
        
        try{
          BufferedReader br = new BufferedReader(new FileReader("SalesDatabase.txt"));
          String s = "";
          
          
          while((s=br.readLine())!=null)
          {  
            int due = checkDue(s.substring(0, 10),"SalesDatabase.txt");
            
            
            if(due>0)
            {
                customerDueIdCombobox.addItem(s.substring(0, 10));
            }
        
            totalDue = totalDue + due;     
         }
   
        }catch(Exception e){System.out.println(e);}
        
        customerDueTextField.setText(""+totalDue);
    }
    
    /**
     * This Method Sets Company
     */
    public void setCompanyDue()
    {
        int totalDue = 0;
        companyDueIdCombobox.addItem("");
        
        try{
          BufferedReader br = new BufferedReader(new FileReader("PurchaseDatabase.txt"));
          String s = "";
          
         
          while((s=br.readLine())!=null)
          {  
            int due = checkDue(s.substring(0, 10),"PurchaseDatabase.txt");
            
            if(due>0)
            {
                companyDueIdCombobox.addItem(s.substring(0, 10));
            }
            
            totalDue = totalDue + due;
              
          }
          
          companyDueTextField.setText(""+totalDue);
          
            
        }catch(Exception e){System.out.println(e);}

    }
    
    /**
     * Checks Due
     * 
     * @param selectedItem Due of Selected Item
     * @param filename Specific FileName
     * @return Due
     */
    public int checkDue(String selectedItem,String filename)
    {
        int totalDue = 0;
        
        try{
         
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s = "";
        
        
        while((s=br.readLine())!=null)
        {
            if(selectedItem.equals(s.substring(0, 10)))
            {                
                char demo[] = s.toCharArray();
                int i = s.length()-1;
                String result="";
                while(demo[i]!=',')
                {
                  result = result + demo[i];
                  i--;
                }
            
                StringBuffer sb=new StringBuffer(result);  
                sb.reverse();    
        
                int due = Integer.parseInt(sb.toString());
                totalDue = totalDue + due;
            }
             
        }

        
        BufferedReader br2 = new BufferedReader(new FileReader("IncomeCostDatabase.txt"));
        String s2 = "";
    
        while((s2=br2.readLine())!=null)
        {  
            if(selectedItem.equals(s2.substring(0, 10)))
            {
                char demo[] = s2.toCharArray();
                int i = s2.length()-1;
                String result="";
                while(demo[i]!=',')
                {
                  result = result + demo[i];
                  i--;
                }
            
                StringBuffer sb=new StringBuffer(result);  
                sb.reverse();    
        
                int due = Integer.parseInt(sb.toString());
                
                totalDue = totalDue - due;  
            }
        }
        
            
        }catch(Exception e) {System.out.println(e);}
        
        return totalDue;
        
    }
    
    /**
     * Checks If Money Digit or Not
     * 
     * @param c A Specific Character 
     * @return True if MoneyDigit otherwise false
     */
    public boolean checkMoneyDigit(char c)
    {
        if(c!='0'&&c!='1'&&c!='2'&&c!='3'&&c!='4'&&c!='5'&&c!='6'&&c!='7'&&c!='8'&&c!='9'&&c!=(char)10&&c!=(char)8)
            return true;
        else
            return false;
    }
    
    
    public void refresh()
    {
        dispose();
        DueCheckPage frame = new DueCheckPage();
        frame.setVisible(true);
        
    }
    
    /**
     *  Sets Due Features
     */
    public void setDueCheckFeatures()
    {
        customerDueIdCombobox.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(customerDueIdCombobox.getSelectedItem()))
                {
                    JOptionPane.showMessageDialog(null, "Please Select an Option");
                    customerAmountTextField.setText("");
                    customerAmountTextField.setEnabled(false);
                }
                else
                {
                    customerAmountTextField.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Due Amount is "+checkDue((String)customerDueIdCombobox.getSelectedItem(),"SalesDatabase.txt"));
                }
            }
        }); 
        
        customerAmountTextField.addKeyListener(new KeyListener(){
      
            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) 
            {
                if(checkMoneyDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digit");
                    customerAmountTextField.setText("");
                }
            }

            public void keyReleased(KeyEvent ke){    
            }
        
        
        
        });
        
        
        companyDueIdCombobox.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(companyDueIdCombobox.getSelectedItem()))
                {
                    JOptionPane.showMessageDialog(null, "Please Select an Option");
                    companyAmountTextField.setText("");
                    companyAmountTextField.setEnabled(false);
                }
                else
                {
                    companyAmountTextField.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Due Amount is "+checkDue((String)companyDueIdCombobox.getSelectedItem(),"PurchaseDatabase.txt"));
                }
            }
        }); 
        
        companyAmountTextField.addKeyListener(new KeyListener(){
      
            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) 
            {
                if(checkMoneyDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digit");
                    companyAmountTextField.setText("");
                }
            }

            public void keyReleased(KeyEvent ke){              
            }
        
        });
        
        
        customerSubmitButton.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                try{
                    
                if("".equals(customerAmountTextField.getText())) 
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Some Amount");   
                }
                
                else if(Integer.parseInt(customerAmountTextField.getText())>checkDue((String)customerDueIdCombobox.getSelectedItem(),"SalesDatabase.txt"))
                {
                    JOptionPane.showMessageDialog(null, "Please Do Not Enter Extra Amount\nYour Due is "+checkDue((String)customerDueIdCombobox.getSelectedItem(),"SalesDatabase.txt"));
                    customerAmountTextField.setText("");
                }
                else
                {
                   
                BufferedWriter bw = new BufferedWriter(new FileWriter("IncomeCostDatabase.txt",true));
                bw.append(customerDueIdCombobox.getSelectedItem()+","+dateTextField.getText()+","+"Income By Customer Due Amount"+","+customerAmountTextField.getText()+"\n");
                bw.close();
                 
                JOptionPane.showMessageDialog(null, "Customer Due Added Successfully");
                JOptionPane.showMessageDialog(null, "Remaining Due Amount is "+checkDue((String)customerDueIdCombobox.getSelectedItem(),"SalesDatabase.txt"));
                
                refresh();
                      
                }
                
                }catch(Exception ex) {}
            }
        });
        
        companySubmitButton.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                try{
                if("".equals(companyAmountTextField.getText())) 
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Some Amount");   
                }
                
                else if(Integer.parseInt(companyAmountTextField.getText())>checkDue((String)companyDueIdCombobox.getSelectedItem(),"PurchaseDatabase.txt"))
                {
                    JOptionPane.showMessageDialog(null, "Please Do Not Enter Extra Amount\nYour Due is "+checkDue((String)companyDueIdCombobox.getSelectedItem(),"PurchaseDatabase.txt"));
                    companyAmountTextField.setText("");
                }
                
                else
                {
  
                BufferedWriter bw = new BufferedWriter(new FileWriter("IncomeCostDatabase.txt",true));
                bw.append(companyDueIdCombobox.getSelectedItem()+","+dateTextField.getText()+","+"Cost By Company Due Amount"+","+companyAmountTextField.getText()+"\n");
                bw.close();
                
                JOptionPane.showMessageDialog(null, "Company Due Paid Successfully");
                JOptionPane.showMessageDialog(null, "Remaining Due Amount is "+checkDue((String)companyDueIdCombobox.getSelectedItem(),"PurchaseDatabase.txt"));

                
                refresh();

                }
                
                }catch(Exception ex) {}
            }
        });
    }
    
    
    public static void main(String[] args) 
    {
       DueCheckPage frame = new DueCheckPage();
       frame.setVisible(true);
    }
}
