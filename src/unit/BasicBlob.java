package unit;

public class BasicBlob extends Unit implements BlobularUnit{

	public BasicBlob() {
		health = 100;
		meleeStr = 5;
		movement = 2;
		name = "Blob";
		movesleft = 2;
		cost = 10;
		maint = 1;
		//Unitdisplay = blobdisplay;
	}
	public BasicBlob clone() {
		return new BasicBlob();
	}
}
