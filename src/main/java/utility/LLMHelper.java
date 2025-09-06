package utility;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;

import dev.langchain4j.model.googleai.GoogleAiGeminiChatModel;
import io.github.cdimascio.dotenv.Dotenv;

public class LLMHelper {
	
	private static Dotenv dotenv;
	private static String apiKey;
	
	public static String getPageLocatorsAsJsonFromOpenAI(String pageSource) {
		
		dotenv = Dotenv.load();
		apiKey = dotenv.get("OPENAI_API_KEY");
		
		OpenAIClient client = OpenAIOkHttpClient.builder().apiKey(apiKey).build();
		
		ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
				.addUserMessage(String.format("Return only a JSON array of locators from the page source %s, provide the locator for the elements described in JSON format like \r\n"
						+ "{\r\n"
						+ "	\"locatorName\":\"name of the locator should match the label name if exist\",\r\n"
						+ "	\"locatorType\":\"id|name|css|xpath|linkText\",\r\n"
						+ "	\"locator\":\"value\"\r\n"
						+ "}\r\n"
						+ ". Do not include any explanations, markdown formatting, or additional text.", pageSource))
				.model(ChatModel.GPT_3_5_TURBO)
				.build();
		
		ChatCompletion chatCompletion = client.chat().completions().create(params);
		String response = chatCompletion.choices().get(0).message()._content().toString();
		
		return response;
	}

	public static String getPageLocatorsAsJsonFromGemini(String pageSource) {
	
		dotenv = Dotenv.load();
		apiKey = dotenv.get("GEMINI_API_KEY");
		
		GoogleAiGeminiChatModel model = GoogleAiGeminiChatModel.builder()
				.apiKey(apiKey)
				.modelName("gemini-2.5-flash")
				.build();		
		
		String response = model.chat(String.format("Return only a JSON array of locators from the page source %s, provide the locator for the elements described in JSON format like \r\n"
				+ "{\r\n"
				+ "	\"locatorName\":\"name of the locator should match the label name if exist\",\r\n"
				+ "	\"locatorType\":\"id|name|css|xpath|linkText\",\r\n"
				+ "	\"locator\":\"value\"\r\n"
				+ "}\r\n"
				+ ". Do not include any explanations, markdown formatting, or additional text.", pageSource));
		
		return response;
	
	}

}
