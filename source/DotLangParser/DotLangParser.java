package source.DotLangParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import source.DotFileEnumerator.DotFileEnumerator;

public class DotLangParser
{
    private BufferedReader reader;
    private LinkedList<String> fileList;
    private LinkedList<DotLangParserObj> parsedData;

    /**
     * Constructor. Requires a DotFileEnumerator object to get the list of files
     * to parse.
     *
     * @param files - Object containing enumerated files
     */
    public DotLangParser(DotFileEnumerator files)
    {
        fileList = files.getFileList();
        parsedData = new LinkedList<DotLangParserObj>();
    }

    /**
     *  Parses objects that were enumerated by the FileEnumerator.
     *
     * @throws Exception - When a file is not a ".dot" file.
     */
    public void parse() throws Exception
    {
        if (checkFiles()) {
            readFiles();
            for (DotLangParserObj dotObj : parsedData) {
                parseAttributes(dotObj);
                // Comment/Uncomment for debugging
                for (DotLangParserObjNode n : dotObj.getNodeList()) {
                    System.out.println(n.getAttributes()[0]);
                }
            }
        } else {
            throw new Exception("All files must have extension '.dot'");
        }
    }

    /**
     * Internal function called by parse() to read contents of files
     * into data structures.
     */
    private void readFiles()
    {
        DotLangParserObj tempData;
        String line;
        try {
            for (String file : fileList) {
                reader = new BufferedReader(new FileReader(file));
                tempData = new DotLangParserObj(file);

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
    private void parseAttributes(DotLangParserObj graphObject)
    {
        String tempString;
        String[] contents = graphObject.getContents(false).split("%");
        DotLangParserObjNode tempNode = new DotLangParserObjNode();

        if (graphObject.getContents(false).contains("digraph")) {
            graphObject.setGraphType(DotLangParserGraphType.DIGRAPH);
        } else {
            graphObject.setGraphType(DotLangParserGraphType.UDIGRAPH);
        }

        for (String s : contents) {
            if (s.contains("label")) {
                tempString = s.substring(s.indexOf("[") + 1, s.indexOf("]") - 1);
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
}
