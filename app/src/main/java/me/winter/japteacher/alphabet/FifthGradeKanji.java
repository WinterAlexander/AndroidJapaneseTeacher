package me.winter.japteacher.alphabet;

import android.content.res.Resources;
import me.winter.japteacher.R;
import me.winter.japteacher.alphabet.Alphabet;

/**
 * Undocumented :(
 * <p>
 * Created by Alexander Winter on 2017-05-10.
 */
public class FifthGradeKanji extends Alphabet
{
	public FifthGradeKanji(Resources resources)
	{
		loadFromResource(resources, R.raw.fifth_grade_kanji);
	}
}
