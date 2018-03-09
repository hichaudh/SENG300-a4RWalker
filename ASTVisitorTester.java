import org.eclipse.jdt.core.dom.*;
import java.util.HashMap;



public class ASTVisitorTester {
	
	
	
	public static void main(String[] args) {
		
		ASTVisitorTester temp = new ASTVisitorTester();
		
		String helloWorld = "public class HelloWorld {
			public static void testClass(){
				public void testMethod(){
				}
			}

    			public static void main(String[] args) {
        		// Prints "Hello, World" to the terminal window.
        		System.out.println("Hello, World");
			testClass t = new testClass();
			
    			}
		}"
		
		
		String testClass = "public class DoesItWork{\n"
				+ "private class MaybeWorks{} \n"
				+ "public void add(){ 1 + 1; }"
				+ "public void add2(){ add() }"
				+ "String a;"
				+ "a = \"Hello\";"
				+ "int b;"
				+ "char c;"
				+ "int d;"
				+ "Time e;"
				+ "enum Quark{ UP, DOWN}"
				+ "}\n"
				+ "interface PleaseWork{}\n";
		
		String sourceForCompilationUnit = "class TicketToTravel {\n" + 
	    		"  Float price;\n" + 
	    		"  String destination;\n" + 
	    		"  Date departureDate;\n" +
	    	    "  int[] ia; \n" + 
	    		"  void signMeUp(  Float forPrice,  String forDest,  Date forDate){\n" + 
	    		"    price=forPrice;\n" + 
	    		"    destination=forDest;\n" + 
	    		"    departureDate=forDate;\n" + 
	    		"  }\n" + 
	    		"}\n";
		
		
		String result = temp.parseCompilationUnit(sourceForCompilationUnit);
		
		//System.out.println(result);
		
		
		CompilationUnit cu = temp.createCompilationUnit(testClass);
		
		
		 class SpecificVisitor extends ASTVisitor{
			
			 public boolean visit(PrimitiveType node) {
					
					System.out.println(node.getNodeType());
					
					return true;
				}
			 
		}
		 
		
//		ASTVisitor visitor = new ASTVisitor() {
//			public boolean visit(ASTNode node) {
//				
//				System.out.println(node.getNodeType());
//				
//				return true;
//			}
//			};
//			
		//ASTVisitor visitor = new SpecificVisitor();
		
		HashMap<String,Integer> log = new HashMap<>();
		 
		cu.accept(new ASTVisitor() {
			public boolean visit(PrimitiveType node) {
				
				System.out.print(node.toString());
				
				System.out.println(" " + node.getNodeType());
				
				if (log.containsKey(node.toString())) {
					log.put(node.toString(), log.get(node.toString())+1);
				} else {
					log.put(node.toString(), 1);
				}
				
				return true;
			}
			
			public boolean visit(SimpleType node) {
				
				System.out.print(node.toString());
				
				System.out.println(" " + node.getNodeType());
				
				if (log.containsKey(node.toString())) {
					log.put(node.toString(), log.get(node.toString())+1);
				} else {
					log.put(node.toString(), 1);
				}
				
				return true;
			}
			
			public boolean visit(ArrayType node) {
				
				System.out.println(node.toString());
				
				if (log.containsKey(node.toString())) {
					log.put(node.toString(), log.get(node.toString())+1);
				} else {
					log.put(node.toString(), 1);
				}
				
				return true;
			}
			
			public boolean visit(TypeDeclaration node) {
				
				System.out.println(node.toString());
				
				System.out.println(" " + node.getNodeType());
				
				if (log.containsKey("classDec")) {
					log.put("classDec", log.get("classDec")+1);
				} else {
					log.put("classDec", 1);
				}
				
				return true;
			}
			
			public boolean visit(EnumDeclaration node) {
				
				System.out.println(node.toString());
				
				System.out.println(" " + node.getNodeType());
				
				if (log.containsKey(node.toString())) {
					log.put(node.toString(), log.get(node.toString())+1);
				} else {
					log.put(node.toString(), 1);
				}
				return true;
			}
			
			public boolean visit(AnnotationTypeDeclaration node) {
				
				System.out.println(node.toString());
				
				System.out.println(" " + node.getNodeType());
				
				if (log.containsKey(node.toString())) {
					log.put(node.toString(), log.get(node.toString())+1);
				} else {
					log.put(node.toString(), 1);
				}
				return true;
			}
			
			});
		
		
		
		
		System.out.println(log.toString());
			
		
		
		
		
		
		
	}

	
	
	
	
	
	public CompilationUnit createCompilationUnit(String sourceCode) {
		  ASTParser parser = ASTParser.newParser(AST.JLS8);
		  parser.setSource(sourceCode.toCharArray());
		  parser.setKind(ASTParser.K_COMPILATION_UNIT);

		    CompilationUnit cu = (CompilationUnit)parser.createAST(null);
		    
		    return cu;
	  }
	
	
	  public String parseCompilationUnit(String sourceCode) {
		    ASTParser parser = ASTParser.newParser(AST.JLS8);
		    parser.setSource(sourceCode.toCharArray());
		    parser.setKind(ASTParser.K_COMPILATION_UNIT);
		   

		    CompilationUnit cu = (CompilationUnit)parser.createAST(null);
		    
		    //cu.accept(new ASTVisitor() {});    

		    return cu.toString();
		  }
	
	
}
