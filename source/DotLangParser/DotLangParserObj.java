package source.DotLangParser;

public class DotLangParserObj
{
    public final String fileName;
    public String contents;

    private String[] attributes;
    private String nodeLabel;

    private DotLangParserGraphType graphType;

    public DotLangParserObj(String fileName, String contents)
    {
        this.fileName = fileName;
        this.contents = contents;
    }

    public DotLangParserObj(String fileName)
    {
        this.fileName = fileName;
    }

    /**************************************************************************/
    /*************************** Acessor Functions ****************************/
    /**************************************************************************/

    public String getFileName()
    {
        return fileName;
    }

    public String getContents()
    {
        return contents;
    }

    public String[] getAttributes()
    {
        return attributes;
    }

    public String getNodeLabel()
    {
        return nodeLabel;
    }

    public DotLangParserGraphType getGraphType()
    {
        return graphType;
    }

    public void setContents(String contents)
    {
        this.contents = contents;
    }

    public void setAttributes(String[] attributes)
    {
        this.attributes = attributes;
    }

    public void setNodeLabel(String nodeLabel)
    {
        this.nodeLabel = nodeLabel;
    }

    public void setGraphType(DotLangParserGraphType graphType)
    {
        this.graphType = graphType;
    }
}
