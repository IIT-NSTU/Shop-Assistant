
package Shop_Assistant;

import Templates.Invoice_Generator_Template;


public class Create_Sales_Invoice_Page extends Invoice_Generator_Template
{
    public Create_Sales_Invoice_Page(String keyword, String file_name) 
    {
        super(keyword, file_name);
    }
    
    public static void main(String[] args) 
    {  
        Create_Sales_Invoice_Page frame = new Create_Sales_Invoice_Page("Customer","Sales_Database.txt");
        frame.setVisible(true);
    }
}
