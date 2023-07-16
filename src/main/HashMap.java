/**
 * This class provides the functionality of the HashMap to store values
 * by using keys. It uses linear probing to solve collisions and rehashes
 * when the load factor is larger then 0.5
 * Known Bugs: None
 *
 * @author Qiuyang Wang 
 * qiuyangwang@brandeis.edu 
 * May 9, 2023
 * COSI 21A PA3
 */

package main;

public class HashMap {
	
	private Entry[] table;
	private int takenSpace;
	private double loadFactor;
	
	/**
	 * Initializes an empty HashMap object
	 * 
	 * @param num
	 */
	public HashMap(int num) {
		table = new Entry[num];
		takenSpace = 0;
		loadFactor = 0.0;
	}
	
	/**
	 * Updates the load factor
	 * 
	 */
	public void updateLoadFactor() {
		loadFactor = (double)takenSpace / (double) table.length;
	}
	
	/**
	 * Checks the HashMap to see if there is an Entry for the GraphNode “key”. If there is,
	 * sets its "value" with the new one. If not, add this Entry object to the HashMap. Rehashs when
	 * it is necessary
	 * 
	 * @param key
	 * @param value
	 */
	public void set(GraphNode key, int value) {
		int index = hashFunction(key, table.length);
		Entry item = new Entry(key, value);
		while(table[index] != null && !table[index].getGraphNode().getId().equals(key.getId())) {		//skip the potential collisions
			if(index == table.length - 1) {
				index = 0;
			} else {
				index++;
			}
		}
		if(table[index] == null) {		//if there is no such key
			table[index] = item;
			takenSpace++;
			updateLoadFactor();
			if(loadFactor >= 0.5) {
				rehash();
				updateLoadFactor();
			}
		} else {						//if there is the key
			table[index].setValue(value);
		}
	}
	
	/**
	 * Obtains the value mapped by GraphNode g
	 * 
	 * @param g
	 * @return -1 or table[index].getValue()
	 */
	public int getValue(GraphNode g) {
		if(hasKey(g)) {
			int index = hashFunction(g, table.length);
			while(!table[index].getGraphNode().getId().equals(g.getId())) {
				if(index == table.length - 1) {
					index = 0;
				} else {
					index++;
				}
			}
			return table[index].getValue();
		} else {
			return -1;
		}
	}
	
	/**
	 * Checks if the hash table has the given GaphNode object as keys
	 * 
	 * @param g
	 * @return true or false
	 */
	public boolean hasKey(GraphNode g) {
		int index = hashFunction(g, table.length);
		int count = 0;
		if(table[index] == null) {
			return false;
		}
		while(!table[index].getGraphNode().getId().equals(g.getId())) {
			if(index == table.length - 1) {
				index = 0;
			} else {
				index++;
			}
			if(table[index] == null) {
				return false;
			}
			count++;
			if(count > table.length) {
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Calulate the hash value in order to implement open indexing in the HashMap
	 * 
	 * @param key
	 * @param size
	 * @return sum
	 */
	public int hashFunction(GraphNode key, int size) {
		String id = key.getId();
		int sum = 0;
		for(int i = 0; i < 8; i++) {
			sum += id.charAt(i);		//add up the first 8 characters of the UUID
		}
		sum = sum % size;
		return sum;
	}
	
	/**
	 * Enlarges the size of hash table and then rehash existing values
	 * to the new one
	 * 
	 */
	public void rehash() {
		Entry[] tmp = new Entry[2 * table.length];
		for(int i = 0; i < table.length; i++) {
			if(table[i] != null) {
				int index = hashFunction(table[i].getGraphNode(), 2 * table.length);
				if(tmp[index] == null) {
					tmp[index] = table[i];
				} else {
					while(tmp[index] != null) {
						if(index == tmp.length - 1) {
							index = 0;
						} else {
							index++;
						}
					}
					tmp[index] = table[i];
				}
			}
		}
		table = tmp;
	}
	
	/**
	 * Obtains the load factor of this object
	 * 
	 * @return loadFactor
	 */
	public double getLoadFactor() {
		return loadFactor;
	}
	
	/**
	 * Obtains the hash table of this object
	 * 
	 * @return table
	 */
	public Entry[] getTable() {
		return table;
	}
}
