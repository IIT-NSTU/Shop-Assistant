
package Shop_Assistant;

import Templates.DashBoard_Template;
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


public class Due_Check_Page extends DashBoard_Template
{
    public JPanel output_panel;
    public JLabel date_label,customer_due_label,company_due_label,customer_due_info_label,company_due_info_label,amount_label;
    public JTextField date_textfield,customer_due_textfield,company_due_textfield,customer_amount_textfield,company_amount_textfield;
    public JComboBox customer_due_id_combobox,company_due_id_combobox;
    public JButton customer_submit_button,company_submit_button;
    
    public Due_Check_Page()
    {
        setPageButton();
        setMainPanel();
        setOutputPanel();
        setDueCheckFeatures();
    }
    
    public void setPageButton()
    {
        getButton("Due Check").setBackground(Color.LIGHT_GRAY);
        getButton("Due Check").setForeground(Color.BLACK);
        getButton("Due Check").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    public void setMainPanel()
    {
        main_panel.setLayout(new BorderLayout(120,50));
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.NORTH);
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.EAST);
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.WEST);
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.SOUTH);
        
        
        output_panel = new JPanel();
        output_panel.setLayout(new GridLayout(8,2));
        output_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        output_panel.setBackground(Color.WHITE);
        main_panel.add(output_panel,BorderLayout.CENTER);
    }
    
    public void setOutputPanel()
    { 
        date_label = new JLabel("Date & Time");
        date_label.setBackground(Color.WHITE);
        date_label.setFont(new Font("Arial",Font.BOLD,14));
        output_panel.add(date_label);       
 
        
        date_textfield = new JTextField(setDate());
        date_textfield.setBackground(Color.WHITE);
        date_textfield.setEditable(false);
        date_textfield.setFont(new Font("Arial",Font.BOLD,14));
        output_panel.add(date_textfield);
       
         
        customer_due_label = new JLabel("Customer Total Due");
        customer_due_label.setBackground(Color.WHITE);
        customer_due_label.setFont(new Font("Arial",Font.BOLD,14));
        output_panel.add(customer_due_label);
        
        
        company_due_label = new JLabel("Company Total Due");
        company_due_label.setBackground(Color.WHITE);
        company_due_label.setFont(new Font("Arial",Font.BOLD,14));
        output_panel.add(company_due_label);
        
        
        customer_due_textfield = new JTextField();
        customer_due_textfield.setFont(new Font("Arial",Font.BOLD,14));
        customer_due_textfield.setEditable(false);
        customer_due_textfield.setBackground(Color.WHITE);
        output_panel.add(customer_due_textfield);
        
        
        company_due_textfield = new JTextField();
        company_due_textfield.setFont(new Font("Arial",Font.BOLD,14));
        company_due_textfield.setEditable(false);
        company_due_textfield.setBackground(Color.WHITE);
        output_panel.add(company_due_textfield);
        
        
        customer_due_info_label = new JLabel("Customer Due ID's");
        customer_due_info_label.setBackground(Color.WHITE);
        customer_due_info_label.setFont(new Font("Arial",Font.BOLD,14));
        output_panel.add(customer_due_info_label);
        
        
        company_due_info_label = new JLabel("Compnay Due ID's");
        company_due_info_label.setBackground(Color.WHITE);
        company_due_info_label.setFont(new Font("Arial",Font.BOLD,14));
        output_panel.add(company_due_info_label);
        
        
        customer_due_id_combobox = new JComboBox();
        customer_due_id_combobox.setFont(new Font("Arial",Font.BOLD,14));
        output_panel.add(customer_due_id_combobox);
        setCustomerDue();
        
        
        company_due_id_combobox = new JComboBox();
        company_due_id_combobox.setFont(new Font("Arial",Font.BOLD,14));
        output_panel.add(company_due_id_combobox);
        setCompanyDue();
        
        
        amount_label = new JLabel("Amount");
        amount_label.setBackground(Color.WHITE);
        amount_label.setFont(new Font("Arial",Font.BOLD,14));        
        output_panel.add(amount_label);
        
        
        amount_label = new JLabel("Amount");
        amount_label.setBackground(Color.WHITE);
        amount_label.setFont(new Font("Arial",Font.BOLD,14));        
        output_panel.add(amount_label);
        
        
        customer_amount_textfield = new JTextField();
        customer_amount_textfield.setFont(new Font("Arial",Font.BOLD,14));
        customer_amount_textfield.setEnabled(false);
        customer_amount_textfield.setBackground(Color.WHITE);
        output_panel.add(customer_amount_textfield); 
        
        
        company_amount_textfield = new JTextField();
        company_amount_textfield.setFont(new Font("Arial",Font.BOLD,14));
        company_amount_textfield.setEnabled(false);
        company_amount_textfield.setBackground(Color.WHITE);
        output_panel.add(company_amount_textfield); 
        
        
        customer_submit_button = new JButton("Submit");
        customer_submit_button.setFont(new Font("Arial",Font.BOLD,14));
        customer_submit_button.setFocusPainted(false);
        output_panel.add(customer_submit_button); 
        
        
        company_submit_button = new JButton("Submit");
        company_submit_button.setFont(new Font("Arial",Font.BOLD,14));
        company_submit_button.setFocusPainted(false);
        output_panel.add(company_submit_button);
        
    }
    
    public void setCustomerDue()
    {
        int total_due = 0;
        customer_due_id_combobox.addItem("");
        
        try{
          BufferedReader br = new BufferedReader(new FileReader("Sales_Database.txt"));
          String s = "";
          
          
          while((s=br.readLine())!=null)
          {  
            int due = checkDue(s.substring(0, 10),"Sales_Database.txt");
            
            
            if(due>0)
            {
                customer_due_id_combobox.addItem(s.substring(0, 10));
            }
        
            total_due = total_due + due;     
         }
   
        }catch(Exception e){System.out.println(e);}
        
        customer_due_textfield.setText(""+total_due);
    }
    
    public void setCompanyDue()
    {
        int total_due = 0;
        company_due_id_combobox.addItem("");
        
        try{
          BufferedReader br = new BufferedReader(new FileReader("Purchase_Database.txt"));
          String s = "";
          
         
          while((s=br.readLine())!=null)
          {  
            int due = checkDue(s.substring(0, 10),"Purchase_Database.txt");
            
            if(due>0)
            {
                company_due_id_combobox.addItem(s.substring(0, 10));
            }
            
            total_due = total_due + due;
              
          }
          
          company_due_textfield.setText(""+total_due);
          
            
        }catch(Exception e){System.out.println(e);}

    }
    
    public int checkDue(String selected_item,String filename)
    {
        int total_due = 0;
        
        try{
         
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String s = "";
        
        
        while((s=br.readLine())!=null)
        {
            if(selected_item.equals(s.substring(0, 10)))
            {                
                char demo[] = s.toCharArray();
                int i = s.length()-1;
                String result="";
                while(demo[i]!=',')
                {
                  result = result + demo[i];
                  i--;
                }
            
                StringBuilder sb=new StringBuilder(result);  
                sb.reverse();    
        
                int due = Integer.parseInt(sb.toString());
                total_due = total_due + due;
            }
             
        }

        
        BufferedReader br2 = new BufferedReader(new FileReader("Income_Cost_Database.txt"));
        String s2 = "";
    
        while((s2=br2.readLine())!=null)
        {  
            if(selected_item.equals(s2.substring(0, 10)))
            {
                char demo[] = s2.toCharArray();
                int i = s2.length()-1;
                String result="";
                while(demo[i]!=',')
                {
                  result = result + demo[i];
                  i--;
                }
            
                StringBuilder sb=new StringBuilder(result);  
                sb.reverse();    
        
                int due = Integer.parseInt(sb.toString());
                
                total_due = total_due - due;  
            }
        }
        
            
        }catch(Exception e) {System.out.println(e);}
        
        return total_due;
        
    }
    
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
        Due_Check_Page frame = new Due_Check_Page();
        frame.setVisible(true);
        
    }
    
    public void setDueCheckFeatures()
    {
        customer_due_id_combobox.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(customer_due_id_combobox.getSelectedItem()))
                {
                    JOptionPane.showMessageDialog(null, "Please Select an Option");
                    customer_amount_textfield.setText("");
                    customer_amount_textfield.setEnabled(false);
                }
                else
                {
                    customer_amount_textfield.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Due Amount is "+checkDue((String)customer_due_id_combobox.getSelectedItem(),"Sales_Database.txt"));
                }
            }
        }); 
        
        customer_amount_textfield.addKeyListener(new KeyListener(){
      
            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) 
            {
                if(checkMoneyDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digit");
                    customer_amount_textfield.setText("");
                }
            }

            public void keyReleased(KeyEvent ke){    
            }
        
        
        
        });
        
        
        company_due_id_combobox.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(company_due_id_combobox.getSelectedItem()))
                {
                    JOptionPane.showMessageDialog(null, "Please Select an Option");
                    company_amount_textfield.setText("");
                    company_amount_textfield.setEnabled(false);
                }
                else
                {
                    company_amount_textfield.setEnabled(true);
                    JOptionPane.showMessageDialog(null, "Due Amount is "+checkDue((String)company_due_id_combobox.getSelectedItem(),"Purchase_Database.txt"));
                }
            }
        }); 
        
        company_amount_textfield.addKeyListener(new KeyListener(){
      
            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) 
            {
                if(checkMoneyDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digit");
                    company_amount_textfield.setText("");
                }
            }

            public void keyReleased(KeyEvent ke){              
            }
        
        });
        
        
        customer_submit_button.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                try{
                    
                if("".equals(customer_amount_textfield.getText())) 
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Some Amount");   
                }
                
                else if(Integer.parseInt(customer_amount_textfield.getText())>checkDue((String)customer_due_id_combobox.getSelectedItem(),"Sales_Database.txt"))
                {
                    JOptionPane.showMessageDialog(null, "Please Do Not Enter Extra Amount\nYour Due is "+checkDue((String)customer_due_id_combobox.getSelectedItem(),"Sales_Database.txt"));
                    customer_amount_textfield.setText("");
                }
                else
                {
                   
                BufferedWriter bw = new BufferedWriter(new FileWriter("Income_Cost_Database.txt",true));
                bw.append(customer_due_id_combobox.getSelectedItem()+","+date_textfield.getText()+","+"Income By Customer Due Amount"+","+customer_amount_textfield.getText()+"\n");
                bw.close();
                 
                JOptionPane.showMessageDialog(null, "Customer Due Added Successfully");
                JOptionPane.showMessageDialog(null, "Remaining Due Amount is "+checkDue((String)customer_due_id_combobox.getSelectedItem(),"Sales_Database.txt"));
                
                refresh();
                      
                }
                
                }catch(Exception ex) {}
            }
        });
        
        company_submit_button.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                try{
                if("".equals(company_amount_textfield.getText())) 
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Some Amount");   
                }
                
                else if(Integer.parseInt(company_amount_textfield.getText())>checkDue((String)company_due_id_combobox.getSelectedItem(),"Purchase_Database.txt"))
                {
                    JOptionPane.showMessageDialog(null, "Please Do Not Enter Extra Amount\nYour Due is "+checkDue((String)company_due_id_combobox.getSelectedItem(),"Purchase_Database.txt"));
                    company_amount_textfield.setText("");
                }
                
                else
                {
  
                BufferedWriter bw = new BufferedWriter(new FileWriter("Income_Cost_Database.txt",true));
                bw.append(company_due_id_combobox.getSelectedItem()+","+date_textfield.getText()+","+"Cost By Company Due Amount"+","+company_amount_textfield.getText()+"\n");
                bw.close();
                
                JOptionPane.showMessageDialog(null, "Company Due Paid Successfully");
                JOptionPane.showMessageDialog(null, "Remaining Due Amount is "+checkDue((String)company_due_id_combobox.getSelectedItem(),"Purchase_Database.txt"));

                
                refresh();

                }
                
                }catch(Exception ex) {}
            }
        });
    }
    
    
    public static void main(String[] args) 
    {
       Due_Check_Page frame = new Due_Check_Page();
       frame.setVisible(true);
    }
}
