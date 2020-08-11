package source.DotFileWriter;

import java.util.LinkedList;

import source.DotGraphParser.*;

public class DotFileWriter
{
    protected TemplateReader templateReader;
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

        graphTemplate = templateReader.readGraphTemplate(true);
        subgraphTemplate = templateReader.readSubgraphTemplate(true);
    }

    public String buildGraph(LinkedList<GraphContainer> graphList)
    {
        String graph = graphTemplate;
        LinkedList<String> subgraphList = buildSubgraphs(graphList);
        String subgraphs = "";

        for (String s : subgraphList) {
            subgraphs += s;
        }

        graph = graph.replaceAll("%graphType%", graphType);
        graph = graph.replaceAll("%graphName%", graphName);
        graph = graph.replaceAll("%subgraph%", subgraphs);

        return graph;
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
            subgraphList.add("\t" + subgraph);
        }

        return subgraphList;
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
