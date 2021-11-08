package com.occ.utility.score.serviceImpl;

import java.util.List;
import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IndividualNameScoreTask implements Callable<Long>{
	private static final Logger log = LoggerFactory.getLogger(IndividualNameScoreTask.class);

	private final List<String> names;
	public IndividualNameScoreTask(List<String> names) {
		this.names = names;
	}
	@Override
	public Long call() throws Exception {
		long fileScore = 0;
		try {
			for(int i=1; i < names.size(); i++) {
				int nameScore = 0;
				for(char letter: names.get(i).toCharArray()){
					if(letter >= 'A' && letter <= 'Z') {
						nameScore += (int)letter - 64;
					}else if(letter >= 'a' && letter <= 'z') {
						nameScore += (int)letter - 96;
					}else {
						continue;
					}
				}
				nameScore *= Integer.parseInt(names.get(0))+i;
				fileScore += nameScore;
			}
		} catch(Exception ex){
			log.error("Excetion occured while calculating score for names :: {}", ex);
			return -1L;
		}
		
		return fileScore;
	}
	
}