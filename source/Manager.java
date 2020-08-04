package source;

import java.util.LinkedList;

import source.DotFileEnumerator.*;
import source.DotGraphParser.*;

public class Manager
{
    public static void main(String[] args)
    {
        DotGraphParser parser;
        LinkedList<GraphContainer> list;
        try {
            parser = new DotGraphParser(new DotFileEnumerator(args[0]));
            list = parser.parse();
            for (GraphContainer container : list) {
                System.out.println("\nFile Name: " + container.getFileName());
                System.out.println("Graph Name: " + container.getGraphName());
                for (GraphNodeContainer node : container.getNodeList()) {
                    System.out.println(node.getNodeName());
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Usage:java source.Manager [directory]");
            e.printStackTrace();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
