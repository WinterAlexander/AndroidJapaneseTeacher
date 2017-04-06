package quiz.main;

/**
 *
 * Created by 1541869 on 2016-09-21.
 */
public class SimpleKatakana extends Alphabet
{
	public SimpleKatakana()
	{
		super();
		loadFromResource("/simplekatakana.txt");
	}

	@Override
	public String getName()
	{
		return "Katakana";
	}
}