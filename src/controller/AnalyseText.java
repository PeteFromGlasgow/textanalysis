package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.DataSet;
/**
 * Analyse Text takes either a list of sets or a set and a body of text to analyse.
 * @author peter
 *
 */
public class AnalyseText {
	List<DataSet> datasets;
	String target;
	
	public AnalyseText(List<DataSet> data, String toAnalyse) {
		this.datasets = data;
		this.target = toAnalyse;
	}
	
	public AnalyseText(DataSet data, String toAnalyse) {
		datasets = new ArrayList<DataSet>();
		datasets.add(data);
		this.target = toAnalyse;
		
	}
	
	public void printResults(){
		int setCount = wordcount(target);
		
		for (DataSet data : datasets){
			int count = 0;
			for (String a : data){
				Pattern pattern = Pattern.compile(a);
				Matcher m= pattern.matcher(target);
				int aweight = wordcount(a);
				
				while (m.find()){
					count += aweight;
				}
			}
			System.out.println("For set "+ data.getSetName() +"\n\tCount: " + count + " / " + setCount + " = " + ((double) count/(double) setCount*100.0d) +"%");
		}
		
	}
	
	private int wordcount(String text){
		int count = 0;
		for (String i : text.split(" ")){
			count++;
		}
		
		return count;
	}

	public void addDataSet(DataSet objects) {
		datasets.add(objects);
		
	}

}
