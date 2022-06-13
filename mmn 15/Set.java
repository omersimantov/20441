/**
 * mmn 15 — Linked Lists
 *
 * @author Omer Siman Tov
 * @version 8/6/2022
 */

public class Set {
    // Instance variables
    private IntNode _head;
    private IntNode _tail;
    private int _numOfElements;


    // Constructor
	/**
	 * Creates a new Set object.
	 */

    public Set() {
        _head = null;
        _tail = _head; // Initialize tail to point to the head
        _numOfElements = 0;
    }


    // Methods
    /**
     *
     * This method checks if the set is empty.
     *
     * Time complexity: 
     * O(1) — a set number of operations are being done.
     * 
     * @return True if the set is empty or false if not.
     *
     */

    public boolean isEmpty() {
        return _head == null;
    }

    /**
     *
     * This method checks if a given integer is a member of the set.
     *
     * Time complexity:
     * O(n) — The method goes through the set element by element in a linear fashion to check if the integer is a member of it.
     * 
     * Space complexity:
     * O(1) — Variables are being declared with no regard to the input size.
     * 
     * @return True if the integer is a member of the set or false if not.
     *
     */

    public boolean isMember(int num) {
        IntNode temp = _head;

        // Go through the set element by element and check if num is a member of it
        while (temp != null) {
            if (num == temp.getValue()) return true;
            temp = temp.getNext();
        }

        return false;
    }

    /**
     *
     * This method checks if a given set is equal to this set.
     *
     * Time complexity:
     * O(n) — The method simultaneously goes through both of the sets in a linear fashion and 
     * compares each pair of integers to check if the values are equal.
     * It does so based on the fact that the sets are sorted to begin with and the values go from small to large.
     * 
     * Space complexity:
     * O(1) — Variables are being declared with no regard to the input size.
     * 
     * @return True if the sets are equal or false if not.
     *
     */

    public boolean equals(Set other) {
        // If the number of elements in the sets are not equal, the sets are not equal
        if (numOfElements() != other.numOfElements()) return false;
        // If both of the sets are empty, they are equal
        if (isEmpty() && other.isEmpty()) return true;
         // Knowing that at least one set is not empty, check if the other set is empty
        if (isEmpty() || other.isEmpty()) return false;

        IntNode temp1 = _head; // temp variable for this set
        IntNode temp2 = other._head; // temp variable for the passed set

        /* Given that the sets are sorted from small to large integers and are of the same size, 
        compare them element by element to find out if the sets are equal. */
        while (temp1 != null && temp2 != null) {
            // If there is even one inequality here, the sets are not equal
            if (temp1.getValue() != temp2.getValue()) return false;

            temp1 = temp1.getNext();
            temp2 = temp2.getNext();
        }

        return true;
    }

    /**
     *
     * This method returns the number of elements in the set.
     *
     * Time complexity:
     * O(1) — a set number of operations are being done.
     * 
     * @return An integer that represents the number of the elements in the set.
     *
     */

    public int numOfElements() {
       return _numOfElements;
    }

    /**
     *
     * This method checks if a given set is a subset of this set.
     *
     * Time complexity:
     * O(n^2) — The method goes through the passed set element by element in a linear fashion to check if there is an 
     * element in it which is not a member of this set.
     * Each one of these checks is being done in a linear fashion as well, which means that the time complexity 
     * is O(n)*O(n) = O(n^2).
     * 
     * Space complexity:
     * O(1) — Variables are being declared with no regard to the input size.
     * 
     * @param other The set to be checked if it is a subset of this set.
     * 
     * @return True if the passed set is a subset of this set or false if not.
     *
     */

    public boolean subSet(Set other) {
        // If a given set is empty, it's a subset of any set
        if (other.isEmpty()) return true;

        IntNode temp = other._head;

        while (temp != null) {
            //  If even one element in other is not a member of this set then other is not a subset of this set
            if (!isMember(temp.getValue())) return false;
            temp = temp.getNext();
        }

        return true;
    }

