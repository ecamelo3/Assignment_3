
import java.util.Comparator;
import java.util.ListIterator;

/**
 * @author Emma Camelo
 * 
 *  Professor Monshi, CMSC204 3/8/2021 Spring 2021
 * This class extends the Basic DoubleLinkedList and sorts the data
 * @param <T>
 */

public class SortedDoubleLinkedList<T> extends BasicDoubleLinkedList<T> {
	protected Comparator<T> comparator2;
	
	public SortedDoubleLinkedList(Comparator<T> comparator2) {
		super();
		head = null;
		tail = null;
		size = 0;
        this.comparator2 = comparator2;
	}
	
	/**Inserts the specified element at the correct position in the sorted list.
	 *  Notice we can insert the same element several times. Your implementation must traverse the list only once.
	 *  Do not implement this method using iterators.
	 * @param data - the data to be added to the list
	 * @return reference to current object
	 */
	public SortedDoubleLinkedList<T> add(T data)
	{
		
		Node newNode = new Node(null, data, null);
		Node nodeBefore = null;
		Node temp = head; //cast 
		
		while((temp != null) && comparator2.compare(data, temp.data) > 0){
			nodeBefore = temp;
			temp = temp.next;
		}
			
		if(head == null && nodeBefore == null) {
			head = tail = newNode;	
		}
		else if(nodeBefore == null) 
		{
			newNode.next = head;
			newNode.prev = null;
			head.prev = newNode;
			head = newNode;
		}
		else if(nodeBefore.next == null) 
		{
			nodeBefore.next = newNode;
			newNode.next = null;
			newNode.prev = nodeBefore;
			nodeBefore = newNode;
			
			tail = nodeBefore;
		}
		else
		{
			Node nodeAfter = nodeBefore.next;
			
			nodeBefore.next = newNode;
			newNode.prev = nodeBefore;
			newNode.next = nodeAfter;
			nodeAfter.prev = newNode;
			
			nodeAfter = newNode;
		}				
		size++;		
		return this;
	}
	
	public BasicDoubleLinkedList<T> addToEnd(T data)throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	public BasicDoubleLinkedList<T> addToFront(T data)throws UnsupportedOperationException{
		throw new UnsupportedOperationException();
	}
	
	/**
	 * @returns an iterator at the correct position
	 */
	public ListIterator<T> iterator(){
		return super.iterator(); 
	}
	
	/**
	 * @returns the data element/null that was removed
	 */
	public SortedDoubleLinkedList<T> remove(T data, Comparator<T> comparator){
		return (SortedDoubleLinkedList<T>) super.remove(data, comparator2);	
	}
}
