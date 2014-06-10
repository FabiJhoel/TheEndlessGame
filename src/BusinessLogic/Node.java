package BusinessLogic;

import java.util.ArrayList;
import java.util.Random;
import java.math.BigInteger;

public class Node {
	
	private long id;
	private BigInteger seed; // intersection number
	private int level;
	private boolean visited;
	private String name;
	private boolean isReturn;
	private boolean isReal;
	private ArrayList<Node> nextArcs = new ArrayList<Node>();
	private SeedOperator seedOp = SeedOperator.getInstance();
	
	public Node(BigInteger pSeed, int pLevel, String pName, long pId)
	{		
		id = pId;
		seed = pSeed;
		level = pLevel;
		name = pName;
		visited = false;
		isReal = false;
	}
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}

	public BigInteger getSeed() 
	{
		return seed;
	}

	public void setSeed(BigInteger seed) 
	{
		this.seed = seed;
	}

	public int getLevel() 
	{
		return level;
	}

	public void setLevel(int level) 
	{
		this.level = level;
	}

	public boolean getVisited()
	{
		return visited;
	}

	public void setVisited(boolean visited) 
	{
		this.visited = visited;
	}

	public String getName() 
	{
		return name;
	}

	public void setName(String name) 
	{
		this.name = name;
	}

	public boolean getIsReturn() 
	{
		return isReturn;
	}

	public void setIsReturn(boolean isReturn) 
	{
		this.isReturn = isReturn;
	}
	
	public boolean isReal() 
	{
		return isReal;
	}

	public void setIsReal(boolean isReal) 
	{
		this.isReal = isReal;
	}

	public ArrayList<Node> getNextArcs() 
	{
		return nextArcs;
	}
	
	public ArrayList<Node> getRealArcs()
	{
		ArrayList<Node> realArcs = new ArrayList<Node>();
		for(Node arc : nextArcs){
			if (arc.isReal)
				realArcs.add(arc);
		}
		return realArcs;
	}

	public void addNextArc(Node nextArc) 
	{
		nextArcs.add(nextArc);
	}	
	
	public int assignLevel(int parentLevel)
    {
    	int level = parentLevel + 1;
    	
    	if (level <= 4)
    		return level;
    	
    	else
    		return 1;
    }
	 
	 public SeedOperator getSeedOp()
	 {
		 return seedOp;
	 }
	 
	 public ArrayList<Node> getAllParents()
	 {
		 ArrayList<Node> parents = new ArrayList<Node>();
		 long currentNodeId = this.id;
		 int currentNodeLevel = this.level;
		 
		 while (currentNodeLevel != 1)
		 {
			 currentNodeId = Math.round((double)currentNodeId/3);
			 currentNodeLevel --;
			 
			 // Create parent
			 Node parent = new Node(seedOp.getSpecificSeed(currentNodeId), currentNodeLevel, generateRandWord(), currentNodeId);
			 parent.setVisited(true);
			 parents.add(parent);	
		 }
		 		 
		 return parents;
	 }
	
	public void generateAdjacents()
	{
		int numbOfChildren = seedOp.getNumbOfNextIntersections(seed, level);	
		BigInteger childrenSeed = null; 
		long childrenId = 0;
		
		// Create always 3 children (not necessarily all real)
		for (int contChildren = -1; contChildren < 2; contChildren++)
		{					
			childrenSeed = seedOp.getNewSeed();
			childrenId = 3*id + contChildren;
			Node children = new Node(childrenSeed, assignLevel(level), generateRandWord(), childrenId);

			if (numbOfChildren != 0) // Real children
			{
				children.isReal = true;
				numbOfChildren --;
			}
	
			addNextArc(children);
		}
	}
	
	public static String generateRandWord()
	{
	    Random random = new Random();
        char[] wordArray = new char[random.nextInt(8)+3]; 
        
        for(int index = 0; index < wordArray.length; index++)
        {
            wordArray[index] = (char)('a' + random.nextInt(26));
        }
        
        String word = new String(wordArray);
        
	    return word;    
	}	
}
