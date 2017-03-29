package me.winter.japteacher;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.regex.Pattern;

public class JapaneseCharacter implements Serializable
{
	private String symbol, romaji, comment;

	public JapaneseCharacter(@NonNull String symbol, @NonNull String romaji)
	{
		this(symbol, romaji, null);
	}
	
	public JapaneseCharacter(@NonNull String symbol, @NonNull String romaji, String comment)
	{
		this.symbol = symbol;
		this.romaji = romaji;
		this.comment = comment;
	}

	@NonNull
	public String getSymbol()
	{
		return this.symbol;
	}

	@NonNull
	public String getRomaji()
	{
		return this.romaji;
	}
	
	public String getComment()
	{
		return this.comment;
	}

	public boolean isValid(String input)
	{
		String[] parts = romaji.split(Pattern.quote("|"));
		
		for(String part : parts)
			if(part.equalsIgnoreCase(input.trim()))
				return true;
		return false;
	}
}
