package community.flock.dialogflow.ci.json;

public class SimpleResponse {
	private String textToSpeech;
	private String ssml;
	private String displayText;
	
	public String getTextToSpeech() {
		return textToSpeech;
	}
	public void setTextToSpeech(String textToSpeech) {
		this.textToSpeech = textToSpeech;
	}
	public String getSsml() {
		return ssml;
	}
	public void setSsml(String ssml) {
		this.ssml = ssml;
	}
	public String getDisplayText() {
		return displayText;
	}
	public void setDisplayText(String displayText) {
		this.displayText = displayText;
	}
}
