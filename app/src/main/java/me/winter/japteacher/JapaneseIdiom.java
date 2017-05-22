package me.winter.japteacher;

import android.support.annotation.NonNull;

import java.io.Serializable;

/**
 * Undocumented :(
 * <p>
 * Created by Alexander Winter on 2017-05-22.
 */
public class JapaneseIdiom implements Serializable
{
	private String content, translation;

	public JapaneseIdiom(@NonNull String content, @NonNull String translation)
	{
		this.content = content;
		this.translation = translation;
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

	public boolean isSynonym(JapaneseIdiom other)
	{
		return translation.equalsIgnoreCase(other.getTranslation());
	}
}
