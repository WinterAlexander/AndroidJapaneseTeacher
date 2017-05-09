package me.winter.japteacher.alphabet;

import android.content.res.Resources;
import me.winter.japteacher.R;

/**
 * Undocumented :(
 * <p>
 * Created by Alexander Winter on 2017-05-09.
 */
public class All extends Alphabet
{
	public All(Resources resources)
	{
		loadFromResource(resources, R.raw.kanji);
		loadFromResource(resources, R.raw.numbers);
		loadFromResource(resources, R.raw.hiragana);
		loadFromResource(resources, R.raw.katakana);
	}
}
