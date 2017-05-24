package me.winter.japteacher;

import android.content.res.Resources;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Undocumented :(
 * <p>
 * Created by Alexander Winter on 2017-05-24.
 */
public class Dictionary implements Serializable
{
	private List<JapaneseWord> wordList;

	public Dictionary()
	{
		this.wordList = new ArrayList<>();
	}

	public void loadFromResource(Resources resources, int resource)
	{
		try
		{
			InputStream stream = resources.openRawResource(resource);
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));

			String line;

			while((line = reader.readLine()) != null)
			{
				String[] parts = line.split(" ");

				JapaneseWord word = new JapaneseWord(parts[0], parts[1]);

				wordList.add(word);
			}
			stream.close();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}

	public List<JapaneseWord> getWordList()
	{
		return wordList;
	}
}
