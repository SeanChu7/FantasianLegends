package handler;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import city.Constructable;
import faction.Faction;
import screen.Map;
import tile.Buildable;
import unit.FlyingUnit;
import unit.Unit;

public class AIControl {

	public static void AIaction(Faction f) {
		//Check Construction Queues
		Random  r = new Random();
		int temp;
		Iterator<Coordinate> iter;
		Coordinate holder = null;
		ArrayList<Constructable> buildable = new ArrayList<Constructable>();
		for (int i = 0; i < f.getUnits().size(); i++) {
			if(f.getUnits().get(i) instanceof FlyingUnit) {
				UnitControl.reset();
				UnitControl.showMoveRangeFlying(f.getUnits().get(i).getX(), f.getUnits().get(i).getY(), f.getUnits().get(i).getMoveLeft(), true, f.getUnits().get(i).getRng(), f.getNum(), 0);
				if (UnitControl.inRangeUnit.size() > 0) {
					iter = UnitControl.inRangeUnit.iterator();
					ProcessCombat.attack(f.getUnits().get(i), iter.next().getX(), iter.next().getY());
				}
				else {
				iter = UnitControl.inRangeTile.iterator();
				UnitControl.moveAI(f.getUnits().get(i), iter.next().getX(), iter.next().getY());
				}
			}
			else {
				UnitControl.reset();
				UnitControl.showMoveRange(f.getUnits().get(i).getX(), f.getUnits().get(i).getY(), f.getUnits().get(i).getMoveLeft(), true, f.getUnits().get(i).getRng(), f.getNum(), 0);
				if (UnitControl.inRangeUnit.size() > 0) {
					iter = UnitControl.inRangeUnit.iterator();
					Coordinate x = iter.next();
					ProcessCombat.attack(f.getUnits().get(i), x.getX(), x.getY());
				}
				else {
				if (UnitControl.inRangeTile.size() > 0) {
				iter = UnitControl.inRangeTile.iterator();
				Coordinate x = iter.next();
				UnitControl.moveAI(f.getUnits().get(i), x.getX(), x.getY());
				}
				}
			}
			
		}
		for (int i = 0; i < f.getCities().size(); i++) {
			if (f.getCities().get(i).getConstruction().isEmpty()) {
				buildable.addAll(f.getCanBuild());
				buildable.addAll(f.getAvailUnits());
				buildable.removeAll(f.getCities().get(i).getBeingBuilt());
				buildable.removeAll(f.getCities().get(i).getBuildings());
				temp = r.nextInt(buildable.size());
				for (Coordinate c : f.getCities().get(i).getTiles()) {
					if (Map.tileMap[c.getX()][c.getY()] instanceof Buildable && Map.buildingMap[c.getX()][c.getY()] == 0 && Map.unitMap[c.getX()][c.getY()] == 0) {
						holder = c;
						break;
					}
				}
				if (holder != null)
					f.getCities().get(i).addConstruct(buildable.get(temp),holder);
			}
		}
		f.harvest();
		for (int i  = 0; i < f.getCities().size(); i++) {
			if (!f.getCities().get(i).getConstruction().isEmpty())
				f.getCities().get(i).calcConstruct();
			f.getCities().get(i).calcFood();
		}
	}
}
