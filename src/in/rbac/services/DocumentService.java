package in.rbac.services;

import java.util.*;

import in.rbac.meta.GUEST;
import in.rbac.meta.OWNER;
import in.rbac.meta.Permissions;
import in.rbac.meta.READER;
import in.rbac.meta.Role;
import in.rbac.meta.WRITER;
import in.rbac.model.DocumentStore;

public class DocumentService {
	private static DocumentStore ds = new DocumentStore();
	
	// Ideally should be a factory
	// Re-factor after UT 
	private static Map<String, Role> ALL_ROLES = new HashMap<>();
	
	static {
		ALL_ROLES.put("owner", new OWNER());
		ALL_ROLES.put("reader", new READER());
		ALL_ROLES.put("writer", new WRITER());
		ALL_ROLES.put("none", new GUEST());
	}
	
	public int createDocument(String name, String text, String userId) {
		int docId = ds.createDocument(name, text);
		
		ds.editRole(docId, userId, ALL_ROLES.get("owner"));
		
		return docId;
	}
	
	public void editRole(int docId, String ownerId, String userId, String newRole) {
		
		Role assigned = this.getDocumentRoleForUser(docId, ownerId);
		
		if (assigned == null || assigned.isAllowed(Permissions.valueOf(("assign_" + newRole).toUpperCase())) == false) {
			
			throw new RuntimeException("Operation not allowed on " + docId);
		}
		
		ds.editRole(docId, userId, ALL_ROLES.get(newRole));
	}
	
	public String readDocument(int docId, String userId) {
		
		Role assigned = this.getDocumentRoleForUser(docId, userId);
		
		if (assigned == null || assigned.isAllowed(Permissions.READ) == false) {
			
			throw new RuntimeException("Read Operation not allowed on " + docId);
		}
		
		return ds.readDocument(docId);
	}
	
	public String updateDocument(int docId, String text, String userId) {
		
		Role assigned = this.getDocumentRoleForUser(docId, userId);
		
		if (assigned == null || assigned.isAllowed(Permissions.WRITE) == false) {
			
			throw new RuntimeException("Write Operation not allowed on " + docId);
		}
		
		return ds.updateDocument(docId, text);
	}
	
	public boolean deleteDocument(int docId, String userId) {
		
		Role assigned = this.getDocumentRoleForUser(docId, userId);
		
		if (assigned == null || assigned.isAllowed(Permissions.READ) == false) {
			
			throw new RuntimeException("Read Operation not allowed on " + docId);
		}
		
		return ds.deleteDocument(docId);
	}
	
	private Role getDocumentRoleForUser(int docId, String userId) {
		if (ds.getDocs().containsKey(docId) == false) {
			throw new RuntimeException("No such doument exist " + docId);
		}

		return ds.getDocRolesForUser(docId, userId);
	}
	
}
