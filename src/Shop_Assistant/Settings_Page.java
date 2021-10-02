
package Shop_Assistant;

import Templates.DashBoard_Template;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Settings_Page extends DashBoard_Template
{
    public JPanel center_panel,password_change_add_new_staff_panel,add_new_model_remove_member_panel;
    public JLabel password_change_label,old_password_label,new_password_label,new_model_add_label,select_category_label,model_name_label,add_new_member_Label,member_name_label,remove_member_Label,select_member_label;
    public JTextField old_password_textfield,new_password_textfield_1,new_password_textfield_2,model_name_textfield_1,model_name_textfield_2,member_name_textfield;
    public JButton password_submit_button,add_model_button,add_member_button,remove_member_button;
    public JComboBox product_type_combobox,member_name_combobox;
    
    public boolean passwordSectionVisibility = false;
    public boolean addNewMemberSectionVisibility = false;
    public boolean addNewModelSectionVisibility = false;
    public boolean removeModelSectionVisibility = false;
    
    
    public Settings_Page()
    {
        setPageButton();
        setMainPanel();
        setCenterPanel();
        setSettingsPageFeatures();
    }
    
    public void setPageButton()
    {
        getButton("Settings").setBackground(Color.lightGray);
        getButton("Settings").setForeground(Color.BLACK);
        getButton("Settings").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    public void setMainPanel()
    {
        main_panel.setLayout(new BorderLayout(50,30));
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.NORTH);
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.EAST);
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.WEST);
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.SOUTH);
        
        center_panel = new JPanel();
        GridLayout grid_layout = new GridLayout(1,2);
        grid_layout.setHgap(20);
        center_panel.setLayout(grid_layout);
        
        center_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        center_panel.setBackground(Color.WHITE);
        main_panel.add(center_panel,BorderLayout.CENTER);
    }
    
    public void setCenterPanel()
    {
        setPasswordChangeAddNewStaffPanel();
        setAddNewModelRemoveMemberPanel();
    }
    
    public void setPasswordChangeAddNewStaffPanel()
    {
        password_change_add_new_staff_panel = new JPanel();
        password_change_add_new_staff_panel.setLayout(new GridLayout(12,1));
        password_change_add_new_staff_panel.setBackground(Color.WHITE);
        center_panel.add(password_change_add_new_staff_panel);
        
        password_change_label = new JLabel("Password Change");
        password_change_label.setOpaque(true);
        password_change_label.setBackground(Color.DARK_GRAY);
        password_change_label.setForeground(Color.WHITE);
        password_change_label.setHorizontalAlignment(JLabel.CENTER);
        password_change_label.setFont(new Font("Arial",Font.BOLD,16));
        password_change_add_new_staff_panel.add(password_change_label);
        
        old_password_label = new JLabel("Enter Old Password");
        old_password_label.setBackground(Color.WHITE);
        old_password_label.setFont(new Font("Arial",Font.BOLD,16));
        old_password_label.setVisible(false);
        password_change_add_new_staff_panel.add(old_password_label);
        
        old_password_textfield = new JTextField();
        old_password_textfield.setBackground(Color.WHITE);
        old_password_textfield.setFont(new Font("Arial",Font.BOLD,16));
        old_password_textfield.setVisible(false);
        password_change_add_new_staff_panel.add(old_password_textfield);
        
        new_password_label = new JLabel("Enter New Password (2 Times)");
        new_password_label.setBackground(Color.WHITE);
        new_password_label.setFont(new Font("Arial",Font.BOLD,16));
        new_password_label.setVisible(false);
        password_change_add_new_staff_panel.add(new_password_label);
        
        new_password_textfield_1 = new JTextField();
        new_password_textfield_1.setBackground(Color.WHITE);
        new_password_textfield_1.setFont(new Font("Arial",Font.BOLD,16));
        new_password_textfield_1.setVisible(false);
        password_change_add_new_staff_panel.add(new_password_textfield_1);
        
        new_password_textfield_2 = new JTextField();
        new_password_textfield_2.setBackground(Color.WHITE);
        new_password_textfield_2.setFont(new Font("Arial",Font.BOLD,16));
        new_password_textfield_2.setVisible(false);
        password_change_add_new_staff_panel.add(new_password_textfield_2);
        
        password_submit_button = new JButton("Submit");
        password_submit_button.setFont(new Font("Arial",Font.BOLD,16));
        password_submit_button.setFocusPainted(false);
        password_submit_button.setVisible(false);
        password_change_add_new_staff_panel.add(password_submit_button);
        
        null_label = new JLabel();
        password_change_add_new_staff_panel.add(null_label);
        
        add_new_member_Label = new JLabel("Add New Staff");
        add_new_member_Label.setOpaque(true);
        add_new_member_Label.setBackground(Color.DARK_GRAY);
        add_new_member_Label.setForeground(Color.WHITE);
        add_new_member_Label.setHorizontalAlignment(JLabel.CENTER);
        add_new_member_Label.setFont(new Font("Arial",Font.BOLD,16));
        password_change_add_new_staff_panel.add(add_new_member_Label);
        
        member_name_label = new JLabel("Enter New Staff Name");
        member_name_label.setBackground(Color.WHITE);
        member_name_label.setFont(new Font("Arial",Font.BOLD,16));
        member_name_label.setVisible(false);
        password_change_add_new_staff_panel.add(member_name_label);
        
        member_name_textfield = new JTextField();
        member_name_textfield.setBackground(Color.WHITE);
        member_name_textfield.setFont(new Font("Arial",Font.BOLD,16));
        member_name_textfield.setVisible(false);
        password_change_add_new_staff_panel.add(member_name_textfield);
        
        add_member_button = new JButton("Add Staff");
        add_member_button.setFont(new Font("Arial",Font.BOLD,16));
        add_member_button.setFocusPainted(false);
        add_member_button.setVisible(false);
        password_change_add_new_staff_panel.add(add_member_button);
    }
    
    public void setAddNewModelRemoveMemberPanel()
    {
        add_new_model_remove_member_panel = new JPanel();
        add_new_model_remove_member_panel.setLayout(new GridLayout(12,1));
        add_new_model_remove_member_panel.setBackground(Color.WHITE);
        center_panel.add(add_new_model_remove_member_panel);
        
        new_model_add_label = new JLabel("Add New Model");
        new_model_add_label.setOpaque(true);
        new_model_add_label.setBackground(Color.DARK_GRAY);
        new_model_add_label.setForeground(Color.WHITE);
        new_model_add_label.setFont(new Font("Arial",Font.BOLD,16));
        new_model_add_label.setHorizontalAlignment(JLabel.CENTER);
        add_new_model_remove_member_panel.add(new_model_add_label);
        
        select_category_label = new JLabel("Select Category");
        select_category_label.setBackground(Color.WHITE);
        select_category_label.setFont(new Font("Arial",Font.BOLD,16));
        select_category_label.setVisible(false);
        add_new_model_remove_member_panel.add(select_category_label);
        
        product_type_combobox = new JComboBox(setProductTypes());
        product_type_combobox.setFont(new Font("Arial",Font.BOLD,16));
        product_type_combobox.setVisible(false);
        add_new_model_remove_member_panel.add(product_type_combobox);
        
        model_name_label = new JLabel("Enter New Model (2 Times)");
        model_name_label.setBackground(Color.WHITE);
        model_name_label.setFont(new Font("Arial",Font.BOLD,16));
        model_name_label.setVisible(false);
        add_new_model_remove_member_panel.add(model_name_label);
        
        model_name_textfield_1 = new JTextField();
        model_name_textfield_1.setBackground(Color.WHITE);
        model_name_textfield_1.setFont(new Font("Arial",Font.BOLD,16));
        model_name_textfield_1.setVisible(false);
        add_new_model_remove_member_panel.add(model_name_textfield_1);
        
        model_name_textfield_2 = new JTextField();
        model_name_textfield_2.setBackground(Color.WHITE);
        model_name_textfield_2.setFont(new Font("Arial",Font.BOLD,16));
        model_name_textfield_2.setVisible(false);
        add_new_model_remove_member_panel.add(model_name_textfield_2);
        
        add_model_button = new JButton("Add Model");
        add_model_button.setFont(new Font("Arial",Font.BOLD,16));
        add_model_button.setFocusPainted(false);
        add_model_button.setVisible(false);
        add_new_model_remove_member_panel.add(add_model_button);
        
        null_label = new JLabel();
        add_new_model_remove_member_panel.add(null_label);
         
        remove_member_Label = new JLabel("Remove Staff");
        remove_member_Label.setOpaque(true);
        remove_member_Label.setBackground(Color.DARK_GRAY);
        remove_member_Label.setForeground(Color.WHITE);
        remove_member_Label.setHorizontalAlignment(JLabel.CENTER);
        remove_member_Label.setFont(new Font("Arial",Font.BOLD,16));
        add_new_model_remove_member_panel.add(remove_member_Label);
        
        select_member_label = new JLabel("Select Staff");
        select_member_label.setBackground(Color.WHITE);
        select_member_label.setFont(new Font("Arial",Font.BOLD,16));
        select_member_label.setVisible(false);
        add_new_model_remove_member_panel.add(select_member_label);
        
        member_name_combobox = new JComboBox();
        member_name_combobox.setFont(new Font("Arial",Font.BOLD,16));
        member_name_combobox.setVisible(false);
        add_new_model_remove_member_panel.add(member_name_combobox);
        setMemberNames();
        
        remove_member_button = new JButton("Remove Staff");
        remove_member_button.setFont(new Font("Arial",Font.BOLD,16));
        remove_member_button.setFocusPainted(false);
        remove_member_button.setVisible(false);
        add_new_model_remove_member_panel.add(remove_member_button);  
    }
    
    public void setSettingsPageFeatures()
    {
        password_change_label.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent me) 
            {
                if(passwordSectionVisibility)
                {
                    setPasswordSectionVisible(false);
                    passwordSectionVisibility = false;
                }
                else
                {
                    setPasswordSectionVisible(true);
                    passwordSectionVisibility = true;
                }                  
            }
        });       
        
        add_new_member_Label.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent me) 
            {
                if(addNewMemberSectionVisibility)
                {
                    setAddNewMemberSectionVisible(false);
                    addNewMemberSectionVisibility = false;
                }
                else
                {
                    setAddNewMemberSectionVisible(true);
                    addNewMemberSectionVisibility = true;
                }                  
            }           
        });
        
        new_model_add_label.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent me) 
            {
                if(addNewModelSectionVisibility)
                {
                    setAddNewModelSectionVisible(false);
                    addNewModelSectionVisibility = false;
                }
                else
                {
                    setAddNewModelSectionVisible(true);
                    addNewModelSectionVisibility = true;
                }                  
            }
        });
        
        remove_member_Label.addMouseListener(new MouseAdapter(){
            
            public void mouseClicked(MouseEvent me) 
            {
                if(removeModelSectionVisibility)
                {
                    setRemoveMemberSectionVisible(false);
                    removeModelSectionVisibility = false;
                }
                else
                {
                    setRemoveMemberSectionVisible(true);
                    removeModelSectionVisibility = true;
                }                  
            }
        });
        
        password_submit_button.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(password_submit_button.isVisible())
                {
                    try{
                        
                    String new_password,old_password = getPassword();
                    
                    if(old_password.equals(old_password_textfield.getText())||"admin".equals(old_password_textfield.getText()))
                    {
                        if(!"".equals(new_password_textfield_1.getText())&&!"".equals(new_password_textfield_2.getText()))
                        {
                       
                        if(new_password_textfield_1.getText().equals(new_password_textfield_2.getText()))
                        {
                            new_password = new_password_textfield_1.getText();
                            JOptionPane.showMessageDialog(null, "Password Changed Successfully");
   
                            BufferedWriter bw = new BufferedWriter(new FileWriter("User_Database.txt",false));
                            bw.write("Password:"+new_password);
                            bw.close();
                    
                            old_password_textfield.setText("");
                            new_password_textfield_1.setText("");
                            new_password_textfield_2.setText("");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "New Password Does Not Match");
                        }
                        
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Please Enter New PassWord Twice");
                        }

                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Old Password is Incorrect");
                    }
                    
     
                    }catch(Exception ex) {System.out.println(ex);}
                        
                }
            }
        
        });
        
        add_member_button.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(add_member_button.isVisible())
                {
                    try{
                        
                    if(!"".equals(member_name_textfield.getText()))
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("Staff_Name_List.txt",true));
                        bw.append("Member:"+member_name_textfield.getText()+"\n");
                        bw.close();
                    
                        JOptionPane.showMessageDialog(null, "New Member Added Successfully");
                    
                        member_name_textfield.setText("");          
                    }    
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Staff Name");
                    }
                    
                    }catch(Exception ex) {System.out.println(ex);}     
                }
            }
        
        });  
        
        add_model_button.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(add_model_button.isVisible())
                {
                    try{
                        
                    String model_number,category = product_type_combobox.getSelectedItem().toString();

                    if(!"".equals(category))
                    {
                        if(!"".equals(model_name_textfield_1.getText())&&!"".equals(model_name_textfield_2.getText()))
                        {
                            if(model_name_textfield_1.getText().equals(model_name_textfield_2.getText()))
                            {
                                model_number = model_name_textfield_1.getText();

                                BufferedReader br = new BufferedReader(new FileReader("Product_Type_&_Model_List.txt"));
                                
                                String raw_data = "",s;
                                
                                while((s=br.readLine())!=null)
                                {
                                    if(s.contains(category))
                                    {        
                                        raw_data = raw_data + s + model_number + "." + "\n";
                                    }
                                    else
                                    {
                                        raw_data = raw_data + s + "\n";
                                    }
                                }
                                
                                br.close();
                                
                                BufferedWriter bw = new BufferedWriter(new FileWriter("Product_Type_&_Model_List.txt",false));           
                                bw.write(raw_data);
                                bw.close();
                    
                                model_name_textfield_1.setText("");
                                model_name_textfield_2.setText("");
                                
                                JOptionPane.showMessageDialog(null, "New Model Added Successfully");
                            } 
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Model Number Does Not Macth");
                            }
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Please Enter New Model Name Twice");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please Select Product Type");
                    }
                    
                    }catch(Exception ex) {System.out.println(ex);}
                        
                }
            }
        
        });
        
        remove_member_button.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(remove_member_button.isVisible())
                {
                    try{
                        
                        String member_name = member_name_combobox.getSelectedItem().toString();
                        
                    if(!"".equals(member_name))
                    {
                        BufferedReader br = new BufferedReader(new FileReader("Staff_Name_List.txt"));
                        String s,raw_data="";
                        
                        while((s=br.readLine())!=null)
                        {
                            if(!s.contains(member_name))
                            {
                                raw_data = raw_data + s + "\n";
                            }
                        }
                        
                        br.close();
                        
                        BufferedWriter bw = new BufferedWriter(new FileWriter("Staff_Name_List.txt",false));
                        bw.write(raw_data);
                        bw.close();
                    
                        JOptionPane.showMessageDialog(null, "Staff "+member_name+" Removed  Successfully");
                    
                        member_name_combobox.removeAllItems();
                        setMemberNames();
                    }    
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please Select A Staff");
                    }
                    
                    }catch(Exception ex) {System.out.println(ex);}     
                }
            }
        
        }); 
    }
    
    public void setMemberNames() 
    {  
        member_name_combobox.addItem("");
        
        try{
       
            BufferedReader br = new BufferedReader(new FileReader("Staff_Name_List.txt"));
            String s;
            
            while((s=br.readLine())!=null)
            {
                if(s.contains("Member"))
                {
                    member_name_combobox.addItem(s.substring(s.indexOf(":")+1, s.length()));
                }
            }  
            
            br.close();
            
        }catch(Exception e) {System.out.println(e);}       
    }
    
    public void setPasswordSectionVisible(boolean visibility)
    {
        old_password_label.setVisible(visibility);
        old_password_textfield.setVisible(visibility);
        new_password_label.setVisible(visibility);
        new_password_textfield_1.setVisible(visibility);
        new_password_textfield_2.setVisible(visibility);
        password_submit_button.setVisible(visibility);  
    }
    
    public void setAddNewMemberSectionVisible(boolean visibility)
    {
        member_name_label.setVisible(visibility);
        member_name_textfield.setVisible(visibility);
        add_member_button.setVisible(visibility);
    }
    
    public void setAddNewModelSectionVisible(boolean visibility)
    {
        select_category_label.setVisible(visibility);
        product_type_combobox.setVisible(visibility);
        model_name_label.setVisible(visibility);
        model_name_textfield_1.setVisible(visibility);
        model_name_textfield_2.setVisible(visibility);
        add_model_button.setVisible(visibility);
    }
    
    public void setRemoveMemberSectionVisible(boolean visibility)
    {
        select_member_label.setVisible(visibility);
        member_name_combobox.setVisible(visibility);
        remove_member_button.setVisible(visibility);   
        member_name_combobox.removeAllItems();
        setMemberNames();
    }
    
    public static void main(String[] args) 
    {
        Settings_Page frame = new Settings_Page();
        frame.setVisible(true);
    }
 
}
