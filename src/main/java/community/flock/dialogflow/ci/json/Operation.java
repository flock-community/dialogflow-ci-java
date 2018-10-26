package community.flock.dialogflow.ci.json;

public class Operation {
	private String name;
	private boolean done;
	private ExportAgentResponse response;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isDone() {
		return done;
	}
	public void setDone(boolean done) {
		this.done = done;
	}
	public ExportAgentResponse getResponse() {
		return response;
	}
	public void setResponse(ExportAgentResponse response) {
		this.response = response;
	}
}
