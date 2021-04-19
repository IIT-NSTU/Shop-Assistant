
package Shop_Assistant;

import Templates.DashBoard_Template;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Advanced_Stock_Check_Page extends DashBoard_Template
{
    public JPanel output_panel,date_panel;
    public JLabel select_label,day_label,month_label,year_label,product_type_label,model_number_label;
    public JComboBox day_combobox,month_combobox,year_combobox,product_type_combobox,model_number_combobox;
    public JTextField show_quantity_textfield;
    
    public String day[] = new String[32];
    public String month[] = new String[13];
    public String year[] = new String[102];
    
    public Advanced_Stock_Check_Page()
    {
        setPageButton();
        setMainPanel();
        setOutputPanel();
        setStockCheckFeatures();
    }
    
    public void setPageButton()
    {
        getButton("Advanced Stock Check").setBackground(Color.LIGHT_GRAY);
        getButton("Advanced Stock Check").setForeground(Color.BLACK);
        getButton("Advanced Stock Check").setFont(new Font("Arial",Font.BOLD,16));
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
        output_panel.setLayout(new GridLayout(7,1));
        output_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        output_panel.setBackground(Color.WHITE);
        main_panel.add(output_panel,BorderLayout.CENTER);
    }
    
    public void setOutputPanel()
    {   
        date_panel = new JPanel();
        date_panel.setBackground(Color.WHITE);
        date_panel.setLayout(new GridLayout(2,3));
        output_panel.add(date_panel);
          
        day_label = new JLabel("Day");
        day_label.setFont(new Font("Arial",Font.BOLD,16));
        date_panel.add(day_label);
        
        month_label = new JLabel("Month");
        month_label.setFont(new Font("Arial",Font.BOLD,16));
        date_panel.add(month_label);
        
        year_label = new JLabel("Year");
        year_label.setFont(new Font("Arial",Font.BOLD,16));
        date_panel.add(year_label);
        
        setManualDate();
        
        day_combobox = new JComboBox(day);
        day_combobox.setFont(new Font("Arial",Font.BOLD,16));
        date_panel.add(day_combobox);
        
        month_combobox = new JComboBox(month);
        month_combobox.setFont(new Font("Arial",Font.BOLD,16));
        date_panel.add(month_combobox);
        
        year_combobox = new JComboBox(year);
        year_combobox.setFont(new Font("Arial",Font.BOLD,16));
        date_panel.add(year_combobox);
        
        product_type_label = new JLabel("Product Type");
        product_type_label.setBackground(Color.WHITE);
        product_type_label.setFont(new Font("Arial",Font.BOLD,16));
        output_panel.add(product_type_label);
        
        product_type_combobox = new JComboBox(setProductTypes());
        product_type_combobox.setFont(new Font("Arial",Font.BOLD,16));
        output_panel.add(product_type_combobox);
        
        model_number_label = new JLabel("Model Number");
        model_number_label.setBackground(Color.WHITE);
        model_number_label.setFont(new Font("Arial",Font.BOLD,16));        
        output_panel.add(model_number_label);
        
        model_number_combobox = new JComboBox();
        model_number_combobox.setEnabled(false);
        model_number_combobox.setFont(new Font("Arial",Font.BOLD,16));
        output_panel.add(model_number_combobox);
        
        show_quantity_textfield = new JTextField();
        show_quantity_textfield.setFont(new Font("Arial",Font.BOLD,14));
        show_quantity_textfield.setEditable(false);
        show_quantity_textfield.setBackground(Color.WHITE);
        output_panel.add(show_quantity_textfield);
    }
    
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
    
    public void setStockCheckFeatures()
    {
         product_type_combobox.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(!day_combobox.getSelectedItem().equals("")&&!month_combobox.getSelectedItem().equals("")&&!year_combobox.getSelectedItem().equals("")&&!product_type_combobox.getSelectedItem().equals(""))
                {
                    setModelNumberCombobox(day_combobox.getSelectedItem().toString()+"/"+month_combobox.getSelectedItem().toString()+"/"+year_combobox.getSelectedItem().toString());
                }
                else if(!month_combobox.getSelectedItem().equals("")&&!year_combobox.getSelectedItem().equals("")&&!product_type_combobox.getSelectedItem().equals(""))
                {
                    setModelNumberCombobox(month_combobox.getSelectedItem().toString()+"/"+year_combobox.getSelectedItem().toString());
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Date and Product Type");
                }
            }
  
        });  
        
    }
    
    public void setModelNumberCombobox(String date)
    {
        model_number_combobox.setEnabled(true);
        model_number_combobox.removeAllItems();
        model_number_combobox.addItem("");

        int sales_model_count=0,sales_stock,purchase_model_count=0,purchase_stock;

        try{
            
            BufferedReader br = new BufferedReader(new FileReader("Product_Type_&_Model_List.txt"));
            String s,result = "",model_number ="";
     
            while((s=br.readLine())!=null)
            {
                if(product_type_combobox.getSelectedItem().equals(s.substring(0, s.indexOf(","))))
                {
                    result = s.substring(s.indexOf(",")+1, s.length());    
                }
            }
         
            char demo[] = result.toCharArray();
            int i=0;
                
            while(i!=demo.length)
            {
                model_number = model_number + demo[i];
                if(demo[i]=='.')
                {
                    sales_stock = getRemainingQuantityUsingDate(date,model_number,"Sales_Database.txt");
                    purchase_stock = getRemainingQuantityUsingDate(date,model_number,"Purchase_Database.txt");
                            
                    if(sales_stock!=0)
                    {
                        sales_model_count = sales_model_count + sales_stock;
                        model_number_combobox.addItem(model_number); 
                        model_number_combobox.addItem("Sales "+sales_stock); 
                    }
                    if(purchase_stock!=0)
                    {
                        purchase_model_count = purchase_model_count + purchase_stock;
                        model_number_combobox.addItem(model_number); 
                        model_number_combobox.addItem("Purchase "+purchase_stock); 
                    }        
                    model_number = "";
                }
                i++;
            }
                    
            show_quantity_textfield.setText("Total "+product_type_combobox.getSelectedItem()+" Sold "+sales_model_count+" Purchased "+purchase_model_count);


            }catch(Exception ex){System.out.println(ex);}
    }
    
    public static void main(String[] args) 
    {
        Advanced_Stock_Check_Page  frame = new Advanced_Stock_Check_Page();
        frame.setVisible(true);
    }
}
