package BusinessLogic;

import java.util.ArrayList;
import java.math.BigInteger;

public class Node {
	
	private static long nodeCounter = 1L;
	private long id;
	private BigInteger seed; // intersection number
	private int level;
	private boolean visited;
	private String name;
	private boolean isReturn;
	private boolean isReal;
	private ArrayList<Node> nextArcs = new ArrayList<Node>();
	private Billboard billboard;
	private SeedOperator seedOp = SeedOperator.getInstance();
	
	public Node(BigInteger pSeed, int pLevel, String pName){
		
		id = nodeCounter;
		seed = pSeed;
		level = pLevel;
		name = pName;
		visited = false;
		isReal = false;
		nodeCounter ++;
	}
	
	/*public void prueba()
	{
		System.out.println("SEMILLA: " + seedOp.getSpecificSeed(10L));
	}*/
	
	public long getId()
	{
		return id;
	}
	
	public void setId(long id)
	{
		this.id = id;
	}

	public BigInteger getSeed() {
		return seed;
	}

	public void setSeed(BigInteger seed) {
		this.seed = seed;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean getVisited() {
		return visited;
	}

	public void setVisited(boolean visited) {
		this.visited = visited;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}
	
	public boolean isReal() {
		return isReal;
	}

	public void setIsReal(boolean isReal) {
		this.isReal = isReal;
	}

	public Billboard getBillboard()
	{
		return this.billboard;
	}
	
	public void setBillboard(Billboard billboard)
	{
		this.billboard = billboard;
	}
	
	public ArrayList<Node> getNextArcs() {
		return nextArcs;
	}

	public void addNextArc(Node nextArc) {
		
		if (findArc(nextArc) == -1)
			nextArcs.add(nextArc);
	}

	public int findArc(Node pNode){
		
        for (int cont = 0; cont < nextArcs.size(); cont++) 
        {
            if (pNode.seed == nextArcs.get(cont).seed)
                return cont;
        }
        
        return -1;
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
			 Node parent = new Node(seedOp.getSpecificSeed(currentNodeId), currentNodeLevel, "");
			 parent.setId(currentNodeId);
			 parents.add(parent);	
			 //System.out.println("PADRE : " + n.getId() + " " + n.getSeed());
		 }
		 		 
		 return parents;
	 }
	
	public void generateAdjacents()
	{
		int numbOfChildren = seedOp.getNumbOfNextIntersections(seed);
		BigInteger childrenSeed = null; 
		
		if(this.isReal)
			System.out.println("PADRE #" + seed + " Nivel: " + level);
		
		// Create always 3 children (not necessarily all real)
		for (int contChildren = 0; contChildren < 3; contChildren++)
		{		
			childrenSeed = seedOp.getNewSeed();			
			Node children = new Node(childrenSeed, assignLevel(level), "");
			
			if (this.isReal) // Real parent
			{
				if (numbOfChildren != 0) // Real children
				{
					children.isReal = true;
					numbOfChildren --;
				}
			}
						
			addNextArc(children);
		}
		
		for (Node c : getNextArcs())
		{
			if (c.isReal)
				System.out.println("HIJO #" + c.getSeed() + " Nivel: " + c.getLevel());
		}
	}
	

}
