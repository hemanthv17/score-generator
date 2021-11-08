package com.occ.utility.score.serviceImpl;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.occ.utility.score.service.ScoreGenerator;
import com.occ.utility.score.util.ExtractFile;

@RunWith(PowerMockRunner.class)
@PrepareForTest(ExtractFile.class)
public class ScoresForNamesTest {
	
	@Mock
	private File file;
	
	private List<String> namesList = new ArrayList<>();
	
	@Before
	public void setup() {
		PowerMockito.mockStatic(ExtractFile.class);
		namesList.add("adam");
		namesList.add("Steve");
		namesList.add("Anne");
	}
	
	@Test
	public void testScoresForNamesSuccess() {
		ScoreGenerator scoresForNames = new ScoresForNames();
		PowerMockito.when(ExtractFile.processFileAndExtractNames(file)).thenReturn(namesList);
		long score = scoresForNames.calculateScore(file);
		assertEquals(233, score);
	}
	
	@Test
	public void testScoresForNamesEmptyNamesList() {
		namesList.clear();
		ScoreGenerator scoresForNames = new ScoresForNames();
		PowerMockito.when(ExtractFile.processFileAndExtractNames(file)).thenReturn(namesList);
		long score = scoresForNames.calculateScore(file);
		assertEquals(0, score);
	}
	
}
