package quiz.main;

import com.sun.istack.internal.NotNull;

public class JapaneseCharacter
{
	private String symbol;
	private String romaji;

	public JapaneseCharacter(@NotNull String symbol, @NotNull String romaji)
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
