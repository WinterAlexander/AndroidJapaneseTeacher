package me.winter.japteacher.alphabet;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.winter.japteacher.JapaneseCharacter;

/**
 * Created by Alexander Winter on 2016-09-19.
 */
public abstract class Alphabet
{
	private List<JapaneseCharacter> charList;

	public Alphabet()
	{
		this.charList = new ArrayList<>();
	}

	protected void loadFromResource(Resources resources, int resource)
	{
		try
		{
			InputStream stream = resources.openRawResource(resource);
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

		String line;

		while((line = reader.readLine()) != null)
		{
			String[] parts = line.split(" ");
			String comment = "";

			for(int i = 2; i < parts.length; i++)
				comment += parts[i] + " ";

			charList.add(new JapaneseCharacter(parts[0], parts[1], comment));
		}
	}

	public List<JapaneseCharacter> getChars()
	{
		return this.charList;
	}

	public JapaneseCharacter fromRomaji(String romaji)
	{
		for(JapaneseCharacter jchar : charList)
			if(jchar.isValid(romaji))
				return jchar;

		return null;
	}

	public boolean containsRomaji(String input)
	{
		for(JapaneseCharacter jchar : charList)
			if(jchar.isValid(input))
				return true;

		return false;
	}
}
