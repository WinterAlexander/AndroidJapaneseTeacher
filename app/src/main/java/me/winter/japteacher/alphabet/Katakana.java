package me.winter.japteacher.alphabet;


import android.content.res.Resources;

import me.winter.japteacher.R;

public class Katakana extends Alphabet
{
	public Katakana(Resources resources)
	{
		super();
		loadFromResource(resources, R.raw.katakana);
	}

}
