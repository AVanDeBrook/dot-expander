package source;

import source.DotFileEnumerator.DotFileEnumerator;
import source.DotLangParser.DotLangParser;

public class Manager
{
    public static void main(String[] args)
    {
        try {
            DotLangParser parser = new DotLangParser(new DotFileEnumerator(args[0]));
            parser.parse();
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Usage:java source.Manager [directory]");
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