    /**
     *
     * This method adds a given element to the set while keeping the set sorted from small to large integers, 
     * and while making sure only valid integers are being added.
     *
     * Time complexity:
     * O(n) — In the worst case, the method will go through the entire set 3 times, which means 3*O(n) = O(3n) -> O(n).
     * 
     * Space complexity:
     * O(1) — Variables are being declared with no regard to the input size.
     * 
     * @param x The integer value of the element that should be added to the set.
     *
     */

    public void addToSet(int x) {
        // If there's already an element with the value x in the set, or if x is not valid, don't do anything
        if (isMember(x) || x<1 || x%2 == 0) return;
        // If the set is empty, a new element with the value x will be the head and the tail of the set
        if (isEmpty())  {
            _head = new IntNode(x, null);
            _tail = _head;
        }
        // If the set is not empty, find where to add the new element inside the set so that it remains sorted
        else {
            // If x is smaller than the head of the set, replace the head with a new element with the value x and the current head as its next
            if (_head.getValue() > x)
                _head = new IntNode(x, _head);
            // If x is larger than the head of the set, go through the set element by element to find where to add the new element so that the set remains sorted
            else {
                IntNode temp = _head;

                while (temp.getNext() != null && temp.getNext().getValue() < x)
                    temp = temp.getNext();
                
                temp.setNext(new IntNode(x,temp.getNext()));
            }

            // Update the value of tail
            IntNode temp = _head;
            _tail = _head;

            while (temp != null) {
                if (temp.getNext() == null)
                    _tail = temp;

                temp = temp.getNext();
            }
        }

        // Knowing the at this point an element was added, increment the number of elements in the set by 1
        _numOfElements++;
    }

    /**
     *
     * This method removes a given element from the set.
     *
     * Time complexity:
     * O(n) — The method performs two linear searches in an attempt to find the element to be removed.
     * This means 2*O(n) = O(2n) -> O(n).
     * 
     * Space complexity:
     * O(1) — Variables are being declared with no regard to the input size.
     * 
     * @param x The integer value of the element that should be removed from the set.
     *
     */

    public void removeFromSet(int x) {
        // If there is no element with the value x in the set, don't do anything
        if (!isMember(x)) return;

        /* If the first element in the set is the one to be removed, replace the head with the next element, 
        reduce the number of elements counter by 1 and finish. */
        if (_head.getValue() == x) _head = _head.getNext();
        else {
            IntNode temp = _head;

            /* Linearly search for the element that should be removed. Once it's found —
            replace its predecessor's next element with its own next element, reduce the number of elements counter by 1 and finish. */
            while (temp != null) {
                if (temp.getNext() != null && temp.getNext().getValue() == x)
                    temp.setNext(temp.getNext().getNext());
    
                temp = temp.getNext();
            }
        }

        // Update the value of tail
        IntNode temp = _head;
        _tail = _head;

        while (temp != null) {
            if (temp.getNext() == null)
                _tail = temp;

            temp = temp.getNext();
        }

        // Knowing the at this point an element was deleted, reduce the number of elements in the set by 1
        _numOfElements--;
    }

    /**
	 * This method returns a string that represents this set.
     * 
     * Time complexity:
     * O(n) — The method goes through the set element by element in a linear fashion to build the string.
     * 
     * Space complexity:
     * O(1) — Variables are being declared with no regard to the input size.
	 *
	 * @return a string that represents this set in the following format -> 
     * {x, x, x, x} while x being the different integer values of the sorted set.
     * 
	 */

    public String toString() {
        if (isEmpty()) return "{}";

        IntNode temp = _head;
        String setString = "{" + temp.getValue();

        while (temp.getNext() != null) {
            setString += ("," + temp.getNext().getValue());
            temp = temp.getNext();
        }

        return setString += "}";
    }

    /**
	 * This method returns the intersection set of a given set with this set.
     * 
     * Time complexity:
     * O(n) — The method goes through both of the sets simultaneously in a linear fashion to find the intersection.
     * 
     * Space complexity:
     * O(n) — New variables are being declared inside the addToEnd helper function every time a new 
     * element is being added to the intersection set, which in the worst case happens n times.
     * 
     * @param other The set to check for intersection with this set.
	 *
	 * @return a new Set object that represents the intersection set of this set and the set passed as a parameter.
     * 
	 */

