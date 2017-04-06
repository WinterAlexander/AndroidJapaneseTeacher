package quiz.main;

import com.sun.istack.internal.NotNull;

import java.io.PrintStream;
import java.util.Scanner;

/**
 *
 * Created by 1541869 on 2016-09-19.
 */
public interface Quiz
{
	/**
	 * The name of the quiz let's the user choose which quiz he want to pratice.
	 * @return name of the quiz
	 */
	@NotNull
	String getName();

	/**
	 * Starts the quiz
	 * @param scanner scanner for input
	 */
	void start(Scanner scanner, PrintStream out);

	/**
	 * Resets the quiz
	 */
	void reset();

	/**
	 * @return current score of the player for this quiz
	 */
	int getScore();
}
