package com.occ.utility.score;

import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.occ.utility.score.service.ScoreGenerator;
import com.occ.utility.score.serviceImpl.ScoresForNames;

public class ScoreApplication {
	private static final Logger log = LoggerFactory.getLogger(ScoresForNames.class);

    public static void main( String[] args ){
    	long startTime = System.currentTimeMillis();
    	ScoreGenerator scoreGenerator = new ScoresForNames();
    	scoreGenerator.calculateScore(new File(args[0]));
    	log.info("Time taken for calculating score for the file is :: {}", (System.currentTimeMillis() - startTime));
    }
}
