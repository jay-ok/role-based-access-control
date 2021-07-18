package in.rbac.model;

public class Document {
	private int id;
	private String name;
	private String text;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Document(int id, String name, String text) {
		super();
		this.id = id;
		this.name = name;
		this.text = text;
	}
	
	
}
