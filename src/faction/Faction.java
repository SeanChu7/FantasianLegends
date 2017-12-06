package faction;
import java.util.ArrayList;
import java.util.List;

import city.Building;
import city.City;
import javafx.scene.paint.Color;
import research.Research;
import unit.Unit;

public class Faction {
	private Research techGoal;
	private List<Research> researched;
	private List<Building> canBuild;
	private List<Unit> AvailUnits;
	private List<Unit> units;
	private int gold;
	private int goldInc;
	private int[][] tileYield;
	private List<City> cities;
	private int science;
	private int scienceInc;
	private int upkeep;
	private int num;
	private Color color;
	public Faction() {
		canBuild = new ArrayList<Building>();
		AvailUnits = new ArrayList<Unit>();
		units = new ArrayList<Unit>();
		gold = 0;
		goldInc = 0;
		//TileYield is as follows, Food-Cons-Gold-Sci
		//Grassland-Forest-Hill-Mountain-Ocean-Ice-Rockland
		tileYield = new int[][] {{4,0,0,0},{2,2,0,0},{1,3,0,0},{0,2,0,2},{1,0,3,0},{0,0,1,3},{0,3,0,1}};
		cities = new ArrayList<City>();
		science = 0;
		scienceInc = 0;
		upkeep = 0;
	}
	public List<Building> getCanBuild(){
		return canBuild;
	}
	public List<Unit> getAvailUnits(){
		return AvailUnits;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color c) {
		color = c;
	}
	public List<City> getCities(){
		return cities;
	}
	public List<Unit> getUnits(){
		return units;
	}
	public int getScience() {
		return science;
	}
	public int getScienceInc() {
		return scienceInc;
	}
	public int getGold() {
		return gold;
	}
	public int getGoldInc() {
		return goldInc;
	}
	public void harvest() {
		for(City c : cities) {
			int[] temp = c.harvest(tileYield);
			goldInc += temp[0];
			scienceInc += temp[1];
		}
	}
	public int[][] gettileYield(){
		return tileYield;
	}
	public void calcUpkeep() {
		int temp = 0;
		for(City c: cities) {
			temp+= c.calcMaint();
		}
		for (Unit u: units) {
			temp+= u.getMaint();
		}
		upkeep = temp;
	}
	public boolean checkIncome() {
		goldInc = goldInc - upkeep;
		return goldInc >= 0;
	}
	public void calcGold() {
		gold+= goldInc;
	}
	public void progressResearch() {
		science+= scienceInc;
		if (science >= techGoal.getScienceReq()) {
			science -= techGoal.getScienceReq();
			techGoal.effect(tileYield, canBuild, AvailUnits);
			researched.add(techGoal);
			techGoal = null;
		}
	}
	public int getNum() {
		return num;
	}
	public void setNum(int x) {
		num = x;
	}
}
