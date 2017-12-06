package unit;

public class Wyvern extends FlyingUnit implements ReptarUnit{
	public Wyvern() {
		health = 100;
		meleeStr = 25;
		movement = 4;
		name = "Wyvern";
		movesleft = 4;
		cost = 300;
		maint = 10;
		Unitdisplay = reptardisplay;
	}
	public Wyvern clone() {
		return new Wyvern();
	}
}
