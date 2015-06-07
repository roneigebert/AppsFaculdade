package com.appsfaculdade.metodosordenadores;
import java.io.File;
import java.io.IOException;

import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import lombok.val;

public class Excel {
	
	private final WritableWorkbook wworkbook;
	private final WritableSheet wsheet;
	
	private int ultimaLinha;

	public Excel(final String outputDir) throws IOException, RowsExceededException, WriteException {
		try {
			val arqExcluir = new File(outputDir);
			arqExcluir.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
		new File(outputDir).createNewFile();
		wworkbook = Workbook.createWorkbook(new File(outputDir));
		wsheet = wworkbook.createSheet("Métodos Comparadores", 0);
		ultimaLinha = 0;
		Label label = new Label(0, ultimaLinha, "MÉTODO");
		wsheet.addCell(label);
		Label label2 = new Label(1, ultimaLinha, "COMPARAÇÕES");
		wsheet.addCell(label2);
		Label label3 = new Label(2, ultimaLinha, "TROCAS");
		wsheet.addCell(label3);
		Label label4 = new Label(3, ultimaLinha, "EXECUÇÕES");
		wsheet.addCell(label4);
		ultimaLinha = 1;
	}
	
	public void write(String metodo, String Comparacoes, String Trocas, String execucoes) throws BiffException, IOException, WriteException {
		Label label = new Label(0, ultimaLinha, metodo);
		wsheet.addCell(label);
		Label label2 = new Label(1, ultimaLinha, Comparacoes);
		wsheet.addCell(label2);
		Label label3 = new Label(2, ultimaLinha, Trocas);
		wsheet.addCell(label3);
		Label label4 = new Label(3, ultimaLinha, execucoes);
		wsheet.addCell(label4);
		ultimaLinha ++;
	}
	
	public void save() throws IOException, WriteException{
		wworkbook.write();
		wworkbook.close();
	}

}