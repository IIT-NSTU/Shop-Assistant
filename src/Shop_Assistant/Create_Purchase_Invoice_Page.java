
package Shop_Assistant;

import Templates.Frame_Setup;
import Templates.Invoice_Generator_Template;

public class Create_Purchase_Invoice_Page extends Invoice_Generator_Template
{
    public Create_Purchase_Invoice_Page(String keyword, String file_name) 
    {
        super(keyword, file_name);
    }
    
    public static void main(String[] args) 
    {
        Create_Purchase_Invoice_Page frame = new Create_Purchase_Invoice_Page("Company","Purchase_Database.txt");
        frame.setVisible(true);
    }
}
