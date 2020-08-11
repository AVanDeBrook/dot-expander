package source.DotFileWriter;

import java.io.*;

public class TemplateReader
{
    private BufferedReader reader;
    protected String graphTemplate;
    protected String subgraphTemplate;

    /**
     * Default, no-arg constructor.
     */
    protected TemplateReader()
    {
        graphTemplate = "templates/temp_graph.dot";
        subgraphTemplate = "templates/temp_subgraph.dot";
    }

    /**
     * Contructor for custom locations and templates.
     *
     * @param graphTemplate - location of the graph template.
     * @param subgraphTemplate - location of the subgraph template.
     */
    public TemplateReader(String graphTemplate, String subgraphTemplate)
    {
        this.graphTemplate = graphTemplate;
        this.subgraphTemplate = subgraphTemplate;
    }

    /**
     * Reads contents of the upper-level graph template.
     *
     * @param delimit - Whether or not to delimit each line with a new line character.
     * @return - content of the graph template.
     */
    public String readGraphTemplate(boolean delimit)
    {
        String templateContents = "";
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(graphTemplate));

            while ((line = reader.readLine()) != null)
                templateContents += line.trim() + (delimit ? "\n" : "");

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return templateContents;
    }

    /**
     * Reads the lower-level subgraph template.
     *
     * @param delimit - Whether or not to delimit each line with a new line character.
     * @return - content of the subgraph template.
     */
    public String readSubgraphTemplate(boolean delimit)
    {
        String templateContents = "";
        String line = "";
        try {
            reader = new BufferedReader(new FileReader(subgraphTemplate));

            while ((line = reader.readLine()) != null)
                templateContents += line.trim() + (delimit ? "\n" : "");

            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return templateContents;
    }
}
