package source.DotGraphParser;

public class GraphNodeContainer
{
    private String nodeName;
    private String attributeString;
    private String[] attributes;

    public GraphNodeContainer()
    {
        nodeName = "";
        attributeString = "";
    }

    /**************************************************************************/
    /*************************** Acessor Functions ****************************/
    /**************************************************************************/

    public String getNodeName()
    {
        return nodeName;
    }

    public String getAttributeSubString()
    {
        return attributeString;
    }

    public String[] getAttributes()
    {
        return attributes;
    }

    public void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    public void setAttributeString(String attributeSubString)
    {
        this.attributeString = attributeSubString;
    }

    public void setAttributes(String[] attributes)
    {
        for (int i = 0; i < attributes.length; i++) {
            attributes[i] = attributes[i].trim();
        }

        this.attributes = attributes;
    }

    @Override
    public String toString()
    {
        String output = "";

        output += attributeString + "\n";

        for (String s : attributes) {
            output += s + "\n";
        }

        return output;
    }
}
