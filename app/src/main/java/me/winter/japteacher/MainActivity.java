package me.winter.japteacher;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import me.winter.japteacher.alphabet.Hiragana;
import me.winter.japteacher.alphabet.Katakana;
import me.winter.japteacher.alphabet.SimpleHiragana;
import me.winter.japteacher.alphabet.SimpleKatakana;

public class MainActivity extends ListActivity
{
    private static final String[] ALPHABETS = new String[] { "Hiragana", "Katakana", "Simple Hiragana", "Simple Katakana" };
    private static final Class[] TYPES = new Class[] { Hiragana.class, Katakana.class, SimpleHiragana.class, SimpleKatakana.class };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setListAdapter(new ArrayAdapter<>(this, R.layout.activity_main, ALPHABETS));

        ListView listView = getListView();
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
