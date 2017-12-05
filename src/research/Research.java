package research;
import java.util.List;

import city.Building;
import unit.Unit;

public abstract class Research {

	private int Sciencereq;
	
	public abstract void effect(int[][] tileYield, List<Building> buildings, List<Unit> units);

	public int getScienceReq() {
		return Sciencereq;
	}
}
