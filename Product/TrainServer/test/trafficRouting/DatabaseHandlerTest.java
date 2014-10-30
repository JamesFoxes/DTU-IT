/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trafficRouting;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author James
 */
public class DatabaseHandlerTest
{

    public DatabaseHandlerTest()
    {
    }

    @BeforeClass
    public static void setUpClass()
    {
    }

    @AfterClass
    public static void tearDownClass()
    {
    }

    @Before
    public void setUp()
    {
    }

    @After
    public void tearDown()
    {
    }
    
    //@Test
    public void testGetEdges() throws Exception
    {
        Map<Integer, TransportNode> nothing = new HashMap<>();
        DatabaseHandler db = new DatabaseHandler();
        db.getEdgesFromStop(8084956, nothing);
    }

    @Test //(timeout = 1000)
    public void testStuff()
    {
        SetupGraph graph = new SetupGraph();
        DirectedGraph<TransportNode> directedGraph = graph.buildAndGetGraph(5);

        AStarTraversal graphTraverser = new AStarTraversal();
        Map<TransportNode, Double> result = graphTraverser.findShortestPath(directedGraph, graph.getNode(0), graph.getNode(5));

        for (Map.Entry<TransportNode, Double> entrySet : result.entrySet())
        {
            TransportNode key = entrySet.getKey();
            Double value = entrySet.getValue();

            System.out.println("key: " + key.identity);
            System.out.println("value: " + value);
            System.out.println("PATH:" + key.returnNode.identity);
            System.out.println("");
        }
    }
}
