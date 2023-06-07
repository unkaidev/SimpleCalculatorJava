package control;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import view.CalculatorView;

public class CalculatorController extends KeyAdapter implements ActionListener{

	private CalculatorView calculatorView;
	private String currentValue,stringMemory, mValue;

	public CalculatorController(CalculatorView calculatorView) {
		this.calculatorView = calculatorView;
	}

	public void setTextAdd(float result, float newIterator) {
		calculatorView.lbl_InputValue.setText(String.valueOf(result));
		calculatorView.lbl_MValue.setText(
				String.valueOf(calculatorView.getOldIterator()) + " + " + String.valueOf(newIterator) + " = ");
	}

	public void setTextSub(float result, float newIterator) {
		calculatorView.lbl_InputValue.setText(String.valueOf(result));
		calculatorView.lbl_MValue.setText(
				String.valueOf(calculatorView.getOldIterator()) + " - " + String.valueOf(newIterator) + " = ");
	}

	public void setTextMul(float result, float newIterator) {
		calculatorView.lbl_InputValue.setText(String.valueOf(result));
		calculatorView.lbl_MValue.setText(
				String.valueOf(calculatorView.getOldIterator()) + " x " + String.valueOf(newIterator) + " = ");
	}

	public void setTextDiv(float result, float newIterator) {
		calculatorView.lbl_InputValue.setText(String.valueOf(result));
		calculatorView.lbl_MValue.setText(String.valueOf(calculatorView.getOldIterator()) + " : "+ String.valueOf(newIterator) + " = ");
		;
	}

	public void setTextOther() {
		calculatorView.setOperatorState(true);
		calculatorView.setOldIterator(Float.parseFloat(currentValue));
		calculatorView.lbl_MValue.setText(stringMemory);
	}

	// CLickEvent
	@Override
	public void actionPerformed(ActionEvent e) {

		String s = e.getActionCommand();
		Object o = e.getSource();
		float result;
		float newIterator = 0;		
		stringMemory = calculatorView.lbl_InputValue.getText() + " " + s;
		mValue = calculatorView.lbl_MValue.getText();		
		currentValue = calculatorView.lbl_InputValue.getText() +"";
		if(!currentValue.isEmpty())
			newIterator = Float.parseFloat(currentValue);

		// Click Button of Result
		if (o == calculatorView.btnResult) {
			switch (calculatorView.getOperator()) {
			case 0:
				result = calculatorView.getOldIterator() + newIterator;
				this.setTextAdd(result, newIterator);
				break;
			case 1:
				result = calculatorView.getOldIterator() - newIterator;
				this.setTextSub(result, newIterator);
				break;
			case 2:
				result = calculatorView.getOldIterator() * newIterator;
				this.setTextSub(result, newIterator);
				break;
			case 3:
				if (newIterator != 0) {
					result = calculatorView.getOldIterator() / newIterator;
					this.setTextDiv(result, newIterator);
				}
				break;
			default:
				calculatorView.setOldIterator(newIterator);
				calculatorView.lbl_MValue.setText(String.valueOf(calculatorView.getOldIterator() + " = "));
			}
			calculatorView.setOperator(-1);
			calculatorView.setOperatorState(true);
			return;
		}

		// Click Button of Add
		if (o == calculatorView.btnAdd) {
			calculatorView.setOperator(0);
			this.setTextOther();
			return;
		}

		// Click Button of Sub
		if (o == calculatorView.btnSub) {
			calculatorView.setOperator(1);
			this.setTextOther();
			return;
		}

		// Click Button of Mul 
		if(o == calculatorView.btnMul) {
			calculatorView.setOperator(2); 
			this.setTextOther();
			return; 
		}

		//	Click Button of Div 
		if(o == calculatorView.btnDiv) {
			calculatorView.setOperator(3); 
			this.setTextOther();
			return; 
		}

		//	Click Button of SQR 
		if(o == calculatorView.btnSqr) {
			if(newIterator >= 0) {
				result = (float)newIterator*newIterator;
				calculatorView.lbl_InputValue.setText(String.valueOf(result));
				if (mValue.isEmpty() || mValue.indexOf('=') > 0 || mValue == "0") {
					calculatorView.lbl_MValue.setText(String.valueOf(newIterator) + "^2 = ");
				}else
					calculatorView.lbl_MValue.setText(mValue + " (" + String.valueOf(newIterator) + "^2) ");

			}
			calculatorView.setOperatorState(true);
			return;
		}

		// Click button of Revert
		if(o == calculatorView.btnRev) {
			if(newIterator != 0) {
				result = (float)1/newIterator;
				calculatorView.lbl_InputValue.setText(String.valueOf(result));
				if (mValue.isEmpty() || mValue.indexOf('=') > 0 || mValue == "0") {
					calculatorView.lbl_MValue.setText("1 / " + String.valueOf(newIterator) + " = ");
				}else
					calculatorView.lbl_MValue.setText(mValue + "1 / " + String.valueOf(newIterator));
			}
			calculatorView.setOperatorState(true);
			return;
		}
		//Click button of SQRT
		if(o == calculatorView.btnSqrt) {
			if(newIterator >= 0) {
				result = (float)Math.sqrt(newIterator);
				calculatorView.lbl_InputValue.setText(String.valueOf(result));
				if (mValue.isEmpty() || mValue.indexOf('=') > 0 || mValue == "0") {
					calculatorView.lbl_MValue.setText("Sqrt(" + String.valueOf(newIterator) + ") = ");
				}else
					calculatorView.lbl_MValue.setText(mValue + "Sqrt(" + String.valueOf(newIterator) + ")");

			}
			calculatorView.setOperatorState(true);
			return;
		}


		// Click button of Percent
		if(o == calculatorView.btnPer) {
			result = (float)(newIterator / 100);
			calculatorView.lbl_InputValue.setText(String.valueOf(result));
			if (mValue.isEmpty() || mValue.indexOf('=') > 0 || mValue == "0") {
				calculatorView.lbl_MValue.setText("(" + String.valueOf(newIterator) + ")% = ");
			}else
				calculatorView.lbl_MValue.setText(mValue + "(" + String.valueOf(newIterator) + ")% ");


			;
			calculatorView.setOperatorState(true);
			return;
		}

		// Click button of Backspace
		if (o == calculatorView.btnBack) {
			if (!currentValue.isEmpty() && mValue.indexOf('=') <= 0) {
				String newValue = currentValue.substring(0, currentValue.length() - 1);
				if (newValue.isEmpty())
				{
					calculatorView.lbl_InputValue.setText("0");
					calculatorView.setOperatorState(true);
				}
				else
					calculatorView.lbl_InputValue.setText(newValue);
			}
			return;			
		}

		// Click point number
		if (o == calculatorView.btnPoint) {
			if (currentValue.indexOf('.') > 0)
				calculatorView.lbl_InputValue.setText(currentValue);
			else
				calculatorView.lbl_InputValue.setText(currentValue + ".");
			return;
		}

		// Click Alter
		if (o == calculatorView.btnAlt) {
			calculatorView.lbl_InputValue.setText(Float.valueOf(currentValue)*(-1) +"");
			return;
		}
		// Click button number
		if (this.calculatorView.isOperatorState()) {
			calculatorView.lbl_InputValue.setText(s);
			calculatorView.setOperatorState(false);
		} else
			calculatorView.lbl_InputValue.setText(currentValue + s );

		// Click Button of Reset
		if (o == calculatorView.btnReset) {
			calculatorView.setOperator(-1);
			calculatorView.setOperatorState(true);
			calculatorView.setOldIterator(0);
			calculatorView.lbl_InputValue.setText("0");
			calculatorView.lbl_MValue.setText("0");
			return;
		}
		// Click Button of edit
		if (o == calculatorView.btnEdit) {
			calculatorView.lbl_InputValue.setText("0");
			calculatorView.setOperatorState(true);
			return;
		}
	}

