package faction;

public class Blobular extends Faction{

	public Blobular(){
		super();
		name= "Blobular";
		tileYield = new int[][] {{3,0,0,0},{1,1,0,0},{0,2,0,0},{0,1,0,1},{0,0,2,0},{0,0,0,2},{0,2,0,0}};
		desc = "Sentient Blobs, their sole desire is to blob the world."
				+ "\n Attributes: \n"
				+ "-Passive Expansion: Buildings capture twice as much territory \n"
				+ "-No Hands: -1 to all tile yields";
	}
}
