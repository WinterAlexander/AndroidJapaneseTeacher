package me.winter.kanjiscrapper;

import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * Undocumented :(
 * <p>
 * Created by Alexander Winter on 2017-05-09.
 */
public class Main
{
	public static final String URL = "http://www.crapulescorp.net/japonais/cours/ecritures/kanji/liste_kanji_grade_1a9.php5";

	public static void main(String[] args) throws Throwable
	{
		Response response = Jsoup.connect(URL).execute();

		Document doc = response.parse();

		for(Element element : doc.select("h3"))
		{
			System.out.println(element.ownText());

			File file = new File(element.ownText());

			PrintStream writer = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));

			Element p = element.nextElementSibling();

			Elements links = p.select("a");

			int total = links.size();
			int current = 0;

			for(Element link : links)
			{
				Document kanjiPage = Jsoup.connect(link.attr("abs:href")).execute().parse();


				StringBuilder entry = new StringBuilder();

				entry.append(kanjiPage.select("td > span").text()).append(' ');


				for(Element answer : kanjiPage.select("ul > li > ul > li"))
					entry.append(answer.ownText().split("\\[")[1].split("]")[0]).append("|");

				entry.deleteCharAt(entry.length() - 1);
				entry.append(" ");

				for(Element meaning : kanjiPage.select("td > ul > li"))
				{
					if(meaning.ownText().toLowerCase().contains("yomi") || meaning.ownText().toLowerCase().contains("=") || meaning.ownText().toLowerCase().contains("...") || meaning.ownText().trim().length() == 0)
						continue;

					entry.append(meaning.ownText()).append(", ");
				}

				entry.deleteCharAt(entry.length() - 1);
				entry.deleteCharAt(entry.length() - 1);

				writer.println(entry);

				current++;
				System.out.println(current + " / " + total);
			}

			writer.close();
		}
	}
}
