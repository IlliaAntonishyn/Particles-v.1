
public class Particle {
	
	public double xVelocity;
	public double yVelocity;
	
	public double x;
	public double y;
	public double mass;
	
	public double[] previousX;
	public double[] previousY;

	public Particle(double x, double y, double mass) {
		
		this.x = x;
		this.y = y;
		this.mass = mass;
		
		previousX = new double[40];
		previousY = new double[40];
		preset();

		xVelocity = 0;
		yVelocity = 0;
	}
	
	public void cicle() {
		for(int i = previousX.length - 1; i > 0; i--) {
			previousX[i] = previousX[i-1];
			previousY[i] = previousY[i-1];
		}
	}
	
	private void preset() {
		for(int i = 0; i < previousX.length; i++) {
			previousX[i] = x;
			previousY[i] = y;
		}
	}
	
}
