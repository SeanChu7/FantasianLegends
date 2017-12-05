package unit;

public class Settler extends Unit{

	public Settler() {
		health = 100;
		meleeStr = 0;
		movement = 2;
		movesleft = 2;
		cost = 300;
		maint = 1;
		name = "Settler";
	}
	
	public Settler clone() {
		return new Settler();
	}
}
