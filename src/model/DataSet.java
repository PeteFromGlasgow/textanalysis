package model;

import java.util.HashSet;
import java.util.Iterator;

public class DataSet extends HashSet<String> {
	
	private static final long serialVersionUID = 2126939667799763525L;
	private String name;
	
	public DataSet(String name) {
		this.name = name;
	}
	
	public String getSetName(){
		return name;
	}
	
	/**
	 * Get a DataSet with values that exist in both sets;
	 * @param name A name for the new set
	 * @param b The set to get the intersect of.
	 * @return The intersect of this set and the input set.
	 */
	public DataSet intersect(String name, DataSet b){
		DataSet output = new DataSet(name);
		Iterator<String> it = b.iterator();
		
		while (it.hasNext()){
			String result = it.next();
			if (contains(result)){
				output.add(result);
			}
		}
		return output;
	}
	
	/**
	 * Creates a union of this set.
	 * @param name
	 * @param b
	 * @return
	 */
	public DataSet union(String name, DataSet b){
		DataSet a = new DataSet(name);
		
		a.addAll(this);
		a.addAll(b);
		return a;
	}
	
	/**
	 * Get all the members of this set that are not in set b.
	 * @param name
	 * @param b
	 * @return
	 */
	public DataSet difference(String name, DataSet b){
		DataSet a = new DataSet(name);
		a.addAll(this);
		a.remove(b);
		return a;
		
	}
	/**
	 * Returns a set where the values are in A, or B but not both
	 * @param name
	 * @param b
	 * @return
	 */
	public DataSet symmetricDifference(String name, DataSet b){
		DataSet c = intersect("temp", b);
		DataSet output = new DataSet(name);
		output.addAll(this);
		output.removeAll(c);
		return output;
		
	}
}
