package com.oxygenxml.resources.batch.converter.converters;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.oxygenxml.resources.batch.converter.BatchConverter;
import com.oxygenxml.resources.batch.converter.BatchConverterImpl;
import com.oxygenxml.resources.batch.converter.ConverterTypes;
import com.oxygenxml.resources.batch.converter.extensions.FileExtensionType;
import com.oxygenxml.resources.batch.converter.reporter.ProblemReporter;
import com.oxygenxml.resources.batch.converter.trasformer.TransformerFactoryCreator;
import com.oxygenxml.resources.batch.converter.utils.ConverterFileUtils;

import tests.utils.ConvertorWorkerInteractorTestImpl;
import tests.utils.FileComparationUtil;
import tests.utils.ProblemReporterTestImpl;
import tests.utils.ProgressDialogInteractorTestImpl;
import tests.utils.StatusReporterImpl;
import tests.utils.TransformerFactoryCreatorImpl;

/**
 * JUnit for Excel to DITA conversion.
 * @author cosmin_duna
 */
public class ExcelToDitaTest {

	@Test
	public void test() throws Exception {
		File sample  = new File("test-sample/excel.xls");		
		File goodSample = new File("test-sample/excelToDITA.dita");
		File outputFolder = sample.getParentFile();
		
		TransformerFactoryCreator transformerCreator = new TransformerFactoryCreatorImpl();
		ProblemReporter problemReporter = new ProblemReporterTestImpl();
		
		BatchConverter converter = new BatchConverterImpl(problemReporter, new StatusReporterImpl(), new ProgressDialogInteractorTestImpl(),
				new ConvertorWorkerInteractorTestImpl() , transformerCreator);

		List<File> inputFiles = new ArrayList<File>();
		inputFiles.add(sample);
				
		File fileToRead = ConverterFileUtils.getOutputFile(sample, FileExtensionType.DITA_OUTPUT_EXTENSION , outputFolder);
		
		try {
			converter.convertFiles(ConverterTypes.EXCEL_TO_DITA, inputFiles, outputFolder, false);
			assertTrue(FileComparationUtil.compareLineToLine(goodSample, fileToRead));
		} finally {
			Files.delete(Paths.get(fileToRead.getPath()));
		}
	}
}