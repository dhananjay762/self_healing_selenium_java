package utility;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Locator {
	
	@JsonProperty("locatorName")
	private String locatorName;
	
	@JsonProperty("locatorType")
	private String locatorType;
	
	@JsonProperty("locator")
	private String locator;

	public String getLocatorName() {
		return locatorName;
	}

	public void setLocatorName(String locatorName) {
		this.locatorName = locatorName;
	}

	public String getLocatorType() {
		return locatorType;
	}

	public void setLocatorType(String locatorType) {
		this.locatorType = locatorType;
	}

	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

}
