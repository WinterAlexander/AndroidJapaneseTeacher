package me.winter.japteacher.alphabet;

import android.content.res.Resources;
import me.winter.japteacher.R;

/**
 * Undocumented :(
 * <p>
 * Created by Alexander Winter on 2017-05-10.
 */
public class FirstGradeKanji extends Alphabet
{
	public FirstGradeKanji(Resources resources)
	{
		loadFromResource(resources, R.raw.first_grade_kanji);
	}
}
