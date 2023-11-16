package socket;

import java.io.Serializable;

public class GuguDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	int value1;
	int value2, result;

	public GuguDTO(int value1, int value2) {

		this.value1 = value1;
		this.value2 = value2;
	}

	public int getValue1() {
		return value1;
	}

	public void setValue1(int value1) {
		this.value1 = value1;
	}
	public int getValue2() {
		return value2;
	}

	public void setValue2(int value2) {
		this.value2 = value2;
	}
	public int getresult() {
		return result;
	}

	public void setresult(int result) {
		this.result = result;
	}


}