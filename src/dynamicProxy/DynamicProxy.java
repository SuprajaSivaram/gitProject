package dynamicProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.Test;

 
/**
 * @author Supraja S
 * Following is an implementation of Dynamic Proxy and uses the 
 * reversal of singly linked list to enunciate. 
 */
public class DynamicProxy {
 
	/*
	 * Node represents each node in the linked list  
	 * Each node holds next node object and an integer value
	 */
	class Node{
		 Node next;
		 int value;
		 
		 Node(int d) {
	            value = d;
	            next = null;
	        }
	}
	
	/*
	 * Interface that the proxy implements
	 */
    interface printReversedLL {
        void printList(Node node);
    }
    
    /*
     * Method to reverse the linked list 
     */
    public Node reverse(Node head){
		if (head == null)
			return head;
		if (head.next == null)
			return head;
		
		Node node = head;
		Node nextNode = head.next;
		Node tempNode;
		
		/*After reversal first node becomes last node.*/
		
		head.next = null;
		
		/* Move through the list and swap pointers at every node using a temporary node to assist swapping*/
		while (nextNode != null){
			tempNode = nextNode.next;
			nextNode.next = node;
			node = nextNode;
			nextNode = tempNode;
		}
		/*Return node as the new head node*/
		return node;
	}
    
    static class ReverseLL implements printReversedLL {
    	  	
        public void printList (Node node) {
        	while (node != null) {
	            System.out.print(node.value + " ");
	            node = node.next;
	        }
        }
    }
 
    /*
     * The proxy handler that implements the InvocationHandler interface
     * Holds a handle to the actual object and calls the print method on that object
     * Object of the interface is passed to the invoke method of the handler
     */
    static class Handler implements InvocationHandler {
        private final printReversedLL rev;
 
        public Handler (printReversedLL rev) {
            this.rev = rev;
        }
 
        public Object invoke(Object proxy, Method method, Object[] args)
                throws IllegalAccessException, IllegalArgumentException,
                InvocationTargetException {
           method.invoke(rev, args);
           return null;
        }
    }
    
    /*
     * Test case to drive the implementation above
     * Creates a linkedList with sample values
     * Calls printList before and after reversal
     * Common use of proxy is to perform logging before and after execution of a method. Sample illustration below
     */
    @Test
	public void testDynamicProxy() throws Exception {
			    
    	ReverseLL rev = new ReverseLL();
        Handler handler = new Handler(rev);
        printReversedLL f = (printReversedLL) Proxy.newProxyInstance(printReversedLL.class.getClassLoader(),
                new Class[] { printReversedLL.class },
                handler);
        DynamicProxy obj = new DynamicProxy();
        Node list = obj.new Node(11);
	    list.next = obj.new Node(22);
	    list.next.next = obj.new Node(33);
	    list.next.next.next = obj.new Node(44);
	    
	    f.printList (list);
	    list = obj.reverse (list);
        System.out.println ("\nReversed linked list ");
        f.printList (list);   
	}
 
     
}