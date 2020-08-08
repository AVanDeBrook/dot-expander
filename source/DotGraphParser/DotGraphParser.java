package source.DotGraphParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import source.DotFileEnumerator.DotFileEnumerator;

public class DotGraphParser
{
    private BufferedReader reader;
    private LinkedList<String> fileList;
    private LinkedList<GraphContainer> parsedData;

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

        parsedData = new LinkedList<GraphContainer>();
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
            parseConnections(dotObj);
            parseAttributes(dotObj);
            setNodeConnections(dotObj);
        }

        return parsedData;
    }

    /**
     * Internal function called by parse() to read contents of files
     * into data structures.
     */
    private void readFiles()
    {
        GraphContainer tempData;
        String line;
        try {
            for (String file : fileList) {
                reader = new BufferedReader(new FileReader(file));
                tempData = new GraphContainer(file);

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

                String nodeName = tempNode.getAttributes()[0];
                tempNode.setNodeName(nodeName.split("=")[1]);

                graphObject.getNodeList().add(tempNode);
            }
        }
    }

    private void parseConnections(GraphContainer graphObject)
    {
        for (String s : graphObject.getContents(false).split("%")) {
            if (s.contains("->")) {
                graphObject.getEdgeList().add(s.substring(0, s.indexOf("[")).trim());
            }
        }
    }

    private void setNodeConnections(GraphContainer graphObject)
    {
        for (String s : graphObject.getEdgeList()) {
            String[] connectionString = s.split("->");
            GraphNodeContainer sourceNode = graphObject.findNode(connectionString[0].trim());
            sourceNode.getConnections().add(graphObject.findNode(connectionString[1].trim()));
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
