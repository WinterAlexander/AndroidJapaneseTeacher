package me.winter.japteacher;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import me.winter.japteacher.alphabet.Alphabet;
import me.winter.japteacher.alphabet.Hiragana;

public class QuizActivity extends AppCompatActivity {

    private Random random = new Random();

    private Map<JapaneseCharacter, Integer> symbolsPriority;

    private Alphabet alphabet;

    private int score;
    private List<Float> timings = new ArrayList<>();

    private JapaneseCharacter toGuess = null;
    private long lastAnswer = -1;

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

        this.symbolsPriority = new HashMap<>();

        for(JapaneseCharacter c : this.alphabet.getChars())
            symbolsPriority.put(c, 0);

        nextSymbol();

        final Button button = (Button)findViewById(R.id.validate);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText input = (EditText)findViewById(R.id.input);
                userInput(input.getText().toString());
                input.setText("");
            }
        });
    }

    private void userInput(String answer)
    {
        TextView error = (TextView)findViewById(R.id.error);
        error.setText("");

        if(!alphabet.containsRomaji(answer))
        {
            error.setText("Typo error ! Answer isn't present in this alphabet");
            return;
        }

        if(toGuess.isValid(answer))
        {
            if(lastAnswer >= 0)
            {
                float time = (System.currentTimeMillis() - lastAnswer) / 1000f;
                timings.add(time);
            }
            nextSymbol();
            score++;
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
            Intent myIntent = new Intent(this, FailedActivity.class);
            myIntent.putExtra("score", score);
            myIntent.putExtra("avgTime", avgTime);
            myIntent.putExtra("answered", answered);
            myIntent.putExtra("actual", toGuess);
            myIntent.putExtra("alphabet", alphabet.getClass());
            startActivity(myIntent);
        }
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

        return lowests.get(random.nextInt(lowests.size()));
    }
}
