package test;

import view.CalculatorView;

public class Test {
	public static void main(String[] args) {
		try {
			new CalculatorView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