	//KeyEvent
	@Override
	public void keyTyped(KeyEvent e) {
		char keyChar = e.getKeyChar();
		currentValue = calculatorView.lbl_InputValue.getText() +"";
		stringMemory = calculatorView.lbl_InputValue.getText() + " " + String.valueOf(keyChar);

		float result;
		float newIterator = 0;
		if(!currentValue.isEmpty())
			newIterator = Float.parseFloat(currentValue);

		if (Character.isDigit(keyChar)) {
			if(this.calculatorView.isOperatorState()){
				// Key Number
				calculatorView.lbl_InputValue.setText(String.valueOf(keyChar));
				calculatorView.setOperatorState(false);
			}else {
				calculatorView.lbl_InputValue.setText(currentValue += String.valueOf(keyChar));
			}

		}else if (calculatorView.isOperatorKey(keyChar)) {
			// KeyIterator
			if (keyChar == '\n') {
				switch (calculatorView.getOperator()) {
				case 0:
					result = calculatorView.getOldIterator() + newIterator;
					this.setTextAdd(result, newIterator);
					break;
				case 1:
					result = calculatorView.getOldIterator() - newIterator;
					this.setTextSub(result, newIterator);
					break;
				case 2:
					result = calculatorView.getOldIterator() * newIterator;
					this.setTextMul(result, newIterator);
					break;
				case 3:
					if (newIterator != 0) {
						result = calculatorView.getOldIterator() / newIterator;
						this.setTextDiv(result, newIterator);
					}
					break;

				default:
					calculatorView.setOldIterator(newIterator);
					calculatorView.lbl_MValue.setText(String.valueOf(calculatorView.getOldIterator() + " = "));
				}
				calculatorView.setOperator(-1);
				calculatorView.setOperatorState(true);
				return;
			}

		}
		if (keyChar == '+') {
			calculatorView.setOperator(0);
			this.setTextOther();
			return;
		}
		if (keyChar == '-'){
			calculatorView.setOperator(1);
			this.setTextOther();
			return;
		}
		if (keyChar == '*'){
			calculatorView.setOperator(2);
			this.setTextOther();
			return;
		}
		if (keyChar == '/'){
			calculatorView.setOperator(3);
			this.setTextOther();
			return;
		}
		if (keyChar == '.') {
			if (currentValue.indexOf('.') > 0)
				calculatorView.lbl_InputValue.setText(currentValue);
			else
				calculatorView.lbl_InputValue.setText(currentValue + ".");
			return;
		}
	} 
	// KeyEvent for BACKSPACE
	@Override
	public void keyPressed(KeyEvent e) {
		mValue = calculatorView.lbl_MValue.getText();
		int keyCode = e.getKeyCode();

		if (keyCode == KeyEvent.VK_BACK_SPACE) {
			if (!currentValue.isEmpty() && mValue.indexOf('=') <= 0) {
				String newValue = currentValue.substring(0, currentValue.length() - 1);
				if (newValue.isEmpty())
				{
					calculatorView.lbl_InputValue.setText("0");
					calculatorView.setOperatorState(true);
				}
				else
					calculatorView.lbl_InputValue.setText(newValue);
			}
			return;			
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {

	}
}
