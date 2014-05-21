package BusinessLogic;

import java.util.ArrayList;

public class PathGraph {
	
	private ArrayList<Node> intersections;

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
	
	/*public void addArc(Node origin, Node destination){
		
		if (origin != null && destination != null)
			origin.addArc(destination);
	}*/
	
	public Node findNode (int nodeID){

        for (int cont = 0; cont < intersections.size(); cont++)
        {
            if (intersections.get(cont).getIntersectionNumb() == nodeID)
                return intersections.get(cont);
        }

        return null;
    }
	
	public void removeNode (Node pIntersection)
    {
        for (int contNodes = 0; contNodes < intersections.size(); contNodes++)
        {
            for (int contArcs = 0; contArcs < intersections.get(contNodes).getPreviousArcs().size(); contArcs++) 
            {
            	Node tempNode =  (Node) intersections.get(contNodes).getPreviousArcs().get(contArcs);
                
            	if ( tempNode.getIntersectionNumb() == pIntersection.getIntersectionNumb())
                    intersections.get(contNodes).getPreviousArcs().remove(contArcs);
            }
        }

        intersections.remove(pIntersection);
    }
	
    // visits every arc of a specific node
    public void visitAdjacentNodes(Node pNode)
    {
        visitIntersection(pNode);
        System.out.print(pNode.getIntersectionNumb()+"  ");

        // for each arc
        for (int cont = 0; cont < pNode.getPreviousArcs().size(); cont++)
        {
            // mark as visited
            if (isNodeVisited((Node) pNode.getPreviousArcs().get(cont)) == false)
            {
            	Node tempNode = (Node) pNode.getPreviousArcs().get(cont);
            	visitAdjacentNodes(findNode(tempNode.getIntersectionNumb()));
            }
        }
    }

    public void visitIntersection(Node pNode)
    {
        for (int i = 0; i < intersections.size(); i++) {
            if (pNode.getIntersectionNumb() == intersections.get(i).getIntersectionNumb())
            	intersections.get(i).setVisited(true);
        }
    }

    public boolean isNodeVisited(Node pNode)
    {
        for (int i = 0; i < intersections.size(); i++) {
            if (pNode.getIntersectionNumb() == intersections.get(i).getIntersectionNumb())
                return intersections.get(i).getVisited();
        }
        
        // if is not visited
        return false;
    }
    
    public void generateGraph()
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
					parent.addNextArcs(child);
					child.addPreviousArc(parent);				
					tempParents.add(child);
					
					System.out.println("HIJO: " +  "seed:" + child.getSeed() + " nivel:" + child.getLevel());
				}
				
				intersections.add(parent);
			}
			
			// New generation of parents
			parents.clear();
			parents = tempParents;

			/*System.out.println("SIZE TEMP PARENTS:" + tempParents.size());
			System.out.println("SIZE NEW PARENTS:" + parents.size());		*/	
			
    	}

	}
    
    public int assignLevel(int parentLevel)
    {
    	int level = parentLevel + 1;
    	
    	if (level <= 4)
    		return level;
    	
    	else
    		return 1;
    }
    
    public int getNumbOfNextIntersections(int value)
    {
    	if (value >= 0 && value <= 2)
    		return 1;
    	
    	if (value == 3 || value ==4)
    		return 2;
    	
    	if (value == 5 || value == 6)
    		return 3;
    	
    	else
    		return 0;
    }
    
    public int seedFunction(int seed)
    {
    	return (seed * 3 + 5) % 7;
    }

	
	public void graphToString ()
    {
        for (int i = 0; i < intersections.size(); i++)
        {
            System.out.print("Nodo "+intersections.get(i).getLevel()+":  ");
            
            for (int j = 0; j < intersections.get(i).getPreviousArcs().size(); j++) 
            {
            	Node tempNode =  (Node) intersections.get(i).getPreviousArcs().get(j);
                System.out.print("Anteriores: "+ tempNode.getLevel() +"  ");
            }            
            
            for (int j = 0; j < intersections.get(i).getNextArcs().size(); j++) 
            {
            	Node tempNode =  (Node) intersections.get(i).getNextArcs().get(j);
                System.out.print("Siguientes: "+ tempNode.getLevel() +"  ");
            }
            
            System.out.println("");
        }
    }

}
