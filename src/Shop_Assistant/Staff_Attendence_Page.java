
package Shop_Assistant;

import Templates.DashBoard_Template;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Staff_Attendence_Page extends DashBoard_Template
{
    public JLabel date_label,select_member_label;
    public JPanel center_panel,input_panel;
    public JTextField date_textfield;
    public JTextArea output_textarea;
    public JScrollPane scroll;
    public JComboBox member_name_combobox;
    public JRadioButton present_radio_button,absent_radio_button;
    public ButtonGroup attendence_button_group;
    public JButton submit_button,show_details_button,print_button;
    
    public Staff_Attendence_Page()
    {
        setPageButton();
        setMainPanel();
        setCenterPanel();
        setStaffAttendenceFeatures();
    }
    
    public void setPageButton()
    {
        getButton("Staff Attendence").setBackground(Color.lightGray);
        getButton("Staff Attendence").setForeground(Color.BLACK);
        getButton("Staff Attendence").setFont(new Font("Arial",Font.BOLD,16));
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
        center_panel.setLayout(new GridLayout(1,2));
        center_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        center_panel.setBackground(Color.WHITE);
        main_panel.add(center_panel,BorderLayout.CENTER);
    }
    
    public void setCenterPanel()
    {
        setintputPanel();
        setOutputTextArea();
    }
    
    public void setintputPanel()
    {
        
        input_panel = new JPanel();
        input_panel.setLayout(new GridLayout(9,1));
        input_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        input_panel.setBackground(Color.WHITE);
        center_panel.add(input_panel);
        
        date_label = new JLabel("Date & Time");
        date_label.setBackground(Color.WHITE);
        date_label.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(date_label);       
 
        
        date_textfield = new JTextField(setDate());
        date_textfield.setBackground(Color.WHITE);
        date_textfield.setEditable(false);
        date_textfield.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(date_textfield);
        
        select_member_label = new JLabel("Select Member");
        select_member_label.setBackground(Color.WHITE);
        select_member_label.setFont(new Font("Arial",Font.BOLD,16));        
        input_panel.add(select_member_label);
        

        member_name_combobox = new JComboBox();
        member_name_combobox.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(member_name_combobox);
        setMemberNames();
        
        present_radio_button = new JRadioButton("Present");   
        present_radio_button.setBackground(Color.WHITE);
        present_radio_button.setFocusPainted(false);
        present_radio_button.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(present_radio_button);
        
        absent_radio_button = new JRadioButton("Absent");   
        absent_radio_button.setFont(new Font("Arial",Font.BOLD,16));
        absent_radio_button.setBackground(Color.WHITE);
        absent_radio_button.setFocusPainted(false);
        input_panel.add(absent_radio_button);
        
        attendence_button_group = new ButtonGroup();
        attendence_button_group.add(present_radio_button);
        attendence_button_group.add(absent_radio_button);
        
        submit_button = new JButton("Submit");
        submit_button.setFont(new Font("Arial",Font.BOLD,16));
        submit_button.setFocusPainted(false);
        input_panel.add(submit_button);
        
        show_details_button = new JButton("Show Details");
        show_details_button.setFont(new Font("Arial",Font.BOLD,16));
        show_details_button.setFocusPainted(false);
        input_panel.add(show_details_button);
        
        print_button = new JButton("Print Details");
        print_button.setFont(new Font("Arial",Font.BOLD,16));
        print_button.setFocusPainted(false);
        input_panel.add(print_button);
        
    }
    
    public void setOutputTextArea()
    {
        output_textarea = new JTextArea();
        output_textarea.setFont(new Font("Arial",Font.BOLD,16));
        output_textarea.setEditable(false);
        scroll = new JScrollPane(output_textarea);
        center_panel.add(scroll);
    }
    
    public void setStaffAttendenceFeatures()
    {
        submit_button.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                try{
                    
                    BufferedWriter bw = new BufferedWriter(new FileWriter("Staff_Attendance_Info.txt",true));
                
                    String member_name = member_name_combobox.getSelectedItem().toString();
                    String date = date_textfield.getText();
                    
                if(!member_name.equals(""))
                {
                    if(!isAttendenceGiven(member_name,date.substring(0, 10)))
                    {
                        if(present_radio_button.isSelected())
                        { 
                            
                            bw.append(member_name+","+"Present"+","+date+"\n");
                            bw.close();
                            
                            JOptionPane.showMessageDialog(null, "Attendence Given SuccessFully");
                        }
                        else if(absent_radio_button.isSelected())
                        {
                            bw.append(member_name+","+"Absent"+","+date.substring(0, 10)+"\n");
                            bw.close();
                            
                            JOptionPane.showMessageDialog(null, "Attendence Given SuccessFully");
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Please Select An Option");
                        }
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(null, "Already Attendence Given");
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please Select A Member");
                }
                
                date_textfield.setText(setDate());
                
                
                }catch(Exception ex) {System.out.println(ex);}
            }
        });
        
        show_details_button.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                
                output_textarea.setText("");
                
                
                try{
                    
                    BufferedReader br = new BufferedReader(new FileReader("Staff_Attendance_Info.txt"));
                    String s;
                    
                if(!member_name_combobox.getSelectedItem().equals(""))
                {
                    output_textarea.setText("\n"+member_name_combobox.getSelectedItem()+" Attendence List\n\n");
                    
                    int month_array_present[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
                    int month_array_absent[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
                    int i;
                    
                    while((s=br.readLine())!=null)
                    {
                        if(s.contains(member_name_combobox.getSelectedItem().toString()))
                        {
                            String show_info = s.substring(s.indexOf(",")+1, s.length())+"\n";
                            
                            String attendence = show_info.substring(0, show_info.indexOf(","));
                            String date = show_info.substring(show_info.indexOf(",")+1 , show_info.indexOf(",")+11);

                            int month = Integer.parseInt(date.substring(3, 5));
                            
                            if(attendence.equals("Present"))
                            {
                                month_array_present[month]++;
                            }
                            else if(attendence.equals("Absent"))
                            {
                                month_array_absent[month]++;
                            }
                            
                            
                            output_textarea.append(attendence+"  "+s.substring(s.indexOf(date), s.length())+"\n");
                        }    
                    }
                    
                    for(i=1;i<=12;i++)
                    {
                        if(month_array_present[i]>0||month_array_absent[i]>0)
                        {
                            output_textarea.append("Total Present in "+(i)+"th month "+month_array_present[i]+"\n");
                            output_textarea.append("Total Absent in "+(i)+"th month "+month_array_absent[i]+"\n");
                        }
                    }
                    
                    br.close();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please Select A Member");
                }
                
                
                }catch(Exception ex) {System.out.println(ex);}
            }
        });
        
        print_button.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(output_textarea.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Get Some Attendence Information");
                }
                else 
                {
                    try {
                        output_textarea.print();
                    } catch (PrinterException ex) {
                    }   
                }  
            }
        
        
        });
    }
    
    public boolean isAttendenceGiven(String name,String date)
    {
        boolean given = false;
        
        try{
       
            BufferedReader br = new BufferedReader(new FileReader("Staff_Attendance_Info.txt"));
            String s;
            
            while((s=br.readLine())!=null)
            {
                if(s.contains(name)&&s.contains(date))
                {
                    given = true;
                }
            }   
            
            br.close();
            
        }catch(Exception e) {System.out.println(e);}  
        
        return given;
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
    
    public static void main(String[] args) 
    {
        Staff_Attendence_Page frame = new Staff_Attendence_Page();
        frame.setVisible(true);
    }
 
}
