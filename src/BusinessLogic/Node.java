package BusinessLogic;

import java.util.ArrayList;

public class Node {
	
	private int seed;
	private int id;
	private int levelCounter;
	private boolean visited;
	private String name;
	private int intersectionNumb;
	private boolean isReturn;
	private ArrayList<Node> arcs;
	private static int numbOfNodes = 0;
	
	public Node(int pSeed, int pLevel, String pName, int pIntersectNumb){
		
		seed = pSeed;
		levelCounter = pLevel;
		name = pName;
		intersectionNumb = pIntersectNumb;
		visited = false;
		id = numbOfNodes ++;
		
	}

	public int getSeed() {
		return seed;
	}

	public void setSeed(int seed) {
		this.seed = seed;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevelCounter() {
		return levelCounter;
	}

	public void setLevelCounter(int levelCounter) {
		this.levelCounter = levelCounter;
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
	
	public ArrayList getArcs(){
		return arcs;
	}
	
	public void addArc(Node arc){
		
		if (findArc(arc) == -1)
			arcs.add(arc);
	}
	
	public int findArc(Node pNode){
		
        for (int cont = 0; cont < arcs.size(); cont++) 
        {
            if (pNode.id == arcs.get(cont).id)
                return cont;
        }
        
        return -1;
    }
	

}
