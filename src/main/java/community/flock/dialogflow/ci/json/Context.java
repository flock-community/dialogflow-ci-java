package community.flock.dialogflow.ci.json;

public class Context {
	private String name;
	private int lifespanCount;
	private Object parameters;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLifespanCount() {
		return lifespanCount;
	}
	public void setLifespanCount(int lifespanCount) {
		this.lifespanCount = lifespanCount;
	}
	public Object getParameters() {
		return parameters;
	}
	public void setParameters(Object parameters) {
		this.parameters = parameters;
	}
}
