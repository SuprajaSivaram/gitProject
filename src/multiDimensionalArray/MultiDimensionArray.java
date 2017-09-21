package multiDimensionalArray;

/**
 * @author Supraja S
 * Following is an implementation of a method to  
 * find the sum of a multi dimensional array, given an array of 
 * integers representing the index of dimensions
 * Time Complexity: O(n ^ k) where n is number of dimensions; k is the depth of the dimension
 * Space Complexity: O(n)
 */

class MultiDimensionArray {
    // This is a provided function, Assume it works
    /**
     * @param indexOfDimension
     * @return Value at the specific dimension
     * NOTE: Cannot be static because we are not specifying which object the method is operating on 
     * Or else the object can be passed as parameter
     */

    /* GIVEN METHOD:
    public static Long getValue(int... indexOfDimension) {
        //... 
        return value;
    }
    */

    /** REMOVING STATIC
     * @param indexOfDimension
     * @return Value at the specific dimension
     */
    public Long getValue(int... indexOfDimension) {
        //... 
        return value;
    }
    
    
    // lengthOfDeminsion: each dimension's length, assume it is valid: lengthOfDeminsion[i]>0.
    public static Long sum(MultiDimensionArray mArray, int[] lengthOfDimension) { 
    
    	int lenOfDim = lengthOfDimension.length - 1;
    	/* Create a buffer of size lenOfDim.
    	 * This will be passed to the getValue method in order to operate starting with lowest dimension
    	 */
    	
    	int[] dimValue = new int[lenOfDim];
    	
    	/*
    	 * We initialize dimension to lenOfDim - 1 because we want to iterate over the lowest dimension
    	 */
    	int dimension = lenOfDim-1;
    	
    	Long sum = 0L;
    	
    	/*
    	 * Iterate through all dimensions
    	 */
    	while (dimension >= 0) {
    		/*
    		 * Iterate through unvisited lowest dimension in a loop and add to sum
    		 */
    	    for (dimValue[lenOfDim-1] = 0; dimValue[lenOfDim-1] < dimValue[lenOfDim-1]; dimValue[lenOfDim-1]++) {
    	        
    	    	sum += getValue(dimValue);
    	    }
    	    /*
    	     * Check if all dimensions have been visited (dimension > 0)
    	     * Also check if the buffer index is at the last tuple of the dimention being looped
    	     * If so, reset the buffer indices prior to current dimension back to 0 -> this prepares the buffer for next iteration
    	     */
    	    while (dimension >= 0 && ++dimValue[dimension] == dimValue[dimension]) {
    	    	
    	    	dimValue[dimension--] = 0;
    	    }
    	    /*
    	     * Skip the last unvisited dimension and take the indices till the dimension before the last one 
    	     * and start next iteration
    	     */
    	    if (dimension >= 0){
    	    	
    	    	dimension = lenOfDim-2;
    	    }
    	}
    	
    return sum;
    }
}