    public Set intersection(Set other) {
        Set intersectionSet = new Set();

        // If one of the sets is empty, there is no intersection, return an empty set
        if (isEmpty() || other.isEmpty()) return intersectionSet;

        IntNode temp1 = _head;
        IntNode temp2 = other._head;

        // Go through both of the sets simultaneously and look for equalities to add to the intersectionSet
        while (temp1 != null && temp2 != null) {
            if (temp1.getValue() == temp2.getValue()) {                
                intersectionSet.addToEnd(temp1);
                temp1 = temp1.getNext();    
                temp2 = temp2.getNext();
            }
            
            else if (temp1.getValue() > temp2.getValue())
                temp2 = temp2.getNext();
            
            else if (temp1.getValue() < temp2.getValue())
                temp1 = temp1.getNext();
        }

        return intersectionSet;
    }

    /**
	 * This method returns an union set of this set and the set passed as a parameter.
     * 
     * Time complexity:
     * O(n^2) — The method goes through the other set element by element in a linear fashion and for each element, checks if it
     * is not a member of this set, which costs O(n) per check. This means that O(n)*O(n) = O(n^2).
     * 
     * Space complexity:
     * O(n) — New variables are being declared inside the addToEnd helper function every time a new 
     * element is being added to the intersection set, which in the worst case happens n times.
     * 
     * @param other The set to check for union with this set.
	 *
	 * @return a new Set object that represents the union set of this set and the set passed as a parameter.
     * 
	 */

    public Set union(Set other) {
        // The union set has all the elements of this set, so first initialize the union set with this set
        Set unionSet = this;

        IntNode temp = other._head;

        /* Go through the other set element by element and for each element, check if it
        is not a member of this set, then add it to the union set if not */
        while (temp != null) {
            if(!unionSet.isMember(temp.getValue()))
                unionSet.addToEnd(temp);

            temp = temp.getNext();
        }

        return unionSet;
    }

    /**
     * 
     * This method checks which elements are members of this set and not members of a given other set.
     *
     * Time complexity:
     * O(n^2) — The method goes through this set element by element in a linear fashion and for each element, checks if it
     * is a member of the other set, which costs O(n) per check. This means that O(n)*O(n) = O(n^2).
     * 
     * Space complexity:
     * O(n) — New variables are being declared inside the addToEnd helper function every time a new 
     * element is being added to the intersection set, which in the worst case happens n times.
     * 
     * @param other The set that should be checked for elements that are not in this set.
     * 
     * @return A new Set object that represent the set of elements that are in this set and not in the other set.
     *
     */

    public Set difference(Set other) {
        Set differenceSet = new Set();

        IntNode temp = _head;

        /* Go through this set element by element and for each element, check if it
        is not a member of the other set, then add it to the difference set if not. */
        while (temp != null) {
            if(!other.isMember(temp.getValue()))
                differenceSet.addToEnd(temp);

            temp = temp.getNext();
        }

        return differenceSet;
    }

    /**
     *
     * This is private helper method that adds an element to the end of the set.
     * 
     * Time complexity:
     * O(1) — a set number of operations are being done.
     * 
     * Space complexity:
     * O(1) — Variables are being declared with no regard to the input size.
     * 
     * @param newTail The element to be added to the end of the set.
     * 
     */

    private void addToEnd(IntNode newTail) {
        // If the set is empty, add the element to the head and set the tail to the head
        if (_head == null) {
            _head = new IntNode(newTail.getValue(), null);
            _tail = _head;
        }
        // If the set is not empty, set the element after the tail to be the new element, then set the tail to be the new element
        else {
            _tail.setNext(new IntNode(newTail.getValue(), null));
            _tail = _tail.getNext();
        }

        // Increment the number of elements in the set by 1
        _numOfElements++;
    }
    // End of methods
}