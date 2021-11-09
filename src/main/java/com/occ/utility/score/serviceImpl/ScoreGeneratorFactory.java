package com.occ.utility.score.serviceImpl;

import com.occ.utility.score.service.ScoreGenerator;

public class ScoreGeneratorFactory {

	public ScoreGenerator getScoreGenerator(String type) {
		if(type == null) {
			return null;
		}
		if(type.equalsIgnoreCase("simple")) {
			return new ScoresForNames();
		}
		return null;
	}
}
