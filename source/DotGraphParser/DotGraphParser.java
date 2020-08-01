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
    public DotGraphParser(DotFileEnumerator files)
    {
        fileList = files.getFileList();
        parsedData = new LinkedList<GraphContainer>();
    }

    /**
     *  Parses objects that were enumerated by the FileEnumerator.
     *
     * @throws Exception - When a file is not a ".dot" file.
     * @return - List of graph objects.
     */
    public LinkedList<GraphContainer> parse() throws Exception
    {
        if (checkFiles()) {
            readFiles();
            for (GraphContainer dotObj : parsedData) {
                parseAttributes(dotObj);
                // Comment/Uncomment for debugging
                // for (DotLangParserObjNode n : dotObj.getNodeList()) {
                //     System.out.println(n.getAttributes()[0]);
                // }
            }
        } else {
            throw new Exception("All files must have extension '.dot'");
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

                while ((line = reader.readLine()) != null) {
                    if (!line.contains("->")) {
                        tempData.add(line);
                    }
                }

                parsedData.add(tempData);
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Comment/Uncomment for debugging purposes
            // for (DotLangParserObj o : parsedData) {
            //     System.out.println(o.fileName);
            //     System.out.println(o.getContents(true));
            // }
        }
    }

    /**
     * Internal function called by parse on each data structure to parse the
     * key, value pairs for each node.
     *
     * @param graphObject - Data structure of the individual file to parse.
     */
    private void parseAttributes(GraphContainer graphObject)
    {
        String tempString;
        String[] contents = graphObject.getContents(false).split("%");
        GraphNodeContainer tempNode = new GraphNodeContainer();

        if (graphObject.getContents(false).contains("digraph")) {
            graphObject.setGraphType(DotGraphType.DIGRAPH);
        } else {
            graphObject.setGraphType(DotGraphType.UDIGRAPH);
        }

        for (String s : contents) {
            if (s.contains("label")) {
                tempString = s.substring(s.indexOf("[") + 1, s.indexOf("]"));
                tempNode.setAttributeString(tempString);
                tempNode.setAttributes(tempString.split(","));
            }
        }

        graphObject.getNodeList().add(tempNode);
    }

    /**
     * Checks passed file names for the ".dot" extension.
     *
     * @return - True when all files have correct extension. False otherwise.
     */
    private boolean checkFiles()
    {
        boolean output = true;

        for (String s : fileList) {
            if (!s.contains(".dot")) {
                output = false;
            }
        }

        return output;
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
