<b>Role Based Access Control on Documents</b>

Document: 
	- name: string
	- id: int
	- text: string

DocumentStore:
	- docIdGenrator: Atomic Int
	- documents: map<docId, fileLocation>
	- roles: map<docId, map<userId, role>>
	+ editRole(docId: int, userId: string, newRole: Role): void
	+ createDocument(name: string, text: string)
	+ readDocument(docId: int)
	+ updateDocument(docId: int, text: string)
	+ deleteDocument(docId: int)
	
Permissions:
	- READ
	- WRITE
	- DELETE
	- ASSIGN_READ
	- ASSIGN_WRITE
	- ASSIGN_NONE
	
Roles:
	- OWNER: SET(READ, WRITE, DELETE, ASSIGN_READ, ASSIGN_WRITE, ASSIGN_NONE)
	- READER: SET(READ)
	- WRITER: SET(READ, WRITE)

DocumentService:
	- documentStore: singleton instance
	+ editRole(docId: int, ownerId: string, userId: string, newRole: string)
	+ createDocument(name: string, text: string, userId: string): docId
	+ readDocument(docId: int, userId: string): Document
	+ updateDocument(docId: int, text: string, userId: string): Document
	+ deleteDocument(docId: int, userId: string): boolean
	
	

