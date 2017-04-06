package quiz.main;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.currentTimeMillis;

/**
 *
 * Created by 1541869 on 2016-09-19.
 */
public class SingleSymbolQuiz<T extends Alphabet> implements Quiz
{
	private Random random = new Random();

	private Map<JapaneseCharacter, Integer> symbolsPriority;

	private String name;
	private T alphabet;

	private int score;
	private List<Float> timings = new ArrayList<>();

	public SingleSymbolQuiz(Class<T> alphabet, String name)
	{
		try
		{
			this.alphabet = alphabet.newInstance();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		this.name = name;

		this.symbolsPriority = new HashMap<>();
		this.alphabet.getChars().forEach(x -> symbolsPriority.put(x, 0));

		reset();
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public void start(Scanner scanner, PrintStream out)
	{
		reset();

		JapaneseCharacter toGuess;
		String input;

		out.println("Cet alphabet contient " + symbolsPriority.size() + " symboles.");

		for(;;)
		{
			score++;

			toGuess = random();
			long startTime = currentTimeMillis();
			boolean typingError;

			do
			{
				out.println("Tappez le romaji de cet " + alphabet.getName() + ": " + toGuess.getSymbol());
				out.print(">");

				input = scanner.nextLine();

				typingError = !alphabet.containsRomaji(input);

				if(typingError)
					out.println("Erreur de frappe !");

			}
			while(typingError);
			symbolsPriority.put(toGuess, symbolsPriority.get(toGuess) + 1);

			if(!toGuess.isValid(input))
				break;
			float delta = (currentTimeMillis() - startTime) / 1000f;

			out.println(delta + "s");
			out.println();
			out.println();
			timings.add(delta);
		}

		JapaneseCharacter answered = alphabet.fromRomaji(input);

		symbolsPriority.put(toGuess, symbolsPriority.get(toGuess) - 2);
		symbolsPriority.put(answered, symbolsPriority.get(toGuess));
		out.println("Raté, la réponse était " + toGuess.getRomaji());
		out.println("Vous avez répondu: " + answered.getSymbol() + "(" + input + ")");

		if(timings.size() > 0)
		{
			float avg = 0;

			for(Float time : timings)
				avg += time;

			avg /= timings.size();
			out.println("Temps de réponse moyen: " + avg + "s");
		}
	}

	@Override
	public void reset()
	{
		score = -1;
		timings.clear();
	}

	@Override
	public int getScore()
	{
		return score;
	}

	private JapaneseCharacter random()
	{
		List<JapaneseCharacter> lowests = new ArrayList<>();
		int lowestUses = Integer.MAX_VALUE;
		for(JapaneseCharacter current : symbolsPriority.keySet())
		{
			if(symbolsPriority.get(current) == lowestUses)
				lowests.add(current);
			else if(symbolsPriority.get(current) < lowestUses)
			{
				lowests.clear();
				lowests.add(current);
				lowestUses = symbolsPriority.get(current);
			}
		}

		if(lowests.size() <= 0)
			return null;

		return lowests.get(random.nextInt(lowests.size()));
	}
}
