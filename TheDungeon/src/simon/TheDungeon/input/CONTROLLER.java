package simon.TheDungeon.input;

public class CONTROLLER {

	public double x, y, z, rotation, xa, za, rotationa;
	public static boolean turnLeft = false;
	public static boolean turnRight = false;
	public static boolean walking = false;
	public static boolean run = false;
	public static boolean sneak = false;

	public void tick(boolean forward, boolean back, boolean right, boolean left, boolean jump, boolean crouch,
			boolean sprint) {
		double rotationSpeed = 0.025;
		double walkSpeed = 1;
		double jumpHeight = 1;
		double crouchHeight = 0.2;
		double xMove = 0;
		double zMove = 0;

		if (forward) {
			zMove++;
			walking = true;
		}

		if (back) {
			zMove--;
			walking = true;

		}
		if (right) {
			xMove++;
		}
		if (left) {
			xMove--;

		}
		if (turnRight) {
			rotationa += rotationSpeed;
		}
		if (turnLeft) {
			rotationa -= rotationSpeed;

		}
		if (jump) {
			y += jumpHeight;
			sprint = false;

		}
		if (crouch) {
			y -= crouchHeight;
			walkSpeed = 0.5;
			sprint = false;
			sneak = true;

		}
		if (sprint) {
			walkSpeed = 2;
			run = true;

		}
		if (!forward && !back && !right && !right) {
			walking = false;
		}
		if (!sprint) {
			run = false;
		}
		if (!crouch) {
			sneak = false;
		}

		xa += (xMove * Math.cos(rotation) + zMove * Math.sin(rotation)) * walkSpeed;
		za += (zMove * Math.cos(rotation) + xMove * Math.sin(rotation)) * walkSpeed;

		x += xa;
		y *= 0.9;
		z += za;

		xa *= 0.1;
		za *= 0.1;
		rotation += rotationa;
		rotationa *= 0.5;
	}
}
