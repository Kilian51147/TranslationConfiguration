package io.github.kilian51147;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;

public class TranslationConfiguration 
{
	private File config = new File("config.tl");

	private static List<String> current = new ArrayList<>();
	private static Map<String, String> translations = new HashMap<>();
	
	public TranslationConfiguration()
	{
		try
		{
			if (!config.exists())
			{
				config.createNewFile();
				
				FileWriter fw = new FileWriter(config);

				fw.write("current=en");
				fw.flush();
			}
			
			File en = new File("lang\\en.lang");
			
			if (!en.exists())
			{
				en.createNewFile();
			}
			
			Scanner cnfg = new Scanner(config);

			while(cnfg.hasNext())
			{
				String line = cnfg.nextLine();

				if (line.startsWith("current="))
				{
					String cur = line.substring(8);

					current.add(cur);
				}
			}

			Scanner sc = new Scanner(new File("lang\\" + getLanguage() + ".lang"));

			while (sc.hasNext())
			{
				String line = sc.nextLine();

				String key = StringUtils.substringBetween(line, "\"", "\"");
				String value = StringUtils.substringBetween(line, "'", "'");

				translations.put(key, value);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public String get(String key)
	{
		if (translations.containsKey(key))
		{
			try
			{
				Scanner sc = new Scanner(new File("lang\\" + getLanguage() + ".lang"));

				while (sc.hasNext())
				{
					String line = sc.nextLine();

					String key1 = StringUtils.substringBetween(line, "\"", "\"");
					String value = StringUtils.substringBetween(line, "'", "'");

					translations.put(key1, value);
					}
			}
			catch (FileNotFoundException e)
			{
				e.printStackTrace();
			}

			return translations.get(key);
		}
		return key;
	}

	public void setLanguage(Language language)
	{
		current.clear();
		current.add(language.getIdentifier());

		try
		{
			config.delete();
			config.createNewFile();

			FileWriter fw = new FileWriter(config);

			fw.write("current=" + language.getIdentifier());
			fw.flush();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public String getLanguage()
	{
		return current.get(0);
	}
}
