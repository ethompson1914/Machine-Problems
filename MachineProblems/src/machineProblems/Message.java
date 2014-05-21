package machineProblems;
import java.util.List;

public class Message {
	
	protected String message;
	
	public Message(String messageString)
	{
		message = messageString;
	}
	
	public String getMessage()
	{
		return message;
	}
	
	public boolean verify()
	{
		Dictionary dict = new Dictionary("C:\\Users\\Eric\\Desktop\\Dictionary.txt");
		List<String> wordList;
		
		String[] words = message.split(" ");
		for (String word : words) {
			word = word.toLowerCase();
			wordList = dict.lookup(word);
			if (wordList.size() == 0) return false;
		}
		return true;

	}

	public String toString()
	{
		return message;
	}
}