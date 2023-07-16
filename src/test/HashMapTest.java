/**
 * This class tests the functionality of HashMap
 * Known Bugs: None
 *
 * @author Qiuyang Wang 
 * qiuyangwang@brandeis.edu 
 * May 9, 2023
 * COSI 21A PA3
 */

package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import main.GraphNode;
import main.GraphWrapper;
import main.HashMap;
import main.Entry;

class HashMapTest {

	@Test
	void test() {
		HashMap m = new HashMap(7);
		GraphNode a = new GraphNode("c4dbe07b-f0b5-4b8a-bf11-28780d609a91", false);		//651 index1 0 index2 7  index3 7
		GraphNode b = new GraphNode("6bb67358-3761-455c-b283-2d309ca375e6", false);		//519 index1 1 index2 1  index3 15
		GraphNode c = new GraphNode("262c6587-096c-4e26-90b0-2d395b1f2f5c", false);		//471 index1 2 index2 9  index3 23
		GraphNode d = new GraphNode("33388c09-e6ee-4fbe-b626-16f73e981091", false);		//469 index1 3 index2 8  index3 21
		GraphNode e = new GraphNode("13f480ea-7811-4461-be93-d7cc3c7a86cb", false);		//556          index2 10 index3 24
		GraphNode f = new GraphNode("91bac7ec-57d1-4399-a992-2f1878f874a9", false);		//655          index2 11 index3 11
		GraphNode g = new GraphNode("97cf6dbb-ca61-49b2-9049-35265ce9494a", false);		//663          index2 5  index3 19
		GraphNode h = new GraphNode("e67b2f86-411c-4aa0-8040-4e1b7a858ce0", false);		//570                    index3 10
		GraphNode i = new GraphNode("3d9f9140-caa3-43d4-a1d5-9581752295e0", false);		//516                    index3 12
		GraphNode j = new GraphNode("15a89056-c1cb-40bc-b95c-e646471976a4", false);		//467                    index3 20
		m.set(a, 1);
		assertEquals(m.getValue(a), 1);
		m.set(b, 1);
		m.set(c, 2);
		m.set(a, 3);
		assertEquals(m.getValue(a), 3);
		assertEquals(m.getValue(b), 1);
		assertEquals(m.getValue(c), 2);
		assertEquals(m.getTable()[0].getGraphNode().getId(), "c4dbe07b-f0b5-4b8a-bf11-28780d609a91");
		assertEquals(m.getTable()[1].getGraphNode().getId(), "6bb67358-3761-455c-b283-2d309ca375e6");
		assertEquals(m.getTable()[2].getGraphNode().getId(), "262c6587-096c-4e26-90b0-2d395b1f2f5c");
		m.set(d, 4);
		assertEquals(m.getValue(d), 4);
		assertEquals(m.getTable()[7].getGraphNode().getId(), "c4dbe07b-f0b5-4b8a-bf11-28780d609a91");
		assertEquals(m.getTable()[1].getGraphNode().getId(), "6bb67358-3761-455c-b283-2d309ca375e6");
		assertEquals(m.getTable()[9].getGraphNode().getId(), "262c6587-096c-4e26-90b0-2d395b1f2f5c");
		assertEquals(m.getTable()[8].getGraphNode().getId(), "33388c09-e6ee-4fbe-b626-16f73e981091");
		assertEquals(m.getLoadFactor(), 4.0 / 14.0);
		m.set(c, 20);
		assertEquals(m.getValue(c), 20);
		assertEquals(m.getValue(e), -1);
		m.set(e, 4);
		m.set(f, 1);
		assertEquals(m.getLoadFactor(), 6.0 / 14.0);
		assertEquals(m.getTable()[10].getGraphNode().getId(), "13f480ea-7811-4461-be93-d7cc3c7a86cb");
		assertEquals(m.getTable()[11].getGraphNode().getId(), "91bac7ec-57d1-4399-a992-2f1878f874a9");
		assertEquals(m.getValue(e), 4);
		assertEquals(m.getValue(f), 1);
		m.set(g, 1);
		m.set(i, 4);
		m.set(h, 1);
		m.set(j, 1);
		assertEquals(m.getLoadFactor(), 10.0 / 28.0);
		assertEquals(m.getTable()[7].getGraphNode().getId(), "c4dbe07b-f0b5-4b8a-bf11-28780d609a91");
		assertEquals(m.getTable()[15].getGraphNode().getId(), "6bb67358-3761-455c-b283-2d309ca375e6");
		assertEquals(m.getTable()[23].getGraphNode().getId(), "262c6587-096c-4e26-90b0-2d395b1f2f5c");
		assertEquals(m.getTable()[21].getGraphNode().getId(), "33388c09-e6ee-4fbe-b626-16f73e981091");
		assertEquals(m.getTable()[24].getGraphNode().getId(), "13f480ea-7811-4461-be93-d7cc3c7a86cb");
		assertEquals(m.getTable()[11].getGraphNode().getId(), "91bac7ec-57d1-4399-a992-2f1878f874a9");
		assertEquals(m.getTable()[19].getGraphNode().getId(), "97cf6dbb-ca61-49b2-9049-35265ce9494a");
		assertEquals(m.getTable()[10].getGraphNode().getId(), "e67b2f86-411c-4aa0-8040-4e1b7a858ce0");
		assertEquals(m.getTable()[12].getGraphNode().getId(), "3d9f9140-caa3-43d4-a1d5-9581752295e0");
		assertEquals(m.getTable()[20].getGraphNode().getId(), "15a89056-c1cb-40bc-b95c-e646471976a4");
		m.set(b, 1);
		m.set(c, -1);
		m.set(e, 3);
		assertEquals(m.getValue(c), -1);
		assertEquals(m.getValue(e), 3);
		assertEquals(m.getValue(b), 1);
	}
}
