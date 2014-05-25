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
    	
    	intersections.add(new Node(initialSeed, 1, ""));
    	currentParents.add(intersections.get(0));
    }
    
    public void generateLevel()
    {
    	ArrayList <Node> newParents = new ArrayList<Node>();
    	
    	//System.out.println(" ");
    	
    	for (Node parent : currentParents)
    	{
    		
    		//System.out.println(parent.getLevel());
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
    
    /*public void generateGraph()
    {
    	
		ArrayList <Node> parents = new ArrayList<Node>();
		ArrayList <Node> tempParents = null;
		Node child;
		
		// create initial node
    	int seed = seedFunction(12);
    	parents.add(new Node(seed, 1,""));    	
		
		for (int contLevels = 0; contLevels < 3; contLevels++) // number of levels to create (4)
    	{		
			System.out.println("Nuevo nivel");	
			tempParents = new ArrayList<Node>();
    			
			for (int contParents = 0; contParents < parents.size(); contParents++) // goes over each parent
			{					
				Node parent = parents.get(contParents);					
				int numbOfChildren = getNumbOfNextIntersections(parent.getSeed());
				
				System.out.println("PADRE: " + "seed:" + parent.getSeed() + " nivel:" + parent.getLevel());
				
				for (int contChildren = 0; contChildren < numbOfChildren; contChildren++) // create children nodes
				{
					seed = seedFunction(seed);	
					child = new Node(seed, assignLevel(parent.getLevel()), "");
					parent.addNextArc(child);
					child.setParentArc(parent);				
					tempParents.add(child);
					
					System.out.println("HIJO: " +  "seed:" + child.getSeed() + " nivel:" + child.getLevel());
				}
				
				intersections.add(parent);
			}
			
			// New generation of parents
			parents.clear();
			parents = tempParents;
    	}

	}*/
	
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
