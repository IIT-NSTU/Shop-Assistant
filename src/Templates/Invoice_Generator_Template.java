
package Templates;

import Shop_Assistant.Create_Purchase_Invoice_Page;
import Shop_Assistant.Create_Sales_Invoice_Page;
import Shop_Assistant.Home_Page;
import Shop_Assistant.Login_Page;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class Invoice_Generator_Template extends Frame_Setup
{
    public JPanel horizontal_panel_top,main_panel,horizontal_panel_bottom,input_panel,output_panel,total_information_panel;
    public JLabel date_label,id_label,name_label,address_label,mobile_number_label,product_type_label,model_number_label,quantity_label,unit_price_label,payment_label,paid_label,due_label,subtotal_label,discount_label,total_payment_label,total_paid_label,total_due_label;
    public JTextField date_textfield,id_textfield,name_textfield,address_textfield,mobile_number_textfield,quantity_textfield,unit_price_textfield,payment_textfield,subtotal_textfield,discount_textfield,total_payment_textfield,total_paid_textfield,total_due_textfield;
    public JButton home_button,create_sales_invoice_button,create_purchase_invoice_button,logout_button,clear_button,add_to_cart_button,print_button,delete_button;
    public JEditorPane cart_editorpane;
    public JScrollPane scroll;
    
    public JButton horizontal_panel_top_buttons[] = {home_button,create_sales_invoice_button,create_purchase_invoice_button,logout_button};
    public JButton horizontal_panel_bottom_buttons[] = {clear_button,add_to_cart_button,delete_button,print_button};
    public JComboBox product_type_combobox,model_number_combobox;
    public int subtotal=0,total_payment,total_paid=0,total_due=0,serial_no=1;
    public String HTML_Text = "";
    public String raw_data = "";

    public Font font = new Font("Arial",Font.BOLD,16);
    
    public Invoice_Generator_Template(String keyword,String file_name)
    {
        setFrame();
        setContainer();
        setPanels();
        setButtons();
        setInputPanel(keyword,file_name);
        setOutputPanel();
        setListeners(keyword,file_name);
    }
    
    public void setFrame()
    {
        super.setFrame();
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
    }
    
    public void setContainer()
    {
        //Method Overriding Contept Used
        super.setContainer();  //Here we get setContainer() of parrent class
        container.setLayout(new BorderLayout(15,0));
    }
    
    public void setPanels()
    {
        null_label = new JLabel("");
        container.add(null_label,BorderLayout.WEST);
        null_label = new JLabel("");
        container.add(null_label,BorderLayout.EAST);
        
        horizontal_panel_top = new JPanel();
        horizontal_panel_top.setLayout(new GridLayout(1,4));
        horizontal_panel_top.setCursor(new Cursor(Cursor.HAND_CURSOR));
        container.add(horizontal_panel_top,BorderLayout.NORTH);
        
        horizontal_panel_bottom = new JPanel();
        horizontal_panel_bottom.setLayout(new GridLayout(1,4));
        horizontal_panel_bottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        container.add(horizontal_panel_bottom,BorderLayout.SOUTH);
        
        main_panel = new JPanel();
        main_panel.setLayout(new GridLayout(1,2));
        container.add(main_panel,BorderLayout.CENTER);
        
        input_panel = new JPanel();
        input_panel.setLayout(new GridLayout(10,2));
        input_panel.setBackground(Color.WHITE);
        main_panel.add(input_panel);
        
        output_panel = new JPanel();
        output_panel.setLayout(new BorderLayout());
        main_panel.add(output_panel);
    }
    
    public void setButtons()
    {
        String horizontal_panel_top_button_names[] = {"Home","Create Sales Invoice","Create Purchase Invoice","Log Out"};
        String horizontal_panel_bottom_button_names[] = {"Clear","Add to Cart","Finish","Print"};
        ImageIcon horizontal_panel_bottom_button_icons[]  = {clear_button_icon,add_to_cart_button_icon,delete_button_icon,print_button_icon}; 

        for(int i = 0 ; i < horizontal_panel_top_buttons.length ; i++)
        {
            horizontal_panel_top_buttons[i] = new JButton(horizontal_panel_top_button_names[i]);
            horizontal_panel_top_buttons[i].setName(horizontal_panel_top_button_names[i]);
            horizontal_panel_top_buttons[i].setFont(new Font("Arial",Font.PLAIN,16));
            horizontal_panel_top_buttons[i].setBackground(Color.DARK_GRAY);
            horizontal_panel_top_buttons[i].setForeground(Color.WHITE);
            horizontal_panel_top_buttons[i].setFocusPainted(false);
            horizontal_panel_top.add(horizontal_panel_top_buttons[i]);
        }
        
        horizontal_panel_top_buttons[0].setIcon(home_button_icon);
        horizontal_panel_top_buttons[3].setIcon(logout_button_icon);
        
        for(int i = 0 ; i < horizontal_panel_bottom_buttons.length ; i++)
        {
            horizontal_panel_bottom_buttons[i] = new JButton(horizontal_panel_bottom_button_names[i]);
            horizontal_panel_bottom_buttons[i].setName(horizontal_panel_bottom_button_names[i]);
            horizontal_panel_bottom_buttons[i].setFont(new Font("Arial",Font.PLAIN,16));
            horizontal_panel_bottom_buttons[i].setBackground(Color.DARK_GRAY);
            horizontal_panel_bottom_buttons[i].setForeground(Color.WHITE);
            horizontal_panel_bottom_buttons[i].setIcon(horizontal_panel_bottom_button_icons[i]);
            horizontal_panel_bottom_buttons[i].setFocusPainted(false);
            horizontal_panel_bottom.add(horizontal_panel_bottom_buttons[i]);
        }
    }
    
    public void setInputPanel(String keyword,String file_name)
    {   
        id_label = new JLabel();
        id_label.setFont(font);
        input_panel.add(id_label);
        
        if("Customer".equals(keyword))
        {
            id_label.setText("Sales Id");
        }
        else
        {
            id_label.setText("Purchase Id");
        }
        
        id_textfield = new JTextField();
        id_textfield.setEditable(false);
        id_textfield.setFont(font);
        input_panel.add(id_textfield);
        setId(file_name);
        
        date_label = new JLabel("Date & Time");
        date_label.setFont(font);
        input_panel.add(date_label);
        date_textfield = new JTextField(""+setDate());
        date_textfield.setEditable(false);
        date_textfield.setFont(font);
        input_panel.add(date_textfield);

        name_label = new JLabel("Enter "+keyword+"'s Name");
        name_label.setFont(font);
        input_panel.add(name_label);
        name_textfield = new JTextField();
        name_textfield.setFont(font);
        input_panel.add(name_textfield);

        address_label = new JLabel("Enter "+keyword+"'s Address");
        address_label.setFont(font);
        input_panel.add(address_label);
        address_textfield = new JTextField();
        address_textfield.setFont(font);
        input_panel.add(address_textfield);
        
        mobile_number_label = new JLabel("Enter Mobile Number");
        mobile_number_label.setFont(font);
        input_panel.add(mobile_number_label);
        mobile_number_textfield = new JTextField();
        mobile_number_textfield.setFont(font);
        input_panel.add(mobile_number_textfield);
        
        product_type_label = new JLabel("Enter Product's Type");
        product_type_label.setFont(font);
        input_panel.add(product_type_label);
        
        product_type_combobox = new JComboBox(setProductTypes());
        product_type_combobox.setFont(font);
        input_panel.add(product_type_combobox);
               
        model_number_label = new JLabel("Enter Product's Model");
        model_number_label.setFont(font);
        input_panel.add(model_number_label);
        model_number_combobox = new JComboBox();
        model_number_combobox.setEnabled(false);
        model_number_combobox.setFont(font);
        input_panel.add(model_number_combobox);
        
        quantity_label = new JLabel("Enter Product's Quantity");
        quantity_label.setFont(font);
        input_panel.add(quantity_label);
        quantity_textfield = new JTextField("");
        quantity_textfield.setFont(font);
        input_panel.add(quantity_textfield);
        
        unit_price_label = new JLabel("Enter Per Item Price");
        unit_price_label.setFont(font);
        input_panel.add(unit_price_label);
        unit_price_textfield = new JTextField("");
        unit_price_textfield.setFont(font);
        input_panel.add(unit_price_textfield);
         
        payment_label = new JLabel("Product's Payment");
        payment_label.setFont(font);
        input_panel.add(payment_label);
        payment_textfield = new JTextField("");
        payment_textfield.setFont(font);
        payment_textfield.setEditable(false);
        input_panel.add(payment_textfield);
        
    }
    
    public void setOutputPanel()
    {
       cart_editorpane = new JEditorPane();
       cart_editorpane.setContentType("text/html");
       cart_editorpane.setText(HTML_Text);
       cart_editorpane.setBackground(Color.white);
       cart_editorpane.setEditable(false);
       scroll = new JScrollPane(cart_editorpane);
       output_panel.add(scroll,BorderLayout.CENTER);
       
       total_information_panel = new JPanel();
       GridLayout gridlayout = new GridLayout(5,2);
       gridlayout.setVgap(7);
       total_information_panel.setLayout(gridlayout);
       output_panel.add(total_information_panel,BorderLayout.SOUTH);
       
       subtotal_label = new JLabel("        Subtotal");
       subtotal_label.setFont(new Font("Arial",Font.BOLD,18));
       total_information_panel.add(subtotal_label);
       subtotal_textfield = new JTextField("");
       subtotal_textfield.setFont(new Font("Arial",Font.BOLD,18));
       subtotal_textfield.setEditable(false);
       total_information_panel.add(subtotal_textfield);
       
       discount_label = new JLabel("        Discount");
       discount_label.setFont(new Font("Arial",Font.BOLD,18));
       total_information_panel.add(discount_label);
       discount_textfield = new JTextField("");
       discount_textfield.setFont(new Font("Arial",Font.BOLD,18));
       total_information_panel.add(discount_textfield);
       
       total_payment_label = new JLabel("        Total Payment");
       total_payment_label.setFont(new Font("Arial",Font.BOLD,18));
       total_information_panel.add(total_payment_label);
       total_payment_textfield = new JTextField("");
       total_payment_textfield.setFont(new Font("Arial",Font.BOLD,18));
       total_payment_textfield.setEditable(false);
       total_information_panel.add(total_payment_textfield);
       
       total_paid_label = new JLabel("        Total Paid Amount");
       total_paid_label.setFont(new Font("Arial",Font.BOLD,18));
       total_information_panel.add(total_paid_label);
       total_paid_textfield = new JTextField("");
       total_paid_textfield.setFont(new Font("Arial",Font.BOLD,18));
       total_information_panel.add(total_paid_textfield);
       
       total_due_label = new JLabel("        Total Due Amount");
       total_due_label.setFont(new Font("Arial",Font.BOLD,18));
       total_information_panel.add(total_due_label);
       total_due_textfield = new JTextField("");
       total_due_textfield.setFont(new Font("Arial",Font.BOLD,18));
       total_due_textfield.setEditable(false);
       total_information_panel.add(total_due_textfield);
    }
    
    public void setId(String file_name)
    {
    try{
        BufferedReader br = new BufferedReader(new FileReader(file_name));
        String s = "",temp="";
        while((s=br.readLine())!=null)
        {
            temp = s;
        }
 
        if("".equals(temp))
        {
            if("Sales_Database.txt".equals(file_name))
            {
                id_textfield.setText("S200000001");
            }
            else
            {
                id_textfield.setText("P100000001");
            }
        }
        else
        {
            if("Sales_Database.txt".equals(file_name))
            {
                id_textfield.setText("S"+(Integer.parseInt(temp.substring(1, 10))+1));
            }
            else
            {
                id_textfield.setText("P"+(Integer.parseInt(temp.substring(1, 10))+1));
            }
        } 
            
        }catch(Exception e){}
    }
    
    public void setPayment()
    {
        try{
        int answer = Math.round(Float.parseFloat(quantity_textfield.getText())*Float.parseFloat(unit_price_textfield.getText()));
                        
        if(answer>1000000000)
        {
                JOptionPane.showMessageDialog(null, "Please Do Not Enter Large Digits");
                quantity_textfield.setText("");
                unit_price_textfield.setText("");
                payment_textfield.setText("");
        }
        else
        {
            payment_textfield.setText(""+answer);
        }  }catch(Exception ex) {}                 
    }
    
    public void setDue()
    {
        try{
        int answer = Math.round(Float.parseFloat(total_payment_textfield.getText())-Float.parseFloat(total_paid_textfield.getText()));
                        
        if(answer<0)
        {
            JOptionPane.showMessageDialog(null, "You Paid More Than Required Amount");     
            total_paid_textfield.setText("");
            total_due_textfield.setText("");
        }
        else
        {
            total_due_textfield.setText(""+answer);
        }  }catch(Exception ex) {}             
    }
    
    public JButton getButton(String button_name)
    {
        int i,j; JButton button = new JButton();

        for(i = 0; i < horizontal_panel_top_buttons.length ; i++)
        {
            if(horizontal_panel_top_buttons[i].getName().equals(button_name))
            {
                button = horizontal_panel_top_buttons[i];
                break;
            }
        }
        
        for(j = 0; j < horizontal_panel_bottom_buttons.length ; j++)
        {   
            if(horizontal_panel_bottom_buttons[j].getName().equals(button_name))
            {
                button = horizontal_panel_bottom_buttons[j];
            } 
        }
        
        return button;
    }
    
    public boolean check_all_filled_up()
    {
        if("".equals(id_textfield.getText())||"".equals(date_textfield.getText())||"".equals(name_textfield.getText())||"".equals(address_textfield.getText())||"".equals(mobile_number_textfield.getText())||"".equals(product_type_combobox.getSelectedItem())||"".equals(model_number_combobox.getSelectedItem())||"".equals(quantity_textfield.getText())||"".equals(unit_price_textfield.getText())||"".equals(payment_textfield.getText()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void add_to_cart(String file_name)
    {
        subtotal = subtotal + Integer.parseInt(payment_textfield.getText());
        subtotal_textfield.setText(""+subtotal);
        
        setDue();
   
        product_type_combobox.setSelectedItem("");
        model_number_combobox.setSelectedItem("");
        quantity_textfield.setText("");
        unit_price_textfield.setText("");
        payment_textfield.setText(""); 
        discount_textfield.setText(""); 
        total_paid_textfield.setText(""); 
    }
    
 
    public void addHTMLbegintext(String keyword)
    {
        HTML_Text = HTML_Text 
            
            +"<table width=\"100%\">" 
            +"<tr style=\"text-align:center;font-size:18px;font-weight:bold;font-family:arial;color:red;\">Samir Electronics</tr>"
            +"<tr style=\"font-family:arial;font-size:13px;font-weight:bold;text-align:center;color:blue;\">WALTON</tr>"
            + "<tr style=\"text-align:center;font-size:13px;font-weight:bold;font-family:arial;\">Exclusive Showroom</tr>"
            +"<tr style=\"text-align:center;font-size:9px;font-family:arial;\">Saiham Future Complex , Madhabpur , Habiganj</tr>"
            +"<tr style=\"text-align:center;font-size:9px;font-family:arial;\">Mobile : 01748987951 , 01746003532</tr>"
            +"<tr style=\"text-align:center;font-size:7px;font-family:arial;\">Money Receipt</tr>"
            +"</table>"
            +"<br>"
            +"<table width=\"100%\" style=\"font-size:8px\" >"
            +"<tr>"
            +"<td>"+keyword+" Name</td><td>:</td><td>"+name_textfield.getText()+"</td><td></td><td></td><td></td><td></td><td></td><td></td><td>Invoice No</td><td>:</td><td>"+id_textfield.getText()+"</td>"
            +"</tr>"
            +"<tr>"
            +"<td>"+keyword+" Address</td><td>:</td><td>"+address_textfield.getText()+"</td><td></td><td></td><td></td><td></td><td></td><td></td><td>Date & Time</td><td>:</td><td>"+date_textfield.getText()+"</td>"
            +"</tr>"
            +"<tr>"
            +"<td>"+keyword+" Mobile Number</td><td>:</td><td>"+mobile_number_textfield.getText()+"</td>"
            + "</tr>"
            + "</table>"
            +"<br>"
            +"<br>"
            +"<br>"
            +"<table width=\"100%\" style=\"font-size:8px\" >"
            +"<tr>"
            +"<td>No.</td><td>Product Type</td><td>Product Model Number</td><td style=\"text-align:right\">Quantity</td><td style=\"text-align:right\">Unit Price</td><td style=\"text-align:right\">Payment</td>"
            +"</tr>"
            +"<tr>"
            +"<td>"+serial_no+"</td><td>"+product_type_combobox.getSelectedItem()+"</td><td>"+model_number_combobox.getSelectedItem()+"</td><td style=\"text-align:right\">"+quantity_textfield.getText()+"</td><td style=\"text-align:right\">"+unit_price_textfield.getText()+"</td><td style=\"text-align:right\">"+payment_textfield.getText()+"</td>"
            +"</tr>"
               
            ;
        
        raw_data = raw_data + id_textfield.getText() +","+ date_textfield.getText() +","+ name_textfield.getText() +","+ address_textfield.getText() +","+ mobile_number_textfield.getText() +","+ product_type_combobox.getSelectedItem() +","+ model_number_combobox.getSelectedItem() +","+ quantity_textfield.getText() +","+ unit_price_textfield.getText() +","+ payment_textfield.getText()+"," ;
    }
    
    public void addHTMLmidtext()
    {
        HTML_Text = HTML_Text 
                        +"<tr>"
                        +"<td>"+(serial_no=serial_no+1)+"</td><td>"+product_type_combobox.getSelectedItem()+"</td><td>"+model_number_combobox.getSelectedItem()+"</td><td style=\"text-align:right\">"+quantity_textfield.getText()+"</td><td style=\"text-align:right\">"+unit_price_textfield.getText()+"</td><td style=\"text-align:right\">"+payment_textfield.getText()+"</td>"
                        +"</tr>"
                        ;
        
        raw_data = raw_data + product_type_combobox.getSelectedItem() +","+ model_number_combobox.getSelectedItem() +","+ quantity_textfield.getText() +","+ unit_price_textfield.getText() +","+ payment_textfield.getText()+"," ;

    }
    
    public void addHTMLendtext(String keyword)
    {
        HTML_Text = HTML_Text 
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Subtotal</td><td style=\"text-align:right\">"+subtotal_textfield.getText()+"</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Discount ( - )</td><td style=\"text-align:right\">"+discount_textfield.getText()+"</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Total Payment</td><td style=\"text-align:right\">"+total_payment_textfield.getText()+"</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Paid Amount</td><td style=\"text-align:right\">"+total_paid_textfield.getText()+"</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Due Amount</td><td style=\"text-align:right\">"+total_due_textfield.getText()+"</td>"
                        +"</tr>"
                        + "</table>"
                        +"<br>"
                        +"<br>"
                        +"<table width=\"100%\" style=\"font-size:10px\">"
                        +"<tr>"
                        +"<td>-----------------------</td><td style=\"text-align:right\">-----------------------</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td>Owner's Sign</td><td style=\"text-align:right\">"+keyword+"'s Sign</td>"
                        +"</tr>"
                        + "</table>"
                        ;
        
        raw_data = raw_data + total_payment_textfield.getText() +","+total_paid_textfield.getText() +","+total_due_textfield.getText();
    }
    
    public void setListeners(String keyword,String file_name)
    {
        name_textfield.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){} 
     
            public void keyPressed(KeyEvent ke) {}

            public void keyReleased(KeyEvent ke) 
            {
                try{
                char demo[] = name_textfield.getText().toCharArray();
                String result="";
                int i = 0 ;
                
                if(demo[0]>='a'&&demo[0]<='z')
                {
                    demo[0] = (char)(demo[0]-32); 
                }
                
                while(i!=demo.length)
                {
                    if((demo[i]<'a'||demo[i]>'z')&&(demo[i]<'A'||demo[i]>'Z')&&demo[i]!=(char)32&&demo[i]!=(char)45)
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Valid Name (not invalid characters)"); 
                        break;
                    }
                    
                    if(demo[i]==(char)32&&demo[i+1]>='a'&&demo[i+1]<='z')
                    {
                        demo[i+1] = (char)(demo[i+1]-32);
                    }
                    
                    result = result + demo[i];
                    i++;
                }
                
                name_textfield.setText(""+result);
                }catch(Exception e){}
            }
        
        });
        
        address_textfield.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){}    
           
            public void keyPressed(KeyEvent ke){}    

            public void keyReleased(KeyEvent ke) 
            {
                try{
                char demo[] = address_textfield.getText().toCharArray();
                String result="";
                int i = 0 ;
                
                if(demo[0]>='a'&&demo[0]<='z')
                {
                    demo[0] = (char)(demo[0]-32); 
                }
                
                while(i!=demo.length)
                {
                    if((demo[i]<'a'||demo[i]>'z')&&(demo[i]<'A'||demo[i]>'Z')&&demo[i]!=(char)32&&demo[i]!=(char)45&&demo[i]!=(char)47&&(demo[i]<'0'||demo[i]>'9'))
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Valid Address (not invalid characters)");
                        break;
                    }
                    
                    if(demo[i]==(char)32&&demo[i+1]>='a'&&demo[i+1]<='z')
                    {
                        demo[i+1] = (char)(demo[i+1]-32);
                    }
                    
                    result = result + demo[i];
                    i++;
                }
                
                address_textfield.setText(""+result);
                }catch(Exception e){}
            }
        });
        
        mobile_number_textfield.addKeyListener(new KeyListener()
        {
           
            public void keyTyped(KeyEvent ke){}    

            public void keyPressed(KeyEvent ke) {} 
            
            public void keyReleased(KeyEvent ke) 
            {
                mobile_number_textfield.setToolTipText(mobile_number_textfield.getText().length()+" Digits");
                        
                if(!isDigit(ke.getKeyChar()))
                {    
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits"); 
                    mobile_number_textfield.setText("");
                }
                
                if(mobile_number_textfield.getText().length()>11)
                {
                   JOptionPane.showMessageDialog(null, "Please Enter Valid Mobile Number (not more than 11 digits)");
                   mobile_number_textfield.setText("");
                }
            }
        
        });
    
        product_type_combobox.addActionListener(new ActionListener()
        {
        
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
            }catch(Exception ex){}
               
            }
  
        });
        
        model_number_combobox.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e)
            {
                quantity_textfield.setText("");
            }
        
        });
        
        unit_price_textfield.addKeyListener(new KeyListener()
        { 
            public void keyTyped(KeyEvent ke){}  
            
            public void keyPressed(KeyEvent ke){} 
           
            public void keyReleased(KeyEvent ke) 
            {
                String input_text = unit_price_textfield.getText();
                
                char demo[] = input_text.toCharArray();
                
                int i = 0,count=0;
                
                while(i!=input_text.length())
                {
                    if(demo[i]=='.')
                    {
                        count++;
                    }
                    i++;
                }
             
                if(count>1||(ke.getKeyChar()!='0'&&ke.getKeyChar()!='1'&&ke.getKeyChar()!='2'&&ke.getKeyChar()!='3'&&ke.getKeyChar()!='4'&&ke.getKeyChar()!='5'&&ke.getKeyChar()!='6'&&ke.getKeyChar()!='7'&&ke.getKeyChar()!='8'&&ke.getKeyChar()!='9'&&ke.getKeyChar()!=(char)8&&ke.getKeyChar()!=(char)10&&ke.getKeyChar()!=(char)46))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits");
                    unit_price_textfield.setText("");
                } 
                
                else
                {   
                    setPayment();
                    setDue();
                }
             }
        });
        
        discount_textfield.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){}    
          
            public void keyPressed(KeyEvent ke) {} 
            
            public void keyReleased(KeyEvent ke) 
            {
                if(!isDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits");
                    discount_textfield.setText("");
                }    
                else
                {
                    try{
                    total_payment = Integer.parseInt(subtotal_textfield.getText())-(Integer.parseInt(discount_textfield.getText()));
                    
                    if(total_payment<=0)
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Valid Digits");                        
                        discount_textfield.setText("");
                    }
                    else
                    {
                        total_payment_textfield.setText(""+total_payment);
                        total_paid_textfield.setText("");
                    }
                    }catch(Exception e) {}
                }
             }
        });
        
        total_paid_textfield.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){}    
          
            public void keyPressed(KeyEvent ke) {} 
            
            public void keyReleased(KeyEvent ke) 
            {
                if(!isDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits");
                    total_paid_textfield.setText("");
                }    
                else
                {    
                    setDue();
                }
             }
        });
        
        getButton("Home").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(cart_editorpane.getText()))
                {
                    setReminder();
                }
                dispose();
                Home_Page frame = new Home_Page();
                frame.setVisible(true);
            }
        });
        
        getButton("Create Sales Invoice").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(cart_editorpane.getText()))
                {
                    setReminder();
                }
                dispose();             
                Create_Sales_Invoice_Page frame = new Create_Sales_Invoice_Page("Customer","Sales_Database.txt");
                frame.setVisible(true);
            }
        });
        
        getButton("Create Purchase Invoice").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(cart_editorpane.getText()))
                {
                    setReminder();
                }
                dispose();
                Create_Purchase_Invoice_Page frame = new Create_Purchase_Invoice_Page("Company","Purchase_Database.txt");
                frame.setVisible(true);  
            }
        });
        
        getButton("Log Out").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(cart_editorpane.getText()))
                {
                    setReminder();
                }
                dispose();
                Login_Page frame = new Login_Page();
                frame.setVisible(true);
            }
        });
        
        getButton("Clear").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                name_textfield.setText("");
                address_textfield.setText("");
                mobile_number_textfield.setText("");
                product_type_combobox.setSelectedItem("");
                model_number_combobox.setSelectedItem("");
                quantity_textfield.setText("");
                unit_price_textfield.setText("");
                payment_textfield.setText("");
                subtotal_textfield.setText("");
                discount_textfield.setText("");
                total_payment_textfield.setText("");
                total_paid_textfield.setText("");
                total_due_textfield.setText("");
                cart_editorpane.setText("");
                HTML_Text = "";
                subtotal=0;
                total_paid=0;
                total_due=0;
                serial_no=1;
            }
        });
        
        getButton("Add to Cart").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!check_all_filled_up()||"0".equals(quantity_textfield.getText())||"0".equals(unit_price_textfield.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter All Valid Information");
                }
                else 
                { 
                    int choice = JOptionPane.showConfirmDialog(null, "Are Your Sure?\n\n\n\n\nID : "+id_textfield.getText()+"\nDate : "+date_textfield.getText()+"\n"+keyword+" Name : "+name_textfield.getText()+"\n"+keyword+" Address : "+address_textfield.getText()+"\n"+keyword+" Mobile Number : "+mobile_number_textfield.getText()+"\nProduct Type : "+product_type_combobox.getSelectedItem()+"\nModel Number : "+model_number_combobox.getSelectedItem()+"\nQuantity : "+quantity_textfield.getText()+"\nPer Item Price : "+unit_price_textfield.getText()+"\nPayment : "+payment_textfield.getText()+"\n\n\n\n\n\n","Confirm",JOptionPane.YES_NO_OPTION);
                    
                    if(choice == JOptionPane.YES_OPTION)
                    {
                        
                    if(!cart_editorpane.getText().contains("WALTON"))
                    {
                        addHTMLbegintext(keyword);
                        cart_editorpane.setText(HTML_Text);
                        add_to_cart(file_name);
                    }
                    else
                    {
                        addHTMLmidtext();
                        cart_editorpane.setText(HTML_Text);
                        add_to_cart(file_name);
                    }
                    
                    }
                }
            }
        });
        
        getButton("Finish").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(subtotal_textfield.getText())||"".equals(discount_textfield.getText())||"".equals(total_payment_textfield.getText())||"".equals(total_paid_textfield.getText())||"".equals(total_due_textfield.getText())||!cart_editorpane.getText().contains("WALTON"))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Discount and Paid Amount");
                }
                else 
                {
                        setDue();
                        addHTMLendtext(keyword);
                        cart_editorpane.setText(HTML_Text);
                        getButton("Finish").setEnabled(false);
                        getButton("Add to Cart").setEnabled(false);
                        getButton("Clear").setEnabled(false);
                        getButton("Print").setEnabled(true);
                        
                        
                        try {
                        File f2 = new File("Income_Cost_Database.txt");
                        PrintWriter q = new PrintWriter(new FileOutputStream(f2, true));
                        if ("Sales_Database.txt".equals(file_name)) {
                            q.append("S200000000" + "," + date_textfield.getText() + "," + "Income By Product Sale " + "," + name_textfield.getText() + "," + address_textfield.getText() + "," + mobile_number_textfield.getText() + "," + total_paid_textfield.getText() + "\n");
                            q.close();
                        } else {
                            q.append("P100000000" + "," + date_textfield.getText() + "," + "Cost By Product Purchase " + "," + name_textfield.getText() + "," + address_textfield.getText() + "," + mobile_number_textfield.getText() + "," + total_paid_textfield.getText() + "\n");
                            q.close();
                        }
                    } catch (FileNotFoundException ex) {
                    }
            
                    
            try
            {
                File f = new File(file_name);
                   
                PrintWriter p = new PrintWriter(new FileOutputStream(f,true));
                p.append(raw_data+"\n");
                p.close();
                
            } 
            catch (FileNotFoundException ex) {}
                }              
            }
        });
        
        getButton("Print").setEnabled(false);
        
        getButton("Print").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(cart_editorpane.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Add Something To The Cart");
                }
                else 
                {
                    try {
                        cart_editorpane.print();
                    } catch (PrinterException ex) {
                    }   
                }    
            }
        });    
    }
}
