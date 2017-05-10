package me.winter.japteacher.alphabet;

import android.content.res.Resources;

import me.winter.japteacher.R;

public class Kanji extends Alphabet
{
	public Kanji(Resources resources)
	{
		loadFromResource(resources, R.raw.first_grade_kanji);
		loadFromResource(resources, R.raw.second_grade_kanji);
		loadFromResource(resources, R.raw.third_grade_kanji);
		loadFromResource(resources, R.raw.fourth_grade_kanji);
		loadFromResource(resources, R.raw.fifth_grade_kanji);
		loadFromResource(resources, R.raw.sixth_grade_kanji);
	}
}
