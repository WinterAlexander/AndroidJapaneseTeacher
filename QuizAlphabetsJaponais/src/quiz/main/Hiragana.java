package quiz.main;

public class Hiragana extends Alphabet
{
	public Hiragana()
	{
		super();
		loadFromResource("/hiragana.txt");
	}

	@Override
	public String getName()
	{
		return "Hiragana";
	}
}
