package in.rbac.meta;

public class OWNER extends Role {

	public OWNER() {
		super();
		
		this.permissions.add(Permissions.READ);
		this.permissions.add(Permissions.WRITE);
		this.permissions.add(Permissions.DELETE);
		this.permissions.add(Permissions.ASSIGN_READER);
		this.permissions.add(Permissions.ASSIGN_WRITER);
		this.permissions.add(Permissions.ASSIGN_NONE);
	}
}
