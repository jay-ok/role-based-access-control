package in.rbac.test;

import in.rbac.services.DocumentService;

public class Test {

	static DocumentService dService = new DocumentService();
	
	public static void main(String[] args) {
		
		dService.createDocument("first document", "Some text for first doc", "amanda");
		dService.createDocument("second document", "Some text for second doc", "megan");
		dService.createDocument("Third document", "Some text for Third doc", "margot");
		
		// try reading by owner
		//tryReading(1,  "amanda");
		
		// Assign read role by not-owner
		//testAssignRole(1, "megan", "margot", "reader");
		
		// try reading
		//tryReading(1,  "margot");

		// Assign read role by owner
		//testAssignRole(1, "amanda", "margot", "reader");
		
		// try reading
		//tryReading(1,  "margot");
		
		// Assign write role by owner
		testAssignRole(1, "amanda", "margot", "writer");
		
		// try writing
		tryWriting(1,  "margot", "updated dcouments");
		
		// try reading
		tryReading(1,  "margot");
		
		// Delete all role
		testAssignRole(1, "amanda", "margot", "none");
		
		// try reading
		tryReading(1,  "margot");
	}
	
	private static void testAssignRole(int docID, String owner, String user, String role) {
		try {

			dService.editRole(docID, owner, user, role);
			System.out.println("Updated role for 1 id doc");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void tryReading(int docID, String user) {
		try {
			System.out.println(dService.readDocument(docID, user));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void tryWriting(int docID, String user, String newText) {
		try {
			System.out.println(dService.updateDocument(docID, newText, user));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
