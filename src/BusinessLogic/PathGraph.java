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

	public void setIntersections(ArrayList<Node> intersections) {
		this.intersections = intersections;
	}
	
	public void addIntersection(Node pNode){
		
		intersections.add(pNode);
	}
	
	public void addArc(Node origin, Node destination){
		
		if (origin != null && destination != null)
			origin.addArc(destination);
	}
	
	public Node findNode (int nodeID){

        for (int cont = 0; cont < intersections.size(); cont++)
        {
            if (intersections.get(cont).getId() == nodeID)
                return intersections.get(cont);
        }

        return null;
    }
	
	public void removeNode (Node pIntersection)
    {
        for (int contNodes = 0; contNodes < intersections.size(); contNodes++)
        {
            for (int contArcs = 0; contArcs < intersections.get(contNodes).getArcs().size(); contArcs++) 
            {
            	Node tempNode =  (Node) intersections.get(contNodes).getArcs().get(contArcs);
                
            	if ( tempNode.getId() == pIntersection.getId())
                    intersections.get(contNodes).getArcs().remove(contArcs);
            }
        }

        intersections.remove(pIntersection);
    }
	
    // visits every arc of a specific node
    public void visitAdjacentNodes(Node pNode)
    {
        visitIntersection(pNode);
        System.out.print(pNode.getId()+"  ");

        // for each arc
        for (int cont = 0; cont < pNode.getArcs().size(); cont++)
        {
            // mark as visited
            if (isNodeVisited((Node) pNode.getArcs().get(cont)) == false)
            {
            	Node tempNode = (Node) pNode.getArcs().get(cont);
            	visitAdjacentNodes(findNode(tempNode.getId()));
            }
        }
    }

    public void visitIntersection(Node pNode)
    {
        for (int i = 0; i < intersections.size(); i++) {
            if (pNode.getId() == intersections.get(i).getId())
            	intersections.get(i).setVisited(true);
        }
    }

    public boolean isNodeVisited(Node pNode)
    {
        for (int i = 0; i < intersections.size(); i++) {
            if (pNode.getId() == intersections.get(i).getId())
                return intersections.get(i).getVisited();
        }
        
        // if is not visited
        return false;
    }

	
	public void graphToString ()
    {
        for (int i = 0; i < intersections.size(); i++)
        {
            System.out.print("Vertice "+intersections.get(i).getId()+":  ");
            
            for (int j = 0; j < intersections.get(i).getArcs().size(); j++) 
            {
            	Node tempNode =  (Node) intersections.get(i).getArcs().get(j);
                System.out.print(tempNode +"  ");
            }
            System.out.println("");
        }
    }

}
