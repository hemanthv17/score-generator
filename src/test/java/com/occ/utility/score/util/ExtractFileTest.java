package com.occ.utility.score.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.File;
import java.util.List;

import org.junit.Test;


public class ExtractFileTest {
	
	@Test
	public void testExtractFileSuccess() {
		List<String> names = ExtractFile.processFileAndExtractNames(new File("src/test/resources/testFile.txt"));
		assertEquals("MARY ABC", names.get(0));
		assertEquals("PATRICIA ABC", names.get(1));
		assertEquals("LINDA ABC", names.get(2));
	}
	
	@Test
	public void testExtractFileException() {
		List<String> names = ExtractFile.processFileAndExtractNames(new File("src/test/resources/testFile2.txt"));
		assertNull(names);
	}

}
