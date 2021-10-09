
package Templates;

import java.awt.Color;
import java.awt.Container;
import java.io.BufferedReader;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


public class FrameSetup extends JFrame 
{
    public Container container;    //Frame Container Declaration  
    public JLabel nullLabel;       //Declaring NullLabel For Design Perpouse
    
    
    /**
     * Declaring All Icons
     */
    public ImageIcon  displayPicture = new ImageIcon(this.getClass().getResource("/Pictures/displayPicture.png"));
    public ImageIcon  loginButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/loginButtonIcon.png"));
    public ImageIcon  shopNameButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/shopNameButtonIcon.png"));
    public ImageIcon  createSalesInvoiceButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/createSalesInvoiceButtonIcon.png"));
    public ImageIcon  createPurchaseInvoiceButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/createPurchaseInvoiceButtonIcon.png"));
    public ImageIcon  logoutButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/logoutButtonIcon.png"));  
    public ImageIcon  homeButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/homeButtonIcon.png"));
    public ImageIcon  addCostButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/addCostButtonIcon.png"));
    public ImageIcon  dailyIncomeCostButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/dailyIncomeCostButtonIcon.png"));
    public ImageIcon  stockCheckButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/stockCheckButtonIcon.png"));
    public ImageIcon  dueCheckButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/dueCheckButtonIcon.png"));
    public ImageIcon  staffAttendanceButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/staffAttendanceButtonIcon.png"));
    public ImageIcon  settingsButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/settingsButtonIcon.png"));
    public ImageIcon  clearButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/clearButtonIcon.png"));
    public ImageIcon  addToCartButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/addToCartButtonIcon.png"));
    public ImageIcon  printButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/printButtonIcon.png"));
    public ImageIcon  deleteButtonIcon = new ImageIcon(this.getClass().getResource("/Pictures/deleteButtonIcon.png"));
    
    
    /**
     *  FrameSetup Constructor
     */
    public FrameSetup()
    {
        setFrame();  
        setContainer();
        setAppIcon();
    }
    
    /**
     *  Sets Frame Basic Features
     */
    public void setFrame()
    {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(900, 500);
        this.setLocationRelativeTo(null);
        this.setTitle("SHOP ASSISTANT");
    }
    
    /**
     *  Sets Container
     */
    public void setContainer()
    {
        container = this.getContentPane();
        container.setBackground(Color.WHITE);
    }
    
    /**
     *  Sets Application Icon
     */
    public void setAppIcon()
    {
        ImageIcon logo = new ImageIcon(this.getClass().getResource("/Pictures/shoppingCart.png"));
        this.setIconImage(logo.getImage());
    }
    
