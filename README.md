<b>Role Based Access Control on Documents</b>

Document: <br />
	- name: string <br />
	- id: int <br />
	- text: string <br />

DocumentStore: <br />
	- docIdGenrator: Atomic Int <br />
	- documents: map<docId, fileLocation> <br />
	- roles: map<docId, map<userId, role>> <br />
	+ editRole(docId: int, userId: string, newRole: Role): void <br />
	+ createDocument(name: string, text: string) <br />
	+ readDocument(docId: int) <br />
	+ updateDocument(docId: int, text: string) <br />
	+ deleteDocument(docId: int) <br />
	
Permissions:<br />
	- READ<br />
	- WRITE<br />
	- DELETE<br />
	- ASSIGN_READ<br />
	- ASSIGN_WRITE<br />
	- ASSIGN_NONE<br />
	
Roles:<br />
	- OWNER: SET(READ, WRITE, DELETE, ASSIGN_READ, ASSIGN_WRITE, ASSIGN_NONE)<br />
	- READER: SET(READ)<br />
	- WRITER: SET(READ, WRITE)<br />

DocumentService:<br />
	- documentStore: singleton instance<br />
	+ editRole(docId: int, ownerId: string, userId: string, newRole: string)<br />
	+ createDocument(name: string, text: string, userId: string): docId<br />
	+ readDocument(docId: int, userId: string): Document<br />
	+ updateDocument(docId: int, text: string, userId: string): Document<br />
	+ deleteDocument(docId: int, userId: string): boolean<br />
	
	

