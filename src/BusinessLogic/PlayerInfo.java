package BusinessLogic;

public class PlayerInfo {
	
	private String Name;
	private long score;
	private int lives;
	private final int NUMBER_OF_LIVES = 3;
	
	public PlayerInfo(String name) 
	{
		Name = name;
		lives = NUMBER_OF_LIVES;
		score = 0;
	}

	public boolean checkLives()
	{
		try
		{
			if (lives <= 0)
				return true;
			else
				return false;
		}
		catch(Exception e)
    	{
    		System.out.println("ERROR: PlayerInfo.checkLives() failure");
    		return false;
    	}
	}
	
	//getters and setters
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public long getScore() {
		return score;
	}

	public void accumulateScore(long points) {
		this.score += points;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

}
