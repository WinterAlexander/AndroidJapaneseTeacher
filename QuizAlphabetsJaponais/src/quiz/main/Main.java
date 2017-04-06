package quiz.main;

import java.util.Scanner;

public class Main
{
	private static Quiz[] quizs = new Quiz[] {
			new SingleSymbolQuiz<>(Hiragana.class, "Hiragana"),
			new SingleSymbolQuiz<>(Katakana.class, "Katakana"),
			new SingleSymbolQuiz<>(SimpleHiragana.class, "Hiragana Simple"),
			new SingleSymbolQuiz<>(SimpleKatakana.class, "Katakana Simple"),
	};

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException
	{
		Scanner scanner = new Scanner(System.in);	
		String answer;

		Quiz quiz = null;

		do
		{
			if(quiz == null)
			{
				System.out.println("Quel alphabet japonais voulez vous apprendre ?");
				for(Quiz current : quizs)
					System.out.println("  - " + current.getName());

				System.out.print(">");
				do
				{
					String entry = scanner.nextLine();

					for(Quiz current : quizs)
						if(current.getName().equalsIgnoreCase(entry))
						{
							quiz = current;
							break;
						}

					if(quiz == null)
					{
						System.out.println("Alphabet introuvable/invalide");
						System.out.print(">");
					}
				}
				while(quiz == null);
			}

			quiz.start(scanner, System.out);

			System.out.println("Vous avez fait un score de " + quiz.getScore() + ".");
			System.out.println();
			do
			{
				System.out.println("Voulez-vous recommencer ? Oui/Non/Different");
				answer = scanner.nextLine();
			}
			while(!answer.equalsIgnoreCase("oui")
					&& !answer.equalsIgnoreCase("non")
					&& !answer.equalsIgnoreCase("different"));

			if(answer.equalsIgnoreCase("different"))
				quiz = null;
			
		}
		while(!answer.equalsIgnoreCase("non"));
		
		scanner.close();
	}
}
