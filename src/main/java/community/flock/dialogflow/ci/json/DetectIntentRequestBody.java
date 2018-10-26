package community.flock.dialogflow.ci.json;

public class DetectIntentRequestBody {
	private QueryParameters queryParams;
	private QueryInput queryInput;
	private String inputAudio;
	
	public QueryParameters getQueryParams() {
		return queryParams;
	}
	public void setQueryParams(QueryParameters queryParameters) {
		this.queryParams = queryParameters;
	}
	public QueryInput getQueryInput() {
		return queryInput;
	}
	public void setQueryInput(QueryInput queryInput) {
		this.queryInput = queryInput;
	}
	public String getInputAudio() {
		return inputAudio;
	}
	public void setInputAudio(String inputAudio) {
		this.inputAudio = inputAudio;
	}
}
