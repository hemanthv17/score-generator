package com.occ.utility.score.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExtractFile {
	private static final Logger log = LoggerFactory.getLogger(ExtractFile.class);
	
	public static List<String> processFileAndExtractNames(File file) {
		log.debug("Reading the file :: {}", file.getName());
		List<String> names = new ArrayList<>();
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String lineOfNames;
			List<String> namesWithQuotes = new ArrayList<>();
			while((lineOfNames = reader.readLine()) != null) {
				namesWithQuotes.addAll(Arrays.asList(lineOfNames.split(",")));
			}
			namesWithQuotes.stream()
				 .forEach(name -> names.add(name.substring(1, name.length()-1)));
			reader.close();
			log.debug("Reading and conversion to list is successful for file :: {}", file.getName());
		} catch (FileNotFoundException ex) {
			log.error("FileNotFoundException Exception occured while accessing the file :: {}", ex);
			return null;
		} catch (IOException ex) {
			log.error("IO Exception occured while accessing the file :: {}", ex);
			return null;
		} catch(Exception ex) {
			log.error("Exception occured while accessing the file :: {}", ex);
			return null;
		}
		return names;
	}
}
