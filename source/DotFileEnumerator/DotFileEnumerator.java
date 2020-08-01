package source.DotFileEnumerator;

import java.io.File;
import java.util.LinkedList;

public class DotFileEnumerator
{
    private LinkedList<String> fileList = new LinkedList<String>();

    /**
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
     * @param filePath - File object-based file path.
     */
    public DotFileEnumerator(File filePath)
    {
        String[] tempArr = filePath.list();

        for (String s : tempArr) {
            fileList.add(filePath.toString() + "/" + s);
        }
    }

    /**
     * @param filePath - string-based path to dot files.
     * @param filter - toggle file-filtering.
     */
    public DotFileEnumerator(String filePath, boolean filter)
    {
        String[] tempArr = new File(filePath).list();

        if (filter) {
            filterStrings(tempArr);
        }
    }

    /**
     * @param filePath - File object-based file path.
     * @param filter - toggle file-filtering.
     */
    public DotFileEnumerator(File filePath, boolean filter)
    {
        String[] tempArr = filePath.list();

        if (filter) {
            filterStrings(tempArr);
        }
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

    /**************************************************************************/
    /*************************** Acessor Functions ****************************/
    /**************************************************************************/

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
