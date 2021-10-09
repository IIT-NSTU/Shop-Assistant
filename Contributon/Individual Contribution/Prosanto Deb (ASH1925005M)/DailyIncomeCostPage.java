
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
import java.io.FileReader;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class DailyIncomeCostPage extends DashBoardTemplate
{
    public JPanel inputPanel,inputDatePanel,datePanel;
    public JLabel selectLabel,dayLabel,monthLabel,yearLabel;
    public JComboBox dayCombobox,monthCombobox,yearCombobox;
    public JButton submitButton,printButton;
    public JTextArea showDetailsTextarea;
    public JScrollPane scroll;
    
    public String day[] = new String[32];
    public String month[] = new String[13];
    public String year[] = new String[102];
    
    /**
     *  Daily Income Cost Page Constructor
     */
    public DailyIncomeCostPage()
    {
        setPageButton();
        setMainPanel();
        setInputPanel();
        setDailyIncomeCostFeatures();
    }
    
    /**
     * This Method Sets Button Using Different BackGround and ForeGround Color
     */
    public void setPageButton()
    {
        getButton("Daily Income Cost").setBackground(Color.LIGHT_GRAY);
        getButton("Daily Income Cost").setForeground(Color.BLACK);
        getButton("Daily Income Cost").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    /**
     * This Method Sets Main Panel
     */
    public void setMainPanel()
    {
        mainPanel.setLayout(new BorderLayout(70,25));

        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.NORTH);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.EAST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.WEST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.SOUTH);
        
        
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(2,1));
        inputPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inputPanel.setBackground(Color.WHITE);
        mainPanel.add(inputPanel,BorderLayout.CENTER);
    }
    
    /**
     * This Method Sets Input Panel
     */
    public void setInputPanel()
    {    
        inputDatePanel = new JPanel();
        inputDatePanel.setLayout(new GridLayout(4,1));
        inputDatePanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inputDatePanel.setBackground(Color.WHITE);
        inputPanel.add(inputDatePanel);
        
        
        selectLabel = new JLabel("Select Date");
        selectLabel.setBackground(Color.WHITE);
        selectLabel.setFont(new Font("Arial",Font.BOLD,16));
        inputDatePanel.add(selectLabel); 
        
        datePanel = new JPanel();
        datePanel.setBackground(Color.WHITE);
        datePanel.setLayout(new GridLayout(2,3));
        inputDatePanel.add(datePanel);
        
        
        dayLabel = new JLabel("Day");
        dayLabel.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(dayLabel);
        
        monthLabel = new JLabel("Month");
        monthLabel.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(monthLabel);
        
        yearLabel = new JLabel("Year");
        yearLabel.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(yearLabel);
        
        setManualDate();
        
        dayCombobox = new JComboBox(day);
        dayCombobox.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(dayCombobox);
        
        monthCombobox = new JComboBox(month);
        monthCombobox.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(monthCombobox);
        
        yearCombobox = new JComboBox(year);
        yearCombobox.setFont(new Font("Arial",Font.BOLD,16));
        datePanel.add(yearCombobox);
        
        submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial",Font.BOLD,16));
        submitButton.setFocusPainted(false);
        inputDatePanel.add(submitButton); 
        
        printButton = new JButton("Print");
        printButton.setFont(new Font("Arial",Font.BOLD,16));
        printButton.setFocusPainted(false);
        inputDatePanel.add(printButton); 
        
        showDetailsTextarea = new JTextArea();
        showDetailsTextarea.setFont(new Font("Arial",Font.BOLD,14));
        showDetailsTextarea.setEditable(false);
        scroll = new JScrollPane(showDetailsTextarea);
        inputPanel.add(scroll); 
    }
 
    /**
     *  This Method Helps To Generate Date Manually
     */
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
    
    /**
     * This Method Used For Set IncomeCost Information
     * 
     * @param keyword KeyWord Determines Sales or Purchase Part
     * @param date Date of A Specific Day
     */
    public void setIncomeCostInformation(String keyword,String date)
    {
        try{
                BufferedReader br = new BufferedReader(new FileReader("IncomeCostDatabase.txt"));
                String s,dataDate;
                String ans = "";
                int incomeAmount = 0;
                int costAmount = 0;
                while((s=br.readLine())!=null)
                { 
                    if(date.length()==10)
                    {
                        dataDate = s.substring(11, 21);
                    }
                    else
                    {
                        dataDate = s.substring(13, 21);
                    }
                    
                    if(date.equals(dataDate))
                    {
                        String demo[] = s.substring(11, s.length()).split(",");
                        int j=0;
                        while(j!=demo.length)
                        {
                            ans = ans + demo[j]+" ";
                            j++;
                        } 
                        ans = ans + "Tk\n";

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
                                
                            costAmount = costAmount+amount;  
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
                            incomeAmount = incomeAmount+amount;  
                            
                            }
                         }
                     }
 
                    if("".equals(ans))
                    {
                        JOptionPane.showMessageDialog(null, "No Information On That "+keyword);
                        showDetailsTextarea.setText("");
                    }
                    else
                    {                     
                        ans = ans + "\n\nThis "+keyword+"'s Total Income "+incomeAmount+" Tk"; 
                        ans = ans + "\nThis "+keyword+"'s Total Cost "+costAmount+" Tk"; 
                        ans = ans + "\nThis "+keyword+"'s Total Cash "+(incomeAmount-costAmount)+" Tk"; 
                        
                        showDetailsTextarea.setText(ans);
                    }  
                        
                    }catch(Exception ex){System.out.println(ex);}
    }
    
    /**
     *  Sets Daily Income Cost Features
     */
    public void setDailyIncomeCostFeatures()
    {
        submitButton.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(dayCombobox.getSelectedItem())&&!"".equals(monthCombobox.getSelectedItem())&&!"".equals(yearCombobox.getSelectedItem()))
                {
                    String result = dayCombobox.getSelectedItem()+"/"+monthCombobox.getSelectedItem()+"/"+yearCombobox.getSelectedItem();
                    setIncomeCostInformation("Day",result);
                }
                else if(!"".equals(monthCombobox.getSelectedItem())&&!"".equals(yearCombobox.getSelectedItem()))
                {
                    String result = "/"+monthCombobox.getSelectedItem()+"/"+yearCombobox.getSelectedItem();
                    setIncomeCostInformation("Month",result);
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Please Enter A Valid Date");
                }
            }
        });
        
        printButton.addActionListener(new ActionListener(){
        
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(showDetailsTextarea.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Get Some Income Cost Information");
                }
                else 
                {
                    try {
                        showDetailsTextarea.print();
                    } catch (PrinterException ex) {
                    }   
                }  
            }
         
        });
        
        
    }
    
    public static void main(String[] args) 
    {
        DailyIncomeCostPage frame = new DailyIncomeCostPage();
        frame.setVisible(true);
    }
}
