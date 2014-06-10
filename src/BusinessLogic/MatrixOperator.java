package BusinessLogic;

import java.util.ArrayList;

public class MatrixOperator {
	
	private static boolean [][] calculateTransitiveMat(boolean [][] adjMat, int size)
    {
    	boolean [][] transitiveMat = new boolean[size][size];
    	
    	// Copy adjacency mat into transitive closure mat
    	for (int indexI = 0; indexI < size; indexI++)
        {
            for (int indexJ = 0; indexJ < size; indexJ++)
            {
            	transitiveMat[indexI][indexJ] = adjMat[indexI][indexJ];
            }
        }
    	
        for (int indexK = 0; indexK < size; indexK++)
        {
            for (int indexM = 0; indexM < size; indexM++)
            {
                for (int indexN = 0; indexN < size; indexN++)
                {
                	transitiveMat[indexM][indexN] = (transitiveMat[indexM][indexN] || (transitiveMat[indexM][indexK] && transitiveMat[indexK][indexN]));
                }
            }
        }
        
    	return transitiveMat;
    }
	
	private static boolean [][] calculateAdjacencyMat(ArrayList<Node> subGraph)
    {
    	// Create adjacency matrix
		int size = subGraph.size();
		boolean [][] adjMat = new boolean[size][size];
		
		for (int iIndex = 0; iIndex < size; iIndex ++)
		{
			Node tempNodeIzq = subGraph.get(iIndex);
			for (int jIndex = iIndex + 1; jIndex < size; jIndex ++)
    		{
				Node tempNodeDer = subGraph.get(jIndex);
    			for (Node arc : tempNodeIzq.getNextArcs())
    			{
    				if (arc.getSeed().equals(tempNodeDer.getSeed()))
    				{
    					adjMat[iIndex][jIndex] = true;
    					break;
    				}
    			}
    		}
		}
		
		return adjMat;
    }
	
	private static int calculateTransitiveClosure(Node localSol)
    {
    	int transitiveClosure = 0;
    	int matSize = 0;
    	ArrayList<Node> subGraph = new ArrayList<Node>();//Loads 3 levels
    	
    	//Generate subGraph
		subGraph.clear();
		subGraph.add(localSol);
		
		for(Node nextArc : localSol.getNextArcs())
		{
			subGraph.add(nextArc);
			for (Node nextNextArc : nextArc.getNextArcs())
			{
				subGraph.add(nextNextArc);
			}
		}  

		matSize = subGraph.size();
		
		// Adjacency matrix
		boolean [][] adjMat = calculateAdjacencyMat(subGraph);
		int contAdjMat = 0;
		
		for (int iIndex = 0; iIndex < matSize; iIndex++)
		{
			for (int jIndex = 0; jIndex < matSize; jIndex++)
			{
				if (adjMat[iIndex][jIndex] == true)
					contAdjMat ++;
			}
		}
		
		// Transitive Closure
		boolean [][] transitiveMat = calculateTransitiveMat(adjMat, subGraph.size());
		int contTransitiveMat = 0;
		
		for (int iIndex = 0; iIndex < matSize; iIndex++)
		{
			for (int jIndex = 0; jIndex < matSize; jIndex++)
			{
				if (transitiveMat[iIndex][jIndex] == true)
					contTransitiveMat ++;
			}
		}
		
		//Calculate trans. closure
		if (contAdjMat == contTransitiveMat)
			transitiveClosure = 0;
		
		else
			transitiveClosure = contTransitiveMat;
		
		return transitiveClosure;
    }
	
	public static int getBestTransitiveClosure(Node firstCadidate, Node secondCandidate)
    {
    	try
    	{
	    	int firstTransClosure = calculateTransitiveClosure(firstCadidate);
	    	int secondTransClosure = calculateTransitiveClosure(secondCandidate);
	    	
	    	if (firstTransClosure == 0)
	    		return 1;
	    	
	    	if (secondTransClosure == 0)
	    		return 2;
	    	
	    	if (firstTransClosure > secondTransClosure)
	    		return 1;
	    	
	    	else 
	    		return 2;
    	}
    	
    	catch(Exception e)
    	{
    		System.out.println("ERROR: MatrixOperator.getBestTransitiveClosure() failure");
    		return 0;
    	}    		
    }

}
