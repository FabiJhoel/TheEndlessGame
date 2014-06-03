package BusinessLogic;

import java.util.ArrayList;

import com.google.common.collect.*;

import java.math.BigInteger;
import java.util.Collection;

public class PathGraph {
	
	private ArrayList <Node> currentParents = new ArrayList<Node>();
	private static Multimap<Integer, Node> visitedNodes = HashMultimap.create();
	
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

	// Adds a node into visitedNodes Hash
    public void visitIntersection(Node pNode)
    {    	
    	pNode.setVisited(true);
    	
    	// No space
    	if (visitedNodes.get(pNode.getLevel()).size() >= 3)    	
    	{
    		Collection <Node> nodes = visitedNodes.get(pNode.getLevel());

	        for (Node intersection : nodes) 
	        {	        	
	        	nodes.remove(intersection);
	        	break;
	        }            
    	}
    	
    	// Hash table has space available
    	if (!findVisitedNode(pNode))
    		visitedNodes.put(pNode.getLevel(), pNode);    	
    }

    public Node setInitialIntersection()
    {
    	BigInteger initialSeed = BigInteger.valueOf(6);
    	Node initialNode = new Node(initialSeed, 1, "");
    	currentParents.add(initialNode);
    	
    	return initialNode;
    }
    
    public void generateLevel()
    {
    	try {
    		
	    	ArrayList <Node> newParents = new ArrayList<Node>();
	
	    	for (Node parent : currentParents)
	    	{
	    		parent.generateAdjacents();
	    		   	
	    		// Set new parents
	    		for (Node child : parent.getNextArcs())
	    		{   			
	    			newParents.add(child);	
	    		}
	    		
	    		// Determine return path
	    		if (parent.getLevel() == 4)
	    		{
	    			setReturnPath(parent.getNextArcs());
	    		}		
	    	}
	    	
	    	// New generation of parents
	    	currentParents.clear();
	    	currentParents = newParents;
    	}
    	
    	catch(Exception e)
    	{
    		System.out.println("ERROR " + e.getMessage());
    	}
    }
    
    // Establish a isReturn intersection
    public void setReturnPath(ArrayList<Node> paths)
    { 
    	BigInteger divisorTwo = BigInteger.valueOf(2);
    	
    	if (paths.size() == 1)
    		paths.get(0).setIsReturn(true);
    	
    	else
    	{   // Selects the first pair seed as return path
        	for (Node node : paths)
        	{
        		if (node.getSeed().mod(divisorTwo).equals(BigInteger.ZERO))
        			node.setIsReturn(true);
        	}
    	}		
    }
    
    // Select a node to return from the visitedNodes Hash
    public Node selectVisitedNode(Node returnPath)
    {
    	BigInteger divisorFive = BigInteger.valueOf(5);
    	int remainder = returnPath.getSeed().mod(divisorFive).intValue(); // value from 0 to 4
    	int key = 0;
    	Node nodeToReturn = null;
    	
    	switch (remainder) // Selects level to return to
    	{
	    	case 0:
	    		key = 1;
	    		break;
	    		
	    	case 1:
	    		key = 1;
	    		break;
	    		
	    	case 2:
	    		key = 2;
	    		break;
	    		
	    	case 3:
	    		key = 3;
	    		break;
	    		
	    	case 4:
	    		key = 4;
	    		break;
    	}
    	
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
