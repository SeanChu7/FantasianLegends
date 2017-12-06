package city;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import faction.Faction;
import handler.CityHandler;
import handler.Coordinate;
import handler.GameHandler;
import handler.UnitControl;
import screen.Map;
import tile.Forest;
import tile.Grassland;
import tile.Hill;
import tile.Ice;
import tile.Mountain;
import tile.Ocean;
import tile.Rockland;
import tile.Tile;
import unit.Unit;

public class City {

	private int population;
	private LinkedList<Constructable> construction;
	private List<Building> buildings;
	private Set<Coordinate> tiles;
	private Faction faction;
	private int food;
	private int health;
	private int DefStr;
	private int x;
	private int y;
	private int foodInc;
	private int constructInc;
	private int scienceInc;
	private int goldInc;
	private int construct;
	private List<Building> beingbuilt;
	private int foodToNext;
	public City(Faction f) {
		population = 1;
		foodToNext = 10;
		food = 0;
		foodInc = 0;
		constructInc = 0;
		scienceInc = 0;
		health = 100;
		goldInc = 0;
		construct = 0;
		tiles = new HashSet<Coordinate>();
		buildings = new ArrayList<Building>();
		beingbuilt = new ArrayList<Building>();
		construction = new LinkedList<Constructable>();
		faction = f;
	}
	public Faction getFaction() {
		return faction;
	}
	public List<Building> getBeingBuilt(){
		return beingbuilt;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int h) {
		health = h;
	}
	public int getDefStr() {
		return 100+(10*buildings.size());
	}
	public Queue<Constructable> getConstruction(){
		return construction;
	}
	public void setX(int xCor) {
		x = xCor;
	}
	public void setY(int yCor) {
		y = yCor;
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getFoodInc() {
		return foodInc;
	}
	public int getConstructInc() {
		return constructInc;
	}
	public int getGoldInc() {
		return goldInc;
	}
	public int getSciInc() {
		return scienceInc;
	}
	public int getPopulation() {
		return population;
	}
	public int getFoodToNext() {
		return foodToNext;
	}
	public List<Building> getBuildings(){
		return buildings;
	}
	public Set<Coordinate> getTiles(){
		return tiles;
	}

	public int[] addArray(int[] a, int[] b) {
		int[] c = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			c[i] = a[i] + b[i];
		}
		return c;
	}
	public int[] harvest(int[][] tileYield) {
		foodInc = 0;
		constructInc = 0;
		int[] SciGold = new int[2];
		for(Building b: buildings) {
			SciGold = addArray(SciGold,harvest(b.getYield()));
		}
		for(Coordinate c: tiles) {
			Tile t = Map.tileMap[c.getX()][c.getY()];
			if (t instanceof Grassland)
				SciGold = addArray(SciGold,harvest(tileYield[0]));
			else if (t instanceof Forest)
				SciGold = addArray(SciGold,harvest(tileYield[1]));
			else if (t instanceof Hill)
				SciGold = addArray(SciGold,harvest(tileYield[2]));
			else if (t instanceof Mountain)
				SciGold = addArray(SciGold,harvest(tileYield[3]));
			else if (t instanceof Ocean)
				SciGold = addArray(SciGold,harvest(tileYield[4]));
			else if (t instanceof Ice)
				SciGold = addArray(SciGold,harvest(tileYield[5]));
			else if (t instanceof Rockland)
				SciGold = addArray(SciGold,harvest(tileYield[6]));
		}
		scienceInc = SciGold[1];
		goldInc = SciGold[0];
		return SciGold;
	}
	public int[] harvest(int[] yield) {
		int[] SciGold = new int[2];
		foodInc+= yield[0];
		constructInc += yield[1];
		SciGold[0] += yield[2];
		SciGold[1] += yield[3];
		return SciGold;
	}
	
	public void calcFood() {
		int foodUpkeep = population*2;
		food = food + foodInc - foodUpkeep;
		while(food > 0) {
			if (food >= foodToNext) {
				food -= foodToNext;
				population++;
				foodToNext = population * 10;
			}
			else {
				foodToNext -= food;
				food = 0;
			}
		}
	}
	public void calcConstruct() {
		construct +=  constructInc;
		if(construction.peek().getCost() <= construct) {
			if (construction.peek() instanceof Building) {
				beingbuilt.remove(construction.peek());
				CityHandler.spawnBuilding((Building) construction.peek(), this, faction);
				construct -= construction.poll().getCost();
				GameHandler.updateYield(faction);
			}
			else {
				if (Map.unitMap[construction.peek().getX()][construction.peek().getY()] ==0) {
				UnitControl.spawnUnit((Unit) construction.peek(),construction.peek().getX(), construction.peek().getY());
				construct -= construction.peek().getCost();
				construction.poll();
				GameHandler.updateYield(faction);
				}
			}
		}
	}
	public void addConstruct(Constructable b,Coordinate g) {
		Constructable c = b.clone();
		c.setX(g.getX());
		c.setY(g.getY());
		construction.add(c);
		if (c instanceof Building)
			beingbuilt.add((Building) c);
		if (c instanceof Unit)
			((Unit) c).setOwner(faction);
	}
	public int calcMaint() {
		int result = 0;
		for(Building b: buildings) {
			result+= b.getM();
		}
		return result;
	}
}
