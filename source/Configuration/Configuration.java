package source.Configuration;

import source.DotGraphParser.DotGraphType;

public class Configuration
{
    private String fileExtension;
    private DotGraphType graphType;

    private boolean singleFile;
    private boolean directory;

    public Configuration()
    {
        fileExtension = ".dot";
        graphType = DotGraphType.CALLER_GRAPH;
        singleFile = false;
        directory = true;
    }

    public Configuration(String[] argv) throws Exception
    {
        parseArgs(argv);
    }

    private void parseArgs(String[] argv) throws Exception
    {

    }

    /********** Accessor Functions **********/

    public boolean isSingleFile()
    {
        return singleFile;
    }

    public boolean isDirectory()
    {
        return directory;
    }

    public String getFileExtension()
    {
        return fileExtension;
    }

    public DotGraphType getGraphType()
    {
        return graphType;
    }

    public void setFileExtension(String fileExtension)
    {
        this.fileExtension = fileExtension;
    }

    public void setGraphType(DotGraphType graphType)
    {
        this.graphType = graphType;
    }

    public void setSingleFile(boolean singleFile)
    {
        this.singleFile = singleFile;
    }
}
