package in.rbac.meta;

public class WRITER extends Role {
	
	public WRITER() {
		super();
		
		this.permissions.add(Permissions.READ);
		this.permissions.add(Permissions.WRITE);
	}
}
