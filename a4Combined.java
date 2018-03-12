import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class a4Combined {
	
	public static void main(String[] args) throws IOException {
			String pathname = args[0];
			File folder = new File(pathname);
			
			FilenameFilter filter = new FilenameFilter() {
				public boolean accept(File dir, String name) {
					return name.endsWith(".java");
				}
			};

			File[] listOfFiles = folder.listFiles(filter);
			

			for (int i = 0; i < listOfFiles.length; i++) {
			    File file = listOfFiles[i];
			    String name = file.getName();
			    //System.out.println("parsing:	"+file.getName());
			    
			    String contents = new String(Files.readAllBytes(Paths.get(pathname+"\\" + name)));
			    //System.out.println(contents);
			    
			    
			   
			    // do something with the file
			    Parser fileParser = new Parser();
			    fileParser.parseFiles(contents);
			    
			}
	}
	
	public static class Parser {
		public void parseFiles(String source) {
			ASTParser parser = ASTParser.newParser(AST.JLS9);
			parser.setSource(source.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT); 
			
			CompilationUnit cu = (CompilationUnit)parser.createAST(null);
			
			HashMap<String,Integer> log = new HashMap<>();
			cu.accept(new ASTVisitor() {
				public boolean visit(PrimitiveType node) {
					
					//System.out.print(node.toString());
					
					//System.out.println(" " + node.getNodeType());
					if (!(node.toString().equals("void"))) {
						if (log.containsKey(node.toString())) {
							log.put(node.toString(), log.get(node.toString())+1);
						} else {
							log.put(node.toString(), 1);
						}
						
					}
					
					return true;
				}
				
				public boolean visit(SimpleType node) {
					
					//System.out.print(node.toString());
					
					//System.out.println(" " + node.getNodeType());
					
					if (log.containsKey(node.toString())) {
						log.put(node.toString(), log.get(node.toString())+1);
					} else {
						log.put(node.toString(), 1);
					}
					
					return true;
				}
				
				public boolean visit(ArrayType node) {
					
					//System.out.println(node.toString());
					
					if (log.containsKey(node.toString())) {
						log.put(node.toString(), log.get(node.toString())+1);
					} else {
						log.put(node.toString(), 1);
					}
					
					return true;
				}
				
				public boolean visit(TypeDeclaration node) {
					
					//System.out.println(node.toString());
					
					//System.out.println(" " + node.getNodeType());
					
					if (log.containsKey("classDec")) {
						log.put("classDec", log.get("classDec")+1);
					} else {
						log.put("classDec", 1);
					}
					
					return true;
				}
				
				public boolean visit(EnumDeclaration node) {
					
					//System.out.println(node.toString());
					
					//System.out.println(" " + node.getNodeType());
					
					if (log.containsKey(node.toString())) {
						log.put(node.toString(), log.get(node.toString())+1);
					} else {
						log.put(node.toString(), 1);
					}
					return true;
				}
				
				public boolean visit(AnnotationTypeDeclaration node) {
					
					//System.out.println(node.toString());
					
					//System.out.println(" " + node.getNodeType());
					
					if (log.containsKey(node.toString())) {
						log.put(node.toString(), log.get(node.toString())+1);
					} else {
						log.put(node.toString(), 1);
					}
					return true;
					}
	 
			});
			System.out.print(cu.toString());
			System.out.println("");
			System.out.println(log.toString());
		
		}
	}
}
