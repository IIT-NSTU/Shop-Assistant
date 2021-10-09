
package ShopAssistant;

import Templates.DashBoardTemplate;
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


public class StaffAttendancePage extends DashBoardTemplate
{
    public JLabel dateLabel,selectMemberLabel;
    public JPanel centerPanel,inputPanel;
    public JTextField dateTextField;
    public JTextArea outputTextarea;
    public JScrollPane scroll;
    public JComboBox memberNameCombobox;
    public JRadioButton presentRadioButton,absentRadioButton;
    public ButtonGroup attendenceButtonGroup;
    public JButton submitButton,showDetailsButton,printButton;
    
    public StaffAttendancePage()
    {
        setPageButton();
        setMainPanel();
        setCenterPanel();
        setStaffAttendenceFeatures();
    }
    
    public void setPageButton()
    {
        getButton("Staff Attendence").setBackground(Color.LIGHT_GRAY);
        getButton("Staff Attendence").setForeground(Color.BLACK);
        getButton("Staff Attendence").setFont(new Font("Arial",Font.BOLD,16));
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
        centerPanel.setLayout(new GridLayout(1,2));
        centerPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        centerPanel.setBackground(Color.WHITE);
        mainPanel.add(centerPanel,BorderLayout.CENTER);
    }
    
    public void setCenterPanel()
    {
        setintputPanel();
        setOutputTextArea();
    }
    
    public void setintputPanel()
    {    
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(9,1));
        inputPanel.setBackground(Color.WHITE);
        centerPanel.add(inputPanel);
        
        dateLabel = new JLabel("Date & Time");
        dateLabel.setBackground(Color.WHITE);
        dateLabel.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(dateLabel);       
  
        dateTextField = new JTextField(setDate());
        dateTextField.setBackground(Color.WHITE);
        dateTextField.setEditable(false);
        dateTextField.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(dateTextField);
        
        selectMemberLabel = new JLabel("Select Member");
        selectMemberLabel.setBackground(Color.WHITE);
        selectMemberLabel.setFont(new Font("Arial",Font.BOLD,16));        
        inputPanel.add(selectMemberLabel);
        
        memberNameCombobox = new JComboBox();
        memberNameCombobox.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(memberNameCombobox);
        setMemberNames();
        
        presentRadioButton = new JRadioButton("Present");   
        presentRadioButton.setBackground(Color.WHITE);
        presentRadioButton.setFocusPainted(false);
        presentRadioButton.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(presentRadioButton);
        
        absentRadioButton = new JRadioButton("Absent");   
        absentRadioButton.setFont(new Font("Arial",Font.BOLD,16));
        absentRadioButton.setBackground(Color.WHITE);
        absentRadioButton.setFocusPainted(false);
        inputPanel.add(absentRadioButton);
        
        attendenceButtonGroup = new ButtonGroup();
        attendenceButtonGroup.add(presentRadioButton);
        attendenceButtonGroup.add(absentRadioButton);
        
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial",Font.BOLD,16));
        submitButton.setFocusPainted(false);
        inputPanel.add(submitButton);
        
        showDetailsButton = new JButton("Show Details");
        showDetailsButton.setFont(new Font("Arial",Font.BOLD,16));
        showDetailsButton.setFocusPainted(false);
        inputPanel.add(showDetailsButton);
        
        printButton = new JButton("Print Details");
        printButton.setFont(new Font("Arial",Font.BOLD,16));
        printButton.setFocusPainted(false);
        inputPanel.add(printButton);
        
    }
    
    public void setOutputTextArea()
    {
        outputTextarea = new JTextArea();
        outputTextarea.setFont(new Font("Arial",Font.BOLD,16));
        outputTextarea.setEditable(false);
        scroll = new JScrollPane(outputTextarea);
        centerPanel.add(scroll);
    }
    
    public void setStaffAttendenceFeatures()
    {
        submitButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                try{
                    
                    BufferedWriter bw = new BufferedWriter(new FileWriter("StaffAttendanceInfo.txt",true));
                
                    String memberName = memberNameCombobox.getSelectedItem().toString();
                    String date = dateTextField.getText();
                    
                if(!memberName.equals(""))
                {
                    if(!isAttendenceGiven(memberName,date.substring(0, 10)))
                    {
                        if(presentRadioButton.isSelected())
                        { 
                            
                            bw.append(memberName+","+"Present"+","+date+"\n");
                            bw.close();
                            
                            JOptionPane.showMessageDialog(null, "Attendence Given SuccessFully");
                        }
                        else if(absentRadioButton.isSelected())
                        {
                            bw.append(memberName+","+"Absent"+","+date.substring(0, 10)+"\n");
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
                
                dateTextField.setText(setDate());
                
                
                }catch(Exception ex) {System.out.println(ex);}
            }
        });
        
        showDetailsButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                
                outputTextarea.setText("");
                
                
                try{
                    
                    BufferedReader br = new BufferedReader(new FileReader("StaffAttendanceInfo.txt"));
                    String s;
                    
                if(!memberNameCombobox.getSelectedItem().equals(""))
                {
                    outputTextarea.setText("\n"+"    "+memberNameCombobox.getSelectedItem()+" Attendence List\n\n");
                    
                    int monthArrayPresent[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
                    int monthArrayAbsent[] = {0,0,0,0,0,0,0,0,0,0,0,0,0};
                    int i;
                    
                    while((s=br.readLine())!=null)
                    {
                        if(s.contains(memberNameCombobox.getSelectedItem().toString()))
                        {
                            String showInfo = s.substring(s.indexOf(",")+1, s.length())+"\n";
                            
                            String attendence = showInfo.substring(0, showInfo.indexOf(","));
                            String date = showInfo.substring(showInfo.indexOf(",")+1 , showInfo.indexOf(",")+11);

                            int month = Integer.parseInt(date.substring(3, 5));
                            
                            if(attendence.equals("Present"))
                            {
                                monthArrayPresent[month]++;
                            }
                            else if(attendence.equals("Absent"))
                            {
                                monthArrayAbsent[month]++;
                            }
                            
                            
                            outputTextarea.append("    "+attendence+"  "+s.substring(s.indexOf(date), s.length())+"\n");
                        }    
                    }
                    
                    outputTextarea.append("\n\n");
                    
                    for(i=1;i<=12;i++)
                    {
                        if(monthArrayPresent[i]>0||monthArrayAbsent[i]>0)
                        {
                            outputTextarea.append("    Total Present in "+(i)+"th month "+monthArrayPresent[i]+" days\n");
                            outputTextarea.append("    Total Absent in "+(i)+"th month "+monthArrayAbsent[i]+" days\n");
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
        
        printButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(outputTextarea.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Get Some Attendence Information");
                }
                else 
                {
                    try {
                        outputTextarea.print();
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
       
            BufferedReader br = new BufferedReader(new FileReader("StaffAttendanceInfo.txt"));
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
    
    public static void main(String[] args) 
    {
        StaffAttendancePage frame = new StaffAttendancePage();
        frame.setVisible(true);
    }
 
}
