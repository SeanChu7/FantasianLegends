package city;

public class Granary extends Building{

	public Granary() {
		super();
		yield = new int[] {3,0,0,0};
		m =  1;
		name = "Granary";
		cost = 20;
	}
	public Granary(int x, int y) {
		super(x,y);
		yield = new int[] {3,0,0,0};
		m =  1;
		name = "Granary";
		cost = 20;
	}
	public Granary clone() {
		return new Granary();
	}
}
