package quiz.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 1541869 on 2016-09-19.
 */
public abstract class Alphabet
{
	private List<JapaneseCharacter> charList;

	abstract String getName();

	public Alphabet()
	{
		this.charList = new ArrayList<>();
	}

	protected void loadFromResource(String resource)
	{
		try
		{
			InputStream stream = Hiragana.class.getResourceAsStream(resource);
			loadFrom(stream);
			stream.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	protected void loadFrom(InputStream stream) throws IOException
	{
		new BufferedReader(new InputStreamReader(stream)).lines().forEach(x -> {
			String[] parts = x.split(" ");
			charList.add(new JapaneseCharacter(parts[0], parts[1]));
		});
	}

	public List<JapaneseCharacter> getChars()
	{
		return this.charList;
	}

	public JapaneseCharacter fromRomaji(String romaji)
	{
		for(JapaneseCharacter jchar : charList)
			if(jchar.getRomaji().equalsIgnoreCase(romaji))
				return jchar;

		return null;
	}

	public boolean containsRomaji(String input)
	{
		for(JapaneseCharacter jchar : charList)
			if(jchar.getRomaji().equalsIgnoreCase(input))
				return true;

		return false;
	}
}
