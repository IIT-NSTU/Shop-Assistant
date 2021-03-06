
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
import javax.swing.JEditorPane;
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
    public JButton search_button,clear_button;
    public JEditorPane show_details_editorpane;
    public JScrollPane scroll;
    public String HTML_Text = "";
    
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
        input_panel.setLayout(new GridLayout(2,2));
        search_panel.add(input_panel);
        
        search_label = new JLabel("Enter Your Keyword");
        search_label.setFont(new Font("Arial",Font.BOLD,16));
        search_label.setOpaque(true);
        search_label.setBackground(Color.WHITE);
        input_panel.add(search_label);
        
        search_textfield = new JTextField();
        search_textfield.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(search_textfield);
        
        clear_button = new JButton("Clear");
        clear_button.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(clear_button);
        
        search_button = new JButton("Search");
        search_button.setFont(new Font("Arial",Font.BOLD,16));
        input_panel.add(search_button);
        
        show_details_editorpane = new JEditorPane();
        show_details_editorpane.setContentType("text/html");
        show_details_editorpane.setText(HTML_Text);
        show_details_editorpane.setEditable(false);
        scroll = new JScrollPane(show_details_editorpane);
        search_panel.add(scroll);  
    }
    
    public void setHTMLText(String filename)
    {
        try{
                
            BufferedReader br_sales_or_purchase = new BufferedReader(new FileReader(filename));
            String str_sales_or_purchase;
                  
            while((str_sales_or_purchase = br_sales_or_purchase.readLine())!=null)
            {
                if(str_sales_or_purchase.contains(search_textfield.getText()))
                {
                    char demo[] = str_sales_or_purchase.toCharArray();
                    String data_array[] = new String[str_sales_or_purchase.length()];
                    
                    int i = 0 , j = 0, start = 0 , end;
                    
                    while(i!=demo.length)
                    {
                        if(demo[i]==',')
                        {
                            end = i;
                            
                            data_array[j] = str_sales_or_purchase.substring(start, end);
                            j++;
                            
                            start = end + 1;
                        }
                        i++;
                    }
                    
                    data_array[j] = str_sales_or_purchase.substring(start, i);
                    
                    int array_size_to_due = j;
                    
                   
                    HTML_Text = HTML_Text
                        +"<table width = \"100%\" style=\"font-family:monospaced;font-weight:bold;font-size:13px;\" >"    
                        ;
                    
                    data_array[0] = "  Invoice  Id  :  " + data_array[0];
                    data_array[1] = "  Date & Time  :  " + data_array[1];
                    data_array[2] = "  Name  :  " + data_array[2];
                    data_array[3] = "  Address  :  " + data_array[3];
                    data_array[4] = "  Mobile Number  :  " + data_array[4];
                    data_array[5] = "  Product Type  :  " + data_array[5];
                    data_array[6] = "  Model Number  :  " + data_array[6];
                    data_array[7] = "  Quantity  :  " + data_array[7];
                    data_array[8] = "  Unit Price  :  " + data_array[8];
                    data_array[9] = "  Payment  :  " + data_array[9];
                    data_array[array_size_to_due-2] = "  Total Payment  :  " + data_array[array_size_to_due - 2];
                    data_array[array_size_to_due-1] = "  Paid Amount  :  " + data_array[array_size_to_due - 1];
                    data_array[array_size_to_due] = "  Due Amount  :  " + data_array[array_size_to_due];
                    
                    BufferedReader br_income_cost = new BufferedReader(new FileReader("Income_Cost_Database.txt"));
                    String str_income_cost;
                    
                    int due_amount = Integer.parseInt(data_array[array_size_to_due].substring(17, data_array[array_size_to_due].length()));
                    int m = array_size_to_due + 1;
                    
                    while((str_income_cost = br_income_cost.readLine())!=null)
                    {
                        if(str_sales_or_purchase.substring(0, 10).equals(str_income_cost.substring(0, 10)))
                        {
                            if(str_income_cost.charAt(0)=='P')
                            {
                                int current_payment = Integer.parseInt(str_income_cost.substring(65, str_income_cost.length()));
                                data_array[m] = "  Due Amount  :  "+(due_amount - current_payment)+"("+str_income_cost.substring(11, 21)+")  Paid "+current_payment;
                                due_amount = due_amount - current_payment;
                                m++;
                            }
                            if(str_income_cost.charAt(0)=='S')
                            {
                                int current_payment = Integer.parseInt(str_income_cost.substring(68, str_income_cost.length()));
                                data_array[m] = "  Due Amount  :  "+(due_amount - current_payment)+"("+str_income_cost.substring(11, 21)+")  Paid "+current_payment;
                                due_amount = due_amount - current_payment;
                                m++;
                            }
                        }
                    }
                    
                    int array_size = m-1;
                            
                    for(i=0; i<= array_size ;i++)
                    {
                        if(data_array[i].contains(search_textfield.getText()))
                        {
                            if(data_array[i].contains(":"))
                            {
                                HTML_Text = HTML_Text
                                +"<tr style=\"color:red;font-weight:bold;\"><td>"+data_array[i].substring(0,data_array[i].indexOf(":"))+"</td><td>:</td><td>"+data_array[i].substring(data_array[i].indexOf(":")+1,data_array[i].length())+"</td></tr>";
                            }
                            else
                            {
                                HTML_Text = HTML_Text
                                +"<tr style=\"color:red;font-weight:bold;\"><td></td><td>:</td><td>"+data_array[i]+"</td></tr>";
                            }                            
                        }    
                        else
                        {
                            if(data_array[i].contains(":"))
                            {
                                HTML_Text = HTML_Text
                                +"<tr><td>"+data_array[i].substring(0,data_array[i].indexOf(":"))+"</td><td>:</td><td>"+data_array[i].substring(data_array[i].indexOf(":")+1,data_array[i].length())+"</td></tr>";
                            }
                            else
                            {
                                HTML_Text = HTML_Text
                                +"<tr><td></td><td>:</td><td>"+data_array[i]+"</td></tr>";
                            }
                        }
                    }
                    
                    HTML_Text = HTML_Text    
                        +"</table><br><br>"
                            ;  
                }    
            }
       
        }catch(Exception ex) { System.out.println(ex);}
            
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
                setHTMLText("Sales_Database.txt");
                setHTMLText("Purchase_Database.txt");
                show_details_editorpane.setText(HTML_Text);
                HTML_Text = "";
            }
        }
        
        });
        
        clear_button.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e)
        {
            show_details_editorpane.setText("");
        }
        
        });
        
    }
    
    
    
    public static void main(String[] args) 
    {
       Home_Page frame = new Home_Page();
       frame.setVisible(true);
    }
}
