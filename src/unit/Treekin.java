package unit;

public class Treekin extends Unit implements FloronisUnit{

	public Treekin() {
		health = 100;
		meleeStr = 15;
		movement = 2;
		name = "Treekin";
		movesleft = 2;
		cost = 60;
		maint = 2;
		//Unitdisplay = plantdisplay;
	}
	public Treekin clone() {
		return new Treekin();
	}
}
