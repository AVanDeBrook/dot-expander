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
            for (GraphContainer o : list) {
                System.out.println(o);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Usage:java source.Manager [directory]");
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
