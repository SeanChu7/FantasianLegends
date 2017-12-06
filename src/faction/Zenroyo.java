package faction;

public class Zenroyo extends Faction{

	public Zenroyo() {
		super();
		name= "Zenroyo";
		tileYield = new int[][] {{4,0,0,1},{2,2,0,1},{1,3,0,1},{0,2,0,3},{1,0,3,1},{0,0,1,4},{0,3,0,2}};
		desc = "Bionic Monks who never resort to violence. The Zenroyo prefer to resolve "
				+ "their conflics through other means." + "\n" + "Attributes: " + "\n"
						+ "-Victory Through Peace: No Military Units at all" +
				"\n" + "-Enlightenment: +1 Science to all tile yields";
	}
}
