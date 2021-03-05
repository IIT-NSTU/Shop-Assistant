
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
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Stock_Check_Page extends DashBoard_Template
{
    public JPanel output_panel;
    public JLabel product_type_label,model_number_label;
    public JTextField show_quantity_textfield;
    public JComboBox product_type_combobox,model_number_combobox;
    public JButton total_item_button;
    
    public Stock_Check_Page()
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
        output_panel.setLayout(new GridLayout(6,1));
        output_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        output_panel.setBackground(Color.WHITE);
        main_panel.add(output_panel,BorderLayout.CENTER);

    }
    
    public void setOutputPanel()
    {    
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
        show_quantity_textfield.setFont(new Font("Arial",Font.BOLD,16));
        show_quantity_textfield.setEditable(false);
        show_quantity_textfield.setBackground(Color.WHITE);
        output_panel.add(show_quantity_textfield);
        
        total_item_button = new JButton("Total");
        total_item_button.setFont(new Font("Arial",Font.BOLD,16));
        output_panel.add(total_item_button);  
    }
    
    public void setStockCheckFeatures()
    {
        product_type_combobox.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                model_number_combobox.setEnabled(true);
                model_number_combobox.removeAllItems();
                model_number_combobox.addItem("");
                
                try{                    
                BufferedReader br = new BufferedReader(new FileReader("Product_Type_&_Model_List.txt"));
                String s,result="",answer="";
     
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
                    answer = answer + demo[i];
                    if(demo[i]=='.')
                    {
                        model_number_combobox.addItem(answer);
                        answer="";
                    }
                    i++;
                }
                
                total_item_button.setText("Total "+product_type_combobox.getSelectedItem());
                                
                
            }catch(Exception ex){System.out.println(ex);}
               
            }
  
        });
        
        
        model_number_combobox.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                show_quantity_textfield.setText(""+getTotalStock((String)model_number_combobox.getSelectedItem(),"Purchase_Database.txt","Sales_Database.txt"));
            }
        });
        
        total_item_button.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(product_type_combobox.getSelectedItem())||product_type_combobox.getSelectedItem()==null)
                {
                    JOptionPane.showMessageDialog(null, "Please Select Product Type");
                }
                else
                {
                    try{
                        
                    int i,model_count=0,stock;
                    
                    for( i = 1 ; i <= model_number_combobox.getItemCount() ; i++ )
                    {      
                            System.out.println("Model Number : "+model_number_combobox.getItemAt(i).toString()+" Stock Number : "+getTotalStock(model_number_combobox.getItemAt(i).toString(),"Purchase_Database.txt","Sales_Database.txt"));
                            
                            stock = getTotalStock(model_number_combobox.getItemAt(i).toString(),"Purchase_Database.txt","Sales_Database.txt");
                        
                            if(stock!=0)
                            {
                                model_count = model_count + stock;
                            }
                            
                        total_item_button.setText("Total "+product_type_combobox.getSelectedItem()+" is "+model_count);
                    }

                    }catch(Exception ex){System.out.println(ex);}
                }
            }
        });
    
    }
    
    public static void main(String[] args) 
    {
        Stock_Check_Page frame = new Stock_Check_Page();
        frame.setVisible(true);
    }
}
