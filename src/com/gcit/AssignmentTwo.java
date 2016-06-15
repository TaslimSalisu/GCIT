package com.gcit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class AssignmentTwo {

	public static void main(String[] args) {
		exerciseOne();
		System.out.println();
		exerciseTwo();
		System.out.println();
		exerciseThree();


	}

	private static void exerciseOne() {
		Set<String> mySet = new HashSet<String>();

		String fruit1 = "pear";
		String fruit2 = "banana";
		String fruit3 = "tangerine";
		String fruit4 = "strawberry";
		String fruit5 = "blackberry";

		mySet.add(fruit1);
		mySet.add(fruit2);
		mySet.add(fruit3);
		mySet.add(fruit2);
		mySet.add(fruit4);
		mySet.add(fruit5);

		System.out.println("mySet now contains: " + mySet);
		System.out.println("mySet contains " + mySet.size() + " number of elements");
		mySet.remove(fruit4);
		mySet.remove(fruit5);
		System.out.println("mySet now contains: " + mySet);
		mySet.clear();
		System.out.println("Is mySet empty? " + mySet.isEmpty());
	}

	private static void exerciseTwo() {
		List<String> myList = new ArrayList<String>();

		String fruit1 = "pear";
		String fruit2 = "banana";
		String fruit3 = "tangerine";
		String fruit4 = "strawberry";
		String fruit5 = "blackberry";

		myList.add(fruit1);
		myList.add(fruit2);
		myList.add(fruit3);
		myList.add(fruit4);
		myList.add(fruit5);

		Iterator<String> myIterator = myList.iterator();
		System.out.println("My list contains: ");
		while (myIterator.hasNext()) {
			System.out.println(myIterator.next());

		}
		System.out.println();

		ListIterator<String> myListIterator = myList.listIterator(myList.size());

		System.out.println("My list contains (in reverse order): ");
		while (myListIterator.hasPrevious()) {
			System.out.println(myListIterator.previous());			
		}

		myList.add(3, fruit2);

		System.out.println();
		System.out.println("myList contains (after insertion of second banana)" + myList);

	}

	@SuppressWarnings("serial")
	private static void exerciseThree() {
		String line;
		Map<String, ArrayList<Double>> myMap = new TreeMap<String, ArrayList<Double>>();
		try(BufferedReader myReader = new BufferedReader(new FileReader("StudentScore.txt"))) {
			line = myReader.readLine();
			while (line != null && line.length() > 0) {
				// System.out.println(line);
				String name = line.split(" ")[0];
				double score = Double.parseDouble(line.split(" ")[1]);
				if(myMap.containsKey(name)) {
					myMap.get(name).add(score);
				}
				else {
					myMap.put(name, new ArrayList<Double>() {
						{
							add(score);
						}
					});
				}
				line = myReader.readLine();
			}


		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Alpha Order");
		System.out.println("***********");
		Map<String, Double> map = new HashMap<String, Double>();		
		List<Double> list = new ArrayList<Double>();
		for(String name : myMap.keySet()) {
			System.out.println(name + " " + myMap.get(name).size() + " " + helper(myMap.get(name)));
			list.add(helper(myMap.get(name)));
			map.put(name, helper(myMap.get(name)));
		}


		Collections.sort(list, Collections.reverseOrder());
		//		System.out.println("MAP " + map);
		//		System.out.println(list);
		TreeSet<Double> treeSet = new TreeSet<Double>(list);

		//		System.out.println("trre" + treeSet.descendingSet());

		System.out.println();
		System.out.println("Merit Order");
		System.out.println("***********");
		int count = 1;
		int occurence;
		String names = null;
		for(Double score : treeSet.descendingSet()) {
			if(Collections.frequency(list, score) == 1) {
				for(Entry<String, Double> entry : map.entrySet()) {
					if(entry.getValue().equals(score)) {
						names = entry.getKey();
						break;
					}
				}
				System.out.println(count + " " + names + " " + myMap.get(names).size() + " " + helper(myMap.get(names)));
				count++;
			}

			else{
				occurence = Collections.frequency(list, score);
				for(Entry<String, Double> entry : map.entrySet()) {
					if(entry.getValue().equals(score)) {
						names = entry.getKey();
						System.out.println(count + " " + names + " " + myMap.get(names).size() + " " + helper(myMap.get(names)));

					}
				}
				count += occurence;
			}


		}

		System.out.println();
		System.out.println("Number of students: " + myMap.size());
		DecimalFormat df = new DecimalFormat("#.00");
		System.out.println("Average student mark " + df.format(helper(list)));

		double total = 0;
		for(Double dub : list) {
			total += (dub - helper(list)) * (dub - helper(list));

		}
		System.out.println("Standard deviation " + df.format(Math.sqrt(total / list.size())));




	}

	private static double helper(List<Double> list) {
		double sum = 0;
		for(Double doubles : list) {
			sum += doubles;
		}

		return sum / list.size();
	}

}



