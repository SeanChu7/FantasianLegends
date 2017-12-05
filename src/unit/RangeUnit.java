package unit;

import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class RangeUnit extends Unit{

	private int rngStr;
	public RangeUnit(int m, int xCor, int yCor, int cost,int maintenance,int health, int meleeStr, int rangeStrength, int range) {
		super(m,xCor,yCor,cost,health,meleeStr,maintenance);
		rngStr = rangeStrength;
		rng = range;
	}
	public VBox displayInfo() {
		VBox info = super.displayInfo();
		info.getChildren().add(new Text("Range Attack: " + rngStr));
		info.getChildren().add(new Text("Range: " + rng));
		for (Node t : info.getChildren()) {
			((Text)t).setStroke(Color.WHITE);
		}
		return info;
	}
	public int getrangeStrength() {
		return rngStr;
	}
}
