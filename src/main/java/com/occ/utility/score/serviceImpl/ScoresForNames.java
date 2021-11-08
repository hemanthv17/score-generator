package com.occ.utility.score.serviceImpl;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.occ.utility.score.service.ScoreGenerator;
import com.occ.utility.score.util.ExtractFile;

public class ScoresForNames implements ScoreGenerator{
	
	private static final Logger log = LoggerFactory.getLogger(ScoresForNames.class);
	
	@Override
	public long calculateScore(File file) {
		long fileScore = 0;
		List<String> names = ExtractFile.processFileAndExtractNames(file);
		
		if(names == null) {
			return -1;
		} else if (names.size() == 0){
			return 0;
		}
		
		int numberOfNamesToBeProcessed = names.size();
		
		log.info("Total names to be processed for score calculation :: {}", numberOfNamesToBeProcessed);
		
		Collections.sort(names);
		
		log.debug("Sorting has been successfully completed");
		
		int numberOfThreads = Runtime.getRuntime().availableProcessors();
		
		log.debug("Thread count for processing the list :: {}", numberOfThreads);
		
		ExecutorService executor = 
			    Executors.newFixedThreadPool(numberOfThreads);
		int sizeOfEachSublist = numberOfNamesToBeProcessed/numberOfThreads == 0 ? 1 : numberOfNamesToBeProcessed/numberOfThreads;
		List<List<String>> subLists = new ArrayList<List<String>>(numberOfThreads);

		for(int i=0, j=0; i < numberOfNamesToBeProcessed; i += sizeOfEachSublist, j++) {
			List<String> list = new ArrayList<>();
			list.add(String.valueOf(i));
			list.addAll(names.subList(i, i+sizeOfEachSublist > numberOfNamesToBeProcessed ? numberOfNamesToBeProcessed : i+sizeOfEachSublist));
			subLists.add(j, list);
		}
		
		log.debug("Number of sublists created :: {}", subLists.size());

		List<IndividualNameScoreTask> taskList = new ArrayList<>();
		for(int i=0; i < subLists.size(); i++) {
			IndividualNameScoreTask task = new IndividualNameScoreTask(subLists.get(i));
			taskList.add(task);
		}
		log.debug("Number of tasks created for processing the list :: {}", taskList.size());

		List<Future<Long>> resultList = null;
		
		log.debug("Invoking all the tasks");
		
        try {
            resultList = executor.invokeAll(taskList);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("Error occured while calculating score for names :: {}", e);
            return -1;
        }
		log.debug("Successfully invoked all the tasks");

        executor.shutdown();
         
        for (int i = 0; i < resultList.size(); i++) {
            Future<Long> future = resultList.get(i);
            try {
                Long result = future.get();
                if(result == -1) return result;
                fileScore += result;
            } catch (InterruptedException | ExecutionException e) {
               future.cancel(true);
               log.error("Error occured while calculating score for names :: {}", e);
               return -1;
            }
        }
        log.info("Score processing successfully completed. Total score is :: {}", fileScore);
		return fileScore;
	}
	
	

}
