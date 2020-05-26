
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Invoice_Generator_Template extends Frame_Setup
{
    public JPanel horizontal_panel_top,main_panel,horizontal_panel_bottom,input_panel,output_panel,total_information_panel;
    public JLabel null_label,date_label,id_label,name_label,address_label,mobile_number_label,product_type_label,model_number_label,quantity_label,per_item_price_label,payment_label,paid_label,due_label,total_payment_label,total_paid_label,total_due_label;
    public JTextField date_textfield,id_textfield,name_textfield,address_textfield,mobile_number_textfield,quantity_textfield,per_item_price_textfield,payment_textfield,paid_textfield,due_textfield,total_payment_textfield,total_paid_textfield,total_due_textfield;
    public JButton home_button,create_sales_invoice_button,create_purchase_invoice_button,logout_button,clear_button,add_to_cart_button,print_button,delete_button;
    public JTextArea cart_textarea;
    public JScrollPane scroll;
    
    public JButton horizontal_panel_top_buttons[] = {home_button,create_sales_invoice_button,create_purchase_invoice_button,logout_button};
    public JButton horizontal_panel_bottom_buttons[] = {clear_button,add_to_cart_button,delete_button,print_button};
    public JComboBox product_type_combobox,model_number_combobox;
    public int total_payment=0,total_paid=0,total_due=0;

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
        this.setSize(950, 550);
    }
    
    public void setContainer()
    {
        super.setContainer();
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
        input_panel.setLayout(new GridLayout(12,2));
        input_panel.setBackground(Color.WHITE);
        main_panel.add(input_panel);
        
        output_panel = new JPanel();
        output_panel.setLayout(new BorderLayout());
        main_panel.add(output_panel);
    }
    
    public void setButtons()
    {
        String horizontal_panel_top_button_names[] = {"Home","Create Sales Invoice","Create Purchase Invoice","Log Out"};
        String horizontal_panel_bottom_button_names[] = {"Clear","Add to Cart","Delete","Print"};
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
        
        per_item_price_label = new JLabel("Enter Per Item Price");
        per_item_price_label.setFont(font);
        input_panel.add(per_item_price_label);
        per_item_price_textfield = new JTextField("");
        per_item_price_textfield.setFont(font);
        input_panel.add(per_item_price_textfield);
         
        payment_label = new JLabel("Product's Payment");
        payment_label.setFont(font);
        input_panel.add(payment_label);
        payment_textfield = new JTextField("");
        payment_textfield.setFont(font);
        payment_textfield.setEditable(false);
        input_panel.add(payment_textfield);
        
        paid_label = new JLabel("Enter Paid Amount");
        paid_label.setFont(font);
        input_panel.add(paid_label);
        paid_textfield = new JTextField("");
        paid_textfield.setFont(font);
        input_panel.add(paid_textfield);
        
        due_label = new JLabel("Due Amount");
        due_label.setFont(font);
        input_panel.add(due_label);
        due_textfield = new JTextField("");
        due_textfield.setFont(font);
        due_textfield.setEditable(false);
        input_panel.add(due_textfield);
    }
    
    public void setOutputPanel()
    {
       cart_textarea = new JTextArea();
       cart_textarea.setFont(new Font("Arial",Font.BOLD,14));
       cart_textarea.setBackground(Color.white);
       cart_textarea.setEditable(false);
       scroll = new JScrollPane(cart_textarea);
       output_panel.add(scroll,BorderLayout.CENTER);
       
       total_information_panel = new JPanel();
       GridLayout gridlayout = new GridLayout(3,2);
       gridlayout.setVgap(5);
       total_information_panel.setLayout(gridlayout);
       output_panel.add(total_information_panel,BorderLayout.SOUTH);
       
       total_payment_label = new JLabel("        Total Payment");
       total_payment_label.setFont(font);
       total_information_panel.add(total_payment_label);
       total_payment_textfield = new JTextField("");
       total_payment_textfield.setFont(font);
       total_payment_textfield.setEditable(false);
       total_information_panel.add(total_payment_textfield);
       
       total_paid_label = new JLabel("        Total Paid Amount");
       total_paid_label.setFont(font);
       total_information_panel.add(total_paid_label);
       total_paid_textfield = new JTextField("");
       total_paid_textfield.setFont(font);
       total_paid_textfield.setEditable(false);
       total_information_panel.add(total_paid_textfield);
       
       total_due_label = new JLabel("        Total Due Amount");
       total_due_label.setFont(font);
       total_information_panel.add(total_due_label);
       total_due_textfield = new JTextField("");
       total_due_textfield.setFont(font);
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
        int answer = Math.round(Float.parseFloat(quantity_textfield.getText())*Float.parseFloat(per_item_price_textfield.getText()));
                        
        if(answer>1000000000)
        {
                JOptionPane.showMessageDialog(null, "Please Do Not Enter Large Digits");
                quantity_textfield.setText("");
                per_item_price_textfield.setText("");
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
        int answer = Math.round(Float.parseFloat(payment_textfield.getText())-Float.parseFloat(paid_textfield.getText()));
                        
        if(answer<0)
        {
            JOptionPane.showMessageDialog(null, "You Paid More Than Required Amount");     
            paid_textfield.setText("");
            due_textfield.setText("");
        }
        else
        {
            due_textfield.setText(""+answer);
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
        if("".equals(id_textfield.getText())||"".equals(date_textfield.getText())||"".equals(name_textfield.getText())||"".equals(address_textfield.getText())||"".equals(mobile_number_textfield.getText())||"".equals(product_type_combobox.getSelectedItem())||"".equals(model_number_combobox.getSelectedItem())||"".equals(quantity_textfield.getText())||"".equals(per_item_price_textfield.getText())||"".equals(payment_textfield.getText())||"".equals(paid_textfield.getText())||"".equals(due_textfield.getText()))
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
        try
            {
                File f = new File(file_name);
                   
                PrintWriter p = new PrintWriter(new FileOutputStream(f,true));
                p.append(id_textfield.getText()+","+date_textfield.getText()+","+name_textfield.getText()+","+address_textfield.getText()+","+mobile_number_textfield.getText()+","+product_type_combobox.getSelectedItem()+","+model_number_combobox.getSelectedItem()+","+quantity_textfield.getText()+","+per_item_price_textfield.getText()+","+payment_textfield.getText()+","+paid_textfield.getText()+","+due_textfield.getText()+"\n");
                p.close();
                
                
                File f2 = new File("Income_Cost_Database.txt");
                   
                PrintWriter q = new PrintWriter(new FileOutputStream(f2,true));
                
                if("Sales_Database.txt".equals(file_name))
                {
                    q.append("S200000000"+","+date_textfield.getText()+","+"Income By Product Sale"+","+paid_textfield.getText()+"\n");
                    q.close();
                }
                else
                {
                    q.append("P100000000"+","+date_textfield.getText()+","+"Cost By Product Purchase"+","+paid_textfield.getText()+"\n");
                    q.close();
                }
                    
            } 
            catch (FileNotFoundException ex) {}
        
        
        
        
        
        cart_textarea.append("\n------------------------------------------------------------------------\n\n");
        cart_textarea.append("          Date & Time : "+date_textfield.getText()+"\n");                    
        cart_textarea.append("          ID : "+id_textfield.getText()+"\n");                                        
        cart_textarea.append("          Product Type : "+product_type_combobox.getSelectedItem()+"\n");
        cart_textarea.append("          Product Model : "+model_number_combobox.getSelectedItem()+"\n");
        cart_textarea.append("          Product Quantity : "+quantity_textfield.getText()+"\n");                    
        cart_textarea.append("          Per Item Price : "+per_item_price_textfield.getText()+"\n");
        cart_textarea.append("          Payment : "+payment_textfield.getText()+"\n");
        cart_textarea.append("          Paid : "+paid_textfield.getText()+"\n");
        cart_textarea.append("          Due : "+due_textfield.getText()+"\n");

        if("Sales_Database.txt".equals(file_name))
        {
            id_textfield.setText("S"+(Integer.parseInt(id_textfield.getText().substring(1, 10))+1));                    
        }
        else
        {
            id_textfield.setText("P"+(Integer.parseInt(id_textfield.getText().substring(1, 10))+1));                   
        }
        
        setDate();
                    
        total_payment = total_payment + Integer.parseInt(payment_textfield.getText());
        total_payment_textfield.setText(""+total_payment);
                    
        total_paid = total_paid + Integer.parseInt(paid_textfield.getText());
        total_paid_textfield.setText(""+total_paid);
                    
        total_due = total_due + Integer.parseInt(due_textfield.getText());
        total_due_textfield.setText(""+total_due);
                 
        product_type_combobox.setSelectedItem("");
        model_number_combobox.setSelectedItem("");
        quantity_textfield.setText("");
        per_item_price_textfield.setText("");
        payment_textfield.setText("");
        paid_textfield.setText("");
        due_textfield.setText(""); 
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
                    answer=answer+demo[i];
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
        
        per_item_price_textfield.addKeyListener(new KeyListener()
        { 
            public void keyTyped(KeyEvent ke){}  
            
            public void keyPressed(KeyEvent ke){} 
           
            public void keyReleased(KeyEvent ke) 
            {
                String input_text = per_item_price_textfield.getText();
                
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
                    per_item_price_textfield.setText("");
                } 
                
                else
                {   
                    setPayment();
                    setDue();
                }
             }
        });
        
        paid_textfield.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){}    
          
            public void keyPressed(KeyEvent ke) {} 
            
            public void keyReleased(KeyEvent ke) 
            {
                if(!isDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits");
                    paid_textfield.setText("");
                }    
                else
                {    
                    setPayment();
                    setDue();
                }
             }
        });
        
        getButton("Home").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(cart_textarea.getText()))
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
                if(!"".equals(cart_textarea.getText()))
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
                if(!"".equals(cart_textarea.getText()))
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
                if(!"".equals(cart_textarea.getText()))
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
                per_item_price_textfield.setText("");
                payment_textfield.setText("");
                paid_textfield.setText("");
                due_textfield.setText("");
            }
        });
        
        getButton("Add to Cart").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!check_all_filled_up()||"0".equals(quantity_textfield.getText())||"0".equals(per_item_price_textfield.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter All Valid Information");
                }
                else 
                {   
                    if("".equals(cart_textarea.getText()))
                    {
                        cart_textarea.append("\n\n");
                       
                        cart_textarea.append("                 Shop Name\n");                                            
                        cart_textarea.append("          "+keyword+" Name : "+name_textfield.getText()+"\n");                    
                        cart_textarea.append("          "+keyword+" Address : "+address_textfield.getText()+"\n");
                        cart_textarea.append("          "+keyword+" Mobile Number : "+mobile_number_textfield.getText()+"\n");                                        
                        add_to_cart(file_name);
                    
                    }
                    else
                    {
                        add_to_cart(file_name);
                    }
                }
            }
        });
        
        getButton("Print").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(cart_textarea.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Add Something To The Cart");
                }
                else 
                {
                   try {
                        cart_textarea.print();
                    } catch (PrinterException ex) {}    
                }    
            }
        });    
    }
}
