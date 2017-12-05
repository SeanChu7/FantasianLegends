package unit;

public class Salamander extends Unit{
	public Salamander() {
		health = 100;
		meleeStr = 25;
		movement = 2;
		name = "Salamander";
		movesleft = 2;
		cost = 2;
		maint = 4;
	}
	public Salamander clone() {
		return new Salamander();
	}
}
