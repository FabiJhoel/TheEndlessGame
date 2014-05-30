package BusinessLogic;

import java.util.ArrayList;
import java.math.BigInteger;

public class PathGraph {
	
	private ArrayList<Node> intersections;
	private ArrayList <Node> currentParents = new ArrayList<Node>();

	public PathGraph()
    {
		intersections = new ArrayList<Node>();
    }
	
	public ArrayList<Node> getIntersections() {
		return intersections;
	}
	
	public void addIntersection(Node pNode){
		
		intersections.add(pNode);
	}

	public Node findNode (BigInteger nodeID){

        for (int cont = 0; cont < intersections.size(); cont++)
        {
            if (intersections.get(cont).getSeed() == nodeID)
                return intersections.get(cont);
        }

        return null;
    }
	
	public void removeNode (Node pIntersection)
    {
		// Remove arcs that contain this Node
        for (int contNodes = 0; contNodes < intersections.size(); contNodes++)
        {
            for (int contArcs = 0; contArcs < intersections.get(contNodes).getNextArcs().size(); contArcs++) 
            {
            	Node tempNode =  (Node) intersections.get(contNodes).getNextArcs().get(contArcs);
                
            	if ( tempNode.getSeed() == pIntersection.getSeed())
            		intersections.get(contNodes).getNextArcs().remove(contArcs);
            }
        }
        
        // Remove specified Node from graph
        intersections.remove(pIntersection);
    }
	
    // visits every arc of a specific node
    public void visitAdjacentNodes(Node pNode)
    {
        visitIntersection(pNode);
        System.out.print(pNode.getSeed()+"  ");

        // for each arc
        for (int cont = 0; cont < pNode.getNextArcs().size(); cont++)
        {
            // mark as visited
            if (isNodeVisited((Node) pNode.getNextArcs().get(cont)) == false)
            {
            	Node tempNode = (Node) pNode.getNextArcs().get(cont);
            	visitAdjacentNodes(findNode(tempNode.getSeed()));
            }
        }
    }

    public void visitIntersection(Node pNode)
    {
        for (int i = 0; i < intersections.size(); i++) {
            if (pNode.getSeed() == intersections.get(i).getSeed())
            	intersections.get(i).setVisited(true);
        }
    }

    public boolean isNodeVisited(Node pNode)
    {
        for (int i = 0; i < intersections.size(); i++) {
            if (pNode.getSeed() == intersections.get(i).getSeed())
                return intersections.get(i).getVisited();
        }
        
        // if is not visited
        return false;
    }
    
    public void setInitialIntersection()
    {
    	BigInteger initialSeed = BigInteger.valueOf(6);
    	currentParents.add(new Node(initialSeed, 1, ""));
    }
    
    public void generateLevel()
    {
    	ArrayList <Node> newParents = new ArrayList<Node>();
    	
    	//System.out.println(" ");
    	
    	for (Node parent : currentParents)
    	{
    		parent.generateAdjacents();
    		intersections.add(parent);
    		
    		// Set new parents
    		for (Node newParent : parent.getNextArcs())
    		{
    			newParents.add(newParent);
    		}
    	}
    	
    	// New generation of parents
    	currentParents.clear();
    	currentParents = newParents;
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
	
	public void graphToString ()
    {
        for (int i = 0; i < intersections.size(); i++)
        {
            System.out.print("Nodo "+intersections.get(i).getLevel()+":  " + "Padre: "+ intersections.get(i).getParentArc().getLevel());        
            
            for (int j = 0; j < intersections.get(i).getNextArcs().size(); j++) 
            {
            	Node tempNode =  (Node) intersections.get(i).getNextArcs().get(j);
                System.out.print("Siguientes: "+ tempNode.getLevel() +"  ");
            }
            
            System.out.println("");
        }
    }

}
