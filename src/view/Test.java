package view;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;
import java.util.Map;

import model.DataSet;
import controller.AnalyseText;
import controller.FileLoader;
import controller.NgramTool;

public class Test {

	public static void main(String[] args) {
		FileLoader files = new FileLoader("colours.txt",true);
		DataSet colours = files.getSet();

		
		DataSet set = new DataSet("colors");
		set.add("blue");
		set.add("green");
		set.add("orange");
		
		DataSet numbers = new DataSet("numbers");
		numbers.add("[0-9][0-9]*");
		
		//A regex definition od all words ending with the suffix Phobia
		DataSet phobias = new DataSet("phobias");
		phobias.add("[A-Za-z]*phobia");
		
		//colours.removeAll(set);
		
		DataSet objects = new DataSet("Objects");
		objects.add("teapot");
		objects.add("daisy");
		
		AnalyseText analyse = new AnalyseText(set, "blue teapot 1 2 3 4 5 6 7 122 green orange daisy green grass baby blue teaphobia claustrophobia");
		analyse.addDataSet(objects);
		analyse.addDataSet(colours);
		analyse.addDataSet(numbers);
		analyse.addDataSet(phobias);
		analyse.printResults();
		long starttime = System.currentTimeMillis();
		String testText = "";
		NgramTool n = null;
		try {
			BufferedReader r = new BufferedReader( new FileReader("rfc1958.txt"));
			n = new NgramTool(4, r);
			r.close();
		} catch (FileNotFoundException e) {
			testText = "File Not Found";
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Starting Shingle Test\n");
		long filetime = System.currentTimeMillis() - starttime;
		
		Map<String,Integer> results = n.getSortedResults(5, 10000);
		long ntime = System.currentTimeMillis() - (starttime + filetime);
		
		FileLoader commonWords = new FileLoader("common.txt");
		FileLoader verbsWords = new FileLoader("verbs.txt");
		Iterator<String> words = commonWords.getSet().iterator();
		Iterator<String> verbs = verbsWords.getSet().iterator(); 
		while (words.hasNext()){
			results.remove(words.next());
		}
		while (verbs.hasNext()){
			results.remove(verbs.next());
		}
		long totaltime = System.currentTimeMillis() - (starttime + ntime);
		
		
		
		for (String t : results.keySet()){
			System.out.println(t+": "+results.get(t));
		}
		System.out.println("\n\nFile Read time: " + filetime +"ms");
		System.out.println("Ngram time: " + ntime +"ms");
		System.out.println("Remove time: " + totaltime +"ms");
		
	}
}
