package handler;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import screen.Map;
import tile.Tile;
import unit.Unit;

public class UnitControl {
	public static ArrayList<Unit> units = new ArrayList<Unit>();
	public static Set<Coordinate> inRangeTile = new HashSet<Coordinate>();
	public static Set<Coordinate> inRangeUnit = new HashSet<Coordinate>();
	public static int[][] moveReq = new int[Map.tileMap.length][Map.tileMap[0].length];
	public static void spawnUnit(Unit u,int x, int y) {
		Map.unitMap[x][y] = u.getOwner().getNum();
		Map.moveMap[x][y] =  0;
		Map.tileMap[x][y].displayUnit(Unit.Unitdisplay);
		u.getOwner().getUnits().add(u);
		units.add(u);
	}
	public static Rectangle addBorder(Color c) {
		Rectangle r = new Rectangle(Tile.gettileSizeX()-4,Tile.gettileSizeY()-4,Color.TRANSPARENT);
		r.setStroke(c);
		r.setStrokeWidth(2.0);
		return r;
	}
	public static void die(Unit u) {
		
		//Remove all traces of the unit
		u.getOwner().getUnits().remove(u);
		units.remove(u);
		Map.unitMap[u.getX()][u.getY()] = 0;
		Map.tileMap[u.getX()][u.getY()].removeUnit();
		Map.moveMap[u.getX()][u.getY()] = Map.tileMap[u.getX()][u.getY()].getMovement();
	}
	public static void display(Unit u) {
		Map.tileMap[u.getX()][u.getY()].displayUnit(Unit.Unitdisplay);
	}

