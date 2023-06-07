package model;

public class CalculatorModel {
	private float oldIterator;
	private boolean operatorState;
	private int operator;
	public CalculatorModel() {
		this.oldIterator = 0;
		// State of operator
		this.operatorState = true;
		// Number of operator
		this.operator = -1;
	}
	public float getOldIterator() {
		return oldIterator;
	}
	public void setOldIterator(float oldIterator) {
		this.oldIterator = oldIterator;
	}
	public boolean isOperatorState() {
		return operatorState;
	}
	public void setOperatorState(boolean operatorState) {
		this.operatorState = operatorState;
	}
	public int getOperator() {
		return operator;
	}
	public void setOperator(int operator) {
		this.operator = operator;
	}	
	
}
