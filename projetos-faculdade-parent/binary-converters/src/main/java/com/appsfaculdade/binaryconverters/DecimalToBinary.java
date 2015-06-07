package com.appsfaculdade.binaryconverters;

import dnl.utils.text.table.TextTable;

public class DecimalToBinary {

	private String binaryResult;

	private TextTable textTableResult;
	
	public static void main(String[] args) {
		new DecimalToBinary().start();
	}
	
	public void start() {
		calculateDecimal(Utils.readInt("Informe um número inteiro"));
		textTableResult.printTable();
		System.out.println("\nBinário gerado: " + binaryResult);
	}
		
	private void calculateDecimal(int number){
		int resto = 0;
		final Object data[][] = new Object[calculateLenght(number, 0)][3];
		int count = 0;
		binaryResult = "";
		do {
			resto = number % 2;
			data[count][0] = number+ "/2 ";
			number = number / 2;
			data[count][1] = number;
			resto = (resto == 0) ? resto : 1;
			data[count][2] = resto;
			binaryResult = resto + binaryResult;
			count ++;
		} while (number > 0);
		textTableResult = new TextTable(new String[]{"Cálculo", "Resultado(int)", "Resto"}, data);
	}

	private  int calculateLenght(int num, int lenght) {
		num = num / 2;
		lenght ++;
		if (num > 0)
			return calculateLenght(num, lenght);
		return lenght;
	}

}
