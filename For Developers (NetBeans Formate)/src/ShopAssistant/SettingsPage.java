
package ShopAssistant;

import Templates.DashBoardTemplate;
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

public class SettingsPage extends DashBoardTemplate
{
    public JPanel centerPanel,passwordChangeAddNewStaffPanel,addNewModelRemoveMemberPanel;
    public JLabel passwordChangeLabel,oldPasswordLabel,newPasswordLabel,newModelAddLabel,selectCategoryLabel,modelNameLabel,addNewMemberLabel,memberNameLabel,removeMemberLabel,selectMemberLabel;
    public JTextField oldPasswordTextField,newPasswordTextField_1,newPasswordTextField_2,modelNameTextField_1,modelNameTextField_2,memberNameTextField;
    public JButton passwordSubmitButton,addModelButton,addMemberButton,removeMemberButton;
    public JComboBox productTypeCombobox,memberNameCombobox;
    
    public boolean passwordSectionVisibility = false;
    public boolean addNewMemberSectionVisibility = false;
    public boolean addNewModelSectionVisibility = false;
    public boolean removeModelSectionVisibility = false;
    
    
    public SettingsPage()
    {
        setPageButton();
        setMainPanel();
        setCenterPanel();
        setSettingsPageFeatures();
    }
    
    public void setPageButton()
    {
        getButton("Settings").setBackground(Color.LIGHT_GRAY);
        getButton("Settings").setForeground(Color.BLACK);
        getButton("Settings").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    public void setMainPanel()
    {
        mainPanel.setLayout(new BorderLayout(50,30));
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.NORTH);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.EAST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.WEST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.SOUTH);
        
        centerPanel = new JPanel();
        GridLayout gridLayout = new GridLayout(1,2);
        gridLayout.setHgap(20);
        centerPanel.setLayout(gridLayout);
        
        centerPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        centerPanel.setBackground(Color.WHITE);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
    }
    
    public void setCenterPanel()
    {
        setPasswordChangeAddNewStaffPanel();
        setAddNewModelRemoveMemberPanel();
    }
    
    public void setPasswordChangeAddNewStaffPanel()
    {
        passwordChangeAddNewStaffPanel = new JPanel();
        passwordChangeAddNewStaffPanel.setLayout(new GridLayout(12,1));
        passwordChangeAddNewStaffPanel.setBackground(Color.WHITE);
        centerPanel.add(passwordChangeAddNewStaffPanel);
        
        passwordChangeLabel = new JLabel("Password Change");
        passwordChangeLabel.setOpaque(true);
        passwordChangeLabel.setBackground(Color.DARK_GRAY);
        passwordChangeLabel.setForeground(Color.WHITE);
        passwordChangeLabel.setHorizontalAlignment(JLabel.CENTER);
        passwordChangeLabel.setFont(new Font("Arial",Font.BOLD,16));
        passwordChangeAddNewStaffPanel.add(passwordChangeLabel);
        
        oldPasswordLabel = new JLabel("Enter Old Password");
        oldPasswordLabel.setBackground(Color.WHITE);
        oldPasswordLabel.setFont(new Font("Arial",Font.BOLD,16));
        oldPasswordLabel.setVisible(false);
        passwordChangeAddNewStaffPanel.add(oldPasswordLabel);
        
        oldPasswordTextField = new JTextField();
        oldPasswordTextField.setBackground(Color.WHITE);
        oldPasswordTextField.setFont(new Font("Arial",Font.BOLD,16));
        oldPasswordTextField.setVisible(false);
        passwordChangeAddNewStaffPanel.add(oldPasswordTextField);
        
        newPasswordLabel = new JLabel("Enter New Password (2 Times)");
        newPasswordLabel.setBackground(Color.WHITE);
        newPasswordLabel.setFont(new Font("Arial",Font.BOLD,16));
        newPasswordLabel.setVisible(false);
        passwordChangeAddNewStaffPanel.add(newPasswordLabel);
        
        newPasswordTextField_1 = new JTextField();
        newPasswordTextField_1.setBackground(Color.WHITE);
        newPasswordTextField_1.setFont(new Font("Arial",Font.BOLD,16));
        newPasswordTextField_1.setVisible(false);
        passwordChangeAddNewStaffPanel.add(newPasswordTextField_1);
        
        newPasswordTextField_2 = new JTextField();
        newPasswordTextField_2.setBackground(Color.WHITE);
        newPasswordTextField_2.setFont(new Font("Arial",Font.BOLD,16));
        newPasswordTextField_2.setVisible(false);
        passwordChangeAddNewStaffPanel.add(newPasswordTextField_2);
        
        passwordSubmitButton = new JButton("Submit");
        passwordSubmitButton.setFont(new Font("Arial",Font.BOLD,16));
        passwordSubmitButton.setFocusPainted(false);
        passwordSubmitButton.setVisible(false);
        passwordChangeAddNewStaffPanel.add(passwordSubmitButton);
        
        nullLabel = new JLabel();
        passwordChangeAddNewStaffPanel.add(nullLabel);
        
        addNewMemberLabel = new JLabel("Add New Staff");
        addNewMemberLabel.setOpaque(true);
        addNewMemberLabel.setBackground(Color.DARK_GRAY);
        addNewMemberLabel.setForeground(Color.WHITE);
        addNewMemberLabel.setHorizontalAlignment(JLabel.CENTER);
        addNewMemberLabel.setFont(new Font("Arial",Font.BOLD,16));
        passwordChangeAddNewStaffPanel.add(addNewMemberLabel);
        
        memberNameLabel = new JLabel("Enter New Staff Name");
        memberNameLabel.setBackground(Color.WHITE);
        memberNameLabel.setFont(new Font("Arial",Font.BOLD,16));
        memberNameLabel.setVisible(false);
        passwordChangeAddNewStaffPanel.add(memberNameLabel);
        
        memberNameTextField = new JTextField();
        memberNameTextField.setBackground(Color.WHITE);
        memberNameTextField.setFont(new Font("Arial",Font.BOLD,16));
        memberNameTextField.setVisible(false);
        passwordChangeAddNewStaffPanel.add(memberNameTextField);
        
        addMemberButton = new JButton("Add Staff");
        addMemberButton.setFont(new Font("Arial",Font.BOLD,16));
        addMemberButton.setFocusPainted(false);
        addMemberButton.setVisible(false);
        passwordChangeAddNewStaffPanel.add(addMemberButton);
    }
    
    public void setAddNewModelRemoveMemberPanel()
    {
        addNewModelRemoveMemberPanel = new JPanel();
        addNewModelRemoveMemberPanel.setLayout(new GridLayout(12,1));
        addNewModelRemoveMemberPanel.setBackground(Color.WHITE);
        centerPanel.add(addNewModelRemoveMemberPanel);
        
        newModelAddLabel = new JLabel("Add New Model");
        newModelAddLabel.setOpaque(true);
        newModelAddLabel.setBackground(Color.DARK_GRAY);
        newModelAddLabel.setForeground(Color.WHITE);
        newModelAddLabel.setFont(new Font("Arial",Font.BOLD,16));
        newModelAddLabel.setHorizontalAlignment(JLabel.CENTER);
        addNewModelRemoveMemberPanel.add(newModelAddLabel);
        
        selectCategoryLabel = new JLabel("Select Category");
        selectCategoryLabel.setBackground(Color.WHITE);
        selectCategoryLabel.setFont(new Font("Arial",Font.BOLD,16));
        selectCategoryLabel.setVisible(false);
        addNewModelRemoveMemberPanel.add(selectCategoryLabel);
        
        productTypeCombobox = new JComboBox(setProductTypes());
        productTypeCombobox.setFont(new Font("Arial",Font.BOLD,16));
        productTypeCombobox.setVisible(false);
        addNewModelRemoveMemberPanel.add(productTypeCombobox);
        
        modelNameLabel = new JLabel("Enter New Model (2 Times)");
        modelNameLabel.setBackground(Color.WHITE);
        modelNameLabel.setFont(new Font("Arial",Font.BOLD,16));
        modelNameLabel.setVisible(false);
        addNewModelRemoveMemberPanel.add(modelNameLabel);
        
        modelNameTextField_1 = new JTextField();
        modelNameTextField_1.setBackground(Color.WHITE);
        modelNameTextField_1.setFont(new Font("Arial",Font.BOLD,16));
        modelNameTextField_1.setVisible(false);
        addNewModelRemoveMemberPanel.add(modelNameTextField_1);
        
        modelNameTextField_2 = new JTextField();
        modelNameTextField_2.setBackground(Color.WHITE);
        modelNameTextField_2.setFont(new Font("Arial",Font.BOLD,16));
        modelNameTextField_2.setVisible(false);
        addNewModelRemoveMemberPanel.add(modelNameTextField_2);
        
        addModelButton = new JButton("Add Model");
        addModelButton.setFont(new Font("Arial",Font.BOLD,16));
        addModelButton.setFocusPainted(false);
        addModelButton.setVisible(false);
        addNewModelRemoveMemberPanel.add(addModelButton);
        
        nullLabel = new JLabel();
        addNewModelRemoveMemberPanel.add(nullLabel);
         
        removeMemberLabel = new JLabel("Remove Staff");
        removeMemberLabel.setOpaque(true);
        removeMemberLabel.setBackground(Color.DARK_GRAY);
        removeMemberLabel.setForeground(Color.WHITE);
        removeMemberLabel.setHorizontalAlignment(JLabel.CENTER);
        removeMemberLabel.setFont(new Font("Arial",Font.BOLD,16));
        addNewModelRemoveMemberPanel.add(removeMemberLabel);
        
        selectMemberLabel = new JLabel("Select Staff");
        selectMemberLabel.setBackground(Color.WHITE);
        selectMemberLabel.setFont(new Font("Arial",Font.BOLD,16));
        selectMemberLabel.setVisible(false);
        addNewModelRemoveMemberPanel.add(selectMemberLabel);
        
        memberNameCombobox = new JComboBox();
        memberNameCombobox.setFont(new Font("Arial",Font.BOLD,16));
        memberNameCombobox.setVisible(false);
        addNewModelRemoveMemberPanel.add(memberNameCombobox);
        setMemberNames();
        
        removeMemberButton = new JButton("Remove Staff");
        removeMemberButton.setFont(new Font("Arial",Font.BOLD,16));
        removeMemberButton.setFocusPainted(false);
        removeMemberButton.setVisible(false);
        addNewModelRemoveMemberPanel.add(removeMemberButton);  
    }
    
    public void setSettingsPageFeatures()
    {
        passwordChangeLabel.addMouseListener(new MouseAdapter(){
            
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
        
        addNewMemberLabel.addMouseListener(new MouseAdapter(){
            
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
        
        newModelAddLabel.addMouseListener(new MouseAdapter(){
            
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
        
        removeMemberLabel.addMouseListener(new MouseAdapter(){
            
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
        
        passwordSubmitButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(passwordSubmitButton.isVisible())
                {
                    try{
                        
                    String newPassword,oldPassword = getPassword();
                    
                    if(oldPassword.equals(oldPasswordTextField.getText())||"admin".equals(oldPasswordTextField.getText()))
                    {
                        if(!"".equals(newPasswordTextField_1.getText())&&!"".equals(newPasswordTextField_2.getText()))
                        {
                       
                        if(newPasswordTextField_1.getText().equals(newPasswordTextField_2.getText()))
                        {
                            newPassword = newPasswordTextField_1.getText();
                            JOptionPane.showMessageDialog(null, "Password Changed Successfully");
   
                            BufferedWriter bw = new BufferedWriter(new FileWriter("UserDatabase.txt",false));
                            bw.write("Password:"+newPassword);
                            bw.close();
                    
                            oldPasswordTextField.setText("");
                            newPasswordTextField_1.setText("");
                            newPasswordTextField_2.setText("");
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
        
        addMemberButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(addMemberButton.isVisible())
                {
                    try{
                        
                    if(!"".equals(memberNameTextField.getText()))
                    {
                        BufferedWriter bw = new BufferedWriter(new FileWriter("StaffNameList.txt",true));
                        bw.append("Member:"+memberNameTextField.getText()+"\n");
                        bw.close();
                    
                        JOptionPane.showMessageDialog(null, "New Member Added Successfully");
                    
                        memberNameTextField.setText("");          
                    }    
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Staff Name");
                    }
                    
                    }catch(Exception ex) {System.out.println(ex);}     
                }
            }
        
        });  
        
        addModelButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(addModelButton.isVisible())
                {
                    try{
                        
                    String modelNumber,category = productTypeCombobox.getSelectedItem().toString();

                    if(!"".equals(category))
                    {
                        if(!"".equals(modelNameTextField_1.getText())&&!"".equals(modelNameTextField_2.getText()))
                        {
                            if(modelNameTextField_1.getText().equals(modelNameTextField_2.getText()))
                            {
                                modelNumber = modelNameTextField_1.getText();

                                BufferedReader br = new BufferedReader(new FileReader("ProductType&ModelList.txt"));
                                
                                String rawData = "",s;
                                
                                while((s=br.readLine())!=null)
                                {
                                    if(s.contains(category))
                                    {        
                                        rawData = rawData + s + modelNumber + "." + "\n";
                                    }
                                    else
                                    {
                                        rawData = rawData + s + "\n";
                                    }
                                }
                                
                                br.close();
                                
                                BufferedWriter bw = new BufferedWriter(new FileWriter("ProductType&ModelList.txt",false));           
                                bw.write(rawData);
                                bw.close();
                    
                                modelNameTextField_1.setText("");
                                modelNameTextField_2.setText("");
                                
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
        
        removeMemberButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if(removeMemberButton.isVisible())
                {
                    try{
                        
                        String memberName = memberNameCombobox.getSelectedItem().toString();
                        
                    if(!"".equals(memberName))
                    {
                        BufferedReader br = new BufferedReader(new FileReader("StaffNameList.txt"));
                        String s,rawData="";
                        
                        while((s=br.readLine())!=null)
                        {
                            if(!s.contains(memberName))
                            {
                                rawData = rawData + s + "\n";
                            }
                        }
                        
                        br.close();
                        
                        BufferedWriter bw = new BufferedWriter(new FileWriter("StaffNameList.txt",false));
                        bw.write(rawData);
                        bw.close();
                    
                        JOptionPane.showMessageDialog(null, "Staff "+memberName+" Removed  Successfully");
                    
                        memberNameCombobox.removeAllItems();
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
        memberNameCombobox.addItem("");
        
        try{
       
            BufferedReader br = new BufferedReader(new FileReader("StaffNameList.txt"));
            String s;
            
            while((s=br.readLine())!=null)
            {
                if(s.contains("Member"))
                {
                    memberNameCombobox.addItem(s.substring(s.indexOf(":")+1, s.length()));
                }
            }  
            
            br.close();
            
        }catch(Exception e) {System.out.println(e);}       
    }
    
    public void setPasswordSectionVisible(boolean visibility)
    {
        oldPasswordLabel.setVisible(visibility);
        oldPasswordTextField.setVisible(visibility);
        newPasswordLabel.setVisible(visibility);
        newPasswordTextField_1.setVisible(visibility);
        newPasswordTextField_2.setVisible(visibility);
        passwordSubmitButton.setVisible(visibility);  
    }
    
    public void setAddNewMemberSectionVisible(boolean visibility)
    {
        memberNameLabel.setVisible(visibility);
        memberNameTextField.setVisible(visibility);
        addMemberButton.setVisible(visibility);
    }
    
    public void setAddNewModelSectionVisible(boolean visibility)
    {
        selectCategoryLabel.setVisible(visibility);
        productTypeCombobox.setVisible(visibility);
        modelNameLabel.setVisible(visibility);
        modelNameTextField_1.setVisible(visibility);
        modelNameTextField_2.setVisible(visibility);
        addModelButton.setVisible(visibility);
    }
    
    public void setRemoveMemberSectionVisible(boolean visibility)
    {
        selectMemberLabel.setVisible(visibility);
        memberNameCombobox.setVisible(visibility);
        removeMemberButton.setVisible(visibility);   
        memberNameCombobox.removeAllItems();
        setMemberNames();
    }
    
    public static void main(String[] args) 
    {
        SettingsPage frame = new SettingsPage();
        frame.setVisible(true);
    }
 
}
