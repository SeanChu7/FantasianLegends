package unit;

public class Salamander extends Unit implements ReptarUnit{
	public Salamander() {
		health = 100;
		meleeStr = 25;
		movement = 6;
		name = "Salamander";
		movesleft = 6;
		cost = 10;
		maint = 4;
		//Unitdisplay = reptardisplay;
	}
	public Salamander clone() {
		return new Salamander();
	}
}
