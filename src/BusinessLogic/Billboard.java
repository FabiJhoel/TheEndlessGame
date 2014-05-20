package BusinessLogic;

public class Billboard {
	
	private int intersectionNum;
	private String intersectionName;
	private String imagePath;
	private PlayerInfo player;
	
	public Billboard(int intersectionNum, String intersectionName,
					 String imagePath) 
	{
		this.intersectionNum = intersectionNum;
		this.intersectionName = intersectionName;
		this.imagePath = imagePath;
	}

	public int getIntersectionNum() {
		return intersectionNum;
	}

	public void setIntersectionNum(int intersectionNum) {
		this.intersectionNum = intersectionNum;
	}

	public String getIntersectionName() {
		return intersectionName;
	}

	public void setIntersectionName(String intersectionName) {
		this.intersectionName = intersectionName;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public PlayerInfo getPlayer()
	{
		return this.player;
	}
	
	public void setPlayer(PlayerInfo player)
	{
		this.player = player;
	}
	
	

}
