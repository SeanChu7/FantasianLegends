package research;

import java.util.List;

import city.Building;
import unit.Unit;

public class Foresting extends Research{
	public Foresting() {
		name = "Foresting";
		Sciencereq = 500;
		desc = "Increases Yield of Forest by 1 food, 1 Construction";
			     
	}
	@Override
	public void effect(int[][] tileYield, List<Building> buildings, List<Unit> units) {
		tileYield[1][1] += 1;
		tileYield[1][0] += 1;
	}
}
