import java.io.File;
import java.io.FilenameFilter;

public class a4 {
	
	public static void main(String[] args) 
	{
			String pathname = args[0];
			File folder = new File(pathname);
			//listFilesForFolder(folder);
			
			FilenameFilter filter = new FilenameFilter() {
			    public boolean accept(File dir, String name) {
			        return name.endsWith(".java");
			    }
			};

			File[] listOfFiles = folder.listFiles(filter);
			

			for (int i = 0; i < listOfFiles.length; i++) {
			    File file = listOfFiles[i];
			    System.out.println(file.getName());
			    
			    //String content = FileUtils.readFileToString(file);
			    // do something with the file
			}
	}
	
/**	public static void listFilesForFolder(File folder) {
	    for (File fileEntry : folder.listFiles()) {
	        if (fileEntry.isDirectory()) {
	            listFilesForFolder(fileEntry);
	        } else {
	            System.out.println(fileEntry.getName());
	            File files[] = folder.listFiles();
	            for (File f: files) 
	            {
	            		//create AST
	            		System.out.println(f.toString());
	            }
	            
	        }
	    }
	}*/
	


}
