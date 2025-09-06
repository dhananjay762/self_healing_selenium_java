package utility;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class LocatorReader {
	
	private static Map<String, By> locatorMap = new HashMap<String, By>();
	
	public static void loadLocatorsFromJson(String jsonString) {
		
		ObjectMapper objectMapper = new ObjectMapper();
		By by;
		
		try {
			
			List<Locator> locators = objectMapper.readValue(jsonString, new TypeReference<List<Locator>>() {});
			
			for(Locator locator : locators) {
				String locatorKey = locator.getLocator();
				String locatorName = locator.getLocatorName();
				String locatorType = locator.getLocatorType();
				
				switch(locatorType) {
					case "id":
						by = By.id(locatorKey);
						break;
					case "name":
						by = By.name(locatorKey);
						break;
					case "css":
						by = By.cssSelector(locatorKey);
						break;
					case "xpath":
						by = By.xpath(locatorKey);
						break;
					case "linkText":
						by = By.linkText(locatorKey);
						break;
					default:
						throw new IllegalArgumentException("Unsupported locator type: "+locatorType);
				}
				
				locatorMap.put(locatorName, by);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	public static By findLocatorByPartialName(String name) {
		for(Map.Entry<String, By> entry : locatorMap.entrySet()) {
			if(entry.getKey().toLowerCase().contains(name.toLowerCase())) {
				return entry.getValue();
			}
		}
		throw new NoSuchElementException("No locator found containing: "+name);
	}

}
