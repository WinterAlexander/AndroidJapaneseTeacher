package me.winter.japteacher;

import android.support.annotation.NonNull;

import java.io.Serializable;

public class JapaneseCharacter implements Serializable
{
	private String symbol;
	private String romaji;

	public JapaneseCharacter(@NonNull String symbol, @NonNull String romaji)
	{
		this.symbol = symbol;
		this.romaji = romaji;
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

	public boolean isValid(String input)
	{
		return romaji.equalsIgnoreCase(input.trim());
	}
}
