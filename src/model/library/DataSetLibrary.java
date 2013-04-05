package model.library;

import controller.FileLoader;
import model.DataSet;

public class DataSetLibrary {

	public static DataSet getAdjectives(){
		FileLoader l = new FileLoader("library/adjectives.txt");
		return l.getSet();
	}
	
	public static DataSet getNouns(){
		FileLoader l = new FileLoader("library/nouns.txt");
		return l.getSet();
	}
	
	public static DataSet getPronouns(){
		FileLoader l = new FileLoader("library/pronouns.txt");
		return l.getSet();
	}
	
	public static DataSet get(String library){
		FileLoader l = new FileLoader("library/"+library+".txt");
		return l.getSet();
	}
}
