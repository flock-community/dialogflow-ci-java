package com.github.flock_se.dialogflow_ci.json;

import java.util.List;

public class QueryResult {
	private String queryText;
	private String languageCode;
	private double speechRecognitionConfidence;
	private String action;
	private Struct parameters;
	private boolean allRequiredParamsPresent;
	private String fulfillmentText;
	private List<Message> fulfillmentMessages;
	private String webhookSource;
	private Struct webhookPayload;
	private List<Context> outputContexts;
	private Intent intent;
	private double intentDetectionConfidence;
	private Struct diagnosticInfo;
	
	public String getQueryText() {
		return queryText;
	}
	public void setQueryText(String queryText) {
		this.queryText = queryText;
	}
	public String getLanguageCode() {
		return languageCode;
	}
	public void setLanguageCode(String languageCode) {
		this.languageCode = languageCode;
	}
	public double getSpeechRecognitionConfidence() {
		return speechRecognitionConfidence;
	}
	public void setSpeechRecognitionConfidence(double speechRecognitionConfidence) {
		this.speechRecognitionConfidence = speechRecognitionConfidence;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Struct getParameters() {
		return parameters;
	}
	public void setParameters(Struct parameters) {
		this.parameters = parameters;
	}
	public boolean isAllRequiredParamsPresent() {
		return allRequiredParamsPresent;
	}
	public void setAllRequiredParamsPresent(boolean allRequiredParamsPresent) {
		this.allRequiredParamsPresent = allRequiredParamsPresent;
	}
	public String getFulfillmentText() {
		return fulfillmentText;
	}
	public void setFulfillmentText(String fulfillmentText) {
		this.fulfillmentText = fulfillmentText;
	}
	public List<Message> getFulfillmentMessages() {
		return fulfillmentMessages;
	}
	public void setFulfillmentMessages(List<Message> fulfillmentMessages) {
		this.fulfillmentMessages = fulfillmentMessages;
	}
	public String getWebhookSource() {
		return webhookSource;
	}
	public void setWebhookSource(String webhookSource) {
		this.webhookSource = webhookSource;
	}
	public Struct getWebhookPayload() {
		return webhookPayload;
	}
	public void setWebhookPayload(Struct webhookPayload) {
		this.webhookPayload = webhookPayload;
	}
	public List<Context> getOutputContexts() {
		return outputContexts;
	}
	public void setOutputContexts(List<Context> outputContexts) {
		this.outputContexts = outputContexts;
	}
	public Intent getIntent() {
		return intent;
	}
	public void setIntent(Intent intent) {
		this.intent = intent;
	}
	public double getIntentDetectionConfidence() {
		return intentDetectionConfidence;
	}
	public void setIntentDetectionConfidence(double intentDetectionConfidence) {
		this.intentDetectionConfidence = intentDetectionConfidence;
	}
	public Struct getDiagnosticInfo() {
		return diagnosticInfo;
	}
	public void setDiagnosticInfo(Struct diagnosticInfo) {
		this.diagnosticInfo = diagnosticInfo;
	}
}
