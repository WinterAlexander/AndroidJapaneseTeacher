package quiz.main;

/**
 * Created by 1541869 on 2016-09-21.
 */
public class SimpleHiragana extends Alphabet
{
	public SimpleHiragana()
	{
		super();
		loadFromResource("/simplehiragana.txt");
	}

	@Override
	public String getName()
	{
		return "Hiragana";
	}
}