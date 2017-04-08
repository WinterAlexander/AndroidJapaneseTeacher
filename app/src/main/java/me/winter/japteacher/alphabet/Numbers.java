package me.winter.japteacher.alphabet;

import android.content.res.Resources;
import me.winter.japteacher.R;

/**
 * Undocumented :(
 * <p>
 * Created by Alexander Winter on 2017-04-08.
 */
public class Numbers extends Alphabet
{
	public Numbers(Resources resources)
	{
		super();
		loadFromResource(resources, R.raw.numbers);
	}

	@Override
	public String getName()
	{
		return "Numbers";
	}
}
