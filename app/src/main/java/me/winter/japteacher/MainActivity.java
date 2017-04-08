package me.winter.japteacher;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import me.winter.japteacher.alphabet.Hiragana;
import me.winter.japteacher.alphabet.Kanji;
import me.winter.japteacher.alphabet.Katakana;
import me.winter.japteacher.alphabet.Numbers;
import me.winter.japteacher.alphabet.SimpleHiragana;
import me.winter.japteacher.alphabet.SimpleKatakana;

public class MainActivity extends Activity
{
    private static final String[] ALPHABETS = new String[] { "ひらがな", "カタカナ", "ひらがな (Simple)", "カタカナ (Katakana)", "漢字", "数字" };
    private static final Class[] TYPES = new Class[] { Hiragana.class, Katakana.class, SimpleHiragana.class, SimpleKatakana.class, Kanji.class, Numbers.class };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.alphabet_listview);

        listView.setAdapter(new ArrayAdapter<>(this, R.layout.quiz_item, ALPHABETS));

        listView.setTextFilterEnabled(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("alphabet", TYPES[position]);
                startActivity(intent);
            }
        });

    }
}
