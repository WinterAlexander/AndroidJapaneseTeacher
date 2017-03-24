package me.winter.japteacher;

import java.io.Serializable;

public class JapaneseCharacter implements Serializable
{
	private String symbol;
	private String romaji;

	public JapaneseCharacter(String symbol, String romaji)
	{
		this.symbol = symbol;
		this.romaji = romaji;
	}

	public String getSymbol()
	{
		return this.symbol;
	}

	public String getRomaji()
	{
		return this.romaji;
	}

	public boolean isValid(String input)
	{
		return romaji.equalsIgnoreCase(input.trim());
	}
}
