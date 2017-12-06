package unit;

public class Disciple extends Unit implements ZenroyoUnit{

	public Disciple() {
		health = 100;
		meleeStr = 0;
		movement = 2;
		name = "Disciple";
		movesleft = 2;
		cost = 60;
		maint = 2;
		//Unitdisplay = robotdisplay;
	}
	public Disciple clone() {
		return new Disciple();
	}
}
