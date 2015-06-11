package com.appsfaculdade.metodosordenadores;

import java.io.File;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class CreateRelatorioTest {

	private final String outputDir = "files/resultados.xls";
	private GerarRelatorio geradorRelatorio;
	
	@Before
	public void setUp() throws Exception{
		new File("files/").mkdirs();
		geradorRelatorio = new GerarRelatorio(outputDir);
	}
	
	@Test
	@Ignore
	public void testCreateRelatorio() throws Exception{
		geradorRelatorio.gerarRelatorio();
		Assert.assertTrue(new File(outputDir).exists());
	}
	
}
