package me.winter.japteacher.alphabet;

import android.content.res.Resources;

import me.winter.japteacher.R;

public class Kanji extends Alphabet
{
	public Kanji(Resources resources)
	{
		super();
		loadFromResource(resources, R.raw.kanji);
	}

	@Override
	public String getName()
	{
		return "Kanji";
	}
}
