package org.ash.calc.engine;

public enum MathOp {
	
	ADDITION ("+", false, true),
	SUBSTRACTION("-", false, true),
	MULTIPLICATION ("*", false, false),
	DIVIDE ("/", false, false),
	LOG("g", true, false),
	LON("n", true, false),
	POWER("^", false, false),
	SQUARE_ROOT ("q", true, false);
	
	
	private String sign;
	private boolean isSingleOperation;
	private boolean isNumberSign;
	
	MathOp(String sign, boolean isSingle, boolean isNSign) {
		this.sign = sign;
		isSingleOperation = isSingle;
		isNumberSign = isNSign;
	}
	
	public String getSign() {
		return sign;
	}
	public boolean isSingleOperation() {
		return this.isSingleOperation;
	}
	public boolean isNumberSign() {
		return isNumberSign;
	}
	
	public static MathOp getMathOp(String sign) {
		MathOp[] all = MathOp.values();
		MathOp res = null;
		
		for (MathOp x : all) {
			String s = x.getSign();
			if (s.equals(sign)) {
				res = x;
				break;
			}
		}
		return res;
	}
}
