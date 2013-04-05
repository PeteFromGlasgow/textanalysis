package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.core.WhitespaceTokenizer;
import org.apache.lucene.analysis.shingle.ShingleFilter;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.TypeAttribute;
import org.apache.lucene.analysis.util.CharArrayMap.EntrySet;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.util.Version;

/**
 * Tool for Analysing text to find Ngrams
 * @author peter
 *
 */
public class NgramTool {
	ShingleFilter filter;
	HashMap<String,Integer> countMap;
	
	public NgramTool(int upperBound, String text) {
		countMap = new HashMap<String,Integer>();
		Tokenizer token = new WhitespaceTokenizer(Version.LUCENE_40, new StringReader(text));
		filter = new ShingleFilter(token, upperBound);
		
		try {
			
			while (filter.incrementToken()){
				String term = filter.getAttribute(CharTermAttribute.class).toString();
				if (countMap.containsKey(term)){
					countMap.put(term, countMap.get(term)+1);
				} else {
					countMap.put(term, 1);
				}
				//System.out.println(term);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public NgramTool(int upperBound, BufferedReader text) {
		countMap = new HashMap<String,Integer>();
		Tokenizer token = new WhitespaceTokenizer(Version.LUCENE_40, text);
		filter = new ShingleFilter(token, upperBound);
		
		try {
			
			while (filter.incrementToken()){
				String term = filter.getAttribute(CharTermAttribute.class).toString();
				if (countMap.containsKey(term)){
					countMap.put(term, countMap.get(term)+1);
				} else {
					countMap.put(term, 1);
				}
				//System.out.println(term);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public Map<String,Integer> getResults(int min, int max){
		HashMap<String, Integer> temp = new HashMap<>();
		
		for (String t : countMap.keySet()){
			if (countMap.get(t)>min && countMap.get(t) < max)
				temp.put(t,countMap.get(t));
		}
		return temp;
	}
	
	public Map<String,Integer> getSortedResults(int min, int max){
		HashMap<String, Integer> temp = new HashMap<>();
		LinkedHashMap<String, Integer> sorted = new LinkedHashMap<>();

		
		for (String t : countMap.keySet()){
			if (countMap.get(t)>min && countMap.get(t) < max)
				temp.put(t,countMap.get(t));
		}
		ArrayList<Entry<String, Integer>> in = new ArrayList<>(temp.entrySet());
		Collections.sort(in, new Comparator<Entry<String, Integer>>() {

			@Override
			public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
				if (o1.getValue() >= o2.getValue()){
					return 1;
				} else {
					return -1;
				}
				
			}
		});
		for (Entry<String,Integer> s : in){
			//System.out.println(s.getKey()+" "+s.getValue());
			sorted.put(s.getKey(), s.getValue());
		}
		
		return sorted;
	}
	


	
	

}
