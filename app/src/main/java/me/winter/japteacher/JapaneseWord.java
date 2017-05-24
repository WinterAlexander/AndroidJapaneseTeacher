package me.winter.japteacher;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Undocumented :(
 * <p>
 * Created by Alexander Winter on 2017-05-22.
 */
public class JapaneseWord implements Serializable
{
	private String content, translation;
	private List<String> synonyms;

	public JapaneseWord(@NonNull String content, @NonNull String translation)
	{
		this.content = content;
		this.translation = translation;
		synonyms = new ArrayList<>();
	}

	@NonNull
	public String getContent()
	{
		return content;
	}

	@NonNull
	public String getTranslation()
	{
		return translation;
	}

	public List<String> getSynonyms()
	{
		return synonyms;
	}
}
