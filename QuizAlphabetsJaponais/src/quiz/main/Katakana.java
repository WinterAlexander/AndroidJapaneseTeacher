package quiz.main;

public class Katakana extends Alphabet
{
	public Katakana()
	{
		super();
		loadFromResource("/katakana.txt");
	}

	@Override
	public String getName()
	{
		return "Katakana";
	}
}
