package quiz.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * Created by 1533564 on 2016-09-19.
 */
public class DefinedSentenceQuiz implements Quiz
{
	private String resourcePath;
	private Map<String, String> sentences;
	private int score;

	private Random random = new Random();

	public DefinedSentenceQuiz(String resourcePath)
	{
		this.resourcePath = resourcePath;
		reset();
	}

	@Override
	public String getName()
	{
		return "Sentences from " + resourcePath;
	}

	@Override
	public void start(Scanner scanner, PrintStream out)
	{
		reset();
		Map.Entry<String, String> sentence = null;
		String answer;

		do
		{
			if(sentence != null)
				sentences.remove(sentence.getKey());

			sentence = randomSentence();
			out.println("Tappez le romaji de cette phrase: ");
			out.println(sentence.getKey());
			out.print(">");

			answer = scanner.nextLine().trim();

			if(sentence.getValue().equalsIgnoreCase(answer))
			{

			}
			else
			{

			}

		}
		while(answer.equalsIgnoreCase("oui"));

	}

	@Override
	public void reset()
	{
		this.score = -1;
		load();
	}

	@Override
	public int getScore()
	{
		return score;
	}

	private void load()
	{
		try
		{
			BufferedReader reader = new BufferedReader(new InputStreamReader(DefinedSentenceQuiz.class.getResourceAsStream(resourcePath)));

			String[] lines = reader.lines().toArray(String[]::new);

			for(int i = 0; i < lines.length - 1; i += 2)
				sentences.put(lines[i], lines[i + 1]);

			reader.close();
		}
		catch(IOException ex)
		{
			ex.printStackTrace();
		}
	}

	public Map.Entry<String, String> randomSentence()
	{
		return sentences.entrySet().toArray(new Map.Entry[sentences.size()])[random.nextInt(sentences.size())];
	}
}
