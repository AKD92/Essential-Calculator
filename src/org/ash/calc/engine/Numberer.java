package org.ash.calc.engine;

public class Numberer {
	
	private StringBuilder number;
	
	public Numberer() {
		number = new StringBuilder();
	}
	
	public void addNumber(double val) {
		String str = String.valueOf(val);
		number.append(str);
	}
	public void addNumber(String num) {
		number.append(num);
	}
	public double getNumber() {
		
		String str = number.toString();
		
		if (str.isEmpty() == true) return 0.0;
		else return Double.parseDouble(str);
	}
	public void clear() {
		int lastIndex = number.length();
		if (number.length() > 0) {
			number.delete(0, lastIndex);
		}
	}
	public boolean containsNumber() {
		String str = toString();
		return !str.isEmpty();
	}
	
	public String toString() {
		return number.toString();
	}

}
