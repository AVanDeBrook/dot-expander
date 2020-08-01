package source;

import java.util.LinkedList;

import source.DotFileEnumerator.DotFileEnumerator;
import source.DotLangParser.DotLangParser;
import source.DotLangParser.DotLangParserObj;

public class Manager
{
    public static void main(String[] args)
    {
        DotLangParser parser;
        LinkedList<DotLangParserObj> list;
        try {
            parser = new DotLangParser(new DotFileEnumerator(args[0]));
            list = parser.parse();
            for (DotLangParserObj o : list) {
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
