package handler;
import faction.Faction;
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
		double atkStr = (u.getMelee()*1.2)*(u.getHealth()/100);
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
				u.setHealth((int)(u.getHealth()-battleResults[1]));
				g.setHealth((int)(g.getHealth()-battleResults[0]));
				aftermath(u,g);
				break;
			}
		}
	}
	public static void Rangedattack(RangeUnit u, int x , int y) {
		if (Math.abs((x+y)-(u.getX()+u.getY())) > u.getRng()){
			moveToAttack(u,x,y);
		}
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
				break;
			}
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
