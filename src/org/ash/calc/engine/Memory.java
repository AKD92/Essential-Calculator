package org.ash.calc.engine;

public class Memory {
	
	private double data;
	private boolean isAvailable;
	
	private static Memory current;
	
	static {
		current = null;
	}
	
	private Memory() {
		data = 0.0;
		isAvailable = false;
	}
	
	public static Memory initialize() {
		if (current == null) {
			current = new Memory();
			System.out.println("Memory Initialized");
		}
		else {
			System.out.println("Returning existing memory object");
		}
		
		return current;
	}
	public static Memory getMemory() {
		if (current == null) initialize();
		return current;
	}
	
	public boolean isDataAvailable() {
		return isAvailable;
	}
	public double getData() {
		return data;
	}
	public void setData(double value) {
		data = value;
		isAvailable = true;
		System.out.println("Value is set in memory : " + data);
	}
	public void clearMemory() {
		data = 0.0;
		isAvailable = false;
		System.out.println("Memory Cleared");
	}
	
}
