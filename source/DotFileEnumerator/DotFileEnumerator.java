package source.DotFileEnumerator;

import java.io.*;
import java.util.LinkedList;

public class DotFileEnumerator
{
    private LinkedList<String> fileList = new LinkedList<String>();

    /**
     * String based path to the collection of dot files.
     * Meant to be used for a collection of files.
     *
     * @param filePath - string-based path to dot files.
     */
    public DotFileEnumerator(String filePath)
    {
        String[] tempArr = new File(filePath).list();

        for (String s : tempArr) {
            fileList.add(filePath + "/" + s);
        }
    }

    /**
     * Meant for use with single files.
     *
     * @param filePath - File object-based file.
     */
    public DotFileEnumerator(File file)
    {
        fileList.add(file.toString());
    }

    /**
     * Meant to be used on a collection of files, lists files and adds them to
     * the file list if they are call graphs (e.g. if they have 'cgraph in the
     * file name')
     *
     * @param filePath - string-based path to dot files.
     * @param filter - toggle file-filtering.
     * @throws IOException when file is not a cgraph.
     */
    public DotFileEnumerator(String filePath, boolean filter)
    {
        String[] tempArr = new File(filePath).list();

        if (filter)
            filterStrings(tempArr);
    }

    /**
     * Meant to be used on a single dot file. If the file is not a call graph
     * (does not have 'cgraph' in file name) then it will throw an IOException
     *
     * @param filePath - File object-based file path.
     * @param filter - toggle file-filtering.
     * @throws IOExcpetion when file is not a cgraph.
     */
    public DotFileEnumerator(File file, boolean filter) throws IOException
    {
        if (file.toString().contains("cgraph"))
            fileList.add(file.toString());
        else
            throw new IOException("File is not a call graph. Does not contain \"cgraph\" in file name.");
    }

    /**
     * Filter strings for "cgraph" phrase.
     *
     * @param arr - array of file-name strings.
     */
    private void filterStrings(String[] arr)
    {
        for (String s : arr) {
            if (s.contains("cgraph")) {
                fileList.add(s);
            }
        }
    }

    /**
     * Removes files from file list if the file name does not match filter.
     *
     * @param filter - String that must be present in file names
     */
    public void removeFiles(String filter)
    {
        for (String s : fileList) {
            if (!s.contains(filter)) {
                fileList.remove(s);
            }
        }
    }

    /********** Acessor Functions **********/

    public void setFileList(LinkedList<String> fileList)
    {
        for (String s : fileList) {
            this.fileList.add(s);
        }
    }

    public LinkedList<String> getFileList()
    {
        return fileList;
    }

    @Override
    public String toString()
    {
        String output = "";

        for (String s : fileList) {
            output += s + "\n";
        }

        return output;
    }
}
