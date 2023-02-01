import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Panel extends JPanel implements ActionListener, ChangeListener, MouseListener{
	
	private JLabel massValue1;
	private JLabel massValue2;
	private JLabel massValue3;
	private JLabel massValue4;
	private JLabel massValue5;
	
	private JButton reset;
	private JSlider time;
	private JSlider constant;
	private JSlider force;
	private JLabel timeName;
	private JLabel constantName;
	private JLabel forceName;
	
	public Panel() {
		this.setSize(500, 700);
		this.setLayout(null);
		this.setVisible(true);
				
		massSpectrValues();
		topElements();
		
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(Color.black);
		g.fillRect(0, 100, 500, 500);
		
		drawTails(g);
		drawParticles(g);
		massSpectr(g);
		
		if(Main.right) {
			g.setColor(new Color(255, 0, 0, 50));
			g.fillOval(MouseInfo.getPointerInfo().getLocation().x - 108, MouseInfo.getPointerInfo().getLocation().y - 130, 200, 200);
		}else if(Main.left) {
			g.setColor(new Color(0, 255, 0, 50));
			g.fillOval(MouseInfo.getPointerInfo().getLocation().x - 108, MouseInfo.getPointerInfo().getLocation().y - 130, 200, 200);
		}
	}
	
	private void drawTails(Graphics g) {
		int fading;
		for(int i = 0; i < Main.particles.length; i++) {
			fading = 255 / Main.particles[i].previousX.length;
			for(int j = 0; j < Main.particles[i].previousX.length - 1; j++) {
				g.setColor(new Color(192, 192, 192, 255 - fading * j));
				g.drawLine((int) Main.particles[i].previousX[j], (int) Main.particles[i].previousY[j] + 100, (int) Main.particles[i].previousX[j + 1], (int) Main.particles[i].previousY[j + 1] + 100);
			}
		}
	}
	
	private void drawParticles(Graphics g) {
		int rd = 0;
		int gr = 0;
		int bl = 0;
		for(int i = 0; i < Main.particles.length; i++) {
			if(Main.particles[i].x < 500 && Main.particles[i].x >= 0 && Main.particles[i].y < 500 && Main.particles[i].y >= 0) {
				double gradient = Main.particles[i].mass / Main.maxMass * 5;
				switch((int) gradient) {
				case 0:
					gradient = gradient - (int) gradient;
					rd = 255;
					gr = 0;
					bl = 0;
					gr += gradient * 255;
					break;
				case 1:
					gradient = gradient - (int) gradient;
					rd = 255;
					gr = 255;
					bl = 0;
					rd -= gradient * 255;
					break;
				case 2:
					gradient = gradient - (int) gradient;
					rd = 0;
					gr = 255;
					bl = 0;
					bl += gradient * 255;
					break;
				case 3:
					gradient = gradient - (int) gradient;
					rd = 0;
					gr = 255;
					bl = 255;
					gr -= gradient *255;
					break;
				case 4:
					gradient = gradient - (int) gradient;
					rd = 0;
					gr = 0;
					bl = 255;
					rd += gradient * 255;
					break;
				case 5:
					gradient = gradient - (int) gradient;
					rd = 255;
					gr = 0;
					bl = 255;
					break;
				}
				g.setColor(new Color(rd, gr, bl));
				g.fillOval( (int) Math.round(Main.particles[i].x) - 2, (int) Math.round(Main.particles[i].y) + 98, 4, 4);
			
			}
		}
		
		g.setColor(Color.white);
		g.fillRect(0, 0, 500, 100);
	}
	
	private void topElements() {
		reset = new JButton("Reset");
		reset.setLocation(0, 0);
		reset.setSize(100,  100);
		reset.addActionListener(this);
		this.add(reset);
		
		time = new JSlider(0, 4, 1);
		time.setSize(250, 33);
		time.setLocation(250, 0);
		time.setPaintTrack(true);
		time.setMajorTickSpacing(1);
		time.setPaintLabels(true);
		time.addChangeListener(this);
		this.add(time);
		
		constant = new JSlider(0, 10, 5);
		constant.setSize(250, 33);
		constant.setLocation(250, 33);
		constant.setPaintTrack(true);
		constant.setMajorTickSpacing(1);
		constant.setPaintLabels(true);
		constant.addChangeListener(this);
		this.add(constant);
		
		force = new JSlider(0, 10, 1);
		force.setSize(250, 33);
		force.setLocation(250, 66);
		force.setPaintTrack(true);
		force.setMajorTickSpacing(1);
		force.setPaintLabels(true);
		force.addChangeListener(this);
		this.add(force);
		
		timeName = new JLabel("Time Coefficient");
		timeName.setSize(150, 33);
		timeName.setLocation(100, 0);
		timeName.setFont(new Font("Arial", Font.BOLD, 12));
		timeName.setHorizontalAlignment(JLabel.CENTER);
		this.add(timeName);
		
		constantName = new JLabel("Negative Power of Ten");
		constantName.setSize(150, 33);
		constantName.setLocation(100, 33);
		constantName.setFont(new Font("Arial", Font.BOLD, 12));
		constantName.setHorizontalAlignment(JLabel.CENTER);
		this.add(constantName);
		
		forceName = new JLabel("Cursor Mass x");
		forceName.setSize(150, 33);
		forceName.setLocation(100, 66);
		forceName.setFont(new Font("Arial", Font.BOLD, 12));
		forceName.setHorizontalAlignment(JLabel.CENTER);
		this.add(forceName);
	}
	
	private void massSpectrValues() {
		massValue1 = new JLabel("0.0");
		massValue1.setLocation(0, 650);
		massValue1.setSize(100, 50);
		massValue1.setFont(new Font("Arial", Font.BOLD, 20));
		this.add(massValue1);
		
		massValue2 = new JLabel(String.valueOf(Main.maxMass / 4));
		massValue2.setLocation(80, 650);
		massValue2.setSize(100, 50);
		massValue2.setFont(new Font("Arial", Font.BOLD, 20));
		massValue2.setHorizontalAlignment(JLabel.CENTER);
		this.add(massValue2);

		massValue3 = new JLabel(String.valueOf(Main.maxMass / 4 * 2));
		massValue3.setLocation(200, 650);
		massValue3.setSize(100, 50);
		massValue3.setFont(new Font("Arial", Font.BOLD, 20));
		massValue3.setHorizontalAlignment(JLabel.CENTER);
		this.add(massValue3);
		
		massValue4 = new JLabel(String.valueOf(Main.maxMass / 4 * 3));
		massValue4.setLocation(320, 650);
		massValue4.setSize(100, 50);
		massValue4.setFont(new Font("Arial", Font.BOLD, 20));
		massValue4.setHorizontalAlignment(JLabel.CENTER);
		this.add(massValue4);
		
		massValue5 = new JLabel(String.valueOf(Main.maxMass));
		massValue5.setLocation(400, 650);
		massValue5.setSize(100, 50);
		massValue5.setFont(new Font("Arial", Font.BOLD, 20));
		massValue5.setHorizontalAlignment(JLabel.RIGHT);
		this.add(massValue5);
	}
	
	private void massSpectr(Graphics g) {
		double step = 256 / 100;
		double rd = 255;
		double gr = 0;
		double bl = 0;
		int count = 0;
		for(int i = 0; i < 100; i++) {
			g.setColor(new Color((int) rd,(int) gr,(int) bl));
			g.drawLine(count, 600, count, 650);
			gr += step;
			count++;
		}
		for(int i = 0; i < 100; i++) {
			g.setColor(new Color((int) rd,(int) gr,(int) bl));
			g.drawLine(count, 600, count, 650);
			rd -= step;
			count++;
		}
		for(int i = 0; i < 100; i++) {
			g.setColor(new Color((int) rd,(int) gr,(int) bl));
			g.drawLine(count, 600, count, 650);
			bl += step;
			count++;
		}
		for(int i = 0; i < 100; i++) {
			g.setColor(new Color((int) rd,(int) gr,(int) bl));
			g.drawLine(count, 600, count, 650);
			gr -= step;
			count++;
		}
		for(int i = 0; i < 100; i++) {
			g.setColor(new Color((int) rd,(int) gr,(int) bl));
			g.drawLine(count, 600, count, 650);
			rd += step;
			count++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Main.createParticles();
		
	}

	@Override
	public void stateChanged(ChangeEvent e) {

		if(e.getSource() == time) {
			Main.time = time.getValue();
		}else if(e.getSource() == constant) {
			double count = 1;
			for(int i = 0; i < constant.getValue(); i++) {
				count /= 10;
			}
			Main.spaceConstant = count;
		}else if(e.getSource() == force) {
			
		}
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		Main.mousePressed = true;
		if(SwingUtilities.isLeftMouseButton(e)) {
			Main.left = true;
		}else if(SwingUtilities.isRightMouseButton(e)) {
			Main.right = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Main.mousePressed = false;
		Main.left = false;
		Main.right = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}
	
	
}
