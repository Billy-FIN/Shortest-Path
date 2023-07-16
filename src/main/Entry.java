/**
 * This class has two fields to store the GraphNodes as keys and 
 * the index of the graph node as values together
 * Known Bugs: None
 *
 * @author Qiuyang Wang 
 * qiuyangwang@brandeis.edu 
 * May 9, 2023
 * COSI 21A PA3
 */

package main;

public class Entry {
	
	private GraphNode key;
	private int value;
	
	/**
	 * Initializes an Entry object to store a GraphNode object
	 * with its index in the heap array
	 * 
	 * Running time: O(1)
	 * @param key
	 * @param value
	 */
	public Entry(GraphNode key, int value) {
		this.key = key;
		this.value = value;
	}
	
	/**
	 * Sets the value of this object with the given integer
	 * 
	 * Running time: O(1)
	 * @param value
	 */
	public void setValue(int value) {
		this.value = value;
	}
	
	/**
	 * Obtains the value of this object
	 * 
	 * Running time: O(1)
	 * @return value
	 */
	public int getValue() {
		return value;
	}
	
	/**
	 * Obtains the key of this object
	 * 
	 * Running time: O(1)
	 * @return key
	 */
	public GraphNode getGraphNode() {
		return key;
	}
}
