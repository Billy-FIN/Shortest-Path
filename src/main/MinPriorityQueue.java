/**
 * This class provides the functionality of the min-priority queue to add items with certain priorities, 
 * and then be able to extract the item with the lowest priority in O(1) time. It has property of a min-heap
 * Known Bugs: None
 *
 * @author Qiuyang Wang 
 * qiuyangwang@brandeis.edu 
 * May 9, 2023
 * COSI 21A PA3
 */

package main;

public class MinPriorityQueue {

	private GraphNode[] queue;
	private HashMap map;
	private int size;
	
	/**
	 * Initializes an empty MinPriorityQueue object
	 * 
	 */
	public MinPriorityQueue() {
		size = 0;
		map = new HashMap(97);
		queue = new GraphNode[100];
	}
	
	/**
	 * Checks if the object stores something
	 * 
	 * @return true or false
	 */
	public boolean isEmpty() {
		return size == 0;
	}
	
	/**
	 * Returns and removes from the priority queue the 
	 * GraphNode with the highest priority in the queue
	 * 
	 * @return result or null
	 */
	public GraphNode pullHighestPriorityElement() {
		if(size > 0) {
			GraphNode result = queue[0];
			queue[0] = queue[size - 1];
			queue[size - 1] = null;
			size--;
			rebalance(queue[0]);
			map.set(result, -1);		//mark the key in the hash map with value -1
			return result;
		} else {
			return null;
		}
	}
	
	/**
	 * Inserts a graph node into the queue with its priority
	 * 
	 * @param g
	 */
	public void insert(GraphNode g) {
		if(size == queue.length) {
			GraphNode[] tmp = new GraphNode[2 * queue.length];
			for(int i = 0; i < queue.length; i++) {
				tmp[i] = queue[i];
			}
			queue = tmp;
		}
		if(g != null) {
			queue[size] = g;
			map.set(g, size);
			size++;
			rebalance(g);
		}
	}
	
	/**
	 * Maintains the min-heap property in the queue
	 * 
	 * @param g
	 */
	public void rebalance(GraphNode g) {
		heapify(g);
	}
	
	/**
	 * Calls different heapify methods to restore the 
	 * heap-like property of the priority queue at g
	 * 
	 * @param g
	 */
	public void heapify(GraphNode g) {
		if(size > 0 && g != null) {
			if(queue[size - 1].getId().equals(g.getId())) {
				heapifyUp(size - 1);
			} else if(queue[0].getId().equals(g.getId())) {
				heapifyDown(0);
			} else {
				heapifyDown(map.getValue(g));
				heapifyUp(map.getValue(g));
			}
		}
	}
	
	/**
	 * Moves the graph node at index i up to the correct position in the priority queue
	 * 
	 * @param i
	 */
	public void heapifyDown(int i) {
		int l = 2 * i + 1;
		int r = 2 * i + 2;
		int smallest;
		if(l < size && queue[l].priority < queue[i].priority) {
			smallest = l;
		} else {
			smallest = i;
		}
		if(r < size && queue[r].priority < queue[smallest].priority) {
			smallest = r;
		}
		if(smallest != i) {
			GraphNode tmp = queue[i];
			queue[i] = queue[smallest];
			queue[smallest] = tmp;
			map.set(queue[i], i);
			map.set(queue[smallest], smallest);
			heapifyDown(smallest);
		}
	}
	
	/**
	 * Moves the graph node at index i down to the correct position in the priority queue
	 * 
	 * @param i
	 */
	public void heapifyUp(int i) {
		int parent = (i - 1) / 2;
		while(i > 0 && queue[i].priority < queue[parent].priority) {
			GraphNode tmp = queue[i];
			queue[i] = queue[parent];
			queue[parent] = tmp;
			map.set(queue[i], i);
			map.set(queue[parent], parent);
			i = (i - 1) / 2;
			parent = (i - 1) / 2;
		}
	}
	
	/**
	 * Obtains the hash table of this object
	 * 
	 * @return map
	 */
	public HashMap getHashMap() {
		return map;
	}
	
	/**
	 * Standardizes the form of output to "[int,int,int,int]"
	 * 
	 * @return output
	 */
	public String toString() {
		String output = "[";
		for(int i = 0; i < size - 1; i++) {
			output += queue[i].priority + ",";
		}
		output += queue[size - 1].priority + "]";
		return output;
	}
}
