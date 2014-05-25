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


	public static SeedOperator getInstance() {
	      if(instance == null) {
	         instance = new SeedOperator();
	      }
	      return instance;
	   }
	
	public int getNumbOfNextIntersections()
    {
		BigInteger divisorTwo = BigInteger.valueOf(2);
		BigInteger divisorThree = BigInteger.valueOf(3);
		
		if (seed.mod(divisorTwo).equals(BigInteger.ZERO)) 
			return 1;
		
		if (seed.mod(divisorThree).equals(BigInteger.ZERO)) 
			return 2;
		
		else 
			return 3;

    }
	
	public BigInteger getNewSeed()
    {
		seed = seed.multiply(a);
    	seed = seed.add(c);
    	seed = seed.mod(m);
    	
    	return seed;
    }
	
	/*public static BigInteger getSquareRoot(BigInteger n) 
	{
        BigInteger a = BigInteger.ONE;
        BigInteger b = new BigInteger(n.shiftRight(5).add(new BigInteger("8")).toString());
        
        while(b.compareTo(a) >= 0) 
        {
          BigInteger mid = new BigInteger(a.add(b).shiftRight(1).toString());
          if(mid.multiply(mid).compareTo(n) > 0) b = mid.subtract(BigInteger.ONE);
          else a = mid.add(BigInteger.ONE);
        }
        
        return a.subtract(BigInteger.ONE);
      }*/

}
