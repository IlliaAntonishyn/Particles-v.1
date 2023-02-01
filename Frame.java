import java.awt.Dimension;
import javax.swing.JFrame;

public class Frame extends JFrame{
	
	public Frame() {
		
		this.getContentPane().setPreferredSize(new Dimension(500, 700));
		this.setLocation(0 ,0);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(null);
		this.setResizable(false);
		this.setVisible(true);
		this.pack();
	}
	
}
