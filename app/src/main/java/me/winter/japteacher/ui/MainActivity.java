package me.winter.japteacher.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import me.winter.japteacher.Alphabet;
import me.winter.japteacher.R;
import me.winter.japteacher.ui.alphabet.AlphabetQuizActivity;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;

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
		    "六年生漢字"};

    private static final int[] TYPES = new int[] {
    		R.raw.hiragana,
		    R.raw.katakana,
		    R.raw.simplehiragana,
		    R.raw.simplekatakana,
		    R.raw.first_grade_kanji,
		    R.raw.second_grade_kanji,
		    R.raw.third_grade_kanji,
		    R.raw.fourth_grade_kanji,
		    R.raw.fifth_grade_kanji,
		    R.raw.sixth_grade_kanji
    };

    private boolean multiSelect = false;
    private List<Integer> selection = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView)findViewById(R.id.alphabet_listview);

        listView.setAdapter(new ArrayAdapter<String>(this, R.layout.listview_item, ALPHABETS) {
	        @Override
	        public int getViewTypeCount() {

		        return getCount();
	        }

	        @Override
	        public int getItemViewType(int position) {

		        return position;
	        }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            	if(!multiSelect)
	            {
		            Intent intent = new Intent(MainActivity.this, AlphabetQuizActivity.class);

		            Alphabet alphabet = new Alphabet();
		            alphabet.loadFromResource(getResources(), TYPES[(int)id]);

		            intent.putExtra("alphabet", alphabet);
		            startActivity(intent);
	            }
	            else
	            {
	            	if(selection.contains(TYPES[(int)id]))
		            {
			            selection.remove(Integer.valueOf(TYPES[(int)id]));
			            view.setBackgroundColor(android.R.color.background_light);
			            return;
		            }
	            	selection.add(TYPES[(int)id]);
	            	view.setBackgroundColor(R.color.colorSelected);
	            }
            }
        });

	    Button button = (Button)findViewById(R.id.go);
	    button.setOnClickListener(new OnClickListener() {
		    @Override
		    public void onClick(View v)
		    {
			    if(v.getVisibility() != View.VISIBLE)
			    	return;

			    if(selection.size() == 0)
			    {
				    Toast.makeText(getApplicationContext(), getString(R.string.select_an_alphabet), LENGTH_SHORT).show();
				    return;
			    }

			    Intent intent = new Intent(MainActivity.this, AlphabetQuizActivity.class);

			    Alphabet alphabet = new Alphabet();

			    for(int type : selection)
			    {
			    	if(type == R.raw.simplehiragana && selection.contains(R.raw.hiragana))
			    		continue;

				    if(type == R.raw.simplekatakana && selection.contains(R.raw.katakana))
					    continue;

				    alphabet.loadFromResource(getResources(), type);
			    }

			    intent.putExtra("alphabet", alphabet);
			    startActivity(intent);

		    }
	    });
    }

	public boolean onCreateOptionsMenu(Menu paramMenu)
	{
		getMenuInflater().inflate(R.menu.main_menu, paramMenu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		if(item.getItemId() == R.id.multiselect)
		{
			multiSelect = !multiSelect;
			item.setTitle(multiSelect ? R.string.disable_multiselect : R.string.enable_multiselect);

			if(!multiSelect)
				selection.clear();

			ListView listView = ((ListView)findViewById(R.id.alphabet_listview));

			for(int i = 0; i < listView.getChildCount(); i++)
			{
				View view = listView.getChildAt(i);

				view.setBackgroundColor(android.R.color.background_light);
			}

			Button button = (Button)findViewById(R.id.go);
			button.setVisibility(multiSelect ? View.VISIBLE : View.INVISIBLE);
		}

		return super.onOptionsItemSelected(item);
	}
}
