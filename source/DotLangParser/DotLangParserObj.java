package source.DotLangParser;

import java.util.LinkedList;

public class DotLangParserObj
{
    public final String fileName;

    private String contents;
    private LinkedList<DotLangParserObjNode> nodeList;
    private DotLangParserGraphType graphType;

    public DotLangParserObj(String fileName, String contents)
    {
        this.fileName = fileName;
        this.contents = contents;
        nodeList = new LinkedList<DotLangParserObjNode>();
    }

    public DotLangParserObj(String fileName)
    {
        this.fileName = fileName;
        this.contents = "";
        nodeList = new LinkedList<DotLangParserObjNode>();
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

    public DotLangParserGraphType getGraphType()
    {
        return graphType;
    }

    public LinkedList<DotLangParserObjNode> getNodeList()
    {
        return nodeList;
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }

    public void setGraphType(DotLangParserGraphType graphType)
    {
        this.graphType = graphType;
    }
}
