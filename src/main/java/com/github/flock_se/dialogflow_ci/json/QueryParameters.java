package com.github.flock_se.dialogflow_ci.json;

import java.util.List;

public class QueryParameters {
	private String timeZone;
	private LatLng geoLocation;
	private List<Context> contexts;
	private boolean resetContexts;
	private List<SessionEntityType> sessionEntityTypes; 
	private Object payload;
	
	public String getTimeZone() {
		return timeZone;
	}
	public void setTimeZone(String timeZone) {
		this.timeZone = timeZone;
	}
	public LatLng getGeoLocation() {
		return geoLocation;
	}
	public void setGeoLocation(LatLng geoLocation) {
		this.geoLocation = geoLocation;
	}
	public List<Context> getContexts() {
		return contexts;
	}
	public void setContexts(List<Context> contexts) {
		this.contexts = contexts;
	}
	public boolean isResetContexts() {
		return resetContexts;
	}
	public void setResetContexts(boolean resetContexts) {
		this.resetContexts = resetContexts;
	}
	public List<SessionEntityType> getSessionEntityTypes() {
		return sessionEntityTypes;
	}
	public void setSessionEntityTypes(List<SessionEntityType> sessionEntityTypes) {
		this.sessionEntityTypes = sessionEntityTypes;
	}
	public Object getPayload() {
		return payload;
	}
	public void setPayload(Object payload) {
		this.payload = payload;
	}
}
