package community.flock.dialogflow.ci.dialogflow.helper;

import java.util.Arrays;

public enum Device {
	PHONE("phone"),
	SPEAKER("speaker"),
	SMART_DISPLAY("smart display"),
	NONE("");
	
	private String stringRepresentation;

	Device(String stringRepresenation) {
		this.stringRepresentation = stringRepresenation;
	}

	public String getStringRepresentation() {
		return stringRepresentation;
	}
	
	public static Device findByStringRepresenation(String stringRepresenation) {
		return Arrays.asList(Device.values()).stream()
				.filter(x -> x.getStringRepresentation().equals(stringRepresenation))
				.findFirst()
				.get();
	}
}
