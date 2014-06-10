package BusinessLogic;

import java.math.BigInteger;

public class SeedOperator {
	
	private static SeedOperator instance = null;
	private BigInteger seed = BigInteger.valueOf(6);
	private final BigInteger m = BigInteger.valueOf((long)Math.pow(2, 60) + 1);//7
	private final BigInteger a = BigInteger.valueOf(9301);//3
	private final BigInteger c = BigInteger.valueOf(49297);//5
	
	protected SeedOperator() 
	{
	      // Exists only to avoid instantiation.
	}
	
	public static SeedOperator getInstance() 
	{
	      if(instance == null) {
	         instance = new SeedOperator();
	      }
	      return instance;
	}

	// Used to generate seed from a specific point (return paths)
	public void setInitialSeed(BigInteger seedVal) 
	{
		try
		{
			seed = seedVal;
		}
		catch(Exception e)
    	{
    		System.out.println("ERROR: SeedOperator.setInitialSeed() failure");
    	}
	}
	
	// Gets seed according to its ID 
	public BigInteger getSpecificSeed(long pos)
	{
		try
		{
			BigInteger tempSeed = BigInteger.valueOf(6);
			
			while ((pos - 1) != 0L) 
			{
				tempSeed = tempSeed.multiply(a);
				tempSeed = tempSeed.add(c);
				tempSeed = tempSeed.mod(m);	
				
				pos --;
			}
			
			return tempSeed;
		}
		catch(Exception e)
    	{
    		System.out.println("ERROR: SeedOperator.getSpecificSeed() failure");
    		return null;
    	}
	}

	public int getNumbOfNextIntersections(BigInteger parentSeed, int level)
    {
		try
		{
			BigInteger divisorTwo = BigInteger.valueOf(2);
			BigInteger divisorThree = BigInteger.valueOf(3);
			
			// Level three nodes only have 2 or 3 children
			if (level == 3)
			{
				if (parentSeed.mod(divisorThree).equals(BigInteger.ZERO)) 
					return 2;
				
				else 
					return 3;
			}
			
			else
			{
				if (parentSeed.mod(divisorTwo).equals(BigInteger.ZERO)) 
					return 1;
				
				if (parentSeed.mod(divisorThree).equals(BigInteger.ZERO)) 
					return 2;
				
				else 
					return 3;
			}	
		}
		catch(Exception e)
    	{
    		System.out.println("ERROR: SeedOperator.getNumbOfNextIntersections() failure");
    		return 0;
    	}
    }
	
	public BigInteger getNewSeed()
    {
		try
		{
			seed = seed.multiply(a);
	    	seed = seed.add(c);
	    	seed = seed.mod(m);
	    	
	    	return seed;
		}
		catch(Exception e)
    	{
    		System.out.println("ERROR: SeedOperator.getNewSeed() failure");
    		return null;
    	}
    }
}
