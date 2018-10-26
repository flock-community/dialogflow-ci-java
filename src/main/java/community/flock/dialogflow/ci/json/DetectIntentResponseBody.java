package community.flock.dialogflow.ci.json;

public class DetectIntentResponseBody {
	private String responseId;
	private QueryResult queryResult;
	private Status webhookStatus;
	
	public String getResponseId() {
		return responseId;
	}
	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}
	public Status getWebhookStatus() {
		return webhookStatus;
	}
	public void setWebhookStatus(Status webhookStatus) {
		this.webhookStatus = webhookStatus;
	}
	public QueryResult getQueryResult() {
		return queryResult;
	}
	public void setQueryResult(QueryResult queryResult) {
		this.queryResult = queryResult;
	}

}
