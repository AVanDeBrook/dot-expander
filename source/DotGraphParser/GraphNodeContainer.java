package source.DotGraphParser;

import java.util.LinkedList;

public class GraphNodeContainer
{
    private String nodeName;
    private String nodeID;
    private String nodePrefix;
    private String attributeString;
    private String[] attributes;
    private LinkedList<GraphNodeContainer> connections;

    public GraphNodeContainer()
    {
        nodeName = "";
        nodeID = "";
        nodePrefix = "";
        attributeString = "";
        connections = new LinkedList<GraphNodeContainer>();
    }

    /**************************************************************************/
    /*************************** Acessor Functions ****************************/
    /**************************************************************************/

    public String getNodeName()
    {
        return nodeName;
    }

    public String getNodeID()
    {
        return nodeID;
    }

    public String getNodePrefix()
    {
        return nodePrefix;
    }

    public String getAttributeSubString()
    {
        return attributeString;
    }

    public String[] getAttributes()
    {
        return attributes;
    }

    public LinkedList<GraphNodeContainer> getConnections()
    {
        return connections;
    }

    public void setNodeName(String nodeName)
    {
        this.nodeName = nodeName;
    }

    public void setNodeID(String nodeID)
    {
        this.nodeID = nodeID;
    }

    public void setNodePrefix(String nodePrefix)
    {
        this.nodePrefix = nodePrefix;
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

    public void setConnections(LinkedList<GraphNodeContainer> connections)
    {
        this.connections = connections;
    }
}
