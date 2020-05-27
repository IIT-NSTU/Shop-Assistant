
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
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Daily_Income_Cost_Page extends DashBoard_Template
{
    public JPanel input_panel,input_date_panel,date_panel;
    public JLabel select_label,day_label,month_label,year_label;
    public JComboBox day_combobox,month_combobox,year_combobox;
    public JButton submit_button;
    public JTextArea show_details_textarea;
    public JScrollPane scroll;
    
    public String day[] = new String[32];
    public String month[] = new String[13];
    public String year[] = new String[102];
    
    public Daily_Income_Cost_Page()
    {
        setPageButton();
        setMainPanel();
        setInputPanel();
        setDailyIncomeCostFeatures();
    }
    
    public void setPageButton()
    {
        getButton("Daily Income Cost").setBackground(Color.LIGHT_GRAY);
        getButton("Daily Income Cost").setForeground(Color.BLACK);
        getButton("Daily Income Cost").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    public void setMainPanel()
    {
        main_panel.setLayout(new BorderLayout(90,40));

        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.NORTH);
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.EAST);
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.WEST);
        
        null_label = new JLabel();
        main_panel.add(null_label,BorderLayout.SOUTH);
        
        
        input_panel = new JPanel();
        input_panel.setLayout(new GridLayout(2,1));
        input_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        input_panel.setBackground(Color.WHITE);
        main_panel.add(input_panel,BorderLayout.CENTER);
    }
    
    
    public void setInputPanel()
    {    
        input_date_panel = new JPanel();
        input_date_panel.setLayout(new GridLayout(3,1));
        input_date_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        input_date_panel.setBackground(Color.WHITE);
        input_panel.add(input_date_panel);
        
        
        select_label = new JLabel("Select Date");
        select_label.setBackground(Color.WHITE);
        select_label.setFont(new Font("Arial",Font.BOLD,16));
        input_date_panel.add(select_label); 
        
        date_panel = new JPanel();
        date_panel.setBackground(Color.WHITE);
        date_panel.setLayout(new GridLayout(2,3));
        input_date_panel.add(date_panel);
        
        
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
        
        submit_button = new JButton("Submit");
        submit_button.setFont(new Font("Arial",Font.BOLD,16));
        submit_button.setFocusPainted(false);
        input_date_panel.add(submit_button); 
        
        show_details_textarea = new JTextArea();
        show_details_textarea.setFont(new Font("Arial",Font.BOLD,16));
        show_details_textarea.setEditable(false);
        scroll = new JScrollPane(show_details_textarea);
        input_panel.add(scroll); 
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
    
    public void setDailyIncomeCostFeatures()
    {
        submit_button.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(day_combobox.getSelectedItem())||"".equals(month_combobox.getSelectedItem())||"".equals(year_combobox.getSelectedItem()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter a Valid Date");
                }
                else
                {
                    String result = day_combobox.getSelectedItem()+"/"+month_combobox.getSelectedItem()+"/"+year_combobox.getSelectedItem();
                         
                 try{
                     
                     BufferedReader br = new BufferedReader(new FileReader("Income_Cost_Database.txt"));
                     String s;
                     String ans = "";
                     int income_amount = 0;
                     int cost_amount = 0;
                     while((s=br.readLine())!=null)
                     { 
                         if(result.equals(s.substring(11, 21)))
                         {
                             String demo[] = s.substring(21, s.length()).split(",");
                             int j=0;
                             while(j!=demo.length)
                             {
                                 ans = ans + demo[j]+" ";
                                 j++;
                             } 
                             ans = ans + "\n";
                             
                             
                             if(s.charAt(0)=='P')
                             {
                                 char demo2[] = s.toCharArray();
                                 
                                 int i = s.length()-1;
                                 String r="";
                                 while(demo2[i]!=',')
                                 {
                                     r = r + demo2[i];
                                     i--;
                                 }
                                 
                                StringBuilder sb = new StringBuilder(r);  
                                sb.reverse();
                                
                                int amount = Integer.parseInt(sb.toString());
                                
                                cost_amount = cost_amount+amount;  
                             }
                             
                             if(s.charAt(0)=='S')
                             {
                                 char demo2[] = s.toCharArray();
                                 
                                 int i = s.length()-1;
                                 String r="";
                                 while(demo2[i]!=',')
                                 {
                                     r = r + demo2[i];
                                     i--;
                                 }
                                 
                                StringBuilder sb = new StringBuilder(r);  
                                sb.reverse();
                                
                                int amount = Integer.parseInt(sb.toString()); 
                                income_amount = income_amount+amount;                 
                             }
                         }
                     }
 
                     if("".equals(ans))
                     {
                         JOptionPane.showMessageDialog(null, "No Information On That Day");
                         show_details_textarea.setText("");
                     }
                     else
                     {                     
                        ans = ans + "\n\nThis Day's Total Income "+income_amount; 
                        ans = ans + "\nThis Day's Total Cost "+cost_amount; 
                        ans = ans + "\nThis Day's Total Cash "+(income_amount-cost_amount); 
                        
                        show_details_textarea.setText(ans);
                     }  
                        
                    }catch(Exception ex){System.out.println(ex);}
                }
            }
        });
        
        
    }
    
    public static void main(String[] args) 
    {
        Daily_Income_Cost_Page frame = new Daily_Income_Cost_Page();
        frame.setVisible(true);
    }
}
