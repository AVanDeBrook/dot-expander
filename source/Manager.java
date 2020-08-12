package source;

import java.util.LinkedList;
import java.io.*;

import source.DotFileEnumerator.*;
import source.DotGraphParser.*;
import source.DotFileWriter.*;

public class Manager
{
    public static void main(String[] args)
    {
        try {
            DotGraphParser parser = new DotGraphParser(new DotFileEnumerator(args[0]));
            LinkedList<GraphContainer> list = parser.parse();
            DotFileWriter graphWriter = new DotFileWriter("digraph", "dot_expander_test");

            graphWriter.writeGraphToFile("out/graph.dot", list);

            // System.out.println(graphWriter.buildGraph(list));
            // System.out.println(graphWriter.getGraphTemplate());
            // System.out.println(graphWriter.getSubgraphTemplate());
            // printDebugInfo(list);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Usage:java source.Manager [directory]");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void printDebugInfo(LinkedList<GraphContainer> list)
    {
        for (GraphContainer container : list) {
            System.out.println("\nFile Name: " + container.getFileName());
            System.out.println("Graph Name: " + container.getGraphName());
            System.out.println("Graph Prefix: " + container.getGraphPrefix());

            for (GraphNodeContainer node : container.getNodeList()) {
                System.out.println("Node ID: " + node.getNodeID());
                System.out.println("Node name: " + node.getNodeName());
                System.out.println("Node prefix: " + node.getNodePrefix());
            }

            System.out.println("Connections:");
            for (String s : container.getEdgeList()) {
                System.out.println(s);
            }

            System.out.println("Connections:");
            for (GraphNodeContainer node : container.getNodeList()) {
                System.out.println("\n" + node.getNodeID() + ":");
                for (GraphNodeContainer s : node.getConnections()) {
                    System.out.println("-" + s.getNodeID());
                }
            }
        }
    }
}
