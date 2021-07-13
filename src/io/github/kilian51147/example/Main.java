package io.github.kilian51147.example;

import io.github.kilian51147.Language;
import io.github.kilian51147.TranslationConfiguration;

public class Main 
{
	private static TranslationConfiguration config = new TranslationConfiguration();
	
	public static void main(String[] args)
	{
		config.setLanguage(Language.SPANISH);
		
		//Get the Translation frome the lang file
		System.out.println(config.get("test.one"));
		System.out.println(config.get("test.two"));
		System.out.println(config.get("test.three"));
		System.out.println(config.get("test.four"));
	}
}
