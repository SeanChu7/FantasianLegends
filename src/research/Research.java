package research;
import java.util.List;

import city.Building;
import unit.Unit;

public abstract class Research {

	protected int Sciencereq;
	protected String name;
	protected String desc;
	public abstract void effect(int[][] tileYield, List<Building> buildings, List<Unit> units);

	public int getScienceReq() {
		return Sciencereq;
	}
	public String toString() {
		return name;
	}
	public String getDesc() {
		return desc;
	}
}
