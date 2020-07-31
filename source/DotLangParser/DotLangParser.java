package source.DotLangParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;

import source.DotFileEnumerator.DotFileEnumerator;

public class DotLangParser
{
    private BufferedReader reader;
    private LinkedList<String> fileList;

    private LinkedList<DotLangParserObj> parsedData;

    public DotLangParser(DotFileEnumerator files)
    {
        fileList = files.getFileList();
        parsedData = new LinkedList<DotLangParserObj>();
    }

    public void parse() throws Exception
    {
        if (checkFiles()) {
            _parse();
        } else {
            throw new Exception("All files must have extension '.dot'");
        }
    }

    private void _parse()
    {
        DotLangParserObj tempData;
        String line;
        try {
            for (String file : fileList) {
                reader = new BufferedReader(new FileReader(file));
                tempData = new DotLangParserObj(file);
                while ((line = reader.readLine()) != null) {
                    tempData.contents += line + "%";
                }
                parsedData.add(tempData);
                reader.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Comment/Uncomment for debugging purposes
            // for (DotLangParserObj o : parsedData) {
            //     System.out.println(o.fileName);
            //     System.out.println(o.contents.replaceAll("%", "\n"));
            // }
        }
    }

    private boolean checkFiles()
    {
        boolean output = true;

        for (String s : fileList) {
            if (!s.contains(".dot")) {
                output = false;
            }
        }

        return output;
    }
}
