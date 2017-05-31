package test.hwms.domain;

public class UserNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 2069495188872990195L;
	
	private int id;
	
	public UserNotFoundException(int id) {
		super("User not found for id " + id);
		this.id = id;
	}
	
	public int getId() {
		return id;
	}
}
