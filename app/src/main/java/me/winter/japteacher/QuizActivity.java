package me.winter.japteacher;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import me.winter.japteacher.alphabet.Alphabet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class QuizActivity extends AppCompatActivity
{
    private Random random = new Random();

    private Map<JapaneseCharacter, Integer> symbolsPriority = new HashMap<>();

    private Alphabet alphabet;

    private int score;
    private List<Float> timings = new ArrayList<>();

    private JapaneseCharacter toGuess = null, lastSymbol = null;
    private long lastAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        try
        {
            Class type = (Class) getIntent().getSerializableExtra("alphabet");

            alphabet = (Alphabet) type.getConstructor(Resources.class).newInstance(getResources());
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }

	    symbolsPriority.clear();

        for(JapaneseCharacter c : this.alphabet.getChars())
            symbolsPriority.put(c, 0);


	    final Button button = (Button)findViewById(R.id.validate);
	    final Button dontknow = (Button)findViewById(R.id.dontknow);
	    final EditText input = (EditText)findViewById(R.id.input);


	    input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
		    @Override
		    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
			    if(actionId == EditorInfo.IME_ACTION_DONE) {

				    userInput(input.getText().toString());
				    input.setText("");
				    input.requestFocus();
				    return true;
			    }
			    return false;
		    }
	    });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userInput(input.getText().toString());
                input.setText("");
            }
        });

	    dontknow.setOnClickListener(new View.OnClickListener() {
		    @Override
		    public void onClick(View v) {

			    giveUp();
		    }
	    });
    }

	@Override
	protected void onResume()
	{
		super.onResume();

		timings.clear();
		score = 0;
		updateScore();
		lastAnswer = -1;
        lastSymbol = null;

		nextSymbol();

		findViewById(R.id.input).performClick();
	}

	private void giveUp()
	{
		float avgTime = 0;

		for(float f : timings)
			avgTime += f;

		if(timings.size() > 0)
			avgTime /= timings.size();

		avgTime = Math.round(avgTime * 1000f) / 1000f;

        for(JapaneseCharacter c : alphabet.getChars())
            symbolsPriority.put(c, 0);

		symbolsPriority.put(toGuess, -1);

		Intent myIntent = new Intent(this, FailedActivity.class);
		myIntent.putExtra("score", score);
		myIntent.putExtra("avgTime", avgTime);
		myIntent.putExtra("answered", (Serializable)null);
		myIntent.putExtra("actual", toGuess);
		myIntent.putExtra("alphabet", alphabet.getClass());
		startActivity(myIntent);
	}

	private void userInput(String answer)
    {
    	if(answer.length() == 0)
    		return;

        if(!alphabet.containsRomaji(answer))
        {
	        Toast.makeText(this, getString(R.string.typo), Toast.LENGTH_SHORT).show();
	        return;
        }

        if(toGuess.isValid(answer))
        {
            if(lastAnswer >= 0)
            {
                float time = (System.currentTimeMillis() - lastAnswer) / 1000f;
                timings.add(time);
            }
	        symbolsPriority.put(toGuess, symbolsPriority.get(toGuess) + 1);
            nextSymbol();
            score++;
	        updateScore();
            lastAnswer = System.currentTimeMillis();
        }
        else
        {

            float avgTime = 0;

            for(float f : timings)
                avgTime += f;

            if(timings.size() > 0)
                avgTime /= timings.size();

            avgTime = Math.round(avgTime * 1000f) / 1000f;


	        JapaneseCharacter answered = alphabet.fromRomaji(answer);


            for(JapaneseCharacter c : alphabet.getChars())
                symbolsPriority.put(c, 0);

	        symbolsPriority.put(toGuess, -1);
	        symbolsPriority.put(answered, -1);

            Intent myIntent = new Intent(this, FailedActivity.class);
            myIntent.putExtra("score", score);
            myIntent.putExtra("avgTime", avgTime);
            myIntent.putExtra("answered", answered);
            myIntent.putExtra("actual", toGuess);
            myIntent.putExtra("alphabet", alphabet.getClass());
            startActivity(myIntent);
        }
    }

    private void updateScore()
    {
    	TextView scoreDisp = (TextView)findViewById(R.id.score_display);
	    scoreDisp.setText(String.format(getString(R.string.score), score));
    }

    private void nextSymbol()
    {
        TextView textView = (TextView)findViewById(R.id.symbol);

        toGuess = random();
        textView.setText(toGuess.getSymbol());
    }

    private JapaneseCharacter random()
    {
        List<JapaneseCharacter> lowests = new ArrayList<>();
        int lowestUses = Integer.MAX_VALUE;
        for(JapaneseCharacter current : symbolsPriority.keySet())
        {
            if(symbolsPriority.get(current) == lowestUses)
                lowests.add(current);
            else if(symbolsPriority.get(current) < lowestUses)
            {
                lowests.clear();
                lowests.add(current);
                lowestUses = symbolsPriority.get(current);
            }
        }

        if(lowests.size() <= 0)
            return null;

        if(lowests.size() > 1 && lastSymbol != null)
            lowests.remove(lastSymbol);

        lastSymbol = lowests.get(random.nextInt(lowests.size()));
        return lastSymbol;
    }
}
