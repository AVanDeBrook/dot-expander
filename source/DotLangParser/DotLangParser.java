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

    public DotLangParser(DotFileEnumerator files)
    {
        fileList = files.getFileList();
        parsedData = new LinkedList<DotLangParserObj>();
    }

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
                tempNode.setAttributeSubString(tempString);
                tempNode.setAttributes(tempString.split(","));
            }
        }

        graphObject.getNodeList().add(tempNode);
    }

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
