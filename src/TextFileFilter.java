import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
/**
 * @author Allison Walther
 * CSC 300 Project 1.2
 * October 16, 2016
 * 
 */
public class TextFileFilter {

	//parameter: directory name
	//returns an ArrayList of *.txt files in the directory specified
	public static ArrayList<String> finder( String dirName){
        File dir = new File(dirName);

        //needed help with understanding how to display files in directory
        //http://stackoverflow.com/questions/1384947/java-find-txt-files-in-specified-folder
        File[] files = dir.listFiles(new FilenameFilter() { 
                 public boolean accept(File dir, String filename)
                      { return filename.endsWith(".txt"); } } );
        ArrayList<String> fileNames = new ArrayList<String>(); ;
        for(File i : files)
        {
        	if (!i.getName().equals("title.txt") && !i.getName().equals("basicCommands.txt") && !i.getName().equals("loading.txt"))
        	{
        		fileNames.add(i.getName());
        	}
        }
        return fileNames;
    }
}
