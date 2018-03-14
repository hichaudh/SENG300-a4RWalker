import org.eclipse.jdt.core.dom.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.Stack;



public class DeclarationAndReferenceCounter {

	
	private String[] javaLangArray = {"Boolean","Byte","Character","Double",
			"Float","Integer","Long","Math","Number","Object","String","StringBuffer",
			"Short","System"};
	
	private List<String> javaLangList = Arrays.asList(javaLangArray);
	
	private HashMap<String,Integer> logReferences = new HashMap<>();
	
	private HashMap<String,Integer> logDeclarations = new HashMap<>();
	
	private Stack<String> typeStack = new Stack<>();
	
	

	
	
	public CompilationUnit createCompilationUnit(String sourceCode) {
		  ASTParser parser = ASTParser.newParser(AST.JLS8);
		  parser.setSource(sourceCode.toCharArray());
		  parser.setKind(ASTParser.K_COMPILATION_UNIT);

		  // Adding bindings
		  parser.setBindingsRecovery(true);
		  
		    CompilationUnit cu = (CompilationUnit)parser.createAST(null);
		    
		    
		    
		    return cu;
	  }
	
	public void countReferencesAndDeclarations(CompilationUnit cu) {
		cu.accept(new ASTVisitor() {
			public boolean visit(PrimitiveType node) {
				
				
				if(!(node.toString().equals("void"))) {
					
					if (logReferences.containsKey(node.toString())) {
						logReferences.put(node.toString(), logReferences.get(node.toString())+1);
					} else {
						logReferences.put(node.toString(), 1);
					}
					
				}
				
				
				return true;
			}
			
			public boolean visit(SimpleType node) {
				
				String curNode = node.toString();
				
				if (javaLangList.contains(curNode)) {
					curNode = "java.lang."+ curNode;
				}
				
				if (logReferences.containsKey(curNode)) {
					
					logReferences.put(curNode, logReferences.get(curNode)+1);
				} else {
					logReferences.put(curNode, 1);
				}
				
				return true;
			}
			
			public boolean visit(ArrayType node) {

				
				if (logReferences.containsKey(node.toString())) {
					logReferences.put(node.toString(), logReferences.get(node.toString())+1);
				} else {
					logReferences.put(node.toString(), 1);
				}
				
				return true;
			}
			
			public boolean visit(TypeDeclaration node) {
		
				
				typeStack.push(node.getName().toString());
				
				return true;
			}
			
			public boolean visit(PackageDeclaration node) {
				
				typeStack.push(node.getName().toString());
				
				return true;
			}
			
			public boolean visit(AnonymousClassDeclaration node) {
				
				System.out.println("Anon Class");
				
				return true;
			}
			
			
			
			public void endVisit(TypeDeclaration node) {
				
				String commonName = "";
				for (String ele: typeStack) {
					commonName += ele +".";
				}
				
				commonName = commonName.substring(0, commonName.length()-1);
				
				if (logDeclarations.containsKey(commonName)) {
					logDeclarations.put(commonName, logDeclarations.get(commonName)+1);
				} else {
					logDeclarations.put(commonName, 1);
				}
				
				typeStack.pop();
				
			}
			
			public boolean visit(EnumDeclaration node) {
	
				
				if (logReferences.containsKey(node.toString())) {
					logReferences.put(node.toString(), logReferences.get(node.toString())+1);
				} else {
					logReferences.put(node.toString(), 1);
				}
				return true;
			}
			
			public boolean visit(AnnotationTypeDeclaration node) {
				
				System.out.println(node.toString());
					
				if (logReferences.containsKey(node.toString())) {
					logReferences.put(node.toString(), logReferences.get(node.toString())+1);
				} else {
					logReferences.put(node.toString(), 1);
				}
				return true;
			}
			
		
			
			});
	}
	
	
	public void printResults() {
		Set<String> refKeys = logReferences.keySet();
		
		Set<String> decKeys = logDeclarations.keySet();
		
		
		
		for (String key: refKeys) {
			if(logDeclarations.containsKey(key)) {
				System.out.println(key +". Declarations found: "+ logDeclarations.get(key)+ "; references found:" + logReferences.get(key)+".");
			} else {
				System.out.println(key +". Declarations found: 0; references found:" + logReferences.get(key)+".");
					
			}
		}
		
		for (String key: decKeys) {
			if (!(logReferences.containsKey(key))) {
				System.out.println(key +". Declarations found: " + logDeclarations.get(key) + "; references found: 0.");
				}
		}
	}
}
