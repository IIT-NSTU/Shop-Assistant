
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class Home_Page extends DashBoard_Template 
{
    public JPanel search_panel,input_panel;
    public JLabel search_label;
    public JTextField search_textfield;
    public JButton search_button;
    public JTextArea show_details_textarea;
    public JScrollPane scroll;
    
    public Home_Page()
    {
        setPageButton();
        setMainPanel();
        setSearchPanel();
        setSeachFeatures();
    }
    
     public void setPageButton()
    {
        getButton("Home").setBackground(Color.LIGHT_GRAY);
        getButton("Home").setForeground(Color.BLACK);
        getButton("Home").setFont(new Font("Arial",Font.BOLD,16));
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
        
        
        search_panel = new JPanel();
        search_panel.setLayout(new GridLayout(2,1));
        search_panel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        search_panel.setBackground(Color.WHITE);
        main_panel.add(search_panel,BorderLayout.CENTER);
    }
    
    public void setSearchPanel()
    {
        input_panel = new JPanel();
        input_panel.setLayout(new GridLayout(3,1));
        search_panel.add(input_panel);
        
        search_label = new JLabel("Enter Your Keyword");
        search_label.setFont(new Font("Arial",Font.BOLD,16));
        search_label.setOpaque(true);
        search_label.setBackground(Color.WHITE);
        input_panel.add(search_label);
        
        search_textfield = new JTextField();
        search_textfield.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(search_textfield);
        
        search_button = new JButton("Search");
        search_button.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(search_button);
        
        show_details_textarea = new JTextArea();
        show_details_textarea.setFont(new Font("Arial",Font.BOLD,16));
        show_details_textarea.setEditable(false);
        scroll = new JScrollPane(show_details_textarea);
        search_panel.add(scroll);  
    }
    
    public void setSeachFeatures()
    {
        search_button.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e)
        {
            if("".equals(search_textfield.getText()))
            {
                JOptionPane.showMessageDialog(null,"Please Enter A Keyword");
            }
            else
            {
                try{
                
                BufferedReader sales = new BufferedReader(new FileReader("Sales_Database.txt"));
                String s;
                                
                while((s=sales.readLine())!=null)
                {
                    if(s.contains(search_textfield.getText()))
                    {
                        char demo[] = s.toCharArray();
                        int i = 0,j = 0;
                        int start = 0,end;
   
                        while(i!=demo.length)
                        {
                            if(demo[i]==',')
                            {
                                end = i ;
           
                                result = result + type[j]+ s.substring(start,end) + "\n";
                                
                                j++;
                                
                                start = i + 1;
                            }
                            
                            i++;
                        }
                        
                        result = result + type[j]+ s.substring(start,i) + "\n";
                          
                        result = result + "\n";
                    }
                }
                
                BufferedReader purchase = new BufferedReader(new FileReader("Purchase_Database.txt"));
                
                while((s=purchase.readLine())!=null)
                {
                    if(s.contains(search_textfield.getText()))
                    {
                        char demo[] = s.toCharArray();
                        int i = 0,j = 0;
                        int start = 0,end;
   
                        while(i!=demo.length)
                        {
                            if(demo[i]==',')
                            {
                                end = i ;
           
                                result = result + type[j]+ s.substring(start,end) + "\n";
                                
                                j++;
                                
                                start = i + 1;
                            }
                            
                            i++;
                        }
                        
                        result = result + type[j]+ s.substring(start,i) + "\n";
                          
                        result = result + "\n";
   
                    }
                }
                
                if("".equals(result))
                {
                    JOptionPane.showMessageDialog(null,"No Information Found");
                }
                else
                {
                    show_details_textarea.setText(result);
                }    
                    
                }catch(Exception ex) { System.out.println(ex);}
            }
             
         
        }
        
        
        });
        
    }
    
    
    
    public static void main(String[] args) 
    {
       Home_Page frame = new Home_Page();
       frame.setVisible(true);
    }
}
