package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import model.DataSet;

/**
 * Load a file and turn it into a set
 * @author peter
 *
 */
public class FileLoader {
	DataSet set;
	
	public FileLoader(String path) {
		
		try {
			File toOpen = new File(path);
			FileReader fr = new FileReader(toOpen);
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			set = new DataSet(br.readLine());
			while ((line = br.readLine()) != null){

					set.add(line);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public FileLoader(String path, boolean toLowerCase) {
		
		try {
			File toOpen = new File(path);
			FileReader fr = new FileReader(toOpen);
			BufferedReader br = new BufferedReader(fr);
			String line;
			
			set = new DataSet(br.readLine());
			while ((line = br.readLine()) != null){
				if (toLowerCase){
					set.add(line.toLowerCase());
				} else {
					set.add(line);
				}
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
		public DataSet getSet(){
			return set;
		}
	

}
