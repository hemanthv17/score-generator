package com.occ.utility.score.serviceImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class IndividualNameScoreTaskTest {
	
	@Test
	public void testIndividualNameScoreTaskSuccess() {
		List<String> names = new ArrayList<>();
		names.add("0");
		names.add("adam");
		names.add("steve jobs");
		names.add("Suresh");
		IndividualNameScoreTask task = new IndividualNameScoreTask(names);
		long score = 0;
		try {
			score = task.call();
		} catch(Exception ex) {
			assertNull(ex);
		}
		assertEquals(523, score);
	}
	
	@Test
	public void testIndividualNameScoreTaskException() throws Exception {
		List<String> names = new ArrayList<>();
		names.add("adam");
		names.add("steve jobs");
		names.add("Suresh");
		IndividualNameScoreTask task = new IndividualNameScoreTask(names);
		long score = 0;
		score = task.call();

		assertEquals(-1, score);
	}

}
