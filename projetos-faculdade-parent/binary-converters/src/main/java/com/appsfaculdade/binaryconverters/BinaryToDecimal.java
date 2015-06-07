package com.appsfaculdade.binaryconverters;

import lombok.val;
import dnl.utils.text.table.TextTable;

public class BinaryToDecimal {
	
	double decimalResult;

	TextTable textTableResult;
	
	public static void main(String[] args) {
		new BinaryToDecimal().start();
	}
	
	public void start(){
		val binary = Utils.readText("Informe o binário");
		calculateDecimal(binary);
		this.textTableResult.printTable();
		System.out.println("\nDecimal Gerado:  " + decimalResult);
	}
	
	private void calculateDecimal(final String binary){
		int lenght = binary.length() - 1;
		final String columns[]  = new String[lenght + 1];
		char [] numbers = binary.toCharArray();
		this.decimalResult = 0;
		double resultOperation = 0;
		final Object data[][] = new Object[2][lenght + 1];
		int count = 0;
		for (char number : numbers){
			int charNumber = number == '0' ? 0 : 1;
			columns[count] = charNumber + "";
			data[0][count] = "2^" + lenght + " X " + charNumber;
			resultOperation = (Math.pow(2,lenght)) * charNumber;
			data[1][count] = resultOperation;
			decimalResult += resultOperation;
			lenght --;
			count ++;
		}
		this.textTableResult = new TextTable(columns, data);
	}
	
}
