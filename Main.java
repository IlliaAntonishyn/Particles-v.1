import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class Main {
	
	public static Frame frame;
	public static Space space;
	public static Panel panel;
	
	public static TimerTask task;
	public static Timer timer;
	public static int ms;
	
	public static Particle[] particles;
	
	public static double maxMass;
	public static int time;
	public static double spaceConstant;
	public static double cursorMassMultiplier;
	
	public static boolean mousePressed;
	public static boolean left;
	public static boolean right;

	public static void main(String[] args) {
		
		maxMass = 1000;
		time = 1;
		spaceConstant = 0.001;
		cursorMassMultiplier = 1;

		frame = new Frame();
		frame.setTitle("Particle Symulation");
		
		ms = 10;
		
		createParticles();
		space = new Space();
		panel = new Panel();
		
		frame.add(panel);
		
		task = new TimerTask() {

			public void run() {
				space.updateSpace();
				panel.repaint();
			}
			
		};
		
		timer = new Timer();
		timer.scheduleAtFixedRate(task, ms, ms);
	}
	
	public static void createParticles() {
		Random rng = new Random();
		
		particles = new Particle[1000];
		
		for(int i = 0; i < particles.length; i++) {
			particles[i] = new Particle(rng.nextDouble(500), rng.nextDouble(500), rng.nextDouble(maxMass));
		}
		
	}

}
