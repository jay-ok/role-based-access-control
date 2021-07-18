package in.rbac.meta;

import java.util.*;

public abstract class Role {
	protected Set<Permissions> permissions = new HashSet<>();
	
	public boolean isAllowed(Permissions op) {
		
		// System.out.println(permissions.toString());
		
		if (permissions.contains(op)) return true;
		
		return false;
	}
}
