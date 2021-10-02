
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Add_Cost_Page extends DashBoard_Template
{
    public JPanel input_panel;
    public JLabel date_label,cost_type_label,amount_label;
    public JButton cost_submit_button;
    public JTextField date_textfield,cost_amount_textfield;
    public JComboBox cost_type_combobox;
    public String cost_type[] = {"","Staff Salary","Electricity Bill","Shop Rent","Miscellaneous"};

    
    public Add_Cost_Page()
    {
        setPageButton();
        setMainPanel();
        setInputPanel();
        setAddCostFeatures();
    }
    
    public void setPageButton()
    {
        getButton("Add Cost").setBackground(Color.LIGHT_GRAY);
        getButton("Add Cost").setForeground(Color.BLACK);
        getButton("Add Cost").setFont(new Font("Arial",Font.BOLD,16));
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
        
        input_panel = new JPanel();
        input_panel.setLayout(new GridLayout(8,1));
        input_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        input_panel.setBackground(Color.WHITE);
        main_panel.add(input_panel,BorderLayout.CENTER);
    }
    
    
    public void setInputPanel()
    {    
        date_label = new JLabel("Date & Time");
        date_label.setBackground(Color.WHITE);
        date_label.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(date_label);       
 
        
        date_textfield = new JTextField(setDate());
        date_textfield.setBackground(Color.WHITE);
        date_textfield.setEditable(false);
        date_textfield.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(date_textfield);

        cost_type_label = new JLabel("Cost Type");
        cost_type_label.setBackground(Color.WHITE);
        cost_type_label.setFont(new Font("Arial",Font.BOLD,16));        
        input_panel.add(cost_type_label);
        

        cost_type_combobox = new JComboBox(cost_type);
        cost_type_combobox.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(cost_type_combobox);
        
        
        amount_label = new JLabel("Amount");
        amount_label.setBackground(Color.WHITE);
        amount_label.setFont(new Font("Arial",Font.BOLD,16));        
        input_panel.add(amount_label);
        
        
        cost_amount_textfield = new JTextField();
        cost_amount_textfield.setFont(new Font("Arial",Font.BOLD,16));
        cost_amount_textfield.setEnabled(false);
        cost_amount_textfield.setBackground(Color.WHITE);
        input_panel.add(cost_amount_textfield); 
        
       
        cost_submit_button = new JButton("Submit");
        cost_submit_button.setFont(new Font("Arial",Font.BOLD,18));
        cost_submit_button.setFocusPainted(false);
        input_panel.add(cost_submit_button);  
    }
    
    public void setAddCostFeatures()
    {
        cost_type_combobox.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(cost_type_combobox.getSelectedItem()=="")
                {
                    JOptionPane.showMessageDialog(null, "Please Select an Option");
                    cost_amount_textfield.setText("");
                    cost_amount_textfield.setEnabled(false);
                }
                else
                {
                    cost_amount_textfield.setEnabled(true);
                }
            }
        }); 
        
        cost_amount_textfield.addKeyListener(new KeyListener(){
      
            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) 
            {
                if(!isDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digit");
                    cost_amount_textfield.setText("");
                }
            }

            public void keyReleased(KeyEvent ke)
            {
                try{
                if(Float.parseFloat(cost_amount_textfield.getText())>100000000)
                {
                    JOptionPane.showMessageDialog(null, "Please Do Not Enter Large Values");   
                    cost_amount_textfield.setText("");
                }
                }catch(Exception e) {}
            }
        });
        
        
        cost_submit_button.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(cost_amount_textfield.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Amount");
                }
                else
                {    
                 try
                 {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Income_Cost_Database.txt",true));
                     
                    bw.append("P100000000"+","+date_textfield.getText()+","+"Cost By "+cost_type_combobox.getSelectedItem()+","+cost_amount_textfield.getText()+"\n");
                    bw.close();
                    
                    JOptionPane.showMessageDialog(null,cost_type_combobox.getSelectedItem()+" "+cost_amount_textfield.getText()+" Added Successfully");
                    
                    cost_amount_textfield.setText(""); 
                 } 
                 catch (Exception ex) {}
                }
            }
        });
    
    }
    
    public static void main(String[] args) 
    {
        Add_Cost_Page frame = new Add_Cost_Page();
        frame.setVisible(true);
    }
}
