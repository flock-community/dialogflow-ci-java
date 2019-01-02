package community.flock.dialogflow.ci.json;

public class Payload {
	
	private boolean isInSandbox;
	private Surface surface;
	private User user;
	
	public boolean isInSandbox() {
		return isInSandbox;
	}
	public void setInSandbox(boolean isInSandbox) {
		this.isInSandbox = isInSandbox;
	}
	public Surface getSurface() {
		return surface;
	}
	public void setSurface(Surface surface) {
		this.surface = surface;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
