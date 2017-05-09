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


/**
 * Undocumented :(
 * <p>
 * Created by Alexander Winter on 2017-05-09.
 */
public class Main
{
	public static final String URL = "https://en.wikipedia.org/wiki/Ky%C5%8Diku_kanji";

	public static void main(String[] args) throws Throwable
	{
		Response response = Jsoup.connect(URL).execute();

		Document doc = response.parse();

		for(Element element : doc.select("h3 > .mw-headline"))
		{
			if(!element.id().contains("29"))
				continue;

			System.out.println();
			System.out.println(element.ownText());

			File file = new File(element.ownText());

			PrintStream writer = new PrintStream(new BufferedOutputStream(new FileOutputStream(file)));

			Element table = element.parent().nextElementSibling();

			Elements rows = table.select("tr:has(td)");

			int total = rows.size();
			int current = 0;

			for(Element row : rows)
			{
				String symbol = row.child(1).text();
				String romaji = row.child(4).text().trim().replaceAll(", *", "|");
				String romajiPart2 = row.child(5).text().trim().replaceAll(", *", "|");

				if(romajiPart2.length() != 0)
					romaji = romaji + "|" + romajiPart2;

				romaji = romaji.replaceAll("" + (char)333, "ou").replaceAll("" + (char)363, "uu").replaceAll("([a-z]+)-([a-z]+)", "$1|$1$2");

				String english = row.child(3).text().trim();

				english = english.substring(0, 1).toUpperCase() + english.substring(1).toLowerCase();
				english = english.replace(';', ',');

				romaji = romaji.replace(" ", "").replace("-", "").replace("'", "");

				String finalString = symbol + " " + romaji + " " + english;

				writer.println(finalString);
				current++;
				System.out.println(current + " / " + total);
			}

			//writer.close();
		}
	}
/*
	public static void main(String[] args)
	{
		String testString = "ō";

		testString = testString.replaceAll("ō", "ou");

		System.out.println(testString);
	}*/
}
