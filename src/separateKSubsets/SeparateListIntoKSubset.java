package separateKSubsets;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

/**
 * @author Supraja S
 * Separates a list of integers into 'k' groups
 * such that each group has equal sum
 * Time Complexity: O(n ^ k)
 * Space complexity: O(n)
 */
public class SeparateListIntoKSubset {
	
	/**
	 * @param listOfNumbers
	 * @param k
	 * @return true if list can be separated into k groups; false otherwise
	 * This method can be augmented to also store/print the specific subsets by 
	 * adding an int array with size as the input list. This would help in maintaining 
	 * the subset in which each item is put in.
	 */
	static boolean separate (List<Integer> listOfNumbers, int k){
		
		/*n - Number of items on the input list*/
		int n = listOfNumbers.size();
		
		/*If number of elements in input list is lesser than the number of subsets required,
		 * separation is not possible
		 * return false
		 */
		if (n<k) {
			return false;
		}
		
		/*If number of elements in input list is lesser than the number of subsets required,
		 * separation is not possible
		 * return false
		 */
		if (k<=0) {
			return false;
		}
		/*
		 * If the number of groups to separate is just one, return true
		 */
		if (k==1) {
			return true;
		}
		
		/*
		 * sumOfArray holds the total sum of all the numbers in the list
		 */
		int sumOfArray = 0;
		sumOfArray = listOfNumbers.stream().mapToInt(Integer::intValue).sum();
		
		/*
		 * sumInSubset is of size of number of groups and 
		 * is initialized to the sum expected to be in each group =(sumOfArray/k)
		 */
		int sumInSubset[] = new int[k];
		
	    for (int i = 0; i < k; i++)
	    	sumInSubset[i] = sumOfArray/k;

	    /*
	     * resultFlag that returns true is all the groups have been assigned numbers 
	     * that give equal sum in each group
	     * Since we subtract from the assigned sum, we check if all groups hold 0 as the sum
	     * using calculateSubsetSum which does the separation
	     */
	    boolean resultFlag = false;
	    
	    if ( (sumOfArray % k) == 0) {
	    	if ( calculateSubsetSum(listOfNumbers, n-1, sumInSubset,k) ){
	    		resultFlag = true;
	    	}
	    }
	    
	   return resultFlag;
	}
	
	/**
	 * @param listOfNumbers
	 * @param n
	 * @param sumInSubset
	 * @param k
	 * @return true if separation was successful, false otherwise
	 * Uses the concept of backtracking to achieve the goal
	 */
	static boolean calculateSubsetSum(List<Integer> listOfNumbers, int n, int[] sumInSubset, int k){
		
		boolean resultFlag = true;
		/*
		 * check if all the k groups' sum are 0. 
		 * we assign expected sum and subtract each element from input list.
		 * so having 0 sum in all groups signifies successful completion of separation.
		 */
		for (int i=0;i <k; i++) {
			if (sumInSubset[i] != 0) {
				 resultFlag = false;
			}
		}
		/* If resultFlag is true, separation was successful.
		 * So, return the result to calling function.
		 */
		if (resultFlag)
			return resultFlag;
		
		/*
		 * If n has gone down below 0, 
		 * separation was not successful for any of the different input combinations
		 * So, return false
		 */
		if (n<0)
			return false;
		
		int i=0;
		/*
		 * Loops through the groups
		 */
		while (i<k) {
			/*
			 * If the resultFlag is not true and if the difference in the current sum of the group and 
			 * current number in input list is greater than 0,
			 * recurse by passing n=n-1 to consider other numbers for the group
			 */
			if (!resultFlag && (sumInSubset[i] - (Integer)listOfNumbers.get(n)) >= 0) {
				sumInSubset[i] = sumInSubset[i] - (Integer)listOfNumbers.get(n);
				resultFlag = calculateSubsetSum(listOfNumbers, n-1, sumInSubset, k);
				/*
				 * Backtracking to previous original sum in the group 
				 * since separation did not satisfy equal sum in k groups
				 */
				sumInSubset[i] += (Integer)listOfNumbers.get(n);
			}
			/*
			 * Continue for remaining groups
			 */
			i ++; 
		}
		/*
		 * Return resultFlag true if separation is successful and false otherwise
		 */
		return resultFlag;
	}
	
	
	/**
	 * @throws Exception
	 * Pass different input lists to the separate method and test for accuracy
	 */
	@Test
	public void testSeparateIntoKSubsets() throws Exception {
		
		/*
		 * Test 1: 
		 * Positive case with list of 4 numbers and 2 groups
		 */
		List<Integer> listOfNumbers = new ArrayList<Integer>(Arrays.asList(1,4,2,3));
	    assertEquals (true, separate(listOfNumbers, 2));
	    
	    /*
		 * Test 2: 
		 * Negative case with list of 4 negative numbers and 3 groups
		 */
	    listOfNumbers = new ArrayList<Integer>(Arrays.asList(-1,-4,-2,-3));
	    assertEquals (false, separate(listOfNumbers, 3));
	    
	    /*
		 * Test 3: 
		 * Positive case with list of 4 same numbers and 4 groups
		 */
	    listOfNumbers = new ArrayList<Integer>(Arrays.asList(1,1,1,1));
	    assertEquals (true, separate(listOfNumbers, 4));
	    
	    /*
		 * Test 4: 
		 * Negative case with list of 3 numbers and 2 groups
		 */
	    listOfNumbers = new ArrayList<Integer>(Arrays.asList(1,4,2));
	    assertEquals (false, separate(listOfNumbers, 2));
	    
	    /*
		 * Test 5: 
		 * Positive case with list of 4 numbers, all zeroes and 3 groups
		 */
	    listOfNumbers = new ArrayList<Integer>(Arrays.asList(0,0,0,0));
	    assertEquals (true, separate(listOfNumbers, 3));
	    
	    /*
		 * Test 6: 
		 * Negative case with list of 4 numbers and 3 groups
		 */
	    listOfNumbers = new ArrayList<Integer>(Arrays.asList(1,2,3,6));
	    assertEquals (false, separate(listOfNumbers, 3));
	    
	    /*
		 * Test 7: 
		 * Positive case with list of 4 numbers and 2 groups
		 */	    
	    listOfNumbers = new ArrayList<Integer>(Arrays.asList(1,2,3,6));
	    assertEquals (true, separate(listOfNumbers, 2));
	    
	    /*
		 * Test 8: 
		 * Positive case with list of 5 numbers and 2 groups
		 */	    
	    listOfNumbers = new ArrayList<Integer>(Arrays.asList(1,2,3,6,2));
	    assertEquals (true, separate(listOfNumbers, 2));
	    
	    /*
		 * Test 9: 
		 * Negative case with list of 5 numbers and 6 groups
		 */
	    listOfNumbers = new ArrayList<Integer>(Arrays.asList(1,2,3,6,2));
	    assertEquals (false, separate(listOfNumbers, 6));
	    
	    /*
		 * Test 10: 
		 * Negative case with list of 5 numbers, one being very large number and 2 groups
		 * which are not possible to be divided into 4 groups
		 */	    
	    listOfNumbers = new ArrayList<Integer>(Arrays.asList(99999999,2,3,6,2));
	    assertEquals (false, separate(listOfNumbers,4));
	    
	}
	
	
}
