package org.ash.calc.engine;

import org.ash.calc.gui.Application;
import org.ash.calc.gui.MainWindow;

public class MathManager {
	
	private Numberer number1;
	private Numberer number2;
	
	private MathOp op;
	
	private MainWindow mn;
	
	private static MathManager current;
	
	private MathManager() {
		number1 = new Numberer();
		number2 = new Numberer();
		op = null;
		mn = Application.getMainWindow();
	}
	public static MathManager initialize() {
		current = new MathManager();
		System.out.println("MathManager initialized");
		return current;
	}
	public static MathManager getManager() {
		if (current == null) initialize();
		return current;
	}
	
	public void addNumber(String str, boolean isReplaceOriginal) {
		
		boolean number1Ok = number1.containsNumber();
		
		if (op == null) {
			number1.addNumber(str);
			System.out.println("Filling " + str + " in number 1");
		}
		else {
			number2.addNumber(str);
			System.out.println("Filling " + str + " in number 2");
		}
		
		if (number1Ok == true ) {
			mn.addTextToOp(str);
		} else {
			mn.updateOp(str);
		}
	}
	public void addNumber(double val, boolean isReplaceOriginal) {
		addNumber(String.valueOf(val), isReplaceOriginal);
	}
	
	public void setOperation(MathOp mop) {
		
		Memory mem = Memory.getMemory();
		
		boolean number1Ok = number1.containsNumber();
		boolean number2Ok = number2.containsNumber();
		boolean memOk = mem.isDataAvailable();
		
		if (number1Ok == false && memOk == true){
			addNumber(mem.getData(), true);
			System.out.println("Filled number from memory : " + mem.getData());
		}
		else if (number1Ok == false && memOk == false && mop.isNumberSign() == false) {
			mn.updateDisplay("Incomplete Instruction");
			return;
		}
		
		number1Ok = number1.containsNumber();
		
		if (number1Ok == false && mop.isNumberSign() == true) {
			addNumber(mop.getSign(), true);
			return;
		}
		
		if (number1Ok == true && op != null && number2Ok == false) {
			addNumber(mop.getSign(), false);
			System.out.println("Crapy method!!");
			return;
		}
		
		if (number1Ok == true && number2Ok == true && op != null) {
			
			doCalculation();
			setOperation(mop);
			return;
			
		}
		
		op = mop;
		
		mn.addTextToOp(op.getSign());
		System.out.println("Operation is set : " + op.toString());
				
		if (op.isSingleOperation() == true) doCalculation();
		
	}
	public void doCalculation() {
		
		double res = 0;
		double data1 = 0;
		double data2 = 0;
		
		try {
			data1 = number1.getNumber();
			data2 = number2.getNumber();
		} catch (NumberFormatException ex) {
			mn.updateDisplay("Incomplete Instruction");
			return;
		}
		
		// not a single operation, and we dont have number 2 !!
		if (number2.containsNumber() == false && (op == null || op.isSingleOperation() == false)) {
			mn.updateDisplay("Incomplete Instruction");
			return;
		}
		
		System.out.println("Calculating the result");
		
		switch (op) {
		case ADDITION : {
			res = data1 + data2; break;
		}
		case SUBSTRACTION : {
			res = data1 - data2; break;
		}
		case MULTIPLICATION : {
			res = data1 * data2; break;
		}
		case DIVIDE : {
			res = data1 / data2; break;
		}
		case LOG : {
			res = StrictMath.log10(data1); break;
		}
		case LON : {
			res = StrictMath.log(data1); break;
		}
		case POWER : {
			res = StrictMath.pow(data1, data2); break;
		}
		case SQUARE_ROOT : {
			res = StrictMath.sqrt(data1); break;
		}
		}
		
		wholeClear();
		
		Memory mem = Memory.getMemory();
		mem.setData(res);
		
		mn.updateDisplay(res);
		
	}
	
	public void wholeClear() {
		op = null;
		number1.clear();
		number2.clear();
	}
	

}
