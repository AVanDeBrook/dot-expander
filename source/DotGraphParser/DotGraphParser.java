package source.DotGraphParser;

import java.io.*;
import java.util.LinkedList;

import source.DotFileEnumerator.DotFileEnumerator;

public class DotGraphParser
{
    private BufferedReader reader;
    private LinkedList<String> fileList;
    private LinkedList<GraphContainer> parsedData = new LinkedList<GraphContainer>();

    /**
     * Constructor. Requires a DotFileEnumerator object to get the list of files
     * to parse.
     *
     * @param files - Object containing enumerated files
     */
    public DotGraphParser(DotFileEnumerator files) throws Exception
    {
        fileList = files.getFileList();

        for (String file : fileList) {
            if (!file.contains(".dot")) {
                throw new Exception("Unexpected file passed.");
            }
        }
    }

    /**
     * Parses objects that were enumerated by the FileEnumerator.
     *
     * @return - List of graph objects.
     */
    public LinkedList<GraphContainer> parse()
    {
        readFiles();
        for (GraphContainer dotObj : parsedData) {
            parseAttributes(dotObj);
            parseConnections(dotObj);
            parseNodeConnections(dotObj);
            parsePrefixes(dotObj);
        }

        return parsedData;
    }

    /**
     * Trims nodes from the graph if they are in the same module as the function
     * that the graph is built from. In other words, if the prefix of the node(s)
     * match the prefix of the graph it will be trimmed from the list of nodes.
     *
     * @param graphList - List of graphs to trim nodes from.
     */
    public void trimNodes(GraphContainer graph)
    {
        LinkedList<GraphNodeContainer> removalList = new LinkedList<GraphNodeContainer>();

        // @FIXME: Does not remove all nodes w/ same module prefix, need to come
        //         back to this later.
        for (GraphNodeContainer node : graph.getNodeList()) {
            if (node.getNodePrefix().equals(graph.getGraphPrefix())) {
                if (!node.getNodeName().equals(graph.getGraphName())) {
                    removalList.add(node);
                }
            }
        }

        for (GraphNodeContainer node : removalList) {
            graph.getNodeList().remove(node);
        }
    }

    /**
     * Internal function called by parse() to read contents of files
     * into data structures.
     */
    private void readFiles()
    {
        String line;
        try {
            for (String file : fileList) {
                reader = new BufferedReader(new FileReader(file));
                GraphContainer tempData = new GraphContainer(file);

                while ((line = reader.readLine()) != null)
                    tempData.add(line);

                parsedData.add(tempData);
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Internal function called by parse() on each data structure to parse the
     * key, value pairs for each node.
     *
     * @param graphObject - Data structure of the individual file to parse.
     */
    private void parseAttributes(GraphContainer graphObject)
    {
        String[] contents = graphObject.getContents(false).split("%");

        String tempString = contents[0].trim();
        graphObject.setGraphName(tempString.substring(tempString.indexOf("\"") + 1, tempString.lastIndexOf("\"")));

        if (graphObject.getContents(false).contains("digraph")) {
            graphObject.setDirectedGraph(true);
        } else {
            graphObject.setDirectedGraph(false);
        }

        for (String s : contents) {
            if (s.contains("Node") && !s.contains("->")) {
                GraphNodeContainer tempNode = new GraphNodeContainer();

                tempNode.setNodeID(s.substring(0, s.indexOf("[") - 1).trim());

                tempString = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
                tempNode.setAttributeString(tempString);
                tempNode.setAttributes(tempString.split(","));

                String nodeName = tempNode.getAttributes()[0].split("=")[1];
                tempNode.setNodeName(nodeName.substring(1, nodeName.lastIndexOf("\"")));

                graphObject.getNodeList().add(tempNode);
            }
        }
    }

    /**
     * Internal function called by parse. Adds edge definitions to a linked list
     * in the graph container to be parsed and reduced later.
     *
     * @param graphObject - Graph to build connection list from.
     */
    private void parseConnections(GraphContainer graphObject)
    {
        for (String s : graphObject.getContents(false).split("%")) {
            if (s.contains("->")) {
                graphObject.getEdgeList().add(s.substring(0, s.indexOf("[")).trim());
            }
        }
    }

    /**
     * Internal function called by parse. Creates a list of connections from one
     * node to all other nodes, for all nodes in a graph.
     *
     * @param graphObject - Graph to build node connections from.
     */
    private void parseNodeConnections(GraphContainer graphObject)
    {
        for (String s : graphObject.getEdgeList()) {
            String[] connectionString = s.split("->");
            GraphNodeContainer sourceNode = graphObject.findNode(connectionString[0].trim());
            sourceNode.getConnections().add(graphObject.findNode(connectionString[1].trim()));
        }
    }

    /**
     * Internal function called by parse. Finds the prefix or module of the graph
     * node by attempting to parse a prefix from the graphs and nodes provided.
     *
     * @param graphObject - Graph and nodes to parse name prefixes from.
     */
    private void parsePrefixes(GraphContainer graphObject)
    {
        String graphName = graphObject.getGraphName();
        graphObject.setGraphPrefix(graphName.substring(0, graphName.indexOf("_")).trim());

        for (GraphNodeContainer node : graphObject.getNodeList()) {
            String nodeName = node.getNodeName();
            node.setNodePrefix(nodeName.substring(0, nodeName.indexOf("_")).trim());
        }
    }

    /**************************************************************************/
    /*************************** Acessor Functions ****************************/
    /**************************************************************************/

    public LinkedList<String> getFileList()
    {
        return fileList;
    }

    public LinkedList<GraphContainer> getParsedData()
    {
        return parsedData;
    }

    public void setFileList(LinkedList<String> fileList)
    {
        this.fileList = fileList;
    }

    public void setParsedData(LinkedList<GraphContainer> parsedData)
    {
        this.parsedData = parsedData;
    }
}
