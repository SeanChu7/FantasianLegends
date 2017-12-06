package handler;
import city.City;
import screen.Map;
import tile.Forest;
import tile.Hill;
import tile.Mountain;
import tile.Ocean;
import tile.Tile;
import unit.RangeUnit;
import unit.Unit;

public class ProcessCombat {

	public static void moveToAttack(Unit u, int xCor, int yCor) {
		int rng = u.getRng();
		for (Coordinate c: UnitControl.inRangeTile) {
			if(Math.abs((xCor+yCor)-(c.getX()+c.getY()))<=rng ) {
				UnitControl.move(u, c.getX(), c.getY());
				break;
			}
		}
	}
	public static double[] calcDmg(double atkStr, double defStr) {
		double total = atkStr + defStr;
		double dmgdef = total * (atkStr/total);
		double dmgatk = total * (defStr/total);
		double[] results = new double[] {dmgdef,dmgatk};
		return results;
	}
	public static void attack(Unit u, int x, int y) {
		if (Math.abs((x+y)-(u.getX()+u.getY())) > u.getRng()){
			moveToAttack(u,x,y);
		}
		double atkStr = (u.getMelee()*1.2)*(u.getHealth()/100.0);
		double defStr = 0;
		boolean found = false;
		double[] battleResults;
		Tile defTile = Map.tileMap[x][y];
		for(Unit g : UnitControl.units) {
			if (g.getX() == x && g.getY() == y) {
				defStr = g.getMelee()*(g.getHealth()/100);
				if (defTile instanceof Ocean || defTile instanceof Mountain)
					defStr *= 1.4;
				else if(defTile instanceof Forest || defTile instanceof Hill)
					defStr *= 1.2;
				battleResults = calcDmg(atkStr,defStr);
				u.setHealth((int)(u.getHealth()-battleResults[1]));
				g.setHealth((int)(g.getHealth()-battleResults[0]));
				aftermath(u,g);
				found = true;
				break;
			}
		}
		if (!found) {
			for (City c: CityHandler.cities) {
				for (int i = 0; i < c.getBuildings().size(); i++) {
					if (c.getBuildings().get(i).getX() == x && c.getBuildings().get(i).getY() == y) {
						defStr = c.getDefStr();
						battleResults = calcDmg(atkStr,defStr);
						u.setHealth((int)(u.getHealth()-battleResults[1]));
						c.setHealth((int)(c.getHealth()-battleResults[0]));
						aftermath(u,c);
					}
				}
			}
		}
		GameHandler.moveAbleUnit.remove(u);
		GameHandler.deSelectUnit();
	}
	public static void Rangedattack(RangeUnit u, int x , int y) {
		if (Math.abs((x+y)-(u.getX()+u.getY())) > u.getRng()){
			moveToAttack(u,x,y);
		}
		boolean found = false;
		double atkStr = (u.getrangeStrength()*1.2)*(u.getHealth()/100);
		double defStr = 0;
		double[] battleResults;
		Tile defTile = Map.tileMap[x][y];
		for(Unit g : UnitControl.units) {
			if (g.getX() == x && g.getY() == y) {
				defStr = g.getMelee()*(g.getHealth()/100);
				if (defTile instanceof Ocean || defTile instanceof Mountain)
					defStr *= 1.4;
				else if(defTile instanceof Forest || defTile instanceof Hill)
					defStr *= 1.2;
				battleResults = calcDmg(atkStr,defStr);
				g.setHealth((int)(g.getHealth()-battleResults[0]));
				if (g.getHealth() <= 0)
					UnitControl.die(g);
				GameHandler.deSelectUnit();
				found = true;
				break;
			}
		}
		if (!found) {
			for (City c: CityHandler.cities) {
				for (int i = 0; i < c.getBuildings().size(); i++) {
					if (c.getBuildings().get(i).getX() == x && c.getBuildings().get(i).getY() == y) {
						defStr = c.getDefStr();
						battleResults = calcDmg(atkStr,defStr);
						u.setHealth((int)(u.getHealth()-battleResults[1]));
						c.setHealth((int)(c.getHealth()-battleResults[0]));
						aftermath(u,c);
					}
				}
			}
		}
		GameHandler.moveAbleUnit.remove(u);
	}
	public static void aftermath(Unit attacker, City defender) {
		if (attacker.getHealth() <= 0) {
			UnitControl.die(attacker);
		}
		if (defender.getHealth() <= 0) {
			defender.getFaction().getCities().remove(defender);
			CityHandler.burnCity(defender);
		}
	}
	public static void aftermath(Unit attacker, Unit defender) {
		if (attacker.getHealth() <= 0 && defender.getHealth() <= 0 ) {
			UnitControl.die(defender);
			UnitControl.die(attacker);
		}
		else if(defender.getHealth() <= 0) {
			UnitControl.die(defender);
			UnitControl.move(attacker, defender.getX(), defender.getY());
		}
		else if(attacker.getHealth() <= 0) {
			UnitControl.die(attacker);
		}
	}
}
