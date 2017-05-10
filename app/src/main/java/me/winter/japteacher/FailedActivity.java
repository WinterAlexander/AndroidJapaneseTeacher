package me.winter.japteacher;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class FailedActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_failed);

        JapaneseCharacter answered = (JapaneseCharacter)getIntent().getSerializableExtra("answered");
        JapaneseCharacter actual = (JapaneseCharacter)getIntent().getSerializableExtra("actual");

        TextView correctAnswer = (TextView)findViewById(R.id.correct_answer);
        correctAnswer.setText(String.format(getString(R.string.correct_answer), unstackRomajis(actual.getRomaji())));

        TextView answeredText = (TextView)findViewById(R.id.answered);
        TextView answeredSymbol = (TextView)findViewById(R.id.answered_symbol);
        TextView answeredComment = (TextView)findViewById(R.id.ansered_comment);

        if(answered != null)
        {
            answeredText.setText(unstackRomajis(answered.getRomaji()));
            answeredSymbol.setText(answered.getSymbol());

            if(answered.getComment().startsWith("@"))
                answeredComment.setText(getResources().getIdentifier(answered.getComment().substring(1).trim(), "string", getPackageName()));
            else
                answeredComment.setText(answered.getComment());
        }
        else
        {
            answeredText.setText("");
            answeredSymbol.setText("");
            answeredComment.setText("");
        }

        TextView actualText = (TextView)findViewById(R.id.actual);
        TextView actualSymbol = (TextView)findViewById(R.id.actual_symbol);
        TextView actualComment = (TextView)findViewById(R.id.actual_comment);

        actualText.setText(unstackRomajis(actual.getRomaji()));
        actualSymbol.setText(actual.getSymbol());
        if(actual.getComment().startsWith("@"))
            actualComment.setText(getResources().getIdentifier(actual.getComment().substring(1).trim(), "string", getPackageName()));
        else
            actualComment.setText(actual.getComment());

        TextView scoreDisplay = (TextView)findViewById(R.id.score);

        int score = getIntent().getIntExtra("score", -1);
        float avgTime = getIntent().getFloatExtra("avgTime", -1);


        scoreDisplay.setText(String.format(getString(R.string.score_and_time), score, avgTime));

        Button retry = (Button)findViewById(R.id.retry);
        retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button different = (Button)findViewById(R.id.different);
        different.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FailedActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private String unstackRomajis(String romajis)
    {
        StringBuilder sb = new StringBuilder();

        String[] romajiArray = romajis.split(Pattern.quote("|"));

        if(romajiArray.length == 0)
            return "";

        sb.append(romajiArray[0]);

        for(int i = 1; i < romajiArray.length; i++)
            sb.append(',').append(' ').append(romajiArray[i]);

        return sb.toString();
    }

}
