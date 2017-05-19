package test.hwms.model;

public class UserNotFoundException extends Exception {
	private static final long serialVersionUID = 2069495188872990195L;
	
	private String id;
	
	public UserNotFoundException(String id) {
		super("User not found for id '" + id + "'");
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
}
