package me.winter.japteacher.alphabet;

import android.content.res.Resources;

import me.winter.japteacher.R;

/**
 *
 * Created by Alexander Winter on 2016-09-21.
 */
public class SimpleKatakana extends Alphabet
{
	public SimpleKatakana(Resources resources)
	{
		super();
		loadFromResource(resources, R.raw.simplekatakana);
	}
}