package source.DotLangParser;

public class DotLangParserObjNode
{
    private String nodeName;
    private String attributeSubString;
    private String[] attributes;

    public DotLangParserObjNode()
    {
        nodeName = "";
        attributeSubString = "";
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
        return attributeSubString;
    }

    public String[] getAttributes()
    {
        return attributes;
    }

    public void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    public void setAttributeSubString(String attributeSubString)
    {
        this.attributeSubString = attributeSubString;
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

        output += attributeSubString + "\n";

        for (String s : attributes) {
            output += s + "\n";
        }

        return output;
    }
}
