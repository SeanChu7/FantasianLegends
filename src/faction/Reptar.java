package faction;

public class Reptar extends Faction{

	public Reptar() {
		super();
		name= "Reptar";
		tileYield = new int[][] {{5,1,1,1},{3,3,1,1},{2,4,1,1},{1,3,1,3},{2,1,4,1},{1,1,2,4},{1,4,1,2}};
		desc = "An Ancient Race of Reptiles and Lizards. The Reptar command a fierce host"
				+ "\n of mighty and ancient flying units." + "\n" + "Attributes: " + "\n"
						+ "-Wizened Elites: Roster of Powerful Flying Units but at a high premium" +
				"\n" + "-Wisdom of the Ancients: +1 to all tile yields";
	}
}
