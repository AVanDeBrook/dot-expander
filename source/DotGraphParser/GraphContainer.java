package source.DotGraphParser;

import java.util.LinkedList;

public class GraphContainer
{
    public final String fileName;
    public String graphName;

    private String contents;
    private LinkedList<GraphNodeContainer> nodeList;
    private DotGraphType graphType;

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
        nodeList = new LinkedList<GraphNodeContainer>();
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
        nodeList = new LinkedList<GraphNodeContainer>();
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

    /**************************************************************************/
    /*************************** Acessor Functions ****************************/
    /**************************************************************************/

    public String getFileName()
    {
        return fileName;
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

    public DotGraphType getGraphType()
    {
        return graphType;
    }

    public LinkedList<GraphNodeContainer> getNodeList()
    {
        return nodeList;
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }

    public void setGraphType(DotGraphType graphType)
    {
        this.graphType = graphType;
    }

    @Override
    public String toString()
    {
        String output = "";

        for (GraphNodeContainer n : nodeList) {
            output += n.toString();
        }

        return output;
    }
}
