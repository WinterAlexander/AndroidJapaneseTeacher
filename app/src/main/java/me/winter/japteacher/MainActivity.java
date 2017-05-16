package me.winter.japteacher;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import me.winter.japteacher.alphabet.All;
import me.winter.japteacher.alphabet.FifthGradeKanji;
import me.winter.japteacher.alphabet.FirstGradeKanji;
import me.winter.japteacher.alphabet.FourthGradeKanji;
import me.winter.japteacher.alphabet.Hiragana;
import me.winter.japteacher.alphabet.Kanji;
import me.winter.japteacher.alphabet.Katakana;
import me.winter.japteacher.alphabet.SecondGradeKanji;
import me.winter.japteacher.alphabet.SimpleHiragana;
import me.winter.japteacher.alphabet.SimpleKatakana;
import me.winter.japteacher.alphabet.SixthGradeKanji;
import me.winter.japteacher.alphabet.ThirdGradeKanji;

public class MainActivity extends AppCompatActivity
{
    private static final String[] ALPHABETS = new String[] {
            "ひらがな",
		    "カタカナ",
		    "ひらがな (Simple)",
		    "カタカナ (Simple)",
		    "一年生漢字",
		    "二年生漢字",
		    "三年生漢字",
		    "四年生漢字",
		    "五年生漢字",
		    "六年生漢字",
		    "教育漢字",
		    "全て" };
    private static final Class[] TYPES = new Class[] {
    		Hiragana.class,
		    Katakana.class,
		    SimpleHiragana.class,
		    SimpleKatakana.class,
		    FirstGradeKanji.class,
		    SecondGradeKanji.class,
		    ThirdGradeKanji.class,
		    FourthGradeKanji.class,
		    FifthGradeKanji.class,
		    SixthGradeKanji.class,
		    Kanji.class,
		    All.class };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.alphabet_listview);

        listView.setAdapter(new ArrayAdapter<>(this, R.layout.quiz_item, ALPHABETS));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("alphabet", TYPES[position]);
                startActivity(intent);
            }
        });

    }
}
