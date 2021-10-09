
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
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class HomePage extends DashBoardTemplate 
{
    public JPanel searchPanel,inputPanel;
    public JLabel searchLabel;
    public JTextField searchTextField;
    public JButton searchButton,clearButton,printButton;
    public JEditorPane showDetailsEditorpane;
    public JScrollPane scroll;
    public String HTMLText = "";
    
    /**
     *  Home Page Constructor
     */
    public HomePage()
    {
        setPageButton();
        setMainPanel();
        setSearchPanel();
        setSeachFeatures();
    }
    
    /**
     * This Method Sets Button Using Different BackGround and ForeGround Color
     */
    public void setPageButton()
    {
        getButton("Home").setBackground(Color.LIGHT_GRAY);
        getButton("Home").setForeground(Color.BLACK);
        getButton("Home").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    /**
     * This Method Sets Main Panel
     */
    public void setMainPanel()
    {
        mainPanel.setLayout(new BorderLayout(90,40));

        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.NORTH);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.EAST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.WEST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.SOUTH);
        
        
        searchPanel = new JPanel();
        searchPanel.setLayout(new BorderLayout());
        searchPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchPanel.setBackground(Color.WHITE);
        mainPanel.add(searchPanel,BorderLayout.CENTER);
    }
    
    /**
     * This Method Sets Search Panel
     */
    public void setSearchPanel()
    {
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3,2));
        searchPanel.add(inputPanel,BorderLayout.NORTH);
        
        searchLabel = new JLabel("Enter Your Keyword");
        searchLabel.setFont(new Font("Arial",Font.BOLD,16));
        searchLabel.setOpaque(true);
        searchLabel.setBackground(Color.WHITE);
        inputPanel.add(searchLabel);
        
        searchTextField = new JTextField();
        searchTextField.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(searchTextField);
        
        clearButton = new JButton("Clear");
        clearButton.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(clearButton);
        
        searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(searchButton);
        
        printButton = new JButton("Print");
        printButton.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(printButton);
        
        showDetailsEditorpane = new JEditorPane();
        showDetailsEditorpane.setContentType("text/html");
        showDetailsEditorpane.setText(HTMLText);
        showDetailsEditorpane.setEditable(false);
        scroll = new JScrollPane(showDetailsEditorpane);
        searchPanel.add(scroll,BorderLayout.CENTER);
    }
    
    /**
     * This Method Sets HTML Text For Design Purpose
     */
    public void setHTMLText(String filename)
    {
        try{
                
            BufferedReader brSalesOrPurchase = new BufferedReader(new FileReader(filename));
            String strSalesOrPurchase;
                  
            while((strSalesOrPurchase = brSalesOrPurchase.readLine())!=null)
            {
                if(strSalesOrPurchase.contains(searchTextField.getText()))
                {
                    char demo[] = strSalesOrPurchase.toCharArray();
                    String dataArray[] = new String[strSalesOrPurchase.length()];
                    
                    int i = 0 , j = 0, start = 0 , end;
                    
                    while(i!=demo.length)
                    {
                        if(demo[i]==',')
                        {
                            end = i;
                            
                            dataArray[j] = strSalesOrPurchase.substring(start, end);
                            j++;
                            
                            start = end + 1;
                        }
                        i++;
                    }
                    
                    dataArray[j] = strSalesOrPurchase.substring(start, i);
                    
                    int arraySizeToDue = j;
                    
                   
                    HTMLText = HTMLText
                        +"<table width = \"100%\" style=\"font-family:monospaced;font-weight:bold;font-size:13px;\" >"    
                        ;
                    
                    dataArray[0] = "  Invoice  Id  :  " + dataArray[0];
                    dataArray[1] = "  Date & Time  :  " + dataArray[1];
                    dataArray[2] = "  Name  :  " + dataArray[2];
                    dataArray[3] = "  Address  :  " + dataArray[3];
                    dataArray[4] = "  Mobile Number  :  " + dataArray[4];
                    dataArray[5] = "  Product Type  :  " + dataArray[5];
                    dataArray[6] = "  Model Number  :  " + dataArray[6];
                    dataArray[7] = "  Quantity  :  " + dataArray[7];
                    dataArray[8] = "  Unit Price  :  " + dataArray[8];
                    dataArray[9] = "  Payment  :  " + dataArray[9];
                    dataArray[arraySizeToDue-2] = "  Total Payment  :  " + dataArray[arraySizeToDue - 2];
                    dataArray[arraySizeToDue-1] = "  Paid Amount  :  " + dataArray[arraySizeToDue - 1];
                    dataArray[arraySizeToDue] = "  Due Amount  :  " + dataArray[arraySizeToDue];
                    
                    BufferedReader brIncomeCost = new BufferedReader(new FileReader("IncomeCostDatabase.txt"));
                    String strIncomeCost;
                    
                    int dueAmount = Integer.parseInt(dataArray[arraySizeToDue].substring(17, dataArray[arraySizeToDue].length()));
                    int m = arraySizeToDue + 1;
                    
                    while((strIncomeCost = brIncomeCost.readLine())!=null)
                    {
                        if(strSalesOrPurchase.substring(0, 10).equals(strIncomeCost.substring(0, 10)))
                        {
                            if(strIncomeCost.charAt(0)=='P')
                            {
                                int currentPayment = Integer.parseInt(strIncomeCost.substring(65, strIncomeCost.length()));
                                dataArray[m] = "  Due Amount  :  "+(dueAmount - currentPayment)+"("+strIncomeCost.substring(11, 21)+")  Paid "+currentPayment;
                                dueAmount = dueAmount - currentPayment;
                                m++;
                            }
                            if(strIncomeCost.charAt(0)=='S')
                            {
                                int currentPayment = Integer.parseInt(strIncomeCost.substring(68, strIncomeCost.length()));
                                dataArray[m] = "  Due Amount  :  "+(dueAmount - currentPayment)+"("+strIncomeCost.substring(11, 21)+")  Paid "+currentPayment;
                                dueAmount = dueAmount - currentPayment;
                                m++;
                            }
                        }
                    }
                    
                    int arraySize = m-1;
                            
                    for(i=0; i<= arraySize ;i++)
                    {
                        if(dataArray[i].contains(searchTextField.getText()))
                        {
                            if(dataArray[i].contains(":"))
                            {
                                HTMLText = HTMLText
                                +"<tr style=\"color:red;font-weight:bold;\"><td>"+dataArray[i].substring(0,dataArray[i].indexOf(":"))+"</td><td>:</td><td>"+dataArray[i].substring(dataArray[i].indexOf(":")+1,dataArray[i].length())+"</td></tr>";
                            }
                            else
                            {
                                HTMLText = HTMLText
                                +"<tr style=\"color:red;font-weight:bold;\"><td></td><td>:</td><td>"+dataArray[i]+"</td></tr>";
                            }                            
                        }    
                        else
                        {
                            if(dataArray[i].contains(":"))
                            {
                                HTMLText = HTMLText
                                +"<tr><td>"+dataArray[i].substring(0,dataArray[i].indexOf(":"))+"</td><td>:</td><td>"+dataArray[i].substring(dataArray[i].indexOf(":")+1,dataArray[i].length())+"</td></tr>";
                            }
                            else
                            {
                                HTMLText = HTMLText
                                +"<tr><td></td><td>:</td><td>"+dataArray[i]+"</td></tr>";
                            }
                        }
                    }
                    
                    HTMLText = HTMLText    
                        +"</table><br><br>"
                            ;  
                }    
            }
       
        }catch(Exception ex) { System.out.println(ex);}
            
    }
    
    /**
     *  Sets Search Features
     */
    public void setSeachFeatures()
    {
        searchButton.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e)
        {
            if("".equals(searchTextField.getText()))
            {
                JOptionPane.showMessageDialog(null,"Please Enter A Keyword");
            }
            else
            {
                setHTMLText("SalesDatabase.txt");
                setHTMLText("PurchaseDatabase.txt");
                showDetailsEditorpane.setText(HTMLText);
                HTMLText = "";
            }
        }
        
        });
        
        clearButton.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e)
        {
            showDetailsEditorpane.setText("");
        }
        
        });
        
        printButton.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e)
        {
            try 
            {
                showDetailsEditorpane.print();
            } catch (PrinterException ex) {}
        }
       
        });
        
    }

    public static void main(String[] args) 
    {
       HomePage frame = new HomePage();
       frame.setVisible(true);
    }
}
