/**
 * This is the client code to implement the Dijikstra’s algorithm to
 * find the shortest path to the place of internship
 * Known Bugs: None
 *
 * @author Qiuyang Wang 
 * qiuyangwang@brandeis.edu 
 * May 9, 2023
 * COSI 21A PA3
 */

package main;
import java.io.*;

public class FindMinPath {

	/**
	 * This is the main method to run the algorithm with the implementation of 
	 * HashMap and MinPriorityQueue to find the shortest path to Yahoo
	 * 
	 * @param args
	 * @throws FileNotFoundException
	 */
	public static void main(String[] args) throws FileNotFoundException {
		MinPriorityQueue Q = new MinPriorityQueue();
		GraphWrapper gw = new GraphWrapper();
		GraphNode home = gw.getHome();
		home.priority = 0;
		Q.insert(home);
		GraphNode destination = new GraphNode("a", false);
		GraphNode curr = null;
		boolean findGoal = false;
		while(!Q.isEmpty() && !findGoal) {
			curr = Q.pullHighestPriorityElement();
			if(curr.isGoalNode()) {
				findGoal = true;
				destination = curr;
			} else {
				if(curr.hasNorth()) {
					GraphNode north = curr.getNorth();
					int x = curr.priority + curr.getNorthWeight();
					if(!Q.getHashMap().hasKey(north) && Q.getHashMap().getValue(north) == -1) {			//not in the queue
						north.priority = x;
						north.previousNode = curr;
						north.previousDirection = "North";
						Q.insert(north);
					} else if(Q.getHashMap().hasKey(north) && Q.getHashMap().getValue(north) != -1 && x < north.priority) {			//in the queue
						north.priority = x;
						Q.rebalance(north);
						north.previousDirection = "North";
						north.previousNode = curr;
					}
				}
				if(curr.hasSouth()) {
					GraphNode south = curr.getSouth();
					int x = curr.priority + curr.getSouthWeight();
					if(!Q.getHashMap().hasKey(south) && Q.getHashMap().getValue(south) == -1) {
						south.priority = x;
						south.previousNode = curr;
						south.previousDirection = "South";
						Q.insert(south);
					} else if(Q.getHashMap().hasKey(south) && Q.getHashMap().getValue(south) != -1 && x < south.priority) {
						south.priority = x;
						Q.rebalance(south);
						south.previousDirection = "South";
						south.previousNode = curr;
					}
				}
				if(curr.hasEast()) {
					GraphNode east = curr.getEast();
					int x = curr.priority + curr.getEastWeight();
					if(!Q.getHashMap().hasKey(east) && Q.getHashMap().getValue(east) == -1) {
						east.priority = x;
						east.previousNode = curr;
						east.previousDirection = "East";
						Q.insert(east);
					} else if(Q.getHashMap().hasKey(east) && Q.getHashMap().getValue(east) != -1 && x < east.priority) {
						east.priority = x;
						Q.rebalance(east);
						east.previousDirection = "East";
						east.previousNode = curr;
					}
				}
				if(curr.hasWest()) {
					GraphNode west = curr.getWest();
					int x = curr.priority + curr.getWestWeight();
					if(!Q.getHashMap().hasKey(west) && Q.getHashMap().getValue(west) == -1) {
						west.priority = x;
						west.previousNode = curr;
						west.previousDirection = "West";
						Q.insert(west);
					} else if(Q.getHashMap().hasKey(west) && Q.getHashMap().getValue(west) != -1 && x < west.priority) {
						west.priority = x;
						Q.rebalance(west);
						west.previousDirection = "West";
						west.previousNode = curr;
					}
				}
			}
		}
		PrintStream output = new PrintStream(new File("answer.txt"));
		if(Q.isEmpty() && !findGoal) {
			output.println("No path!");
		} else {
			outputResult(destination, output);
		}
	}
	
	/**
	 * Back tracks to the home node and then print out the direction in a file
	 * 
	 * @param node
	 * @param output
	 */
	public static void outputResult(GraphNode node, PrintStream output) {
		if(node.previousDirection != null) {
			outputResult(node.previousNode, output);
			output.println(node.previousDirection);
		}
	}
}
