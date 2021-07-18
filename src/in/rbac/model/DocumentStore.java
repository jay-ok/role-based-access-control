package in.rbac.model;

import java.util.*; // for saving time
import java.util.concurrent.atomic.AtomicInteger;

import in.rbac.meta.Role;

public class DocumentStore {
	
	Map<Integer, Document> docs = new HashMap<>();
	Map<Integer, Map<String, Role>> roles = new HashMap<>();
	
	AtomicInteger docIdGen = new AtomicInteger();
	
	public int createDocument(String name, String text) {
		int docId = docIdGen.incrementAndGet();
		
		docs.put(docId, new Document(docId, name, text));
		
		roles.put(docId, new HashMap<>());
		
		return docId;
	}
	
	public void editRole(int docId, String userId, Role newRole) {
		Map<String, Role> assigned = roles.get(docId);
		assigned.put(userId, newRole);
	}
	
	public String readDocument(int docId) {
		return docs.get(docId).getText();
	}
	
	public String updateDocument(int docId, String text) {
		docs.get(docId).setText(text);
		return text;
	}
	
	public boolean deleteDocument(int docId) {
		
		docs.remove(docId);
		roles.remove(docId);
		
		return true;
	}

	public Map<Integer, Document> getDocs() {
		return docs;
	}

	public Role getDocRolesForUser(int docId, String userId) {
		
		return roles.get(docId).get(userId);
	}
	
}
