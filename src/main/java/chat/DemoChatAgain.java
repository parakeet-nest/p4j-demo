package chat;

import org.parakeetnest.parakeet4j.llm.Message;
import org.parakeetnest.parakeet4j.llm.Options;
import org.parakeetnest.parakeet4j.llm.Query;

import java.util.List;

import static org.parakeetnest.parakeet4j.completion.Completion.Chat;

public class DemoChatAgain
{
    public static void main( String[] args )
    {

        Options options = new Options()
                .setTemperature(0.0)
                .setRepeatLastN(2);

        var systemContent = "You are a useful AI agent, expert with the Star Trek franchise.";
        var userContent = "Who is James Tiberius Kirk?";

        List<Message> messages = List.of(
                new Message("system", systemContent),
                new Message("user", userContent)
        );

        Query queryChat = new Query("tinyllama", options)
                .setMessages(messages);

        var resultAnswer = Chat("http://0.0.0.0:11434", queryChat);

        if (resultAnswer.exception().isEmpty()) {
            System.out.println("🙂: " +
                    resultAnswer.getAnswer()
                            .getMessage().getContent()
            );
        } else {
            System.out.println("😡: " + resultAnswer.exception().toString());
        }
    }
}
