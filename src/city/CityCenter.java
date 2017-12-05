package city;

public class CityCenter extends Building{

	public CityCenter() {
		yield = new int[] {4,4,4,4};
		m = 0;
		name = "City Center";
	}
	public CityCenter(int x, int y) {
		super(x,y);
		yield = new int[] {4,4,4,4};
		m = 0;
		name = "City Center";
	}
	public CityCenter clone() {
		return new CityCenter();
	}
	
}
