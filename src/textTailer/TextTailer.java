package textTailer;

import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;

public class TextTailer {

	private File file;
	private JFrame frame;
	private JTextField textField;
	public String text;
	private JTextArea display;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TextTailer window = new TextTailer();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TextTailer() {
		initialize();
	}

	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 1024, 768);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		display = new JTextArea ( 16, 58 );
	    display.setEditable ( false ); // set textArea non-editable
	    JScrollPane scroll = new JScrollPane ( display );
	    scroll.setBounds(10, 11, 600, 717);
	    scroll.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
	    panel.add(scroll);
	    
	    frame.getContentPane().add(panel);
	    
	    textField = new JTextField();
	    textField.setBounds(620, 14, 388, 20);
	    panel.add(textField);
	    textField.setColumns(10);
	    
	    frame.setVisible(true);
	    one.start();
	}
	Thread one = new Thread(){
        public void run() {
        	while(true){
        	   file = new File(textField.getText());
				text = new String();
				try {
					Scanner in = new Scanner(file);
					text = in.useDelimiter("\\A").next();
					in.close();
					display.setText(text);
				} catch (IOException e) {
				}
				frame.repaint();
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
           }
        }
    };
	
}