    /**
     * This Method returns date as a string
     * 
     * @return date Instant time and Date
     */
    public String setDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy E hh:mm:ss a");
        Date date = new Date();
        return sdf.format(date);
    }
    
    
    /**
     * This Method Returns A String Array of All Models Numbers
     * 
     * @return String Array of Model Number
     */
    public String[] setProductTypes()
    {
        String productType[] = new String[80];
        
        try{                    
                BufferedReader br = new BufferedReader(new FileReader("ProductType&ModelList.txt"));
                String s;
                int i = 1;
                productType[0]="";
                while((s=br.readLine())!=null)
                {
                    productType[i] = s.substring(0, s.indexOf(","));
                    i++;
                }
                
                int p = i;
                
                while(p<80)
                {
                    productType[p] = "";
                    p++;
                }
                
            }catch(Exception ex){}
        
        return productType;
    }
   
    /**
     * @param character Any Character
     * @return True if Character is Digit
     */
    
    public boolean isDigit(char character)
    {
       if(character!='0'&&character!='1'&&character!='2'&&character!='3'&&character!='4'&&character!='5'&&character!='6'&&character!='7'&&character!='8'&&character!='9'&&character!=(char)8&&character!=(char)10)
       {
           return false;
       }
       else
       {
           return true;
       }
    }
    
    /**
     * This Method Shows A Reminder to Print Invoice
     */
    public void setReminder()
    {
        JOptionPane.showMessageDialog(null, "Remember to Print");
    }
    
    /**
     * This Method shows Remaining Quantity For a Specific ModelNumber And Data Stored At a Specific File
     * 
     * @param modelNumber  A Model Number
     * @param filename     A FileName  
     * @return Remaining Quantity
     */
    public int getRemainingQuantity(String modelNumber,String filename)
    {
        boolean found = false;
        int modelCount = 0,quantityStringIndex;
        
        if(modelNumber==null||"".equals(modelNumber))
        {
            return 0;
        } 
        else
        {
        /*try
        {
            BufferedReader br = new BufferedReader(new FileReader(filename));
            int s = 0;
            String text="";
            
            while((s=br.read())!=-1)
            {
                text = text + (char)s;
            }
            
            Pattern pattern = Pattern.compile(modelNumber);
            Matcher matcher = pattern.matcher(text);
            
            while(matcher.find())
            {                
                String demoText = text.substring(matcher.end()+1, matcher.end()+10);             
                
                String result = demoText.substring(0, demoText.indexOf(","));
                
                modelCount = modelCount+Integer.parseInt(result);               
                found = true;
            }
      
            }catch(Exception e){}
            
            if(!found)
            {
                return 0;
            }
            else
            {
                return modelCount;
            }*/
                    
            try{
                
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String s;
                
                while((s=br.readLine())!=null)
                {
                    while(s.contains(modelNumber))
                    {
                        //if(s.contains(modelNumber))
                        //{
                            quantityStringIndex = s.indexOf(modelNumber) + modelNumber.length() + 1;
                            String demoString = s.substring(quantityStringIndex, quantityStringIndex+10);
                            modelCount = modelCount + Integer.parseInt(demoString.substring(0, demoString.indexOf(",")));
                            s = s.substring(s.indexOf(modelNumber) + modelNumber.length() + 1, s.length());
                       // }
                    }
                }
    
            }catch(Exception e) {}
              
        return modelCount;
        
        }
    }
    
    /**
     * This Method shows Remaining Quantity For a Specific ModelNumber, Date And Data Stored At a Specific File
     * 
     * @param date   A Date
     * @param modelNumber ModelNumber
     * @param filename FileName
     * @return Remaining Quantity
     */
    public int getRemainingQuantityUsingDate(String date,String modelNumber,String filename)
    {
        int modelCount = 0,quantityStringIndex;
        
        if(modelNumber==null||"".equals(modelNumber))
        {
            return 0;
        } 
        else
        {    
            try{
                
                BufferedReader br = new BufferedReader(new FileReader(filename));
                String s;
                
                while((s=br.readLine())!=null)
                {                    
                    if(s.contains(date))
                    {             
                        while(s.contains(modelNumber))
                        {
                            quantityStringIndex = s.indexOf(modelNumber) + modelNumber.length() + 1;
                            String demoString = s.substring(quantityStringIndex, quantityStringIndex+10);
                            modelCount = modelCount + Integer.parseInt(demoString.substring(0, demoString.indexOf(",")));
                            s = s.substring(s.indexOf(modelNumber) + modelNumber.length() + 1, s.length());  
                        }
                    }
                }
    
            }catch(Exception e) {}
              
        return modelCount;
        
        }
    }
    
    /**
     * This Method Shows Exact Quantity After Subtracting SoldItem From Purchased Item
     * 
     * @param modelNumber  A Model Number
     * @param purchaseFilename  Purchase File Name
     * @param salesFilename Sales File Name
     * @return Quantity
     */
    public int getTotalStock(String modelNumber,String purchaseFilename,String salesFilename)
    {  
        int productSold = getRemainingQuantity(modelNumber,salesFilename);
        int productPurchased = getRemainingQuantity(modelNumber,purchaseFilename);
        
        return (productPurchased-productSold);
    }
    
    /**
     * This Method Shows Password From File as String
     * 
     * @return Password
     */
    
    public String getPassword()
    {
        String password = "";
        
        try{
            
            BufferedReader br = new BufferedReader(new FileReader("UserDatabase.txt"));   
            String s = br.readLine();
            password = s.substring(9,s.length());
            br.close();        
            
        }catch(Exception e) {System.out.println(e);}
        
        return password;
    }  
}

