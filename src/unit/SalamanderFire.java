package unit;

public class SalamanderFire extends RangeUnit implements ReptarUnit{
	public SalamanderFire() {
		health = 100;
		meleeStr = 15;
		movement = 2;
		name = "Salamander(Fire)";
		rng = 2;
		rngStr = 20;
		movesleft = 2;
		cost = 150;
		maint = 6;
		Unitdisplay = reptardisplay;
	}
	public SalamanderFire clone() {
		return new SalamanderFire();
	}
}
