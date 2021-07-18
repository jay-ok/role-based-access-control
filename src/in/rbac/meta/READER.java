package in.rbac.meta;

public class READER extends Role {

	public READER() {
		super();
		
		this.permissions.add(Permissions.READ);
	}
}
