package research;

import java.util.List;

import city.Building;
import unit.Unit;

public class AdvancedFarming extends Research{

	public AdvancedFarming() {
		name = "Advanced Farming";
		Sciencereq = 50;
		desc = "Increases Yield of Grassland by 2 food";
			     
	}
	@Override
	public void effect(int[][] tileYield, List<Building> buildings, List<Unit> units) {
		tileYield[0][1] += 2;
	}
	
}
