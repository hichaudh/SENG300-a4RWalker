import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import org.eclipse.jdt.core.dom.*;

public class DriverForGroupAssign1 {
	
	
	public DeclarationAndReferenceCounter fileParser;
	
//	public static void main(String args[]) throws IOException {
//		
//		String pathname = args[0];
//		
//		getRefAndDecCounts(pathname);
//		
//	}
	

	public void getRefAndDecCounts(String pathname) throws IOException {
		
		//String pathname = "\\home\\ugc\\zachary.kahn\\Desktop\\SENG300_GroupAssign1_TestFiles";
		
		//String pathname = args[0];
		File folder = new File(pathname);
		
		System.out.println(folder);

		System.out.println(pathname);
		
		FilenameFilter filter = new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".java");
			}
		};

		File[] listOfFiles = folder.listFiles(filter);
		
		DeclarationAndReferenceCounter fileParser = new DeclarationAndReferenceCounter();
	    

		for (int i = 0; i < listOfFiles.length; i++) {
		    File file = listOfFiles[i];
		    String name = file.getName();
		    System.out.println("parsing:	"+file.getName());
		    
		    String contents = new String(Files.readAllBytes(Paths.get(pathname+"/" + name)));
		    //System.out.println(contents);
		    
		    
		   
		    // do something with the file
		    //DeclarationAndReferenceCounter fileParser = new DeclarationAndReferenceCounter();
		    
		    CompilationUnit cu = fileParser.createCompilationUnit(contents);
		    
		    fileParser.countReferencesAndDeclarations(cu);
		    
		    //fileParser.printResults();
		    
		    
		    
		}
		fileParser.printResults();
}
	
	
}
