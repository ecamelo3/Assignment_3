import java.util.ArrayList;
import java.util.ListIterator;
import java.util.NoSuchElementException;
//import BasicDoubleLinkedListTest.Car;

/**
 * @author Emma Camelo
 * 
 *  Professor Monshi, CMSC204 3/8/2021 Spring 2021
 * This class implements the basic double linked list
 * @param <T>
 */

public class BasicDoubleLinkedList<T> {
	
	protected Node head; //first Node
	protected Node tail; //last Node
	protected int size;

    public BasicDoubleLinkedList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}
	
	
	public class Node
	{
		protected Node next;
		protected Node prev;
		protected T data;
		
		public Node(Node prev, T data, Node next) {
			this.next = next;
			this.prev = prev;
			this.data = data;
		}
	}
	
	/**
	 * @return the value of the instance variable to keep track of size
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * Adds an element to the END of the list. (NO ITERATORS!)
	 * @param data - data from Node in Linked list
	 * @return reference to current object
	 */
	public BasicDoubleLinkedList<T> addToEnd(T data)
	{
		size++;
		
		if(tail == null) {
			Node newNode = new Node(null, data, null);
			head = tail = newNode;
			tail.next = tail;
			
			return this;
		}
		else
		{
			Node newNode = new Node(null, data, null);	
			newNode.prev = tail;
			newNode.next = null;
			tail.next = newNode;
			tail = newNode;
			
			return this;
		}
	}
	
	/**
	 * Adds an element to the FRONT of the list. (NO ITERATORS!)
	 * @param data - data from Node in Linked list
	 * @return reference to current object
	 */
	public BasicDoubleLinkedList<T> addToFront(T data)
	{
		size++;
		
		if(head == null) {
		    Node newNode = new Node(null, data, null);
			head = tail = newNode;
			head.next = head;
			
			return this;
		}
		else
		{
			Node newNode = new Node(null, data, null);
				
			newNode.next = head;
			newNode.prev = null;
			head.prev = newNode;
			head = newNode;
			
			return this;
		}
	}
	
	/**
	 * @return  the 1st element (or null if none) from the list
	 */
	public T getFirst() {
		return head.data;		
	}

	/**
	 * @return the last element (or null if none) from the list
	 */
	public T getLast() {
		return tail.data;
	}
	
	/**
	 * This method must be implemented using an inner class that implements ListIterator
	 * and defines the methods of hasNext(), next(), hasPrevious() and previous().
	 * Remember that we should be able to call the hasNext() method as many times
	 * as we want without changing what is considered the next element. 
	 * @return the data element/null after the iterator
	 */
	public ListIterator<T> iterator() throws UnsupportedOperationException, NoSuchElementException
	{
		return new ListIterator<T>()
		{
			Node temp;			
			Node pointer = head; 
			int x = 0;

			@Override
			public boolean hasNext() {
				return pointer != null;
			}
			
			@Override 
			public T next() throws NoSuchElementException{
				if(hasNext() == false)
					throw new NoSuchElementException();
				else
				{
					
					
					temp = pointer;
					if(pointer.next != null)
						pointer = pointer.next;
					
					//Pointer.next should be null when its 2nd to last element, and last element
					//3rd time it is null it should throw a NoSuchElementException
					if(pointer.next == null) {
						if(x > 1) {
							x = 0;
							throw new NoSuchElementException();
						}
						x++;
					}
					
					return temp.data;
				} 
			}

			@Override
			public boolean hasPrevious() {
				return pointer != null;
			}

			@Override
			public T previous() throws NoSuchElementException{
				if(hasPrevious() == false)
					throw new NoSuchElementException();
				else
				{
					temp = pointer;
					pointer = pointer.prev;
					return temp.data;
				} 
			}


			public int nextIndex() throws UnsupportedOperationException{
				throw new UnsupportedOperationException();
			}
			
			public int previousIndex() throws UnsupportedOperationException {
				throw new UnsupportedOperationException();
			}

			public void remove() throws UnsupportedOperationException {
				throw new UnsupportedOperationException();
			}

			public void set(Object e) throws UnsupportedOperationException {
				throw new UnsupportedOperationException();
			}

			public void add(Object e) throws UnsupportedOperationException {
				throw new UnsupportedOperationException();				
			}
		};
	}
	
	/**
	 * @param targetData
	 * @param comparator
	 * @return data element or null
	 */
	public BasicDoubleLinkedList<T> remove(T targetData, java.util.Comparator<T> comparator)
	{
		if(head == null)
			return null;
		else if(head == tail)
		{
			head = tail = null;
			return this;
		}
		else if(head.data == targetData)
		{
			Node temp = head;
			head = head.next; 
			temp.next = null;
			head.prev = null;
			
			size--;
		
			return this;
		}
		else if(tail.data == targetData)
		{
			Node temp = tail;
			tail = tail.prev;
			
			temp.next = null;
			tail.next = null;
			
		
			size--;
			return this;
		}
		else
		{
			Node search = head;
			
			while(search.data != null) {
				if(comparator.compare(search.data, targetData) == 0)
					break;
				search = search.next;
			}
			
			search.next.prev = search.prev; //search.next.prev is the next node's previous location
			search.prev.next = search.next; //search.prev.next is the next node's next  location
			
			size--;
			return this;
		}	
	}
	
	/**
	 * REMOVES AND RETURNS the 1st element from the list
	 * @return data element/null
	 */
	public T retrieveFirstElement() 
	{
		if(head == null) {
			return null;
		}
		else if(head == tail) {
			head = tail = null;
			return null;
		}
		else {
			Node temp = head;
			head = head.next; 
			temp.next = null;
			head.prev = null;
			
			size--;		
			return temp.data;
		}			
	}
	
	/**
	 * REMOVES AND RETURNS the last element from the list
	 * @return data element/null
	 */
	public T retrieveLastElement() {
		if(tail == null) {
			return null;
		}
		else
		{
			Node temp = tail;
			tail = tail.prev;
			
			temp.next = null;
			tail.next = null;
			
			size--;
			return temp.data; //why is this null?
		}
	}
	
	/**
	 * @return arraylist of list items from head to tail
	 */
	public ArrayList<T> toArrayList(){
		ArrayList<T> x = new ArrayList<T>();
		Node temp = head;
		
		while(temp != null) {
			x.add(temp.data);
			temp = temp.next;
		}
		
		return x;
	}
}
