package me.winter.japteacher.ui.vocabulary;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import me.winter.japteacher.R;

public class VocabularyQuizActivity extends AppCompatActivity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		int size = getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK;

		if(size == Configuration.SCREENLAYOUT_SIZE_NORMAL || size == Configuration.SCREENLAYOUT_SIZE_SMALL)
			setTheme(R.style.NoTitleAppTheme);

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vocabularyquiz);
	}
}
