/**
 * mmn 15 — Linked Lists
 * 
 * This class represents an integer node in a linked list
 *
 * @author Omer Siman Tov
 * @version 8/6/2022
 */

public class IntNode {
    // Instance variables
    private int _value;
    private IntNode _next;
    

    // Constructor
	/**
	 * Creates a new IntNode object
	 * @param v the integer value of the node
	 * @param n the next node in the linked list
	 */

    public IntNode(int v, IntNode n) {
        _value = v;
        _next = n;
    }

    
    // Methods
    /** 
     * Gets the value of the node
	 * @return an integer that represents the value of the node
     */

    public int getValue() {
        return _value;
    }

    /** 
     * Gets the the next node in the linked list
	 * @return an IntNode object that represents the next node in the linked list
     */

    public IntNode getNext() {
        return _next;
    }

    /** 
     * Sets the the value of the node
     * @param v the value to be set
     */

    public void setValue(int v) {
        _value = v;
    }
    
    /** 
     * Sets the next node of the linked list
     * @param n the IntNode object to be set as the next node
     */

    public void setNext(IntNode n) {
        _next = n;
    }
    // End of methods
}