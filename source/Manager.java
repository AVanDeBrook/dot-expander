package source;

import source.DotFileEnumerator.DotFileEnumerator;
import source.DotLangParser.DotLangParser;

public class Manager
{
    public static void main(String[] args)
    {
        DotLangParser parser = new DotLangParser(new DotFileEnumerator(args[0]));
        try {
            parser.parse();
        } catch (Exception e) {
            System.err.println(e);
            e.printStackTrace();
        }
    }
}
