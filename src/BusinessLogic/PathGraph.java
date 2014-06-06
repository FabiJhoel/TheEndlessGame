package BusinessLogic;

import java.util.ArrayList;

import com.google.common.collect.*;

import java.math.BigInteger;
import java.util.Collection;

public class PathGraph {
	
	private static Multimap<Integer, Node> visitedNodes = HashMultimap.create();
	private Node currentNode;
	
	public Node getCurrentNode() {
		return currentNode;
	}

	public void setCurrentNode(Node currentNode) {
		this.currentNode = currentNode;
	}

	// Looks for a node in the visitedNodes Hash
	public Boolean findVisitedNode(Node pNode)
	{
		Collection <Node> nodes = visitedNodes.get(pNode.getLevel());

        for (Node intersection : nodes) 
        {
        	if (intersection == pNode)
        		return true;       	
        }  
        
        return false;
    }

	public void loadHashVisitedNodes(Node isReturnNode)
	{
		visitedNodes.clear();
		
		// Get all parents
		ArrayList<Node> parents = isReturnNode.getAllParents();
		
		// Load parents to hash table
		for (Node visitedNode : parents)
		{
			if (visitedNodes.get(visitedNode.getLevel()).size() < 3)    	
	    	{	    		
	    		if (!findVisitedNode(visitedNode))
		    		visitedNodes.put(visitedNode.getLevel(), visitedNode);	                  
	    	}	    	    				
		}
	}

    public void setInitialIntersection()
    {
    	BigInteger initialSeed = BigInteger.valueOf(6);
    	Node initialNode = new Node(initialSeed, 1, "", 1);
    	initialNode.setIsReal(true);
    	currentNode = initialNode;

    }
    
    public void generateLevel()
    {
    	try {
    		currentNode.generateAdjacents();
	   		
    		// Determine return path
    		if (currentNode.getLevel() == 3)
    		{
    			setReturnPath(currentNode.getNextArcs());
    		}		  
    	}
    	
    	catch(Exception e)
    	{
    		System.out.println("ERROR generating new level " + e.getMessage());
    	}
    }
    
    // Establish a isReturn intersection (level 4 nodes)
    public void setReturnPath(ArrayList<Node> paths)
    { 
    	BigInteger divisorTwo = BigInteger.valueOf(2);
    	boolean selected = false;
    			
	   // Selects the first pair seed as return path
    	for (Node node : paths)
    	{
    		if (node.getSeed().mod(divisorTwo).equals(BigInteger.ZERO))
    		{
    			node.setIsReturn(true);
    			selected = true;
    		}
    	}
    	
    	// If there are no pair seeds
    	if (!selected)   	
    		paths.get(0).setIsReturn(true);   		  	   		
    }
    
    // Select a node to return from the visitedNodes Hash
    public Node selectVisitedNode(Node returnPath)
    {
    	BigInteger divisorFive = BigInteger.valueOf(4);
    	int remainder = returnPath.getSeed().mod(divisorFive).intValue(); // value from 0 to 3
    	int key = remainder + 1;
    	Node nodeToReturn = null;
    	
    	Collection <Node> nodes = visitedNodes.get(key);
    	
    	for (Node path : nodes) // Selects first element of the key
        {	        	
    		nodeToReturn = path;
    		return nodeToReturn;
        }   
    	
    	return nodeToReturn;
    }
    
    public Node suggestPath(ArrayList<Node> paths) //los niveles superiores a estos nodos ya deben estar cargados
    {
    	Node bestPath = null; 	//global solution 
    	
    	while(!paths.isEmpty())
    	{
    		Node localSol = paths.get(0);  	// possible solution;	
    		paths.remove(0);
    		
    		
    		if (bestPath == null || MatrixOperator.getBestTransitiveClosure(localSol, bestPath) == 1)
    		{
    			bestPath = localSol;
    		}
    		
    	}
    	
    	return bestPath;
    }

}
