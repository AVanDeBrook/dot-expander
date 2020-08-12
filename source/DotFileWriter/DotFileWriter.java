package source.DotFileWriter;

import java.io.*;
import java.util.LinkedList;

import source.DotGraphParser.*;

public class DotFileWriter
{
    protected TemplateReader templateReader;
    private BufferedWriter writer;
    private String graphType;
    private String graphName;
    private String graphTemplate;
    private String subgraphTemplate;

    /**
     * Default no-arg constructor.
     */
    public DotFileWriter(String graphType, String graphName)
    {
        templateReader = new TemplateReader();
        this.graphType = graphType;
        this.graphName = graphName;

        graphTemplate = templateReader.readGraphTemplate(false);
        subgraphTemplate = templateReader.readSubgraphTemplate(false);
    }

    public String buildGraph(LinkedList<GraphContainer> graphList)
    {
        String graph = graphTemplate;
        LinkedList<String> subgraphList = buildSubgraphs(graphList);
        LinkedList<String> connectionsList = buildConnections(graphList);
        String subgraphs = "";
        String connections = "";

        for (String s : subgraphList) {
            subgraphs += s;
        }

        for (String s : connectionsList) {
            connections += s + ";";
        }

        graph = graph.replaceAll("%graphType%", graphType);
        graph = graph.replaceAll("%graphName%", graphName);
        graph = graph.replaceAll("%subgraph%", subgraphs);
        graph = graph.replaceAll("%nodeConnections%", connections);

        return graph;
    }

    public void writeGraphToFile(String dir, LinkedList<GraphContainer> graphList)
    {
        String graph = buildGraph(graphList);
        try {
            writer = new BufferedWriter(new FileWriter(dir));

            for (char c : graph.toCharArray())
                writer.write(c);

            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private LinkedList<String> buildSubgraphs(LinkedList<GraphContainer> graphList)
    {
        LinkedList<String> subgraphList = new LinkedList<String>();

        for (GraphContainer graph : graphList) {
            String nodeDefinitions = "";
            String subgraph = subgraphTemplate;

            for (GraphNodeContainer node : graph.getNodeList()) {
                nodeDefinitions += node.getNodeName() + ";";
            }

            subgraph = subgraph.replaceAll("%subgraphLabel%", graph.getGraphName());
            subgraph = subgraph.replaceAll("%subgraphName%", "cluster_" + graph.getGraphName());
            subgraph = subgraph.replaceAll("%nodeDefinitions%", nodeDefinitions);
            subgraphList.add(subgraph);
        }

        return subgraphList;
    }

    private LinkedList<String> buildConnections(LinkedList<GraphContainer> graphList)
    {
        LinkedList<String> connectionsList = new LinkedList<String>();

        // TODO: Worst case O(n^3), might need to make a faster algorithm later
        for (GraphContainer graph : graphList) {
            for (GraphNodeContainer node : graph.getNodeList()) {
                for (GraphNodeContainer connection : node.getConnections()) {
                    connectionsList.add(node.getNodeName() + " -> " + connection.getNodeName());
                }
            }
        }

        return connectionsList;
    }

    /********** Accessor Functions **********/

    public String getGraphTemplate()
    {
        return graphTemplate;
    }

    public String getSubgraphTemplate()
    {
        return subgraphTemplate;
    }
}
