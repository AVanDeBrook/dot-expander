package source.DotGraphParser;

import java.util.LinkedList;

public class GraphContainer
{
    public final String fileName;

    private String graphName;
    private String contents;
    private String graphPrefix;
    private LinkedList<GraphNodeContainer> nodeList = new LinkedList<GraphNodeContainer>();
    private LinkedList<String> edgeList = new LinkedList<String>();
    private boolean directedGraph;

    /**
     * File-name and content constructor.
     *
     * @param fileName - name of the file.
     * @param contents - content of the file.
     */
    public GraphContainer(String fileName, String contents)
    {
        this.fileName = fileName;
        this.contents = contents;
        this.graphPrefix = "";
    }

    /**
     * File-name constructor.
     *
     * @param fileName - name of the file.
     */
    public GraphContainer(String fileName)
    {
        this.fileName = fileName;
        this.contents = "";
        this.graphPrefix = "";
    }

    /**
     * Adds a line to the objects contents.
     *
     * @param line - Line to add.
     */
    public void add(String line)
    {
        contents += line + "%";
        contents.replaceAll(" ", "");
    }

    public GraphNodeContainer findNode(String nodeName)
    {
        for (GraphNodeContainer node : nodeList) {
            if (node.getNodeID().equals(nodeName)) {
                return node;
            }
        }
        return null;
    }

    /**************************************************************************/
    /*************************** Acessor Functions ****************************/
    /**************************************************************************/

    public String getFileName()
    {
        return fileName;
    }

    public String getGraphName()
    {
        return graphName;
    }

    public String getContents(boolean prettyPrint)
    {
        String output;

        if (prettyPrint) {
            output = contents.replaceAll("%", "\n");
        } else {
            output = contents;
        }

        return output;
    }

    public String getGraphPrefix()
    {
        return graphPrefix;
    }

    public boolean isDirectedGraph()
    {
        return directedGraph;
    }

    public LinkedList<GraphNodeContainer> getNodeList()
    {
        return nodeList;
    }

    public LinkedList<String> getEdgeList()
    {
        return edgeList;
    }

    public void setGraphName(String graphName)
    {
        this.graphName = graphName;
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }

    public void setGraphPrefix(String graphPrefix)
    {
        this.graphPrefix = graphPrefix;
    }

    public void setDirectedGraph(boolean directedGraph)
    {
        this.directedGraph = directedGraph;
    }

    public void setEdgeList(LinkedList<String> edgeList)
    {
        this.edgeList = edgeList;
    }
}
