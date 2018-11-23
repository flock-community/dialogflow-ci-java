package community.flock.dialogflow.ci.json;


public class Message {
	private Text text;
	private Card card;
	private BasicCard basicCard;
	private SimpleResponses simpleResponses;
	
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	public BasicCard getBasicCard() {
		return basicCard;
	}
	public void setBasicCard(BasicCard basicCard) {
		this.basicCard = basicCard;
	}
	public Text getText() {
		return text;
	}
	public void setText(Text text) {
		this.text = text;
	}
	public SimpleResponses getSimpleResponses() {
		return simpleResponses;
	}
	public void setSimpleResponses(SimpleResponses simpleResponses) {
		this.simpleResponses = simpleResponses;
	}
}
