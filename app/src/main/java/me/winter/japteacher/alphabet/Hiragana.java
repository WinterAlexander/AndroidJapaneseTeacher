package me.winter.japteacher.alphabet;

import android.content.res.Resources;

import me.winter.japteacher.R;

public class Hiragana extends Alphabet
{
	public Hiragana(Resources resources)
	{
		super();
		loadFromResource(resources, R.raw.hiragana);
	}
}
