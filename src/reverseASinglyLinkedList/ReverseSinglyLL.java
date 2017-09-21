package reverseASinglyLinkedList;

import static org.junit.Assert.assertEquals;
import java.util.HashMap;
import org.junit.Test;

/**
 * @author Supraja S
 * Following is an implementation of reversing a linked list
 * Time complexity:  O(n)
 * Space complexity:  O(1)
 */

public class ReverseSinglyLL {
	
	/*
	 * Node represents each node in the linked list  
	 * Each node holds next node object and an integer value
	 */
	class Node{
		 Node next;
		 Integer value;
		 
		 Node (Integer d) {
	            value = d;
	            next = null;
	        }
		}
	
	/**
	 * Method to reverse the linked list
	 * @param head - Takes in a head node of the linked list
	 * @return head of reversed list 
	 */
	public Node reverse(Node head){
		/*
		 * If head is null, return head. There is no linked list.
		 */
		if (head == null) {
			return head;
		}
		
		/*
		 * If head->next is null, there is just one element in the list. So return head.
		 */
		if (head.next == null) {
			return head;
		}
		
		/*
		 * Make a copy of the head and walk through the list using the copy. 
		 * tempNode to assist in swapping.
		 */
		Node node = head;
		Node nextNode = head.next;
		Node tempNode = null;
			
		/* While a LL is reversed, head becomes the last node
		 * So, head->next = null
		 */
		head.next = null;
		
		/* Move through the list and swap pointers at every node.*/
		while (nextNode != null) {
			tempNode = nextNode.next;
			nextNode.next = node;
			node = nextNode;
			nextNode = tempNode;
		}
		/*Return node as the new head node*/
		return node;
	}
		
		 			    
	 /**
	 * Returns generated test data in a HashMap mapping test input with expected output for every case
	 * @return HashMap<Node,Node>
	 * @throws Exception
	 */
	protected HashMap<Node,Node> generateTestData() throws Exception {

	    HashMap <Node,Node> listOfNodes = new HashMap <Node,Node> ();
		ReverseSinglyLL obj = new ReverseSinglyLL();

		//Test 1 - Normal positive case with 4 nodes
		Node list = obj.new Node(1);
	    list.next = obj.new Node(2);
	    list.next.next = obj.new Node(3);
	    list.next.next.next = obj.new Node(4);
	    
	    Node reversedList = obj.new Node(4);
	    reversedList.next = obj.new Node(3);
	    reversedList.next.next = obj.new Node(2);
	    reversedList.next.next.next = obj.new Node(1);
	    
	    listOfNodes.put(list, reversedList);
	    
	    //Test 2 - Two nodes with same values
	    list = obj.new Node(1);
	    reversedList = obj.new Node(1);
	    listOfNodes.put(list, reversedList);
	    
	    //Test 3 - Null list
	    list = null;
	    reversedList = null;
	    listOfNodes.put(list, reversedList);
	    
	    //Test 4 - List with a node holding null value
	    list = obj.new Node(null);
	    list.next = obj.new Node(2);
	    
	    reversedList = obj.new Node(2);
	    reversedList.next = obj.new Node(null);

	    listOfNodes.put (list, reversedList);
   
	    //HashMap containing a map of all generated test data
	    return listOfNodes;
	}
		
	
	/**
	 * Receives a HashMap containing generated input-expected output combos
	 * Tests each of them against the implemented reverse method
	 * @throws Exception
	 */
	@Test
	public void testReverseList() throws Exception {
			    
	    HashMap<Node, Node> listOfNodes = generateTestData();
	    for (Node n: listOfNodes.keySet()) {
	    	assertEquals (true, testListsAreEqual(reverse(n), listOfNodes.get(n)));
	    }
	    
	}
	
	
	/**
	 * Compares if two lists are equal by walking through the reversed list and the expected output
	 * Returns true if they are equal; false otherwise
	 * @param list
	 * @param reversedList
	 * @return boolean
	 */
	public boolean testListsAreEqual(Node list, Node reversedList){
		if (list == null){
			return (list==reversedList);
		}
		while (list.next != null) {
	        if (list.value != reversedList.value) {
	        	return false;
	        }
	        list = list.next;
	        reversedList = reversedList.next;
	    }
		return true;
	}
}