	public static void initalize(Unit u) {
		Map.unitMap[u.getX()][u.getY()] = 1;
		Map.moveMap[u.getX()][u.getY()] = 0;
	}
	public static void reset() {
		for (int i = 0; i < moveReq.length; i++) {
			Arrays.fill(moveReq[i], 100);
		}
		for(Coordinate c : inRangeTile) {
			Map.tileMap[c.getX()][c.getY()].removeBorder();
		}
		for (Coordinate c: inRangeUnit) {
			Map.tileMap[c.getX()][c.getY()].removeBorder();
		}
		inRangeTile = new HashSet<Coordinate>();
		inRangeUnit = new HashSet<Coordinate>();
	}
	//Rewrite MoveRangeFlying
	public static void showMoveRangeFlying(int x, int y, int moves,boolean moving, int rng,int num,int iterations) {
		if (moves <= 0) {	
			showAttackRange(x,y,rng,num);
		}
		else {
		if (y+1 > Map.moveMap[0].length) {
		}
		else if(Map.unitMap[x][y+1] != 0 && Map.unitMap[x][y+1] != num)	{
			inRangeUnit.add(new Coordinate(x,y+1));
			Map.tileMap[x][y+1].setBorder(addBorder(Color.RED));
			showAttackRange(x,y+1,rng,num);
		}
		else {
			if(moving) {
				if(Map.unitMap[x][y+1] == 0) {
					inRangeTile.add(new Coordinate(x,y+1));
					moveReq[x][y+1] = Math.min(moveReq[x][y+1],iterations+1);
					Map.tileMap[x][y+1].setBorder(addBorder(Color.DARKBLUE));
				}
			}
			showMoveRangeFlying(x,y+1,moves - 1,moving,rng,num,iterations+1);
		}
		if (y-1 < 0 ) {
		}
		else if(Map.unitMap[x][y-1] != 0 && Map.unitMap[x][y-1] != num)	{
			inRangeUnit.add(new Coordinate(x,y-1));
			Map.tileMap[x][y-1].setBorder(addBorder(Color.RED));
			showAttackRange(x,y-1,rng,num);
		}
		else {
			if(moving) {
				if(Map.unitMap[x][y-1] == 0) {
					inRangeTile.add(new Coordinate(x,y-1));
					moveReq[x][y-1] = Math.min(moveReq[x][y-1],iterations+1);
					Map.tileMap[x][y-1].setBorder(addBorder(Color.DARKBLUE));
					}
			}
			showMoveRangeFlying(x,y-1,moves - 1,moving,rng,num,iterations+1);
		}
		if (x+1 > Map.moveMap.length) {
		}
		else if(Map.unitMap[x+1][y] != 0 && Map.unitMap[x+1][y] != num)	{
			inRangeUnit.add(new Coordinate(x+1,y));
			Map.tileMap[x+1][y].setBorder(addBorder(Color.RED));
			showAttackRange(x+1,y,rng,num);
		}
		else {
			if(moving) {
				if(Map.unitMap[x+1][y] == 0) {
					inRangeTile.add(new Coordinate(x+1,y));
					moveReq[x+1][y] = Math.min(moveReq[x+1][y],iterations+1);
					Map.tileMap[x+1][y].setBorder(addBorder(Color.DARKBLUE));
					}
			}
			showMoveRangeFlying(x+1,y,moves - 1,moving,rng,num,iterations+1);
		}
		if (x-1 < 0) {
		}
		else if(Map.unitMap[x-1][y] != 0 && Map.unitMap[x-1][y] != num)	{
			inRangeUnit.add(new Coordinate(x-1,y));
			Map.tileMap[x-1][y].setBorder(addBorder(Color.RED));
			showAttackRange(x-1,y,rng,num);
		}
		else {
			if(moving) {
				if(Map.unitMap[x-1][y] == 0) {
					inRangeTile.add(new Coordinate(x-1,y));
					moveReq[x-1][y] = Math.min(moveReq[x-1][y],iterations+1);
					Map.tileMap[x-1][y].setBorder(addBorder(Color.DARKBLUE));
					}
			}
			showMoveRangeFlying(x-1,y,moves - 1,moving,rng,num,iterations+1);
		}
		}
	}
	public static void showAttackRange(int x, int y, int rng, int num) {
		if (rng <= 0) {
			
		}
		else {
			if (y+1 > Map.moveMap[0].length) {
			}
			else if(Map.unitMap[x][y+1] != 0 && Map.unitMap[x][y+1] != num)	{
				inRangeUnit.add(new Coordinate(x,y+1));
				Map.tileMap[x][y+1].setBorder(addBorder(Color.RED));
			}
			showAttackRange(x,y+1,rng-1,num);
			if (y -1 < 0) {	
			}
			else if(Map.unitMap[x][y-1] != 0 && Map.unitMap[x][y-1] != num)	{
				inRangeUnit.add(new Coordinate(x,y-1));
				Map.tileMap[x][y-1].setBorder(addBorder(Color.RED));
			}
			showAttackRange(x,y-1,rng-1,num);
			if(x+1 > Map.moveMap.length) {
			}
			else if(Map.unitMap[x+1][y] != 0 && Map.unitMap[x+1][y] != num)	{
				inRangeUnit.add(new Coordinate(x+1,y));
				Map.tileMap[x+1][y].setBorder(addBorder(Color.RED));
			}
			showAttackRange(x+1,y,rng-1,num);
			if(x - 1 < 0) {
			}
			else if(Map.unitMap[x-1][y] != 0  && Map.unitMap[x-1][y] != num) {
				inRangeUnit.add(new Coordinate(x-1,y));
				Map.tileMap[x-1][y].setBorder(addBorder(Color.RED));
			}
			showAttackRange(x-1,y,rng-1,num);
		}
	}
	public static void showMoveRange(int x, int y, int moves,boolean moving, int rng,int num,int iterations) {
		if (moves <= 0) {	
			showAttackRange(x,y,rng,num);
		}
		else {
		if (y+1 > Map.moveMap[0].length) {
		}
		else if(Map.unitMap[x][y+1] != 0 && Map.unitMap[x][y+1] != num)	{
			inRangeUnit.add(new Coordinate(x,y+1));
			Map.tileMap[x][y+1].setBorder(addBorder(Color.RED));
			showAttackRange(x,y+1,rng,num);
		}
		else if (Map.moveMap[x][y+1] == 0) {
			showAttackRange(x,y+1,rng,num);
		}
		else {
			if(moving) {
			inRangeTile.add(new Coordinate(x,y+1));
			moveReq[x][y+1] = Math.min(moveReq[x][y+1],iterations+Map.moveMap[x][y+1]);
			Map.tileMap[x][y+1].setBorder(addBorder(Color.DARKBLUE));}
			showMoveRange(x,y+1,moves - Map.moveMap[x][y+1],moving,rng,num,iterations+Map.moveMap[x][y+1]);
		}
		if (y-1 < 0 ) {
		}
		else if(Map.unitMap[x][y-1] != 0 && Map.unitMap[x][y-1] != num)	{
			inRangeUnit.add(new Coordinate(x,y-1));
			Map.tileMap[x][y-1].setBorder(addBorder(Color.RED));
			showAttackRange(x,y-1,rng,num);
		}
		else if (Map.moveMap[x][y-1] == 0) {
			showAttackRange(x,y-1,rng,num);
		}
		else {
			if(moving) {
			inRangeTile.add(new Coordinate(x,y-1));
			moveReq[x][y-1] = Math.min(moveReq[x][y-1],iterations+Map.moveMap[x][y-1]);
			Map.tileMap[x][y-1].setBorder(addBorder(Color.DARKBLUE));}
			showMoveRange(x,y-1,moves - Map.moveMap[x][y-1],moving,rng,num,iterations+Map.moveMap[x][y-1]);
		}
		if (x+1 > Map.moveMap.length) {
		}
		else if(Map.unitMap[x+1][y] != 0 && Map.unitMap[x+1][y] != num)	{
			inRangeUnit.add(new Coordinate(x+1,y));
			Map.tileMap[x+1][y].setBorder(addBorder(Color.RED));
			showAttackRange(x+1,y,rng,num);
		}
		else if(Map.moveMap[x+1][y] == 0) {
			showAttackRange(x+1,y,rng,num);
		}
		else {
			if(moving) {
			inRangeTile.add(new Coordinate(x+1,y));
			moveReq[x+1][y] = Math.min(moveReq[x+1][y],iterations+Map.moveMap[x+1][y]);
			Map.tileMap[x+1][y].setBorder(addBorder(Color.DARKBLUE));}
			showMoveRange(x+1,y,moves - Map.moveMap[x+1][y],moving,rng,num,iterations+Map.moveMap[x+1][y]);
		}
		if (x-1 < 0) {
		}
		else if(Map.unitMap[x-1][y] != 0 && Map.unitMap[x-1][y] != num)	{
			inRangeUnit.add(new Coordinate(x-1,y));
			Map.tileMap[x-1][y].setBorder(addBorder(Color.RED));
			showAttackRange(x-1,y,rng,num);
		}
		else if(Map.moveMap[x-1][y] == 0) {
			showAttackRange(x-1,y,rng,num);
		}
		else {
			if(moving) {
			inRangeTile.add(new Coordinate(x-1,y));
			moveReq[x-1][y] = Math.min(moveReq[x-1][y],iterations+Map.moveMap[x-1][y]);
			Map.tileMap[x-1][y].setBorder(addBorder(Color.DARKBLUE));}
			showMoveRange(x-1,y,moves - Map.moveMap[x-1][y],moving,rng,num,iterations+Map.moveMap[x-1][y]);
		}
		}
	}
	/*public static void testing() {
		for(int i = 0; i < Map.tileMap.length; i++) {
			for (int g = 0 ; g < Map.tileMap[i].length; g++) {
				Map.tileMap[i][g].getChildren().add(new Text(""+moveReq[i][g]));
			}
		}
	}*/
	public static void move(Unit u, int xCor, int yCor) {
		die(u);
		spawnUnit(u,xCor,yCor);
		u.setMovesLeft(u.getMoveLeft()-moveReq[xCor][yCor]);
		if(u.getMoveLeft() == 0)
			GameHandler.moveAbleUnit.remove(u);
		u.setX(xCor);
		u.setY(yCor);
		GameHandler.deSelectUnit();
	}
}
