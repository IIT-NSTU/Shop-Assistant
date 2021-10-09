
package Templates;

import ShopAssistant.CreatePurchaseInvoicePage;
import ShopAssistant.CreateSalesInvoicePage;
import ShopAssistant.HomePage;
import ShopAssistant.LoginPage;
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
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class InvoiceGeneratorTemplate extends FrameSetup
{
    public JPanel horizontalPanelTop,mainPanel,horizontalPanelBottom,inputPanel,outputPanel,totalInformationPanel;
    public JLabel dateLabel,idLabel,nameLabel,addressLabel,mobileNumberLabel,productTypeLabel,modelNumberLabel,quantityLabel,unitPriceLabel,paymentLabel,paidLabel,dueLabel,subtotalLabel,discountLabel,totalPaymentLabel,totalPaidLabel,totalDueLabel;
    public JTextField dateTextField,idTextField,nameTextField,addressTextField,mobileNumberTextField,quantityTextField,unitPriceTextField,paymentTextField,subtotalTextField,discountTextField,totalPaymentTextField,totalPaidTextField,totalDueTextField;
    public JButton homeButton,createSalesInvoiceButton,createPurchaseInvoiceButton,logoutButton,clearButton,addToCartButton,printButton,deleteButton;
    public JEditorPane cartEditorPane;
    public JScrollPane scroll;
    
    public JButton horizontalPanelTopButtons[] = {homeButton,createSalesInvoiceButton,createPurchaseInvoiceButton,logoutButton};
    public JButton horizontalPanelBottomButtons[] = {clearButton,addToCartButton,deleteButton,printButton};
    public JComboBox productTypeCombobox,modelNumberCombobox;
    public int subtotal=0,totalPayment,totalPaid=0,totalDue=0,serialNo=1;
    public String htmlText = "";
    public String rawData = "";

    public Font font = new Font("Arial",Font.BOLD,16);
    
    public InvoiceGeneratorTemplate(String keyword,String fileName)
    {
        setFrame();
        setContainer();
        setPanels();
        setButtons();
        setInputPanel(keyword,fileName);
        setOutputPanel();
        setListeners(keyword,fileName);
    }
    
    public void setFrame()
    {
        super.setFrame();
        this.setSize(950, 600);
        this.setLocationRelativeTo(null);
    }
    
    public void setContainer()
    {
        //Method Overriding Contept Used
        super.setContainer();  //Here we get setContainer() of parrent class
        container.setLayout(new BorderLayout(15,0));
    }
    
    public void setPanels()
    {
        nullLabel = new JLabel("");
        container.add(nullLabel,BorderLayout.WEST);
        nullLabel = new JLabel("");
        container.add(nullLabel,BorderLayout.EAST);
        
        horizontalPanelTop = new JPanel();
        horizontalPanelTop.setLayout(new GridLayout(1,4));
        horizontalPanelTop.setCursor(new Cursor(Cursor.HAND_CURSOR));
        container.add(horizontalPanelTop,BorderLayout.NORTH);
        
        horizontalPanelBottom = new JPanel();
        horizontalPanelBottom.setLayout(new GridLayout(1,4));
        horizontalPanelBottom.setCursor(new Cursor(Cursor.HAND_CURSOR));
        container.add(horizontalPanelBottom,BorderLayout.SOUTH);
        
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(1,2));
        container.add(mainPanel,BorderLayout.CENTER);
        
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(10,2));
        inputPanel.setBackground(Color.WHITE);
        mainPanel.add(inputPanel);
        
        outputPanel = new JPanel();
        outputPanel.setLayout(new BorderLayout());
        mainPanel.add(outputPanel);
    }
    
    public void setButtons()
    {
        String horizontalPanelTopButtonNames[] = {"Home","Create Sales Invoice","Create Purchase Invoice","Log Out"};
        String horizontalPanelBottomButtonNames[] = {"Clear","Add to Cart","Finish","Print"};
        ImageIcon horizontalPanelBottomButtonIcons[]  = {clearButtonIcon,addToCartButtonIcon,deleteButtonIcon,printButtonIcon}; 

        for(int i = 0 ; i < horizontalPanelTopButtons.length ; i++)
        {
            horizontalPanelTopButtons[i] = new JButton(horizontalPanelTopButtonNames[i]);
            horizontalPanelTopButtons[i].setName(horizontalPanelTopButtonNames[i]);
            horizontalPanelTopButtons[i].setFont(new Font("Arial",Font.PLAIN,16));
            horizontalPanelTopButtons[i].setBackground(Color.DARK_GRAY);
            horizontalPanelTopButtons[i].setForeground(Color.WHITE);
            horizontalPanelTopButtons[i].setFocusPainted(false);
            horizontalPanelTop.add(horizontalPanelTopButtons[i]);
        }
        
        horizontalPanelTopButtons[0].setIcon(homeButtonIcon);
        horizontalPanelTopButtons[3].setIcon(logoutButtonIcon);
        
        for(int i = 0 ; i < horizontalPanelBottomButtons.length ; i++)
        {
            horizontalPanelBottomButtons[i] = new JButton(horizontalPanelBottomButtonNames[i]);
            horizontalPanelBottomButtons[i].setName(horizontalPanelBottomButtonNames[i]);
            horizontalPanelBottomButtons[i].setFont(new Font("Arial",Font.PLAIN,16));
            horizontalPanelBottomButtons[i].setBackground(Color.DARK_GRAY);
            horizontalPanelBottomButtons[i].setForeground(Color.WHITE);
            horizontalPanelBottomButtons[i].setIcon(horizontalPanelBottomButtonIcons[i]);
            horizontalPanelBottomButtons[i].setFocusPainted(false);
            horizontalPanelBottom.add(horizontalPanelBottomButtons[i]);
        }
    }
    
    public void setInputPanel(String keyword,String fileName)
    {   
        idLabel = new JLabel();
        idLabel.setFont(font);
        inputPanel.add(idLabel);
        
        if("Customer".equals(keyword))
        {
            idLabel.setText("Sales Id");
        }
        else
        {
            idLabel.setText("Purchase Id");
        }
        
        idTextField = new JTextField();
        idTextField.setEditable(false);
        idTextField.setFont(font);
        inputPanel.add(idTextField);
        setId(fileName);
        
        dateLabel = new JLabel("Date & Time");
        dateLabel.setFont(font);
        inputPanel.add(dateLabel);
        
        dateTextField = new JTextField(""+setDate());
        dateTextField.setEditable(false);
        dateTextField.setFont(font);
        inputPanel.add(dateTextField);

        nameLabel = new JLabel("Enter "+keyword+"'s Name");
        nameLabel.setFont(font);
        inputPanel.add(nameLabel);
        nameTextField = new JTextField();
        nameTextField.setFont(font);
        inputPanel.add(nameTextField);

        addressLabel = new JLabel("Enter "+keyword+"'s Address");
        addressLabel.setFont(font);
        inputPanel.add(addressLabel);
        addressTextField = new JTextField();
        addressTextField.setFont(font);
        inputPanel.add(addressTextField);
        
        mobileNumberLabel = new JLabel("Enter Mobile Number");
        mobileNumberLabel.setFont(font);
        inputPanel.add(mobileNumberLabel);
        mobileNumberTextField = new JTextField();
        mobileNumberTextField.setFont(font);
        inputPanel.add(mobileNumberTextField);
        
        productTypeLabel = new JLabel("Enter Product's Type");
        productTypeLabel.setFont(font);
        inputPanel.add(productTypeLabel);
        
        productTypeCombobox = new JComboBox(setProductTypes());
        productTypeCombobox.setFont(font);
        inputPanel.add(productTypeCombobox);
               
        modelNumberLabel = new JLabel("Enter Product's Model");
        modelNumberLabel.setFont(font);
        inputPanel.add(modelNumberLabel);
        modelNumberCombobox = new JComboBox();
        modelNumberCombobox.setEnabled(false);
        modelNumberCombobox.setFont(font);
        inputPanel.add(modelNumberCombobox);
        
        quantityLabel = new JLabel("Enter Product's Quantity");
        quantityLabel.setFont(font);
        inputPanel.add(quantityLabel);
        quantityTextField = new JTextField("");
        quantityTextField.setFont(font);
        inputPanel.add(quantityTextField);
        
        unitPriceLabel = new JLabel("Enter Per Item Price");
        unitPriceLabel.setFont(font);
        inputPanel.add(unitPriceLabel);
        unitPriceTextField = new JTextField("");
        unitPriceTextField.setFont(font);
        inputPanel.add(unitPriceTextField);
         
        paymentLabel = new JLabel("Product's Payment");
        paymentLabel.setFont(font);
        inputPanel.add(paymentLabel);
        paymentTextField = new JTextField("");
        paymentTextField.setFont(font);
        paymentTextField.setEditable(false);
        inputPanel.add(paymentTextField);
        
    }
    
    public void setOutputPanel()
    {
       cartEditorPane = new JEditorPane();
       cartEditorPane.setContentType("text/html");
       cartEditorPane.setText(htmlText);
       cartEditorPane.setBackground(Color.white);
       cartEditorPane.setEditable(false);
       scroll = new JScrollPane(cartEditorPane);
       outputPanel.add(scroll,BorderLayout.CENTER);
       
       totalInformationPanel = new JPanel();
       GridLayout gridlayout = new GridLayout(5,2);
       gridlayout.setVgap(7);
       totalInformationPanel.setLayout(gridlayout);
       outputPanel.add(totalInformationPanel,BorderLayout.SOUTH);
       
       subtotalLabel = new JLabel("        Subtotal");
       subtotalLabel.setFont(new Font("Arial",Font.BOLD,18));
       totalInformationPanel.add(subtotalLabel);
       subtotalTextField = new JTextField("");
       subtotalTextField.setFont(new Font("Arial",Font.BOLD,18));
       subtotalTextField.setEditable(false);
       totalInformationPanel.add(subtotalTextField);
       
       discountLabel = new JLabel("        Discount");
       discountLabel.setFont(new Font("Arial",Font.BOLD,18));
       totalInformationPanel.add(discountLabel);
       discountTextField = new JTextField("");
       discountTextField.setFont(new Font("Arial",Font.BOLD,18));
       totalInformationPanel.add(discountTextField);
       
       totalPaymentLabel = new JLabel("        Total Payment");
       totalPaymentLabel.setFont(new Font("Arial",Font.BOLD,18));
       totalInformationPanel.add(totalPaymentLabel);
       totalPaymentTextField = new JTextField("");
       totalPaymentTextField.setFont(new Font("Arial",Font.BOLD,18));
       totalPaymentTextField.setEditable(false);
       totalInformationPanel.add(totalPaymentTextField);
       
       totalPaidLabel = new JLabel("        Total Paid Amount");
       totalPaidLabel.setFont(new Font("Arial",Font.BOLD,18));
       totalInformationPanel.add(totalPaidLabel);
       totalPaidTextField = new JTextField("");
       totalPaidTextField.setFont(new Font("Arial",Font.BOLD,18));
       totalInformationPanel.add(totalPaidTextField);
       
       totalDueLabel = new JLabel("        Total Due Amount");
       totalDueLabel.setFont(new Font("Arial",Font.BOLD,18));
       totalInformationPanel.add(totalDueLabel);
       totalDueTextField = new JTextField("");
       totalDueTextField.setFont(new Font("Arial",Font.BOLD,18));
       totalDueTextField.setEditable(false);
       totalInformationPanel.add(totalDueTextField);
    }
    
    public void setId(String fileName)
    {
    try{
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String s = "",temp="";
        while((s=br.readLine())!=null)
        {
            temp = s;
        }
 
        if("".equals(temp))
        {
            if("SalesDatabase.txt".equals(fileName))
            {
                idTextField.setText("S200000001");
            }
            else
            {
                idTextField.setText("P100000001");
            }
        }
        else
        {
            if("SalesDatabase.txt".equals(fileName))
            {
                idTextField.setText("S"+(Integer.parseInt(temp.substring(1, 10))+1));
            }
            else
            {
                idTextField.setText("P"+(Integer.parseInt(temp.substring(1, 10))+1));
            }
        } 
            
        }catch(Exception e){}
    }
    
    public void setPayment()
    {
        try{
        int answer = Math.round(Float.parseFloat(quantityTextField.getText())*Float.parseFloat(unitPriceTextField.getText()));
                        
        if(answer>1000000000)
        {
                JOptionPane.showMessageDialog(null, "Please Do Not Enter Large Digits");
                quantityTextField.setText("");
                unitPriceTextField.setText("");
                paymentTextField.setText("");
        }
        else
        {
            paymentTextField.setText(""+answer);
        }  }catch(Exception ex) {}                 
    }
    
    public void setDue()
    {
        try{
        int answer = Math.round(Float.parseFloat(totalPaymentTextField.getText())-Float.parseFloat(totalPaidTextField.getText()));
                        
        if(answer<0)
        {
            JOptionPane.showMessageDialog(null, "You Paid More Than Required Amount");     
            totalPaidTextField.setText("");
            totalDueTextField.setText("");
        }
        else
        {
            totalDueTextField.setText(""+answer);
        }  }catch(Exception ex) {}             
    }
    
    public JButton getButton(String buttonName)
    {
        int i,j; JButton button = new JButton();

        for(i = 0; i < horizontalPanelTopButtons.length ; i++)
        {
            if(horizontalPanelTopButtons[i].getName().equals(buttonName))
            {
                button = horizontalPanelTopButtons[i];
                break;
            }
        }
        
        for(j = 0; j < horizontalPanelBottomButtons.length ; j++)
        {   
            if(horizontalPanelBottomButtons[j].getName().equals(buttonName))
            {
                button = horizontalPanelBottomButtons[j];
            } 
        }
        
        return button;
    }
    
    public boolean checkAllFilledUp()
    {
        if("".equals(idTextField.getText())||"".equals(dateTextField.getText())||"".equals(nameTextField.getText())||"".equals(addressTextField.getText())||"".equals(mobileNumberTextField.getText())||"".equals(productTypeCombobox.getSelectedItem())||"".equals(modelNumberCombobox.getSelectedItem())||"".equals(quantityTextField.getText())||"".equals(unitPriceTextField.getText())||"".equals(paymentTextField.getText()))
        {
            return false;
        }
        else
        {
            return true;
        }
    }
    
    public void addToCart(String fileName)
    {
        subtotal = subtotal + Integer.parseInt(paymentTextField.getText());
        subtotalTextField.setText(""+subtotal);
        
        setDue();
   
        productTypeCombobox.setSelectedItem("");
        modelNumberCombobox.setSelectedItem("");
        quantityTextField.setText("");
        unitPriceTextField.setText("");
        paymentTextField.setText(""); 
        discountTextField.setText(""); 
        totalPaidTextField.setText(""); 
    }
    
 
    public void addHTMLbegintext(String keyword)
    {
        htmlText = htmlText 
            
            +"<table width=\"100%\">" 
            +"<tr style=\"text-align:center;font-size:18px;font-weight:bold;font-family:arial;color:red;\">Samir Electronics</tr>"
            +"<tr style=\"font-family:arial;font-size:13px;font-weight:bold;text-align:center;color:blue;\">WALTON</tr>"
            + "<tr style=\"text-align:center;font-size:13px;font-weight:bold;font-family:arial;\">Exclusive Showroom</tr>"
            +"<tr style=\"text-align:center;font-size:9px;font-family:arial;\">Saiham Future Complex , Madhabpur , Habiganj</tr>"
            +"<tr style=\"text-align:center;font-size:9px;font-family:arial;\">Mobile : 01748987951 , 01746003532</tr>"
            +"<tr style=\"text-align:center;font-size:7px;font-family:arial;\">Money Receipt</tr>"
            +"</table>"
            +"<br>"
            +"<table width=\"100%\" style=\"font-size:8px\" >"
            +"<tr>"
            +"<td>"+keyword+" Name</td><td>:</td><td>"+nameTextField.getText()+"</td><td></td><td></td><td></td><td></td><td></td><td></td><td>Invoice No</td><td>:</td><td>"+idTextField.getText()+"</td>"
            +"</tr>"
            +"<tr>"
            +"<td>"+keyword+" Address</td><td>:</td><td>"+addressTextField.getText()+"</td><td></td><td></td><td></td><td></td><td></td><td></td><td>Date & Time</td><td>:</td><td>"+dateTextField.getText()+"</td>"
            +"</tr>"
            +"<tr>"
            +"<td>"+keyword+" Mobile Number</td><td>:</td><td>"+mobileNumberTextField.getText()+"</td>"
            + "</tr>"
            + "</table>"
            +"<br>"
            +"<br>"
            +"<br>"
            +"<table width=\"100%\" style=\"font-size:8px\" >"
            +"<tr>"
            +"<td>No.</td><td>Product Type</td><td>Product Model Number</td><td style=\"text-align:right\">Quantity</td><td style=\"text-align:right\">Unit Price</td><td style=\"text-align:right\">Payment</td>"
            +"</tr>"
            +"<tr>"
            +"<td>"+serialNo+"</td><td>"+productTypeCombobox.getSelectedItem()+"</td><td>"+modelNumberCombobox.getSelectedItem()+"</td><td style=\"text-align:right\">"+quantityTextField.getText()+"</td><td style=\"text-align:right\">"+unitPriceTextField.getText()+"</td><td style=\"text-align:right\">"+paymentTextField.getText()+"</td>"
            +"</tr>"
               
            ;
        
        rawData = rawData + idTextField.getText() +","+ dateTextField.getText() +","+ nameTextField.getText() +","+ addressTextField.getText() +","+ mobileNumberTextField.getText() +","+ productTypeCombobox.getSelectedItem() +","+ modelNumberCombobox.getSelectedItem() +","+ quantityTextField.getText() +","+ unitPriceTextField.getText() +","+ paymentTextField.getText()+"," ;
    }
    
    public void addHTMLmidtext()
    {
        htmlText = htmlText 
                        +"<tr>"
                        +"<td>"+(serialNo=serialNo+1)+"</td><td>"+productTypeCombobox.getSelectedItem()+"</td><td>"+modelNumberCombobox.getSelectedItem()+"</td><td style=\"text-align:right\">"+quantityTextField.getText()+"</td><td style=\"text-align:right\">"+unitPriceTextField.getText()+"</td><td style=\"text-align:right\">"+paymentTextField.getText()+"</td>"
                        +"</tr>"
                        ;
        
        rawData = rawData + productTypeCombobox.getSelectedItem() +","+ modelNumberCombobox.getSelectedItem() +","+ quantityTextField.getText() +","+ unitPriceTextField.getText() +","+ paymentTextField.getText()+"," ;

    }
    
    public void addHTMLendtext(String keyword)
    {
        htmlText = htmlText 
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Subtotal</td><td style=\"text-align:right\">"+subtotalTextField.getText()+"</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Discount ( - )</td><td style=\"text-align:right\">"+discountTextField.getText()+"</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Total Payment</td><td style=\"text-align:right\">"+totalPaymentTextField.getText()+"</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Paid Amount</td><td style=\"text-align:right\">"+totalPaidTextField.getText()+"</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td></td><td></td><td></td><td></td><td style=\"text-align:right\">Due Amount</td><td style=\"text-align:right\">"+totalDueTextField.getText()+"</td>"
                        +"</tr>"
                        + "</table>"
                        +"<br>"
                        +"<br>"
                        +"<table width=\"100%\" style=\"font-size:10px\">"
                        +"<tr>"
                        +"<td>-----------------------</td><td style=\"text-align:right\">-----------------------</td>"
                        +"</tr>"
                        +"<tr>"
                        +"<td>Owner's Sign</td><td style=\"text-align:right\">"+keyword+"'s Sign</td>"
                        +"</tr>"
                        + "</table>"
                        ;
        
        rawData = rawData + totalPaymentTextField.getText() +","+totalPaidTextField.getText() +","+totalDueTextField.getText();
    }
    
    public void setListeners(String keyword,String fileName)
    {
        nameTextField.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){} 
     
            public void keyPressed(KeyEvent ke) {}

            public void keyReleased(KeyEvent ke) 
            {
                try{
                char demo[] = nameTextField.getText().toCharArray();
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
                
                nameTextField.setText(""+result);
                }catch(Exception e){}
            }
        
        });
        
        addressTextField.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){}    
           
            public void keyPressed(KeyEvent ke){}    

            public void keyReleased(KeyEvent ke) 
            {
                try{
                char demo[] = addressTextField.getText().toCharArray();
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
                
                addressTextField.setText(""+result);
                }catch(Exception e){}
            }
        });
        
        mobileNumberTextField.addKeyListener(new KeyListener()
        {
           
            public void keyTyped(KeyEvent ke){}    

            public void keyPressed(KeyEvent ke) {} 
            
            public void keyReleased(KeyEvent ke) 
            {
                mobileNumberTextField.setToolTipText(mobileNumberTextField.getText().length()+" Digits");
                        
                if(!isDigit(ke.getKeyChar()))
                {    
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits"); 
                    mobileNumberTextField.setText("");
                }
                
                if(mobileNumberTextField.getText().length()>11)
                {
                   JOptionPane.showMessageDialog(null, "Please Enter Valid Mobile Number (not more than 11 digits)");
                   mobileNumberTextField.setText("");
                }
            }
        
        });
    
        productTypeCombobox.addActionListener(new ActionListener()
        {
        
            public void actionPerformed(ActionEvent e)
            {
                modelNumberCombobox.setEnabled(true);
                modelNumberCombobox.removeAllItems();
                modelNumberCombobox.addItem("");
                
                try{                    
                BufferedReader br = new BufferedReader(new FileReader("ProductType&ModelList.txt"));
                String s,result="",answer="";
     
                while((s=br.readLine())!=null)
                {
                    if(productTypeCombobox.getSelectedItem().equals(s.substring(0, s.indexOf(","))))
                    {
                        result = s.substring(s.indexOf(",")+1, s.length());    
                    }
                }
         
                char demo[] = result.toCharArray();
                int i=0;
                
                while(i!=demo.length)
                {
                    answer = answer + demo[i];
                    if(demo[i]=='.')
                    {
                        modelNumberCombobox.addItem(answer);
                        answer="";
                    }
                    i++;
                }              
            }catch(Exception ex){}
               
            }
  
        });
        
        modelNumberCombobox.addActionListener(new ActionListener(){
        
        public void actionPerformed(ActionEvent e)
            {
                quantityTextField.setText("");
            }
        
        });
        
        unitPriceTextField.addKeyListener(new KeyListener()
        { 
            public void keyTyped(KeyEvent ke){}  
            
            public void keyPressed(KeyEvent ke){} 
           
            public void keyReleased(KeyEvent ke) 
            {
                String inputText = unitPriceTextField.getText();
                
                char demo[] = inputText.toCharArray();
                
                int i = 0,count=0;
                
                while(i!=inputText.length())
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
                    unitPriceTextField.setText("");
                } 
                
                else
                {   
                    setPayment();
                    setDue();
                }
             }
        });
        
        discountTextField.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){}    
          
            public void keyPressed(KeyEvent ke) {} 
            
            public void keyReleased(KeyEvent ke) 
            {
                if(!isDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits");
                    discountTextField.setText("");
                }    
                else
                {
                    try{
                    totalPayment = Integer.parseInt(subtotalTextField.getText())-(Integer.parseInt(discountTextField.getText()));
                    
                    if(totalPayment<=0)
                    {
                        JOptionPane.showMessageDialog(null, "Please Enter Valid Digits");                        
                        discountTextField.setText("");
                    }
                    else
                    {
                        totalPaymentTextField.setText(""+totalPayment);
                        totalPaidTextField.setText("");
                    }
                    }catch(Exception e) {}
                }
             }
        });
        
        totalPaidTextField.addKeyListener(new KeyListener()
        {
            public void keyTyped(KeyEvent ke){}    
          
            public void keyPressed(KeyEvent ke) {} 
            
            public void keyReleased(KeyEvent ke) 
            {
                if(!isDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digits");
                    totalPaidTextField.setText("");
                }    
                else
                {    
                    setDue();
                }
             }
        });
        
        getButton("Home").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(cartEditorPane.getText()))
                {
                    setReminder();
                }
                dispose();
                HomePage frame = new HomePage();
                frame.setVisible(true);
            }
        });
        
        getButton("Create Sales Invoice").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(cartEditorPane.getText()))
                {
                    setReminder();
                }
                dispose();             
                CreateSalesInvoicePage frame = new CreateSalesInvoicePage("Customer","SalesDatabase.txt");
                frame.setVisible(true);
            }
        });
        
        getButton("Create Purchase Invoice").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(cartEditorPane.getText()))
                {
                    setReminder();
                }
                dispose();
                CreatePurchaseInvoicePage frame = new CreatePurchaseInvoicePage("Company","PurchaseDatabase.txt");
                frame.setVisible(true);  
            }
        });
        
        getButton("Log Out").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!"".equals(cartEditorPane.getText()))
                {
                    setReminder();
                }
                dispose();
                LoginPage frame = new LoginPage();
                frame.setVisible(true);
            }
        });
        
        getButton("Clear").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                nameTextField.setText("");
                addressTextField.setText("");
                mobileNumberTextField.setText("");
                productTypeCombobox.setSelectedItem("");
                modelNumberCombobox.setSelectedItem("");
                quantityTextField.setText("");
                unitPriceTextField.setText("");
                paymentTextField.setText("");
                subtotalTextField.setText("");
                discountTextField.setText("");
                totalPaymentTextField.setText("");
                totalPaidTextField.setText("");
                totalDueTextField.setText("");
                cartEditorPane.setText("");
                htmlText = "";
                subtotal=0;
                totalPaid=0;
                totalDue=0;
                serialNo=1;
            }
        });
        
        getButton("Add to Cart").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(!checkAllFilledUp()||"0".equals(quantityTextField.getText())||"0".equals(unitPriceTextField.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter All Valid Information");
                }
                else 
                { 
                    int choice = JOptionPane.showConfirmDialog(null, "Are Your Sure?\n\n\n\n\nID : "+idTextField.getText()+"\nDate : "+dateTextField.getText()+"\n"+keyword+" Name : "+nameTextField.getText()+"\n"+keyword+" Address : "+addressTextField.getText()+"\n"+keyword+" Mobile Number : "+mobileNumberTextField.getText()+"\nProduct Type : "+productTypeCombobox.getSelectedItem()+"\nModel Number : "+modelNumberCombobox.getSelectedItem()+"\nQuantity : "+quantityTextField.getText()+"\nPer Item Price : "+unitPriceTextField.getText()+"\nPayment : "+paymentTextField.getText()+"\n\n\n\n\n\n","Confirm",JOptionPane.YES_NO_OPTION);
                    
                    if(choice == JOptionPane.YES_OPTION)
                    {
                        
                    if(!cartEditorPane.getText().contains("WALTON"))
                    {
                        addHTMLbegintext(keyword);
                        cartEditorPane.setText(htmlText);
                        addToCart(fileName);
                    }
                    else
                    {
                        addHTMLmidtext();
                        cartEditorPane.setText(htmlText);
                        addToCart(fileName);
                    }
                    
                    }
                }
            }
        });
        
        getButton("Finish").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(subtotalTextField.getText())||"".equals(discountTextField.getText())||"".equals(totalPaymentTextField.getText())||"".equals(totalPaidTextField.getText())||"".equals(totalDueTextField.getText())||!cartEditorPane.getText().contains("WALTON"))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Discount and Paid Amount");
                }
                else 
                {
                        setDue();
                        addHTMLendtext(keyword);
                        cartEditorPane.setText(htmlText);
                        getButton("Finish").setEnabled(false);
                        getButton("Add to Cart").setEnabled(false);
                        getButton("Clear").setEnabled(false);
                        getButton("Print").setEnabled(true);
                        
                        
                        try {
                        File f2 = new File("IncomeCostDatabase.txt");
                        PrintWriter q = new PrintWriter(new FileOutputStream(f2, true));
                        if ("SalesDatabase.txt".equals(fileName)) {
                            q.append("S200000000" + "," + dateTextField.getText() + "," + "Income By Product Sale " + "," + nameTextField.getText() + "," + addressTextField.getText() + "," + mobileNumberTextField.getText() + "," + totalPaidTextField.getText() + "\n");
                            q.close();
                        } else {
                            q.append("P100000000" + "," + dateTextField.getText() + "," + "Cost By Product Purchase " + "," + nameTextField.getText() + "," + addressTextField.getText() + "," + mobileNumberTextField.getText() + "," + totalPaidTextField.getText() + "\n");
                            q.close();
                        }
                    } catch (FileNotFoundException ex) {
                    }
            
                    
            try
            {
                File f = new File(fileName);
                   
                PrintWriter p = new PrintWriter(new FileOutputStream(f,true));
                p.append(rawData+"\n");
                p.close();
                
            } 
            catch (FileNotFoundException ex) {}
                }              
            }
        });
        
        getButton("Print").setEnabled(false);
        
        getButton("Print").addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(cartEditorPane.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Add Something To The Cart");
                }
                else 
                {
                    try {
                        cartEditorPane.print();
                    } catch (PrinterException ex) {
                    }   
                }    
            }
        });    
    }
}
