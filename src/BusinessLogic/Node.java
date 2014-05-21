package BusinessLogic;

import java.util.ArrayList;

public class Node {
	
	private int seed;
	private int intersectionNumb;
	private int level;
	private boolean visited;
	private String name;
	private boolean isReturn;
	private ArrayList<Node> previousArcs = new ArrayList<Node>();
	private ArrayList<Node> nextArcs = new ArrayList<Node>();
	private static int numbOfNodes = 0;
	private Billboard billboard;
	
	public Node(int pSeed, int pLevel, String pName){
		
		seed = pSeed;
		level = pLevel;
		name = pName;
		visited = false;
		intersectionNumb = numbOfNodes ++;		
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
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

	public int getIntersectionNumb() {
		return intersectionNumb;
	}

	public void setIntersectionNumb(int intersectionNumb) {
		this.intersectionNumb = intersectionNumb;
	}

	public boolean getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(boolean isReturn) {
		this.isReturn = isReturn;
	}
	
	public Billboard getBillboard()
	{
		return this.billboard;
	}
	
	public void setBillboard(Billboard billboard)
	{
		this.billboard = billboard;
	}
	
	public ArrayList<Node> getPreviousArcs(){
		return previousArcs;
	}
	
	public void addPreviousArc(Node previousArc){
		
		if (findArc(previousArc) == -1)
			previousArcs.add(previousArc);
	}
	
	public ArrayList<Node> getNextArcs() {
		return nextArcs;
	}

	public void addNextArcs(Node nextArc) {
		
		if (findArc(nextArc) == -1)
			nextArcs.add(nextArc);
	}

	public int findArc(Node pNode){
		
        for (int cont = 0; cont < previousArcs.size(); cont++) 
        {
            if (pNode.intersectionNumb == previousArcs.get(cont).intersectionNumb)
                return cont;
        }
        
        return -1;
    }	
	

}
