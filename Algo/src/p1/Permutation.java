package p1;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Permutation {
	// Sn - Ordnung
	private int numberOfElements;
	private Map<Integer, Integer> permutationMap;
	private List<Integer> identity;
	
	public Permutation(int[] intArray) {
		// NO CHECK FOR VALID DATA
		identity = new LinkedList<Integer>();
		createPermutationMap(intArray);
		createIdentity();
		numberOfElements = intArray.length;
	}
	
	public Permutation multiplicate(Permutation inputP) {
		int[] newPermArray = new int[numberOfElements];
		Map<Integer, Integer> inputMap = inputP.getMap();
		
		for (int i=0; i<numberOfElements;i++) {
			newPermArray[i] = inputMap.get(permutationMap.get(i).intValue()).intValue();
		}
		
		Permutation newPermutation = new Permutation(newPermArray);
		return newPermutation;
	}
	
	public Permutation getInverse() {
		int[] inverseArray = new int[numberOfElements];
		
		for (int i=0; i<numberOfElements; i++) {
			inverseArray[permutationMap.get(i)] = i;
		}
		
		Permutation inversePermutation = new Permutation(inverseArray);
		return inversePermutation;
	}
	
	public void printCycleNotation() {
		List<Integer> usedNumbers = new LinkedList<Integer>();
		
		String tempNotation = "";
		for (int i=0; i<numberOfElements; i++) {
			if (usedNumbers.contains(new Integer(i))) {
				continue;
			}
			
			if (permutationMap.get(i) == i) {
				usedNumbers.add(permutationMap.get(i));
				continue;
			}
			
			Integer currentMappedNumber = permutationMap.get(i);
			List<Integer> newTupel = new LinkedList<Integer>();
			int smallestValueInTupel = numberOfElements;
			while(!usedNumbers.contains(currentMappedNumber)) {
				// die gemappte Nummer zu benutzten Nummern hinzuf�gen, f�r Abbruchbedingung der �u�eren FOR-Schleife
				usedNumbers.add(currentMappedNumber);
				
				// im neuen Tupel die aktuell gemappte Nummer speichern.
				newTupel.add(currentMappedNumber);
				
				// finde kleinste Nummer
				if (currentMappedNumber.intValue() < smallestValueInTupel) {
					smallestValueInTupel = currentMappedNumber.intValue();
				}
				
				// zum n�chsten Mapping springen
				currentMappedNumber = permutationMap.get(currentMappedNumber);
			}
			
			// Shifte Tupel bis zur kleinsten Nummer
			for (int k=0; k<newTupel.size();k++) {
				if (newTupel.get(0).intValue() != smallestValueInTupel) {
					newTupel.add(0, newTupel.get(newTupel.size()-1));
					newTupel.remove(newTupel.size()-1);
				} else {
					break;
				}
			}
			
			// Erzeuge String
			tempNotation += "( ";
			for (int j=0; j<newTupel.size(); j++) {
				tempNotation += newTupel.get(j).toString() + " ";
			}
			tempNotation += ")";
			
			if (usedNumbers.size() == numberOfElements) {
				break;
			}
		}
		
		System.out.println("PI = " + tempNotation);
	}
	
	public Map<Integer, Integer> getMap() {
		return permutationMap;
	}
	
	private void createIdentity() {
		for (int i=0; i<numberOfElements; i++) {
			identity.add(new Integer(i));
		}
	}
	
	private void createPermutationMap(int[] intArray) {
		permutationMap = new HashMap<Integer, Integer>();
		for (int i=0; i<intArray.length; i++) {
			permutationMap.put(i, intArray[i]);
		}
	}
}
