package faction;

public class Floronis extends Faction{
	public Floronis() {
	super();
	name= "Floronis";
	tileYield = new int[][] {{4,1,0,0},{3,3,0,0},{2,3,0,0},{0,1,0,1},{1,0,3,0},{0,0,0,2},{0,2,0,0}};
	desc = "Guardians of the Forest, The Floronis will destroy any who desecrate their woods."
			+"\n" + "Attributes: " + "\n"
					+ "-Caretakers of the Forest: Bonuses to Grassland, Hill, Forest yields" +
			"\n" + "-Soil Not Included: Penalties to Ice, Rockland, and Mountain yields";
}
}
