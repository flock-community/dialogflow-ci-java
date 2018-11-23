package community.flock.dialogflow.ci.dialogflow.helper;

import java.util.HashSet;
import java.util.Set;

public class CoverageInfo {
	
	private Set<String> coveredIntents;
	private Set<String> allIntents;
	
	public CoverageInfo(Set<String> allIntents, Set<String> coveredIntents) {
		this.coveredIntents = coveredIntents;
		this.allIntents = allIntents;
	}
	
	public Set<String> getAllIntents() {
		return this.allIntents;
	}
	
	public Set<String> getCoveredIntents() {
		return this.coveredIntents;
	}
	
	public Set<String> getUncoveredIntents() {
		Set<String> clone = new HashSet<String>(allIntents);
		clone.removeAll(coveredIntents);
		return clone;
	}
}
