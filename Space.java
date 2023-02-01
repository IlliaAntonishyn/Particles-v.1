import java.awt.MouseInfo;

public class Space{
		
	public Space() {
	}
	
	public void updateSpace() {
		double xAcceleration;
		double yAcceleration;
		double distance;
		for(int i = 0; i < Main.particles.length; i++) {
			xAcceleration = 0;
			yAcceleration = 0;
			for(int j = 0; j < Main.particles.length; j++) {
				if(i != j) {
					distance = Math.sqrt(Math.pow(Main.particles[j].x - Main.particles[i].x, 2) + Math.pow(Main.particles[j].y - Main.particles[i].y,  2));
					xAcceleration += Main.spaceConstant * Main.particles[j].mass / Math.pow(distance, 1) * (Main.particles[j].x - Main.particles[i].x) / distance;
					yAcceleration += Main.spaceConstant * Main.particles[j].mass / Math.pow(distance, 1) * (Main.particles[j].y - Main.particles[i].y) / distance;
					
				}
			}
			
			double x;
			double y;
			if(Main.mousePressed) {
				x = MouseInfo.getPointerInfo().getLocation().x + 8;
				y = MouseInfo.getPointerInfo().getLocation().y + 130;
				
				distance = Math.sqrt(Math.pow(x - Main.particles[i].x, 2) + Math.pow(y - Main.particles[i].y,  2));
				
				if(Main.right) {
					xAcceleration -= Main.spaceConstant * Main.maxMass * 10 * Main.cursorMassMultiplier / Math.pow(distance, 1) * (x - Main.particles[i].x) / distance;
					yAcceleration -= Main.spaceConstant * Main.maxMass * 10 * Main.cursorMassMultiplier / Math.pow(distance, 1) * (y - Main.particles[i].y) / distance;
				}else if(Main.left) {
					xAcceleration += Main.spaceConstant * Main.maxMass * Main.cursorMassMultiplier / Math.pow(distance, 1) * (x - Main.particles[i].x) / distance;
					yAcceleration += Main.spaceConstant * Main.maxMass * Main.cursorMassMultiplier / Math.pow(distance, 1) * (y - Main.particles[i].y) / distance;
				}
				
			}
			if(Main.time != 0) {
				Main.particles[i].cicle();
			}
			
			Main.particles[i].x += Main.particles[i].xVelocity * Main.time + 0.5 * xAcceleration * Math.pow(Main.time, 2);
			Main.particles[i].y += Main.particles[i].yVelocity * Main.time + 0.5 * yAcceleration * Math.pow(Main.time, 2);
			Main.particles[i].xVelocity += xAcceleration * Main.time;
			Main.particles[i].yVelocity += yAcceleration * Main.time;

			Main.particles[i].previousX[0] = Main.particles[i].x;
			Main.particles[i].previousY[0] = Main.particles[i].y;

		}
	}
	
}
