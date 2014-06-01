import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class editor implements ActionListener {
   
	static JFrame frame;
	static JMenuItem news , save;
	static JPanel contentpane;
	static JMenu menu;
	static JEditorPane text;
	static JButton bold,italics,plain;
	static Font b,i,n;
		
	editor()
	{
		frame = new JFrame("Editor");
		contentpane = new JPanel(new BorderLayout());
		text = new JEditorPane();
		b = new Font("Serif", Font.BOLD, 12);
	    i = new Font("Serif", Font.ITALIC, 12);
	    n = new Font("Serif", Font.PLAIN, 12);
	  
	    text.setFont(n);
	}
	
    private static void start(editor obj) {
        
    	
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.add(contentpane,BorderLayout.CENTER);
   
        addMenu();
        addSides();
        
        news.addActionListener(obj);     
        save.addActionListener(obj);     
        bold.addActionListener(obj);     
        italics.addActionListener(obj);     
        plain.addActionListener(obj);   
        
        contentpane.add(new JScrollPane(text),BorderLayout.CENTER);
        
        frame.pack();
        frame.setSize(500,500);
        frame.setVisible(true);
        
       
    }

   private static void addSides()
    {
    	JPanel b1 = new JPanel();
        b1.setOpaque(true);
        b1.setBackground(new Color(248, 213, 131));
        b1.setPreferredSize(new Dimension(60, 180));
        b1.add(plain = new JButton("PLAIN"));
        b1.add(bold = new JButton("BOLD"));
        b1.add(italics = new JButton("ITALICS"));
        frame.add(b1, BorderLayout.WEST);
        
        JLabel yellowLabel2 = new JLabel();
        yellowLabel2.setOpaque(true);
        yellowLabel2.setBackground(new Color(248, 213, 131));
        yellowLabel2.setPreferredSize(new Dimension(60, 180));
        frame.add(yellowLabel2, BorderLayout.EAST);
    }
    
    private static void addMenu()
    {
    	JMenuBar greenMenuBar = new JMenuBar();
        greenMenuBar.setOpaque(true);
        greenMenuBar.setBackground(new Color(154, 165, 127));
  
        menu = new JMenu("Menu");       
        greenMenuBar.add(menu);          
        news = new JMenuItem("NEW",
                KeyEvent.VK_T);       
        menu.add(news);       
       
        save = new JMenuItem("SAVE",
                KeyEvent.VK_S);       
        menu.add(save);       
        
        frame.setJMenuBar(greenMenuBar);
    	
    }
    public void actionPerformed(ActionEvent ae) {
		if(ae.getSource()==news)
		{		
			
			 javax.swing.SwingUtilities.invokeLater(new Runnable() {
		            public void run() {		            	
		                newone();
		                }
		        });
		}
		else if(ae.getSource()==save)
		{
			try{
			save();
			}
			catch(Exception e)
			{
				
			}
		}
		else if(ae.getSource()==plain)
		{
			text.setFont(n);
		}
		else if(ae.getSource()==bold)
		{
			text.setFont(b);
		}
		else if(ae.getSource()==italics)
		{
			text.setFont(i);
		}
	} 
    
    private static void newone()
    {
    	
    	if(text.getText().length() != 0 )
    	{
    		Object[] options = {"Yes","No","Cancel"};
        	int n = JOptionPane.showOptionDialog(frame,
        			"Open New Without Saving","Sure?",
        			JOptionPane.YES_NO_CANCEL_OPTION,
        			JOptionPane.QUESTION_MESSAGE,
        			null,
        			options,
        			options[2]);
        	
        	if(n == 0)
        	text.setText("");
    	}
    	
    }
    
	private static void save() throws IOException
	{
		FileWriter fw = new FileWriter("file1");
		
		fw.write(text.getText());
       
		fw.close();
	}
    public static void main(String[] args) {
       
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                start(new editor());
            }
        });
    }
}