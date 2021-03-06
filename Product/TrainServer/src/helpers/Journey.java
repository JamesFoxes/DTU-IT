/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helpers;

import execute.Server;
import java.util.ArrayList;
import java.util.Stack;
import trafficrouting.AStarTraversal;
import trafficrouting.Edge;
import trafficrouting.SearchZones;
import trafficrouting.TransportNode;

/**
 *
 * @author JamesFoxes
 */
public class Journey
{

    private final Stack<TransportNode> journey;
    private final TransportNode startPoint;
    private final TransportNode endPoint;
    private int cost = 0;
    private double journeyTime = 0;

    public Journey(TransportNode startPoint, TransportNode endPoint)
    {
        journey = new Stack<>();    //Journey stack has starting stop at top, end at bottom.
        this.startPoint = startPoint;
        this.endPoint = endPoint;

        findShortestPath();
        calculateCost();
        calculateJourneyTime();
    }

    private void findShortestPath()
    {
        AStarTraversal traverser = new AStarTraversal();
        if (Server.trafficGraph == null)
        {
            Server.trafficGraph = Server.serverTransmitter.getTrafficGraph();
        }
        traverser.findShortestPaths(Server.trafficGraph.getGraph(endPoint.referenceID), startPoint, endPoint);

        fillStack();
    }

    private void fillStack()
    {
        TransportNode node = endPoint;

        while (node.returnNode != node)
        {
            journey.add(node);
            node = node.returnNode;
        }
        journey.add(node);
    }

    private void calculateCost()
    {
        SearchZones zones = new SearchZones();
        cost = ServerData.costPerZone * zones.getZonesNeeded(startPoint, endPoint);
    }

    private void calculateJourneyTime()
    {
        Stack<TransportNode> tempStack = (Stack<TransportNode>) journey.clone();

        TransportNode node;
        while (!tempStack.empty())
        {
            node = tempStack.pop();
            if (!node.equals(endPoint))
            {
                for (Edge edge : node)
                {
                    if (edge.toNode.equals(tempStack.peek()))
                    {
                        journeyTime += edge.cost;
                        break;
                    }
                }
            }
        }
    }

    public Stack<TransportNode> getJourney()
    {
        return journey;
    }

    public int getCost()
    {
        return cost;
    }

    public double getTimeToTravel()
    {
        return journeyTime;
    }
}
