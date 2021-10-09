
package ShopAssistant;

import Templates.DashBoardTemplate;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddCostPage extends DashBoardTemplate
{
    public JPanel inputPanel;
    public JLabel dateLabel,costTypeLabel,amountLabel;
    public JButton costSubmitButton;
    public JTextField dateTextField,costAmountTextField;
    public JComboBox costTypeCombobox;
    public String costType[] = {"","Staff Salary","Electricity Bill","Shop Rent","Miscellaneous"};

    /**
     *  Add Cost Page Constructor
     */
    public AddCostPage()
    {
        setPageButton();
        setMainPanel();
        setInputPanel();
        setAddCostFeatures();
    }
    
    /**
     * This Method Sets Button Using Different BackGround and ForeGround Color
     */
    public void setPageButton()
    {
        getButton("Add Cost").setBackground(Color.LIGHT_GRAY);
        getButton("Add Cost").setForeground(Color.BLACK);
        getButton("Add Cost").setFont(new Font("Arial",Font.BOLD,16));
    }
    
    /**
     *  This Method Sets Main Panel Components
     */
    public void setMainPanel()
    {
        mainPanel.setLayout(new BorderLayout(120,50));
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.NORTH);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.EAST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.WEST);
        
        nullLabel = new JLabel();
        mainPanel.add(nullLabel,BorderLayout.SOUTH);
        
        inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8,1));
        inputPanel.setCursor(new Cursor(Cursor.HAND_CURSOR));
        inputPanel.setBackground(Color.WHITE);
        mainPanel.add(inputPanel,BorderLayout.CENTER);
    }
    
    /**
     * This Method Sets Input Panel Components
     */
    public void setInputPanel()
    {    
        dateLabel = new JLabel("Date & Time");
        dateLabel.setBackground(Color.WHITE);
        dateLabel.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(dateLabel);       
 
        
        dateTextField = new JTextField(setDate());
        dateTextField.setBackground(Color.WHITE);
        dateTextField.setEditable(false);
        dateTextField.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(dateTextField);

        costTypeLabel = new JLabel("Cost Type");
        costTypeLabel.setBackground(Color.WHITE);
        costTypeLabel.setFont(new Font("Arial",Font.BOLD,16));        
        inputPanel.add(costTypeLabel);
        

        costTypeCombobox = new JComboBox(costType);
        costTypeCombobox.setFont(new Font("Arial",Font.BOLD,16));
        inputPanel.add(costTypeCombobox);
        
        
        amountLabel = new JLabel("Amount");
        amountLabel.setBackground(Color.WHITE);
        amountLabel.setFont(new Font("Arial",Font.BOLD,16));        
        inputPanel.add(amountLabel);
        
        
        costAmountTextField = new JTextField();
        costAmountTextField.setFont(new Font("Arial",Font.BOLD,16));
        costAmountTextField.setEnabled(false);
        costAmountTextField.setBackground(Color.WHITE);
        inputPanel.add(costAmountTextField); 
        
       
        costSubmitButton = new JButton("Submit");
        costSubmitButton.setFont(new Font("Arial",Font.BOLD,18));
        costSubmitButton.setFocusPainted(false);
        inputPanel.add(costSubmitButton);  
    }
    
    /**
     *  This Method Sets Cost Features
     */
    public void setAddCostFeatures()
    {
        costTypeCombobox.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if(costTypeCombobox.getSelectedItem()=="")
                {
                    JOptionPane.showMessageDialog(null, "Please Select an Option");
                    costAmountTextField.setText("");
                    costAmountTextField.setEnabled(false);
                }
                else
                {
                    costAmountTextField.setEnabled(true);
                }
            }
        }); 
        
        costAmountTextField.addKeyListener(new KeyListener(){
      
            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) 
            {
                if(!isDigit(ke.getKeyChar()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Valid Digit");
                    costAmountTextField.setText("");
                }
            }

            public void keyReleased(KeyEvent ke)
            {
                try{
                if(Float.parseFloat(costAmountTextField.getText())>100000000)
                {
                    JOptionPane.showMessageDialog(null, "Please Do Not Enter Large Values");   
                    costAmountTextField.setText("");
                }
                }catch(Exception e) {}
            }
        });
        
        
        costSubmitButton.addActionListener(new ActionListener()
        {     
            public void actionPerformed(ActionEvent e)
            {
                if("".equals(costAmountTextField.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Please Enter Amount");
                }
                else
                {    
                 try
                 {
                    BufferedWriter bw = new BufferedWriter(new FileWriter("IncomeCostDatabase.txt",true));
                     
                    bw.append("P100000000"+","+dateTextField.getText()+","+"Cost By "+costTypeCombobox.getSelectedItem()+","+costAmountTextField.getText()+"\n");
                    bw.close();
                    
                    JOptionPane.showMessageDialog(null,costTypeCombobox.getSelectedItem()+" "+costAmountTextField.getText()+" Added Successfully");
                    
                    costAmountTextField.setText(""); 
                 } 
                 catch (Exception ex) {}
                }
            }
        });
    
    }
    
    public static void main(String[] args) 
    {
        AddCostPage frame = new AddCostPage();
        frame.setVisible(true);
    }
}
